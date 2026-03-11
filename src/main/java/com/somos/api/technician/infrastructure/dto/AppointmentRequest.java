package com.somos.api.technician.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AppointmentRequest(
        @NotBlank(message="installation_id is mandatory")
        @Pattern(
                regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "Invalid UUID format for installation_id"
        )
        String installation_id,

        @NotBlank(message="start_time is mandatory")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$",
                message = "The date format for start_time must follow the ISO 8601 standard (yyyy-MM-dd'T'HH:mm:ss.SSSZ)"
        )
        String start_time,

        @NotBlank(message="end_time is mandatory")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$",
                message = "The date format for end_time must follow the ISO 8601 standard (yyyy-MM-dd'T'HH:mm:ss.SSSZ)"
        )
        String end_time
) {}
