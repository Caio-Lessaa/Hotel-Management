package com.caio.transilvania.controller;

import com.caio.transilvania.controller.dto.QuartoCreateDTO;
import com.caio.transilvania.controller.dto.QuartoDTO;
import com.caio.transilvania.model.Quarto;
import com.caio.transilvania.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {
    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public QuartoDTO cadastrarQuarto(@RequestBody @Valid QuartoCreateDTO quartoCreateDTO){
        return quartoService.cadastrarQuarto(quartoCreateDTO);
    }

    @GetMapping
    public List<QuartoDTO> listarQuartos(){
        return quartoService.listarQuartos();
    }

    @GetMapping("/disponiveis")
    public List<QuartoDTO> listarQuartosDisponiveis(){
        return quartoService.listarQuartosDisponiveis();
    }

    @GetMapping("/{id}")
    public QuartoDTO buscarQuarto(@PathVariable Long id){
       return quartoService.buscarQuartoDTO(id);
    }

    @GetMapping("/disponiveisPorData")
    public List<QuartoDTO> listarQuartosDisponiveisPorData( @RequestParam String dataInicial, @RequestParam String dataFinal, @RequestParam String tipoQuarto) {
        return quartoService.listarQuartosDisponiveisPorDataETipo(dataInicial, dataFinal, tipoQuarto);
    }

    @PutMapping("/{id}")
    public QuartoDTO atualizarQuarto(@RequestBody QuartoCreateDTO quarto, @PathVariable Long id){
        return quartoService.atualizarQuarto(quarto, id);
    }

    @DeleteMapping("/{id}")
    public void deletarQuarto(@PathVariable Long id){
        quartoService.deletarQuarto(id);
    };
}
