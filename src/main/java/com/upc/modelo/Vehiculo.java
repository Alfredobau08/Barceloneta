package com.upc.modelo;

/**
 * Clase Abstracta Vehiculo
 * Representa un vehículo genérico en el sistema de venta de tickets.
 *
 * Esta clase abstracta define las características comunes de todos los vehículos
 * y establece el contrato para el cálculo de tarifas mediante el método abstracto
 * calcularTarifaFinal().
 *
 * Concepto POO demostrado: Herencia y Abstracción
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public abstract class Vehiculo {

    /**
     * Placa única del vehículo
     */
    protected String placa;

    /**
     * Capacidad máxima de pasajeros
     */
    protected int capacidad;

    /**
     * Modelo del vehículo (año)
     */
    protected String modelo;

    /**
     * Tarifa base por kilómetro
     */
    protected double tarifaBase;

    /**
     * Constructor por defecto
     */
    public Vehiculo() {
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param placa Placa del vehículo
     * @param capacidad Capacidad de pasajeros
     * @param modelo Modelo del vehículo
     * @param tarifaBase Tarifa base por kilómetro
     */
    public Vehiculo(String placa, int capacidad, String modelo, double tarifaBase) {
        this.placa = placa;
        this.capacidad = capacidad;
        this.modelo = modelo;
        this.tarifaBase = tarifaBase;
    }

    /**
     * Método abstracto para calcular la tarifa final.
     * Cada tipo de vehículo implementará su propia lógica de cálculo.
     *
     * @param distanciaKm Distancia del viaje en kilómetros
     * @return Tarifa final calculada
     */
    public abstract double calcularTarifaFinal(double distanciaKm);

    // Getters y Setters

    /**
     * Obtiene la placa del vehículo.
     *
     * @return Placa del vehículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece la placa del vehículo.
     *
     * @param placa Nueva placa del vehículo
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene la capacidad del vehículo.
     *
     * @return Capacidad de pasajeros
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad del vehículo.
     *
     * @param capacidad Nueva capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el modelo del vehículo.
     *
     * @return Modelo del vehículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del vehículo.
     *
     * @param modelo Nuevo modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene la tarifa base del vehículo.
     *
     * @return Tarifa base por kilómetro
     */
    public double getTarifaBase() {
        return tarifaBase;
    }

    /**
     * Establece la tarifa base del vehículo.
     *
     * @param tarifaBase Nueva tarifa base
     */
    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    /**
     * Obtiene el tipo de vehículo (nombre de la clase).
     *
     * @return Tipo de vehículo
     */
    public String getTipoVehiculo() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("%s [Placa: %s, Capacidad: %d, Modelo: %s, Tarifa Base: $%.2f/km]",
                getTipoVehiculo(), placa, capacidad, modelo, tarifaBase);
    }
}
