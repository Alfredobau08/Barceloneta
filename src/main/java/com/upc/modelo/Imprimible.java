package com.upc.modelo;

/**
 * Interfaz Imprimible
 * Define el contrato para objetos que pueden ser impresos o generar recibos.
 *
 * Esta interfaz permite la generación de diferentes representaciones textuales
 * de un objeto, útil para mostrar información al usuario o generar documentos.
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public interface Imprimible {

    /**
     * Genera una representación imprimible del objeto.
     *
     * @return String con la información del objeto formateada para impresión
     */
    String imprimir();

    /**
     * Genera un recibo detallado del objeto.
     *
     * @return String con el recibo formateado, incluyendo todos los detalles relevantes
     */
    String generarRecibo();
}
