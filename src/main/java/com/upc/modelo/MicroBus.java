package com.upc.modelo;

/**
 * Clase MicroBus
 * Representa un vehículo tipo microbús (20-30 pasajeros).
 *
 * El microbús es un vehículo de capacidad media que aplica un descuento
 * del 10% sobre la tarifa base debido a su mayor eficiencia.
 *
 * Concepto POO demostrado: Herencia y Polimorfismo
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class MicroBus extends Vehiculo {

    /**
     * Multiplicador de tarifa para microbuses (10% de descuento sobre tarifa base)
     */
    private static final double MULTIPLICADOR_TARIFA = 0.9;

    /**
     * Constructor por defecto
     */
    public MicroBus() {
        super();
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param placa Placa del microbús
     * @param capacidad Capacidad de pasajeros (típicamente 20-30)
     * @param modelo Modelo del microbús
     * @param tarifaBase Tarifa base por kilómetro
     */
    public MicroBus(String placa, int capacidad, String modelo, double tarifaBase) {
        super(placa, capacidad, modelo, tarifaBase);
    }

    /**
     * Calcula la tarifa final para un microbús.
     * Implementación del método abstracto de la clase padre.
     *
     * El microbús aplica un descuento del 10% sobre la tarifa base (multiplicador 0.9)
     *
     * @param distanciaKm Distancia del viaje en kilómetros
     * @return Tarifa final calculada (tarifaBase * distancia * 0.9)
     */
    @Override
    public double calcularTarifaFinal(double distanciaKm) {
        return tarifaBase * distanciaKm * MULTIPLICADOR_TARIFA;
    }

    @Override
    public String toString() {
        return "MicroBus [Placa: " + placa + ", Capacidad: " + capacidad +
                ", Modelo: " + modelo + ", Tarifa Base: $" + String.format("%.2f", tarifaBase) + "/km]";
    }
}
