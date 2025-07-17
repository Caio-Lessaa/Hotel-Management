package com.caio.transilvania.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ReservaCheckoutStatusInvalidoException extends RuntimeException{
    public ReservaCheckoutStatusInvalidoException() {
        super("O checkout só pode ser feito se o status do quarto for ocupado");
    }
}
