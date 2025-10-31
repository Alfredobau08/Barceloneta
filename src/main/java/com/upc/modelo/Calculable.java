package com.upc.modelo;

/**
 * Interfaz Calculable
 * Define el contrato para objetos que pueden calcular precios y aplicar descuentos.
 *
 * <p>Esta interfaz es parte del patrón de diseño Strategy, permitiendo diferentes
 * implementaciones de cálculo de precios según el tipo de objeto.</p>
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public interface Calculable {

    /**
     * Calcula el precio final del objeto.
     *
     * @return El precio calculado como valor double
     */
    double calcularPrecio();

    /**
     * Aplica un descuento al precio actual.
     *
     * @param porcentaje El porcentaje de descuento a aplicar (0-100)
     * @return El precio con el descuento aplicado
     * @throws IllegalArgumentException si el porcentaje es negativo o mayor a 100
     */
    double aplicarDescuento(double porcentaje);
}
