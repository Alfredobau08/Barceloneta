package com.upc.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Ticket
 * Representa un ticket de viaje vendido a un pasajero.
 *
 * Esta clase implementa las interfaces Calculable e Imprimible,
 * demostrando el uso de interfaces y polimorfismo.
 *
 * Conceptos POO demostrados: Interfaces, Polimorfismo, Composición
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class Ticket implements Calculable, Imprimible {

    /**
     * Identificador único del ticket
     */
    private String idTicket;

    /**
     * Vehículo asignado al viaje
     */
    private Vehiculo vehiculo;

    /**
     * Pasajero que compra el ticket
     */
    private Pasajero pasajero;

    /**
     * Ciudad de origen
     */
    private String origen;

    /**
     * Ciudad de destino
     */
    private String destino;

    /**
     * Fecha y hora de emisión del ticket
     */
    private LocalDateTime fecha;

    /**
     * Distancia del viaje en kilómetros
     */
    private double distanciaKm;

    /**
     * Constructor por defecto
     */
    public Ticket() {
        this.fecha = LocalDateTime.now();
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param idTicket Identificador del ticket
     * @param vehiculo Vehículo del viaje
     * @param pasajero Pasajero del ticket
     * @param origen Ciudad de origen
     * @param destino Ciudad de destino
     * @param distanciaKm Distancia en kilómetros
     */
    public Ticket(String idTicket, Vehiculo vehiculo, Pasajero pasajero,
                  String origen, String destino, double distanciaKm) {
        this.idTicket = idTicket;
        this.vehiculo = vehiculo;
        this.pasajero = pasajero;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.fecha = LocalDateTime.now();
    }

    /**
     * Calcula el precio base del ticket sin descuentos.
     * Implementación de la interfaz Calculable.
     *
     * El precio se calcula usando el método calcularTarifaFinal() del vehículo,
     * demostrando polimorfismo (diferentes tipos de vehículos calculan diferente).
     *
     * @return Precio base del ticket
     */
    @Override
    public double calcularPrecio() {
        if (vehiculo == null) {
            throw new IllegalStateException("El ticket no tiene vehículo asignado");
        }
        return vehiculo.calcularTarifaFinal(distanciaKm);
    }

    /**
     * Aplica un descuento al precio del ticket.
     * Implementación de la interfaz Calculable.
     *
     * @param porcentaje Porcentaje de descuento (0-100)
     * @return Precio con descuento aplicado
     * @throws IllegalArgumentException si el porcentaje es inválido
     */
    @Override
    public double aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
        }
        double precioBase = calcularPrecio();
        return precioBase - (precioBase * porcentaje / 100);
    }

    /**
     * Calcula el precio final del ticket aplicando el descuento del pasajero.
     *
     * @return Precio final con descuento del pasajero aplicado
     */
    public double calcularPrecioFinal() {
        if (pasajero == null) {
            return calcularPrecio();
        }
        return aplicarDescuento(pasajero.getDescuento());
    }

    /**
     * Genera una representación simple del ticket.
     * Implementación de la interfaz Imprimible.
     *
     * @return String con información resumida del ticket
     */
    @Override
    public String imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Ticket #%s | %s -> %s | %s | %.0f km | $%.2f",
                idTicket, origen, destino,
                vehiculo.getTipoVehiculo(),
                distanciaKm,
                calcularPrecioFinal());
    }

    /**
     * Genera un recibo detallado del ticket.
     * Implementación de la interfaz Imprimible.
     *
     * @return String con el recibo completo formateado
     */
    @Override
    public String generarRecibo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder recibo = new StringBuilder();

        recibo.append("\n========================================\n");
        recibo.append("         TICKET DE VIAJE BARCELONETA    \n");
        recibo.append("========================================\n\n");

        recibo.append(String.format("Ticket No: %s\n", idTicket));
        recibo.append(String.format("Fecha: %s\n\n", fecha.format(formatter)));

        recibo.append("--- INFORMACIÓN DEL PASAJERO ---\n");
        recibo.append(String.format("Nombre: %s\n", pasajero.getNombre()));
        recibo.append(String.format("Cédula: %s\n", pasajero.getCedula()));
        recibo.append(String.format("Tipo: %s\n\n", pasajero.getTipoPasajero().getDescripcion()));

        recibo.append("--- INFORMACIÓN DEL VIAJE ---\n");
        recibo.append(String.format("Origen: %s\n", origen));
        recibo.append(String.format("Destino: %s\n", destino));
        recibo.append(String.format("Distancia: %.2f km\n\n", distanciaKm));

        recibo.append("--- INFORMACIÓN DEL VEHÍCULO ---\n");
        recibo.append(String.format("Tipo: %s\n", vehiculo.getTipoVehiculo()));
        recibo.append(String.format("Placa: %s\n", vehiculo.getPlaca()));
        recibo.append(String.format("Capacidad: %d pasajeros\n\n", vehiculo.getCapacidad()));

        recibo.append("--- DESGLOSE DE PRECIOS ---\n");
        double precioBase = calcularPrecio();
        double descuento = pasajero.getDescuento();
        double precioFinal = calcularPrecioFinal();

        recibo.append(String.format("Precio base: $%.2f\n", precioBase));
        recibo.append(String.format("Descuento (%.0f%%): -$%.2f\n", descuento, precioBase - precioFinal));
        recibo.append("----------------------------------------\n");
        recibo.append(String.format("TOTAL A PAGAR: $%.2f\n", precioFinal));
        recibo.append("========================================\n");
        recibo.append("     ¡Gracias por viajar con nosotros!\n");
        recibo.append("========================================\n");

        return recibo.toString();
    }

    // Getters y Setters

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    @Override
    public String toString() {
        return imprimir();
    }
}
