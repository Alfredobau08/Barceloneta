package com.upc.modelo;

/**
 * Clase Pasajero
 * Representa un pasajero que compra tickets de viaje.
 *
 * Cada pasajero tiene un tipo asociado (REGULAR, ESTUDIANTE, ADULTO_MAYOR)
 * que determina el descuento aplicable en la compra de tickets.
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class Pasajero {

    /**
     * Cédula de identidad del pasajero
     */
    private String cedula;

    /**
     * Nombre completo del pasajero
     */
    private String nombre;

    /**
     * Tipo de pasajero (REGULAR, ESTUDIANTE, ADULTO_MAYOR)
     */
    private TipoPasajero tipoPasajero;

    /**
     * Constructor por defecto
     */
    public Pasajero() {
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param cedula Cédula del pasajero
     * @param nombre Nombre completo del pasajero
     * @param tipoPasajero Tipo de pasajero
     */
    public Pasajero(String cedula, String nombre, TipoPasajero tipoPasajero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoPasajero = tipoPasajero;
    }

    /**
     * Obtiene el porcentaje de descuento del pasajero según su tipo.
     *
     * @return Porcentaje de descuento (0-100)
     */
    public double getDescuento() {
        return tipoPasajero.getPorcentajeDescuento();
    }

    // Getters y Setters

    /**
     * Obtiene la cédula del pasajero.
     *
     * @return Cédula del pasajero
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del pasajero.
     *
     * @param cedula Nueva cédula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el nombre del pasajero.
     *
     * @return Nombre del pasajero
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del pasajero.
     *
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de pasajero.
     *
     * @return Tipo de pasajero
     */
    public TipoPasajero getTipoPasajero() {
        return tipoPasajero;
    }

    /**
     * Establece el tipo de pasajero.
     *
     * @param tipoPasajero Nuevo tipo de pasajero
     */
    public void setTipoPasajero(TipoPasajero tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    @Override
    public String toString() {
        return String.format("Pasajero [Cédula: %s, Nombre: %s, Tipo: %s]",
                cedula, nombre, tipoPasajero.getDescripcion());
    }
}
