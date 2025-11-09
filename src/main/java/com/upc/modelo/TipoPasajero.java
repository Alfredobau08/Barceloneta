package com.upc.modelo;

/**
 * Enumeración TipoPasajero
 * Define los tipos de pasajeros y sus descuentos asociados.
 *
 * Cada tipo de pasajero tiene un porcentaje de descuento predefinido que se
 * aplica automáticamente al calcular el precio del ticket.
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public enum TipoPasajero {
    /**
     * Pasajero regular sin descuento especial (0%)
     */
    REGULAR("Regular", 0.0),

    /**
     * Pasajero estudiante con descuento del 20%
     */
    ESTUDIANTE("Estudiante", 20.0),

    /**
     * Pasajero adulto mayor con descuento del 30%
     */
    ADULTO_MAYOR("Adulto Mayor", 30.0);

    private final String descripcion;
    private final double porcentajeDescuento;

    /**
     * Constructor del enum TipoPasajero.
     *
     * @param descripcion Descripción del tipo de pasajero
     * @param porcentajeDescuento Porcentaje de descuento (0-100)
     */
    TipoPasajero(String descripcion, double porcentajeDescuento) {
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * Obtiene la descripción del tipo de pasajero.
     *
     * @return Descripción del tipo de pasajero
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el porcentaje de descuento del tipo de pasajero.
     *
     * @return Porcentaje de descuento (0-100)
     */
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public String toString() {
        return descripcion + " (" + porcentajeDescuento + "% descuento)";
    }
}
