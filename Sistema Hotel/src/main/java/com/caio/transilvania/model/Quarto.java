package com.caio.transilvania.model;

import jakarta.persistence.*;

@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;
    @Enumerated(EnumType.STRING)
    private StatusQuarto status;
    private double precoDiaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public StatusQuarto getStatusQuarto() {
        return status;
    }

    public void setStatusQuarto(StatusQuarto status) {
        this.status = status;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
