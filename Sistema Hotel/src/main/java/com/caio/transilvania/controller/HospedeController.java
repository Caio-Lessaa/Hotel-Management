package com.caio.transilvania.controller;

import com.caio.transilvania.controller.dto.HospedeCreateDTO;
import com.caio.transilvania.controller.dto.HospedeDTO;
import com.caio.transilvania.model.Hospede;
import com.caio.transilvania.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
@CrossOrigin(origins = "http://localhost:8081/")
public class HospedeController {
    private final HospedeService hospedeService;

    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping
    public HospedeDTO cadastrarHospede(@RequestBody @Valid HospedeCreateDTO hospedeCreateDTO){
        return hospedeService.cadastrarHospede(hospedeCreateDTO);
    }

    @GetMapping
    public List<HospedeDTO> listarHospedes(){
        return hospedeService.listarHospedes();
    }

    @GetMapping("/{id}")
    public HospedeDTO buscarHospede(@PathVariable Long id){
        return hospedeService.buscarHospedeDTO(id);
    }

    @PutMapping("/{id}")
    public HospedeDTO atualizarHospede(@RequestBody HospedeCreateDTO hospedeCreateDTO, @PathVariable Long id){
        return hospedeService.atualizarHospede(hospedeCreateDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deletarQuarto(@PathVariable Long id){
        hospedeService.deletarHospede(id);
    }
}