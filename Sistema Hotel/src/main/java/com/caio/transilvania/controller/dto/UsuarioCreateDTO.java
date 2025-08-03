package com.caio.transilvania.controller.dto;

import com.caio.transilvania.model.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDTO {
    @NotBlank(message = "O Usuario não pode ser vazio!")
    @Size(max = 50, message = "O usuário não pode ter mais de 50 caracteres")
    private String usuario;
    @NotBlank(message = "A senha não pode ser vazia!")
    @Size(min = 8, message = "A senha precisa ter pelo menos 8 caracteres")
    private String senha;
    private RoleEnum role;
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "O nome não pode ter mais de 50 caracteres")
    private String nome;
    @NotBlank(message = "Documento não pode ser vazio")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$", message = "CPF inválido")
    private String documento;
    private String telefone;
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
