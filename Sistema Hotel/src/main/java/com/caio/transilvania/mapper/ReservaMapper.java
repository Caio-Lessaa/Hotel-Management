package com.caio.transilvania.mapper;

import com.caio.transilvania.controller.dto.ReservaCreateDTO;
import com.caio.transilvania.controller.dto.ReservaDTO;
import com.caio.transilvania.model.Hospede;
import com.caio.transilvania.model.Quarto;
import com.caio.transilvania.model.Reserva;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReservaMapper { public Reserva toModel(ReservaCreateDTO quartoDTO) {
    Reserva reserva = new Reserva();
    reserva.setDataEntrada(quartoDTO.getDataEntrada());
    reserva.setDataSaida(quartoDTO.getDataSaida());
    Quarto quarto = new Quarto();
    quarto.setId(quartoDTO.getQuarto());
    reserva.setQuarto(quarto);
    Hospede hospede = new Hospede();
    reserva.setHospede(hospede);
    return reserva;
}

    public ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setDataEntrada(reserva.getDataEntrada());
        reservaDTO.setDataSaida(reserva.getDataSaida());
        reservaDTO.setQuarto(reserva.getQuarto().getId());
        reservaDTO.setHospede(reserva.getHospede().getId());
        reservaDTO.setStatus(reserva.getStatus().toString());
        return reservaDTO;
    }

    public List<ReservaDTO> toListDTO(List<Reserva> reservas) {
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for(Reserva reserva: reservas) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setId(reserva.getId());
            reservaDTO.setDataEntrada(reserva.getDataEntrada());
            reservaDTO.setDataSaida(reserva.getDataSaida());
            reservaDTO.setQuarto(reserva.getQuarto().getId());
            reservaDTO.setHospede(reserva.getHospede().getId());
            reservaDTO.setStatus(reserva.getStatus().toString());
            reservasDTO.add(reservaDTO);
        }
        return reservasDTO;
    }
}
