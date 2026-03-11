package com.somos.api.shared.infrastructure.web;

import com.somos.api.technician.infrastructure.TechnicianController;
import io.javalin.config.JavalinConfig;

public class Router {

    public static void register(JavalinConfig config,
                                TechnicianController technicianController) {
        config.routes.post("/api/v1/appointments", technicianController::handleAppointment);
    }
}
