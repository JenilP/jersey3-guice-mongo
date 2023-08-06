package com.monke.resource.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class NullPointerExceptionMapper extends Throwable implements ExceptionMapper<NullPointerException> {
    @Override
    public Response toResponse(NullPointerException exception) {
        return Response.status(400)
                       .entity(new ExceptionResponse(exception.getMessage()))
                       .type(APPLICATION_JSON)
                       .build();
    }
}