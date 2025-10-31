package com.upc.modelo;

/**
 * Clase Bus
 * Representa un vehículo tipo bus (más de 30 pasajeros).
 *
 * <p>El bus es el vehículo de mayor capacidad de la flota y aplica un descuento
 * del 20% sobre la tarifa base debido a su alta eficiencia en transporte masivo.</p>
 *
 * <p><b>Concepto POO demostrado:</b> Herencia y Polimorfismo</p>
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class Bus extends Vehiculo {

    /**
     * Multiplicador de tarifa para buses (20% de descuento sobre tarifa base)
     */
    private static final double MULTIPLICADOR_TARIFA = 0.8;

    /**
     * Constructor por defecto
     */
    public Bus() {
        super();
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param placa Placa del bus
     * @param capacidad Capacidad de pasajeros (típicamente más de 30)
     * @param modelo Modelo del bus
     * @param tarifaBase Tarifa base por kilómetro
     */
    public Bus(String placa, int capacidad, String modelo, double tarifaBase) {
        super(placa, capacidad, modelo, tarifaBase);
    }

    /**
     * Calcula la tarifa final para un bus.
     * Implementación del método abstracto de la clase padre.
     *
     * <p>El bus aplica un descuento del 20% sobre la tarifa base (multiplicador 0.8)</p>
     *
     * @param distanciaKm Distancia del viaje en kilómetros
     * @return Tarifa final calculada (tarifaBase * distancia * 0.8)
     */
    @Override
    public double calcularTarifaFinal(double distanciaKm) {
        return tarifaBase * distanciaKm * MULTIPLICADOR_TARIFA;
    }

    @Override
    public String toString() {
        return "Bus [Placa: " + placa + ", Capacidad: " + capacidad +
                ", Modelo: " + modelo + ", Tarifa Base: $" + String.format("%.2f", tarifaBase) + "/km]";
    }
}
