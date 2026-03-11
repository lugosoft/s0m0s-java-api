package com.somos.api.shared.infrastructure.web;

import com.somos.api.shared.infrastructure.response_mapper.ErrorResponse;
import io.javalin.config.JavalinConfig;

import java.net.HttpURLConnection;
import io.javalin.http.HttpResponseException;
import io.javalin.validation.ValidationException;

public class GlobalExceptionHandler {

    public static void register(JavalinConfig config) {

        config.routes.exception(RuntimeException.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("Installation not assigned.", e.getMessage());
            ctx.status(HttpURLConnection.HTTP_BAD_REQUEST).json(response);
        });

        config.routes.exception(HttpResponseException.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("Installation not assigned", e.getMessage());
            ctx.status(HttpURLConnection.HTTP_FORBIDDEN).json(response);
        });

        config.routes.exception(ValidationException.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("Installation not assigned", e.getMessage());
            ctx.status(HttpURLConnection.HTTP_BAD_REQUEST).json(response);
        });

        config.routes.exception(Exception.class, (e, ctx) -> {
            ErrorResponse response = new ErrorResponse("Installation not assigned", e.getMessage());
            System.out.println(e);
            ctx.status(HttpURLConnection.HTTP_INTERNAL_ERROR).json(response);
        });
    }
}
