package com.caio.transilvania.service;

import com.caio.transilvania.controller.dto.ReservaCreateDTO;
import com.caio.transilvania.controller.dto.ReservaDTO;
import com.caio.transilvania.exception.*;
import com.caio.transilvania.mapper.ReservaMapper;
import com.caio.transilvania.model.*;
import com.caio.transilvania.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final HospedeService hospedeService;
    private final QuartoService quartoService;
    private final ReservaMapper reservaMapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, HospedeService hospedeService, QuartoService quartoService, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        this.reservaMapper = reservaMapper;
    }

    public ResponseEntity cadastrarReserva(ReservaCreateDTO reservaDTO){
        Date dataAtual = new Date();

        if(dataAtual.after(reservaDTO.getDataEntrada())){
            throw new ReservaAnteriorDataAtualException();
        }

        if(reservaDTO.getDataSaida().before(reservaDTO.getDataEntrada())){
            throw new ReservaDataSaidaAntesEntradaException();
        }

        Hospede hospede = hospedeService.buscarHospede(reservaDTO.getHospede());
        Quarto quarto = quartoService.buscarQuarto(reservaDTO.getQuarto());

        hospedeService.buscarHospede(reservaDTO.getHospede());
        quartoService.buscarQuarto(reservaDTO.getQuarto());
        boolean reservaExistente = reservaRepository.
                existsByDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatusAndQuartoId
                        (reservaDTO.getDataSaida(), reservaDTO.getDataEntrada(), StatusReserva.ATIVA, reservaDTO.getQuarto());

        if(reservaExistente){
            return new ResponseEntity<> ("Data não disponível", HttpStatus.NOT_FOUND);
        }

        Reserva reserva = reservaMapper.toModel(reservaDTO);

        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setStatus(StatusReserva.ATIVA);

        reserva = reservaRepository.save(reserva);

        return new ResponseEntity<> (reservaMapper.toDTO(reserva), HttpStatus.CREATED);
    }

    public List<ReservaDTO> buscarReservaHospede(String documento){
        List<Reserva> reservas = reservaRepository.findAllByHospedeDocumentoAndStatus(documento, StatusReserva.ATIVA);
        if(reservas.isEmpty()){
            throw new ReservaNaoEncontradaException(documento);
        }
        return reservaMapper.toListDTO(reservas);
    }

    public Reserva buscarReserva(Long id){
        return reservaRepository.findById(id).orElseThrow(() -> new ReservaNaoEncontradaException());
    }

    public ReservaDTO fazerCheckin(Long id){
        Reserva reserva = buscarReserva(id);
        reserva.getQuarto().setStatusQuarto(StatusQuarto.OCUPADO);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public ReservaDTO fazerCheckout(Long id){
        Reserva reserva = buscarReserva(id);
        Date dataAtual = new Date();
        if(dataAtual.before(reserva.getDataSaida())){
            throw new ReservaDataCheckoutInvalidaException(reserva.getDataSaida());
        }

        if(reserva.getQuarto().getStatusQuarto() != StatusQuarto.OCUPADO){
            throw new ReservaCheckoutStatusInvalidoException();
        }

        reserva.setStatus(StatusReserva.CONCLUIDA);
        reserva.getQuarto().setStatusQuarto(StatusQuarto.DISPONIVEL);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public boolean buscarReservaPorData(Date dataInicial, Date dataFinal, StatusReserva statusReserva, Long idQuarto) {
        return reservaRepository.existsByDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatusAndQuartoId(dataFinal, dataInicial, statusReserva, idQuarto);
    }


    public ReservaDTO cancelarReserva(Long id){
        Reserva reserva = buscarReserva(id);
        reserva.setStatus(StatusReserva.CANCELADA);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }
}
