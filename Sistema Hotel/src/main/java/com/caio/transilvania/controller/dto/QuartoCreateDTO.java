package com.caio.transilvania.controller.dto;

import com.caio.transilvania.model.TipoQuarto;
import jakarta.validation.constraints.NotBlank;

public class QuartoCreateDTO {
    @NotBlank(message = "O número do quarto não pode ser vazio!")
    private String numero;
    private String tipoQuarto;
    private double precoDiaria;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
