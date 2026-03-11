package com.somos.api.technician.infrastructure;

import com.somos.api.shared.infrastructure.utils.StringUtils;
import com.somos.api.technician.application.TechnicianService;
import com.somos.api.technician.domain.Technician;
import com.somos.api.technician.infrastructure.dto.AppointmentRequest;
import com.somos.api.technician.infrastructure.dto.AppointmentResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianController {
    private final TechnicianService technicianService;
    private final Validator validator;

    public TechnicianController(Validator validator, TechnicianService technicianService) {
        this.validator = validator;
        this.technicianService = technicianService;
    }

    public void handleAppointment (Context ctx){
        AppointmentRequest appointmentRequest = ctx.bodyValidator(AppointmentRequest.class).get();
        Set<ConstraintViolation<AppointmentRequest>> violations = validator.validate(appointmentRequest);
        String errors = violations.stream()
                .map(userRequestConstraintViolation ->
                        userRequestConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));
        if(!StringUtils.isNullOrBlank(errors)){
            throw new RuntimeException(errors);
        }

        Technician technician = technicianService.getAppointment(appointmentRequest.installation_id(), appointmentRequest.start_time(), appointmentRequest.end_time());

        AppointmentResponse response = new AppointmentResponse("Installation assigned successfully", technician.getId().toString(), appointmentRequest.installation_id());
        ctx.status(HttpStatus.CREATED).result("Success").json(response);
    }
}
