package com.somos.api.technician.infrastructure.dto;

public record AppointmentResponse(
        String message,
        String technician_id,
        String installation_id
) {}
