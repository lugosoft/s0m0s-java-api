package com.somos.api.shared.infrastructure.response_mapper;

public record ErrorResponse(
        String message,
        String error
) {}
