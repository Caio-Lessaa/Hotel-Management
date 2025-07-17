package com.caio.transilvania.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReservaAnteriorDataAtualException extends RuntimeException{
    public ReservaAnteriorDataAtualException() {
        super("A data de entrada da reserva não pode ser anterior a data atual!");
    }
}
