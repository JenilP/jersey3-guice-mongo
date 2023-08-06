package com.monke.resource.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class UncaughtExceptionMapper extends Throwable implements ExceptionMapper<Throwable> {
    private static final String ERROR_MESSAGE = "Something went wrong";
    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(400)
                       .entity(new ExceptionResponse(ERROR_MESSAGE))
                       .type(APPLICATION_JSON)
                       .build();
    }
}