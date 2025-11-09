package com.upc.persistencia;

import com.upc.modelo.Pasajero;
import com.upc.modelo.TipoPasajero;

/**
 * Clase SerializadorPasajero
 * Responsable de convertir objetos Pasajero a formato CSV y viceversa.
 *
 * Formato CSV: cedula,nombre,tipoPasajero
 * Ejemplo: 1000000001,Juan Pérez,REGULAR
 *
 * Esta clase maneja la serialización y deserialización de pasajeros con sus
 * tipos asociados (REGULAR, ESTUDIANTE, ADULTO_MAYOR).
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 2
 * @since 2025-11-08
 */
public class SerializadorPasajero {

    /**
     * Separador CSV (coma)
     */
    private static final String SEPARADOR = ",";

    /**
     * Convierte un objeto Pasajero a formato CSV.
     *
     * @param pasajero Pasajero a serializar
     * @return String en formato CSV
     * @throws IllegalArgumentException si el pasajero es null
     */
    public static String serializar(Pasajero pasajero) {
        if (pasajero == null) {
            throw new IllegalArgumentException("El pasajero no puede ser null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pasajero.getCedula()).append(SEPARADOR);
        sb.append(pasajero.getNombre()).append(SEPARADOR);
        sb.append(pasajero.getTipoPasajero().name());

        return sb.toString();
    }

    /**
     * Convierte una línea CSV a un objeto Pasajero.
     *
     * @param lineaCSV Línea en formato CSV
     * @return Objeto Pasajero
     * @throws IllegalArgumentException si la línea es inválida o el tipo es desconocido
     */
    public static Pasajero deserializar(String lineaCSV) {
        if (lineaCSV == null || lineaCSV.trim().isEmpty()) {
            throw new IllegalArgumentException("La línea CSV no puede ser null o vacía");
        }

        String[] partes = lineaCSV.split(SEPARADOR);

        if (partes.length != 3) {
            throw new IllegalArgumentException(
                "Formato CSV inválido. Se esperaban 3 campos, se encontraron " + partes.length
            );
        }

        try {
            String cedula = partes[0].trim();
            String nombre = partes[1].trim();
            String tipoStr = partes[2].trim();

            // Convertir el string a enum TipoPasajero
            TipoPasajero tipoPasajero;
            try {
                tipoPasajero = TipoPasajero.valueOf(tipoStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                    "Tipo de pasajero desconocido: " + tipoStr +
                    ". Valores válidos: REGULAR, ESTUDIANTE, ADULTO_MAYOR"
                );
            }

            return new Pasajero(cedula, nombre, tipoPasajero);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "Error al parsear la línea CSV: " + lineaCSV, e
            );
        }
    }
}
