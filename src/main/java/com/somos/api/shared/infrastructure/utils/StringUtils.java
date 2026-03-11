package com.somos.api.shared.infrastructure.utils;

import java.time.Instant;
import java.util.Date;

public final class StringUtils {

    // Constructor privado para evitar que alguien cree una instancia de esta clase
    private StringUtils() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    /**
     * Verifica si un string es nulo, está vacío o solo contiene espacios.
     */
    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static Date convertIsoToDate(String isoString) {
        // isoString = "2025-03-10T10:00:00.000Z"
        Instant instant = Instant.parse(isoString);
        return Date.from(instant);
    }
}
