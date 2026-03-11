package com.somos.api;

import com.somos.api.shared.infrastructure.web.GlobalExceptionHandler;
import com.somos.api.shared.infrastructure.web.Router;
import io.javalin.Javalin;

public class App {
    private static final int PORT_NO = 8080;
    public static void main(String[] args) {
        // 1. Bootstrap the application dependency grap
        var registry = new DependencyRegistry();

        // 2. Start Javalin and delegate configurations
        Javalin app = Javalin.create(config -> {
            // Requests log
            config.requestLogger.http((ctx, ms) -> {
                System.out.println("Request: " + ctx.method() + " " + ctx.path() + " - time: " + ms + "ms");
            });

            // Register routes
            Router.register(
                    config,
                    registry.getTechnicianController()
            );

            // Handle Errors
            GlobalExceptionHandler.register(config);

        }).start(PORT_NO);

        System.out.println("\n=== Server ready ===");
        System.out.println("Endpoint: POST http://localhost:"+PORT_NO+"/[endpint]");
    }
}
