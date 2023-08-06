package com.monke.resource.exception;

import com.monke.exception.MonkeMongoException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class MongoServerExceptionMapper extends Throwable implements ExceptionMapper<MonkeMongoException> {
    @Override
    public Response toResponse(MonkeMongoException exception) {
        final String error_message = Optional.of(exception.getMessage()).orElse("Something went wrong");
        return Response.status(400)
                       .entity(new ExceptionResponse(error_message))
                       .type(APPLICATION_JSON)
                       .build();
    }
}