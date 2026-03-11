package com.somos.api;

import com.somos.api.event.infrastructure.MysqlEventRepository;
import com.somos.api.technician.application.TechnicianService;
import com.somos.api.technician.infrastructure.MysqlTechnicianRepository;
import com.somos.api.technician.infrastructure.TechnicianController;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import lombok.Getter;

@Getter
public class DependencyRegistry {
    private final TechnicianController technicianController;

    public DependencyRegistry() {
        // 1. Cargar la configuración de Hibernate PRIMERO
        Configuration hibernateConfig = new Configuration().configure();

        // 2. Extraer los datos de conexión de esa configuración
        String url = hibernateConfig.getProperty("hibernate.connection.url");
        String user = hibernateConfig.getProperty("hibernate.connection.username");
        String pass = hibernateConfig.getProperty("hibernate.connection.password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // 1. Configuración de Base de Datos
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // 5. Repositorios (Infraestructura)
        var technicianRepo = new MysqlTechnicianRepository(sessionFactory);
        var eventRepo = new MysqlEventRepository(sessionFactory);
        var technicianService = new TechnicianService(technicianRepo, eventRepo);
        this.technicianController = new TechnicianController(validator, technicianService);
    }
}
