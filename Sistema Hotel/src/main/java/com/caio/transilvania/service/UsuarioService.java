package com.caio.transilvania.service;

import com.caio.transilvania.controller.dto.HospedeCreateDTO;
import com.caio.transilvania.controller.dto.UsuarioCreateDTO;
import com.caio.transilvania.model.Usuario;
import com.caio.transilvania.repository.HospedeRepository;
import com.caio.transilvania.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final HospedeService hospedeService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, HospedeService hospedeService) {
        this.usuarioRepository = usuarioRepository;
        this.hospedeService = hospedeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsuario(username).orElseThrow(() -> new RuntimeException("Credênciais não encontradas!"));
    }

    public Usuario criarUsuario(UsuarioCreateDTO usuarioDto, String tipo) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        if(tipo.equals("HOSPEDE")){
            HospedeCreateDTO hospedeCreateDTO = new HospedeCreateDTO();
            BeanUtils.copyProperties(usuarioDto, hospedeCreateDTO);
            hospedeCreateDTO.setUsuario(novoUsuario.getId());
            hospedeService.cadastrarHospede(hospedeCreateDTO);
        }else if (tipo.equals("ADMIN")){

        }
        return novoUsuario;
    }
}
