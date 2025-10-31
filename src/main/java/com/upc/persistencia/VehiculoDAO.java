package com.upc.persistencia;

import com.upc.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase VehiculoDAO (Data Access Object)
 * Maneja la persistencia de vehículos en memoria (Fase 1).
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
public class VehiculoDAO {

    /**
     * Almacenamiento en memoria de vehículos (Placa -> Vehiculo)
     */
    private Map<String, Vehiculo> vehiculos;

    /**
     * Instancia única del DAO (patrón Singleton)
     */
    private static VehiculoDAO instancia;

    /**
     * Constructor privado para implementar Singleton
     */
    private VehiculoDAO() {
        this.vehiculos = new HashMap<>();
    }

    /**
     * Obtiene la instancia única del DAO.
     *
     * @return Instancia de VehiculoDAO
     */
    public static VehiculoDAO getInstancia() {
        if (instancia == null) {
            instancia = new VehiculoDAO();
        }
        return instancia;
    }

    /**
     * Guarda un vehículo en el almacenamiento.
     *
     * @param vehiculo Vehículo a guardar
     * @return true si se guardó exitosamente, false si ya existía
     */
    public boolean guardar(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null) {
            return false;
        }
        if (vehiculos.containsKey(vehiculo.getPlaca())) {
            return false; // Ya existe
        }
        vehiculos.put(vehiculo.getPlaca(), vehiculo);
        return true;
    }

    /**
     * Busca un vehículo por su placa.
     *
     * @param placa Placa del vehículo
     * @return Vehículo encontrado o null si no existe
     */
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculos.get(placa);
    }

    /**
     * Obtiene todos los vehículos registrados.
     *
     * @return Lista de todos los vehículos
     */
    public List<Vehiculo> obtenerTodos() {
        return new ArrayList<>(vehiculos.values());
    }

    /**
     * Actualiza un vehículo existente.
     *
     * @param vehiculo Vehículo con datos actualizados
     * @return true si se actualizó, false si no existe
     */
    public boolean actualizar(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null) {
            return false;
        }
        if (!vehiculos.containsKey(vehiculo.getPlaca())) {
            return false; // No existe
        }
        vehiculos.put(vehiculo.getPlaca(), vehiculo);
        return true;
    }

    /**
     * Elimina un vehículo del almacenamiento.
     *
     * @param placa Placa del vehículo a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(String placa) {
        return vehiculos.remove(placa) != null;
    }

    /**
     * Verifica si existe un vehículo con la placa especificada.
     *
     * @param placa Placa a verificar
     * @return true si existe, false si no
     */
    public boolean existe(String placa) {
        return vehiculos.containsKey(placa);
    }

    /**
     * Obtiene la cantidad de vehículos registrados.
     *
     * @return Número de vehículos
     */
    public int contarVehiculos() {
        return vehiculos.size();
    }

    /**
     * Limpia todos los vehículos del almacenamiento.
     * Útil para pruebas.
     */
    public void limpiar() {
        vehiculos.clear();
    }
}
