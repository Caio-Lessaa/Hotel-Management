package com.caio.transilvania.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReservaDataSaidaAntesEntradaException extends RuntimeException{
    public ReservaDataSaidaAntesEntradaException() {
        super("A data de saída não pode ser antes da data de entrada!");
    }
}
