package com.caio.transilvania.mapper;

import com.caio.transilvania.controller.dto.HospedeCreateDTO;
import com.caio.transilvania.controller.dto.HospedeDTO;
import com.caio.transilvania.model.Hospede;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HospedeMapper {
    public Hospede toModel(HospedeCreateDTO hospedeCreateDTO){
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeCreateDTO.getNome());
        hospede.setDocumento(hospedeCreateDTO.getDocumento());
        hospede.setTelefone(hospedeCreateDTO.getTelefone());
        hospede.setEmail(hospedeCreateDTO.getEmail());
        hospede.setUsuario(hospedeCreateDTO.getUsuario());
        return hospede;
    }

    public HospedeDTO toDTO(Hospede hospede) {
        HospedeDTO hospedeDTO = new HospedeDTO();
        hospedeDTO.setId(hospede.getId());
        hospedeDTO.setNome(hospede.getNome());
        hospedeDTO.setDocumento(hospede.getDocumento());
        hospedeDTO.setTelefone(hospede.getTelefone());
        hospedeDTO.setEmail(hospede.getEmail());
        return hospedeDTO;
    }

    public List<HospedeDTO> toListDTO(List<Hospede> hospedes) {
        List<HospedeDTO> hospedesDTO = new ArrayList<>();
        for(Hospede hospede: hospedes) {
            HospedeDTO hospedeDTO = new HospedeDTO();
            hospedeDTO.setId(hospede.getId());
            hospedeDTO.setNome((hospede.getNome()));
            hospedeDTO.setDocumento((hospede.getDocumento()));
            hospedeDTO.setTelefone((hospede.getTelefone()));
            hospedeDTO.setEmail((hospede.getEmail()));
            hospedesDTO.add(hospedeDTO);
        }
        return hospedesDTO;
    }

    public void copyProperties(HospedeCreateDTO hospedeDto, Hospede hospede) {
        if (hospedeDto.getNome() != null && !hospedeDto.getNome().trim().isEmpty()) {
            hospede.setNome(hospedeDto.getNome());
        }

        if (hospedeDto.getDocumento() != null && !hospedeDto.getDocumento().trim().isEmpty()) {
            hospede.setDocumento(hospedeDto.getDocumento());
        }

        if (hospedeDto.getTelefone() != null && !hospedeDto.getTelefone().trim().isEmpty()) {
            hospede.setTelefone(hospedeDto.getTelefone());
        }

        if (hospedeDto.getEmail() != null && !hospedeDto.getEmail().trim().isEmpty()) {
            hospede.setEmail(hospedeDto.getEmail());
        }
    }
}
