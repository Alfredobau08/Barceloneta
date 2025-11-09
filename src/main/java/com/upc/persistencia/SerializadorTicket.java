package com.upc.persistencia;

import com.upc.modelo.Pasajero;
import com.upc.modelo.Ticket;
import com.upc.modelo.Vehiculo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase SerializadorTicket
 * Responsable de convertir objetos Ticket a formato CSV y viceversa.
 *
 * Formato CSV: idTicket,placaVehiculo,cedulaPasajero,origen,destino,fecha,distanciaKm
 * Ejemplo: TK-00001,ABC123,1000000002,Valledupar,Barranquilla,2025-11-08T10:30:45,200.0
 *
 * Esta clase maneja la serialización y deserialización de tickets, incluyendo
 * referencias a vehículos y pasajeros mediante sus identificadores únicos.
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 2
 * @since 2025-11-08
 */
public class SerializadorTicket {

    /**
     * Separador CSV (coma)
     */
    private static final String SEPARADOR = ",";

    /**
     * Formateador de fechas ISO 8601
     */
    private static final DateTimeFormatter FORMATTER_FECHA = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Convierte un objeto Ticket a formato CSV.
     *
     * @param ticket Ticket a serializar
     * @return String en formato CSV
     * @throws IllegalArgumentException si el ticket es null o falta información requerida
     */
    public static String serializar(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("El ticket no puede ser null");
        }

        if (ticket.getVehiculo() == null || ticket.getPasajero() == null) {
            throw new IllegalArgumentException("El ticket debe tener vehículo y pasajero asignados");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ticket.getIdTicket()).append(SEPARADOR);
        sb.append(ticket.getVehiculo().getPlaca()).append(SEPARADOR);
        sb.append(ticket.getPasajero().getCedula()).append(SEPARADOR);
        sb.append(ticket.getOrigen()).append(SEPARADOR);
        sb.append(ticket.getDestino()).append(SEPARADOR);
        sb.append(ticket.getFecha().format(FORMATTER_FECHA)).append(SEPARADOR);
        sb.append(ticket.getDistanciaKm());

        return sb.toString();
    }

    /**
     * Convierte una línea CSV a un objeto Ticket.
     *
     * Este método requiere que los vehículos y pasajeros referenciados ya existan
     * en sus respectivos DAOs.
     *
     * @param lineaCSV Línea en formato CSV
     * @param vehiculoDAO DAO de vehículos para buscar el vehículo por placa
     * @param pasajeroDAO DAO de pasajeros para buscar el pasajero por cédula
     * @return Objeto Ticket
     * @throws IllegalArgumentException si la línea es inválida, faltan datos, o no se encuentran referencias
     */
    public static Ticket deserializar(String lineaCSV, VehiculoDAO vehiculoDAO, PasajeroDAO pasajeroDAO) {
        if (lineaCSV == null || lineaCSV.trim().isEmpty()) {
            throw new IllegalArgumentException("La línea CSV no puede ser null o vacía");
        }

        if (vehiculoDAO == null || pasajeroDAO == null) {
            throw new IllegalArgumentException("Los DAOs no pueden ser null");
        }

        String[] partes = lineaCSV.split(SEPARADOR);

        if (partes.length != 7) {
            throw new IllegalArgumentException(
                "Formato CSV inválido. Se esperaban 7 campos, se encontraron " + partes.length
            );
        }

        try {
            String idTicket = partes[0].trim();
            String placaVehiculo = partes[1].trim();
            String cedulaPasajero = partes[2].trim();
            String origen = partes[3].trim();
            String destino = partes[4].trim();
            String fechaStr = partes[5].trim();
            double distanciaKm = Double.parseDouble(partes[6].trim());

            // Buscar vehículo por placa
            Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
            if (vehiculo == null) {
                throw new IllegalArgumentException(
                    "No se encontró el vehículo con placa: " + placaVehiculo
                );
            }

            // Buscar pasajero por cédula
            Pasajero pasajero = pasajeroDAO.buscarPorCedula(cedulaPasajero);
            if (pasajero == null) {
                throw new IllegalArgumentException(
                    "No se encontró el pasajero con cédula: " + cedulaPasajero
                );
            }

            // Parsear fecha
            LocalDateTime fecha;
            try {
                fecha = LocalDateTime.parse(fechaStr, FORMATTER_FECHA);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "Formato de fecha inválido: " + fechaStr + ". Se esperaba formato ISO (yyyy-MM-dd'T'HH:mm:ss)"
                );
            }

            // Crear el ticket
            Ticket ticket = new Ticket(idTicket, vehiculo, pasajero, origen, destino, distanciaKm);
            ticket.setFecha(fecha);

            return ticket;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Error al parsear números en la línea CSV: " + lineaCSV, e
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "Error al parsear la línea CSV: " + lineaCSV, e
            );
        }
    }
}
