package com.upc.persistencia;

import com.upc.modelo.Pasajero;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase PasajeroDAO (Data Access Object)
 * Maneja la persistencia de pasajeros en archivos de texto (Fase 2).
 *
 * <p>Esta clase implementa el patrón DAO para separar la lógica de acceso a datos
 * de la lógica de negocio. En Fase 2 usa archivos CSV con codificación UTF-8.</p>
 *
 * <p>Los datos se mantienen en memoria (HashMap) para eficiencia y se sincronizan
 * automáticamente con el archivo de texto en cada operación CRUD.</p>
 *
 * <p><b>Patrón de diseño:</b> Data Access Object (DAO) + Singleton</p>
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 2
 * @since 2025-11-08
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
     * Ruta del archivo de persistencia
     */
    private static final String ARCHIVO_PASAJEROS = "data/pasajeros.txt";

    /**
     * Constructor privado para implementar Singleton.
     * Carga automáticamente los datos desde el archivo al inicializar.
     */
    private PasajeroDAO() {
        this.pasajeros = new HashMap<>();
        cargarDesdeArchivo();
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
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param pasajero Pasajero a guardar
     * @return true si se guardó exitosamente, false si ya existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean guardar(Pasajero pasajero) {
        if (pasajero == null || pasajero.getCedula() == null) {
            return false;
        }
        if (pasajeros.containsKey(pasajero.getCedula())) {
            return false; // Ya existe
        }
        pasajeros.put(pasajero.getCedula(), pasajero);
        guardarEnArchivo();
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
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param pasajero Pasajero con datos actualizados
     * @return true si se actualizó, false si no existe
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean actualizar(Pasajero pasajero) {
        if (pasajero == null || pasajero.getCedula() == null) {
            return false;
        }
        if (!pasajeros.containsKey(pasajero.getCedula())) {
            return false; // No existe
        }
        pasajeros.put(pasajero.getCedula(), pasajero);
        guardarEnArchivo();
        return true;
    }

    /**
     * Elimina un pasajero del almacenamiento.
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param cedula Cédula del pasajero a eliminar
     * @return true si se eliminó, false si no existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean eliminar(String cedula) {
        boolean eliminado = pasajeros.remove(cedula) != null;
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
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
     * Sincroniza automáticamente con el archivo de persistencia.
     * Útil para pruebas.
     *
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public void limpiar() {
        pasajeros.clear();
        guardarEnArchivo();
    }

    /**
     * Carga los pasajeros desde el archivo de texto.
     * Este método se ejecuta automáticamente al inicializar el DAO.
     *
     * Si el archivo no existe, se crea vacío.
     * Si el archivo está corrupto, se lanza una excepción.
     *
     * @throws RuntimeException si ocurre un error al leer el archivo o si los datos están corruptos
     */
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_PASAJEROS);

        // Si el archivo no existe, crearlo
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                return;
            } catch (IOException e) {
                throw new RuntimeException("Error al crear el archivo de pasajeros: " + ARCHIVO_PASAJEROS, e);
            }
        }

        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            int numeroLinea = 0;
            pasajeros.clear();

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();

                // Saltar líneas vacías
                if (linea.isEmpty()) {
                    continue;
                }

                try {
                    Pasajero pasajero = SerializadorPasajero.deserializar(linea);
                    pasajeros.put(pasajero.getCedula(), pasajero);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(
                        "Error al parsear línea " + numeroLinea + " del archivo " + ARCHIVO_PASAJEROS + ": " + linea, e
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de pasajeros: " + ARCHIVO_PASAJEROS, e);
        }
    }

    /**
     * Guarda todos los pasajeros en el archivo de texto.
     * Este método se ejecuta automáticamente después de cada operación CRUD.
     *
     * @throws RuntimeException si ocurre un error al escribir en el archivo
     */
    private void guardarEnArchivo() {
        File archivo = new File(ARCHIVO_PASAJEROS);

        // Crear directorio si no existe
        if (!archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            for (Pasajero pasajero : pasajeros.values()) {
                String lineaCSV = SerializadorPasajero.serializar(pasajero);
                writer.write(lineaCSV);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de pasajeros: " + ARCHIVO_PASAJEROS, e);
        }
    }
}
