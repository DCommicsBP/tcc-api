package br.com.daione.pavan.Util;


import br.com.daione.pavan.errors.BadRequest;
import br.com.daione.pavan.errors.InternalError;
import br.com.daione.pavan.errors.NotFound;
import org.springframework.stereotype.Component;

@Component
public abstract class UtilErrors {

    public static InternalError internalError(String message) { return  new InternalError(message); }

    public static NotFound notFound(String message) { return new NotFound(message); }

    public static BadRequest badRequest(String message) { return new BadRequest(message); }
}
