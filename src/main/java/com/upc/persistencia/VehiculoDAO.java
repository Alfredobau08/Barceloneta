package com.upc.persistencia;

import com.upc.modelo.Vehiculo;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase VehiculoDAO (Data Access Object)
 * Maneja la persistencia de vehículos en archivos de texto (Fase 2).
 *
 * Esta clase implementa el patrón DAO para separar la lógica de acceso a datos
 * de la lógica de negocio. En Fase 2 usa archivos CSV con codificación UTF-8.
 *
 * Los datos se mantienen en memoria (HashMap) para eficiencia y se sincronizan
 * automáticamente con el archivo de texto en cada operación CRUD.
 *
 * Patrón de diseño: Data Access Object (DAO) + Singleton
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 2
 * @since 2025-11-08
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
     * Ruta del archivo de persistencia
     */
    private static final String ARCHIVO_VEHICULOS = "data/vehiculos.txt";

    /**
     * Constructor privado para implementar Singleton.
     * Carga automáticamente los datos desde el archivo al inicializar.
     */
    private VehiculoDAO() {
        this.vehiculos = new HashMap<>();
        cargarDesdeArchivo();
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
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param vehiculo Vehículo a guardar
     * @return true si se guardó exitosamente, false si ya existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean guardar(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null) {
            return false;
        }
        if (vehiculos.containsKey(vehiculo.getPlaca())) {
            return false; // Ya existe
        }
        vehiculos.put(vehiculo.getPlaca(), vehiculo);
        guardarEnArchivo();
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
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param vehiculo Vehículo con datos actualizados
     * @return true si se actualizó, false si no existe
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean actualizar(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null) {
            return false;
        }
        if (!vehiculos.containsKey(vehiculo.getPlaca())) {
            return false; // No existe
        }
        vehiculos.put(vehiculo.getPlaca(), vehiculo);
        guardarEnArchivo();
        return true;
    }

    /**
     * Elimina un vehículo del almacenamiento.
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param placa Placa del vehículo a eliminar
     * @return true si se eliminó, false si no existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean eliminar(String placa) {
        boolean eliminado = vehiculos.remove(placa) != null;
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
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
     * Sincroniza automáticamente con el archivo de persistencia.
     * Útil para pruebas.
     *
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public void limpiar() {
        vehiculos.clear();
        guardarEnArchivo();
    }

    /**
     * Carga los vehículos desde el archivo de texto.
     * Este método se ejecuta automáticamente al inicializar el DAO.
     *
     * Si el archivo no existe, se crea vacío.
     * Si el archivo está corrupto, se lanza una excepción.
     *
     * @throws RuntimeException si ocurre un error al leer el archivo o si los datos están corruptos
     */
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_VEHICULOS);

        // Si el archivo no existe, crearlo
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                return;
            } catch (IOException e) {
                throw new RuntimeException("Error al crear el archivo de vehículos: " + ARCHIVO_VEHICULOS, e);
            }
        }

        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            int numeroLinea = 0;
            vehiculos.clear();

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();

                // Saltar líneas vacías
                if (linea.isEmpty()) {
                    continue;
                }

                try {
                    Vehiculo vehiculo = SerializadorVehiculo.deserializar(linea);
                    vehiculos.put(vehiculo.getPlaca(), vehiculo);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(
                        "Error al parsear línea " + numeroLinea + " del archivo " + ARCHIVO_VEHICULOS + ": " + linea, e
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de vehículos: " + ARCHIVO_VEHICULOS, e);
        }
    }

    /**
     * Guarda todos los vehículos en el archivo de texto.
     * Este método se ejecuta automáticamente después de cada operación CRUD.
     *
     * @throws RuntimeException si ocurre un error al escribir en el archivo
     */
    private void guardarEnArchivo() {
        File archivo = new File(ARCHIVO_VEHICULOS);

        // Crear directorio si no existe
        if (!archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            for (Vehiculo vehiculo : vehiculos.values()) {
                String lineaCSV = SerializadorVehiculo.serializar(vehiculo);
                writer.write(lineaCSV);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de vehículos: " + ARCHIVO_VEHICULOS, e);
        }
    }
}
