package com.upc.persistencia;

import com.upc.modelo.Pasajero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase PasajeroDAO (Data Access Object)
 * Maneja la persistencia de pasajeros en memoria (Fase 1).
 *
 * <p>Esta clase implementa el patrón DAO para separar la lógica de acceso a datos
 * de la lógica de negocio. En Fase 1 usa HashMap para almacenamiento en memoria.</p>
 *
 * <p><b>Patrón de diseño:</b> Data Access Object (DAO)</p>
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class PasajeroDAO {

    /**
     * Almacenamiento en memoria de pasajeros (Cédula -> Pasajero)
     */
    private Map<String, Pasajero> pasajeros;

    /**
     * Instancia única del DAO (patrón Singleton)
     */
    private static PasajeroDAO instancia;

    /**
     * Constructor privado para implementar Singleton
     */
    private PasajeroDAO() {
        this.pasajeros = new HashMap<>();
    }

    /**
     * Obtiene la instancia única del DAO.
     *
     * @return Instancia de PasajeroDAO
     */
    public static PasajeroDAO getInstancia() {
        if (instancia == null) {
            instancia = new PasajeroDAO();
        }
        return instancia;
    }

    /**
     * Guarda un pasajero en el almacenamiento.
     *
     * @param pasajero Pasajero a guardar
     * @return true si se guardó exitosamente, false si ya existía
     */
    public boolean guardar(Pasajero pasajero) {
        if (pasajero == null || pasajero.getCedula() == null) {
            return false;
        }
        if (pasajeros.containsKey(pasajero.getCedula())) {
            return false; // Ya existe
        }
        pasajeros.put(pasajero.getCedula(), pasajero);
        return true;
    }

    /**
     * Busca un pasajero por su cédula.
     *
     * @param cedula Cédula del pasajero
     * @return Pasajero encontrado o null si no existe
     */
    public Pasajero buscarPorCedula(String cedula) {
        return pasajeros.get(cedula);
    }

    /**
     * Obtiene todos los pasajeros registrados.
     *
     * @return Lista de todos los pasajeros
     */
    public List<Pasajero> obtenerTodos() {
        return new ArrayList<>(pasajeros.values());
    }

    /**
     * Actualiza un pasajero existente.
     *
     * @param pasajero Pasajero con datos actualizados
     * @return true si se actualizó, false si no existe
     */
    public boolean actualizar(Pasajero pasajero) {
        if (pasajero == null || pasajero.getCedula() == null) {
            return false;
        }
        if (!pasajeros.containsKey(pasajero.getCedula())) {
            return false; // No existe
        }
        pasajeros.put(pasajero.getCedula(), pasajero);
        return true;
    }

    /**
     * Elimina un pasajero del almacenamiento.
     *
     * @param cedula Cédula del pasajero a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(String cedula) {
        return pasajeros.remove(cedula) != null;
    }

    /**
     * Verifica si existe un pasajero con la cédula especificada.
     *
     * @param cedula Cédula a verificar
     * @return true si existe, false si no
     */
    public boolean existe(String cedula) {
        return pasajeros.containsKey(cedula);
    }

    /**
     * Obtiene la cantidad de pasajeros registrados.
     *
     * @return Número de pasajeros
     */
    public int contarPasajeros() {
        return pasajeros.size();
    }

    /**
     * Limpia todos los pasajeros del almacenamiento.
     * Útil para pruebas.
     */
    public void limpiar() {
        pasajeros.clear();
    }
}
