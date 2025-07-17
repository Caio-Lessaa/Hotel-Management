package com.caio.transilvania.exception;

import java.util.Date;

public class ReservaDataCheckoutInvalidaException extends RuntimeException{
    public ReservaDataCheckoutInvalidaException(Date dataSaida) {
        super("O checkout só pode ser feito a partir da data " + dataSaida);
    }
}
