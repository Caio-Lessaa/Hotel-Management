package com.caio.transilvania.service;

import com.caio.transilvania.controller.dto.QuartoCreateDTO;
import com.caio.transilvania.controller.dto.QuartoDTO;
import com.caio.transilvania.exception.QuartoNaoEncontradoException;
import com.caio.transilvania.mapper.QuartoMapper;
import com.caio.transilvania.model.Quarto;
import com.caio.transilvania.model.StatusQuarto;
import com.caio.transilvania.model.StatusReserva;
import com.caio.transilvania.model.TipoQuarto;
import com.caio.transilvania.repository.QuartoRepository;
import com.caio.transilvania.utils.FormatarData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;
    private final QuartoMapper quartoMapper;
    private final ReservaService reservaService;

    @Autowired
    @Lazy
    public QuartoService(QuartoRepository quartoRepository, QuartoMapper quartoMapper, ReservaService reservaService) {
        this.quartoRepository = quartoRepository;
        this.quartoMapper = quartoMapper;
        this.reservaService = reservaService;
    }

    public QuartoDTO cadastrarQuarto(QuartoCreateDTO quartoCreateDTO){
        Quarto quarto = quartoMapper.toModel(quartoCreateDTO);
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);
        quarto = quartoRepository.save(quarto);
        return quartoMapper.toDTO(quarto);
    }

    public List<QuartoDTO> listarQuartos(){
        List<Quarto> quartos = quartoRepository.findAll();
        return quartoMapper.toListDTO(quartos);
    }

    public List<QuartoDTO> listarQuartosDisponiveis(){
        List<Quarto> quartos = quartoRepository.findAllByStatus(StatusQuarto.DISPONIVEL);
        return quartoMapper.toListDTO(quartos);
    }

    public Quarto buscarQuarto(Long id){
        return quartoRepository.findById(id).orElseThrow(QuartoNaoEncontradoException::new);
    }

    public QuartoDTO buscarQuartoDTO(Long id) {
        Quarto quarto = quartoRepository.findById(id).orElseThrow(QuartoNaoEncontradoException::new);
        return quartoMapper.toDTO(quarto);
    }

    public List<QuartoDTO> listarQuartosDisponiveisPorDataETipo(String dataEntrada, String dataFinal, String tipoQuarto) {
        FormatarData formatarData = new FormatarData();
        Date entrada = formatarData.formatar(dataEntrada);
        Date saida = formatarData.formatar(dataFinal);

        List<Quarto> quartos = quartoRepository.findAllByStatusAndTipo(StatusQuarto.DISPONIVEL, TipoQuarto.valueOf(tipoQuarto));
        List<Quarto> quartosDisponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
           if(!reservaService.buscarReservaPorData(entrada, saida, StatusReserva.ATIVA, quarto.getId())) {
               quartosDisponiveis.add(quarto);
            }
        }
        return quartoMapper.toListDTO(quartosDisponiveis);
    }

    public QuartoDTO atualizarQuarto(QuartoCreateDTO novoQuarto, Long id){
        Quarto quarto = buscarQuarto(id);
        quartoMapper.copyProperties(novoQuarto, quarto);
        return quartoMapper.toDTO(quartoRepository.save(quarto));
    }

    public void deletarQuarto(Long id){
        buscarQuarto(id);
        quartoRepository.deleteById(id);
    }
}
