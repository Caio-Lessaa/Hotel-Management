package com.caio.transilvania.mapper;

import com.caio.transilvania.controller.dto.QuartoCreateDTO;
import com.caio.transilvania.controller.dto.QuartoDTO;
import com.caio.transilvania.model.Quarto;
import com.caio.transilvania.model.TipoQuarto;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class QuartoMapper {
    public Quarto toModel(QuartoCreateDTO quartoDTO) {
        Quarto quarto = new Quarto();
        quarto.setNumero(quartoDTO.getNumero());
        try {
            quarto.setTipo(TipoQuarto.valueOf(quartoDTO.getTipo()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de quarto inválido: " + quartoDTO.getTipo());
        }
        quarto.setPrecoDiaria(quartoDTO.getPrecoDiaria());
        return quarto;
    }

    public QuartoDTO toDTO(Quarto quarto) {
        QuartoDTO quartoDTO = new QuartoDTO();
        quartoDTO.setId(quarto.getId());
        quartoDTO.setNumero(quarto.getNumero());
        quartoDTO.setTipo(quarto.getTipo().toString());
        quartoDTO.setStatus(quarto.getStatusQuarto().toString());
        quartoDTO.setPrecoDiaria(quarto.getPrecoDiaria());
        return quartoDTO;
    }

    public List<QuartoDTO> toListDTO(List<Quarto> quartos) {
        List<QuartoDTO> quartosDTO = new ArrayList<>();
        for(Quarto quarto: quartos) {
            QuartoDTO quartoDTO = new QuartoDTO();
            quartoDTO.setId(quarto.getId());
            quartoDTO.setNumero(quarto.getNumero());
            quartoDTO.setTipo(quarto.getTipo().toString());
            quartoDTO.setStatus(quarto.getStatusQuarto().toString());
            quartoDTO.setPrecoDiaria(quarto.getPrecoDiaria());
            quartosDTO.add(quartoDTO);
        }
        return quartosDTO;
    }

    public void copyProperties(QuartoCreateDTO quartoDto, Quarto quarto) {
        if (quartoDto.getNumero() != null && !quartoDto.getNumero().trim().isEmpty()) {
            quarto.setNumero(quartoDto.getNumero());
        }

    if (quartoDto.getTipo() != null && !quartoDto.getTipo().trim().isEmpty()) {
            try {
                quarto.setTipo(TipoQuarto.valueOf(quartoDto.getTipo()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de quarto inválido: " + quartoDto.getTipo());
            }
        }

        if (quartoDto.getPrecoDiaria() > 0) {
            quarto.setPrecoDiaria(quartoDto.getPrecoDiaria());
        }
    }
}
