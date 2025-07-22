package com.caio.transilvania.controller.dto;

import com.caio.transilvania.model.TipoQuarto;
import jakarta.validation.constraints.NotBlank;

public class QuartoCreateDTO {
    @NotBlank(message = "O número do quarto não pode ser vazio!")
    private String numero;
    private String tipo;
    private double precoDiaria;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
