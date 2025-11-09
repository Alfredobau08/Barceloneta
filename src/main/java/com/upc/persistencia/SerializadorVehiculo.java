package com.upc.persistencia;

import com.upc.modelo.Bus;
import com.upc.modelo.Buseta;
import com.upc.modelo.MicroBus;
import com.upc.modelo.Vehiculo;

/**
 * Clase SerializadorVehiculo
 * Responsable de convertir objetos Vehiculo a formato CSV y viceversa.
 *
 * Formato CSV: TipoVehiculo,placa,capacidad,modelo,tarifaBase
 * Ejemplo: Buseta,ABC123,19,2024,2500.0
 *
 * Esta clase maneja la serialización y deserialización de los tres tipos de vehículos:
 * - Buseta
 * - MicroBus
 * - Bus
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 2
 * @since 2025-11-08
 */
public class SerializadorVehiculo {

    /**
     * Separador CSV (coma)
     */
    private static final String SEPARADOR = ",";

    /**
     * Convierte un objeto Vehiculo a formato CSV.
     *
     * @param vehiculo Vehículo a serializar
     * @return String en formato CSV
     * @throws IllegalArgumentException si el vehículo es null
     */
    public static String serializar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(vehiculo.getTipoVehiculo()).append(SEPARADOR);
        sb.append(vehiculo.getPlaca()).append(SEPARADOR);
        sb.append(vehiculo.getCapacidad()).append(SEPARADOR);
        sb.append(vehiculo.getModelo()).append(SEPARADOR);
        sb.append(vehiculo.getTarifaBase());

        return sb.toString();
    }

    /**
     * Convierte una línea CSV a un objeto Vehiculo.
     *
     * @param lineaCSV Línea en formato CSV
     * @return Objeto Vehiculo (Buseta, MicroBus o Bus)
     * @throws IllegalArgumentException si la línea es inválida o el tipo es desconocido
     */
    public static Vehiculo deserializar(String lineaCSV) {
        if (lineaCSV == null || lineaCSV.trim().isEmpty()) {
            throw new IllegalArgumentException("La línea CSV no puede ser null o vacía");
        }

        String[] partes = lineaCSV.split(SEPARADOR);

        if (partes.length != 5) {
            throw new IllegalArgumentException(
                "Formato CSV inválido. Se esperaban 5 campos, se encontraron " + partes.length
            );
        }

        try {
            String tipoVehiculo = partes[0].trim();
            String placa = partes[1].trim();
            int capacidad = Integer.parseInt(partes[2].trim());
            String modelo = partes[3].trim();
            double tarifaBase = Double.parseDouble(partes[4].trim());

            // Crear el vehículo según su tipo
            switch (tipoVehiculo) {
                case "Buseta":
                    return new Buseta(placa, capacidad, modelo, tarifaBase);
                case "MicroBus":
                    return new MicroBus(placa, capacidad, modelo, tarifaBase);
                case "Bus":
                    return new Bus(placa, capacidad, modelo, tarifaBase);
                default:
                    throw new IllegalArgumentException(
                        "Tipo de vehículo desconocido: " + tipoVehiculo
                    );
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Error al parsear números en la línea CSV: " + lineaCSV, e
            );
        }
    }
}
