package com.caio.transilvania.service;

import com.caio.transilvania.controller.dto.HospedeCreateDTO;
import com.caio.transilvania.controller.dto.HospedeDTO;
import com.caio.transilvania.exception.HospedeNaoEncontradoException;
import com.caio.transilvania.mapper.HospedeMapper;
import com.caio.transilvania.model.Hospede;
import com.caio.transilvania.repository.HospedeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    private final HospedeMapper hospedeMapper;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository, HospedeMapper hospedeMapper) {
        this.hospedeRepository = hospedeRepository;
        this.hospedeMapper = hospedeMapper;
    }

    public HospedeDTO cadastrarHospede(HospedeCreateDTO hospedeCreateDTO){
        return hospedeMapper.toDTO(hospedeRepository.save(hospedeMapper.toModel(hospedeCreateDTO)));
    }

    public List<HospedeDTO> listarHospedes(){
        List<Hospede> hospedes = hospedeRepository.findAll();
        return hospedeMapper.toListDTO(hospedes);
    }

    public Hospede buscarHospede(Long id){
        return hospedeRepository.findById(id).orElseThrow(HospedeNaoEncontradoException::new);
    }

    public HospedeDTO buscarHospedeDTO(Long id) {
        Hospede hospede = buscarHospede(id);
        return hospedeMapper.toDTO(hospede);
    }

    public HospedeDTO atualizarHospede(HospedeCreateDTO novoHospede, Long id){
        Hospede hospede = buscarHospede(id);
        hospedeMapper.copyProperties(novoHospede, hospede);
        return hospedeMapper.toDTO(hospedeRepository.save(hospede));
    }

    public void deletarHospede(Long id){
        buscarHospede(id);
        hospedeRepository.deleteById(id);
    }
}
