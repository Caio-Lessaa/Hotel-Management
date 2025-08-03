package com.caio.transilvania.controller;

import com.caio.transilvania.controller.dto.UsuarioCreateDTO;
import com.caio.transilvania.model.Usuario;
import com.caio.transilvania.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/hospede")
    public Usuario criarHospede(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.criarUsuario(usuarioCreateDTO, "HOSPEDE");
    }
}
