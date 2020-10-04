package br.com.daione.pavan.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalError extends RuntimeException {
    public InternalError(String error) {
        super(error);
    }

}