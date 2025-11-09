package com.upc.modelo;

/**
 * Clase Buseta
 * Representa un vehículo tipo buseta (16-19 pasajeros).
 *
 * La buseta es el vehículo más pequeño de la flota y aplica la tarifa base
 * sin modificaciones adicionales.
 *
 * Concepto POO demostrado: Herencia y Polimorfismo
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class Buseta extends Vehiculo {

    /**
     * Multiplicador de tarifa para busetas (tarifa base sin modificación)
     */
    private static final double MULTIPLICADOR_TARIFA = 1.0;

    /**
     * Constructor por defecto
     */
    public Buseta() {
        super();
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param placa Placa de la buseta
     * @param capacidad Capacidad de pasajeros (típicamente 16-19)
     * @param modelo Modelo de la buseta
     * @param tarifaBase Tarifa base por kilómetro
     */
    public Buseta(String placa, int capacidad, String modelo, double tarifaBase) {
        super(placa, capacidad, modelo, tarifaBase);
    }

    /**
     * Calcula la tarifa final para una buseta.
     * Implementación del método abstracto de la clase padre.
     *
     * La buseta aplica la tarifa base sin modificaciones (multiplicador 1.0)
     *
     * @param distanciaKm Distancia del viaje en kilómetros
     * @return Tarifa final calculada (tarifaBase * distancia * 1.0)
     */
    @Override
    public double calcularTarifaFinal(double distanciaKm) {
        return tarifaBase * distanciaKm * MULTIPLICADOR_TARIFA;
    }

    @Override
    public String toString() {
        return "Buseta [Placa: " + placa + ", Capacidad: " + capacidad +
                ", Modelo: " + modelo + ", Tarifa Base: $" + String.format("%.2f", tarifaBase) + "/km]";
    }
}
