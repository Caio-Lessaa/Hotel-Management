package com.caio.transilvania.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HospedeNaoEncontradoException extends RuntimeException{
    public HospedeNaoEncontradoException() {
        super("Hospede não encontrado!");
    }
}
