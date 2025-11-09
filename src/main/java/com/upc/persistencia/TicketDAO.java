package com.upc.persistencia;

import com.upc.modelo.Ticket;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase TicketDAO (Data Access Object)
 * Maneja la persistencia de tickets en archivos de texto (Fase 2).
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
public class TicketDAO {

    /**
     * Almacenamiento en memoria de tickets (IdTicket -> Ticket)
     */
    private Map<String, Ticket> tickets;

    /**
     * Contador para generar IDs únicos de tickets
     */
    private int contadorId;

    /**
     * Instancia única del DAO (patrón Singleton)
     */
    private static TicketDAO instancia;

    /**
     * Ruta del archivo de persistencia
     */
    private static final String ARCHIVO_TICKETS = "data/tickets.txt";

    /**
     * Constructor privado para implementar Singleton.
     * Carga automáticamente los datos desde el archivo al inicializar.
     */
    private TicketDAO() {
        this.tickets = new HashMap<>();
        this.contadorId = 1;
        cargarDesdeArchivo();
    }

    /**
     * Obtiene la instancia única del DAO.
     *
     * @return Instancia de TicketDAO
     */
    public static TicketDAO getInstancia() {
        if (instancia == null) {
            instancia = new TicketDAO();
        }
        return instancia;
    }

    /**
     * Genera un ID único para un nuevo ticket.
     *
     * @return ID generado en formato "TK-00001"
     */
    public String generarId() {
        return String.format("TK-%05d", contadorId++);
    }

    /**
     * Guarda un ticket en el almacenamiento.
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param ticket Ticket a guardar
     * @return true si se guardó exitosamente, false si ya existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean guardar(Ticket ticket) {
        if (ticket == null || ticket.getIdTicket() == null) {
            return false;
        }
        if (tickets.containsKey(ticket.getIdTicket())) {
            return false; // Ya existe
        }
        tickets.put(ticket.getIdTicket(), ticket);
        guardarEnArchivo();
        return true;
    }

    /**
     * Busca un ticket por su ID.
     *
     * @param idTicket ID del ticket
     * @return Ticket encontrado o null si no existe
     */
    public Ticket buscarPorId(String idTicket) {
        return tickets.get(idTicket);
    }

    /**
     * Obtiene todos los tickets registrados.
     *
     * @return Lista de todos los tickets
     */
    public List<Ticket> obtenerTodos() {
        return new ArrayList<>(tickets.values());
    }

    /**
     * Busca tickets por cédula del pasajero.
     *
     * @param cedula Cédula del pasajero
     * @return Lista de tickets del pasajero
     */
    public List<Ticket> buscarPorPasajero(String cedula) {
        List<Ticket> ticketsPasajero = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getPasajero() != null &&
                ticket.getPasajero().getCedula().equals(cedula)) {
                ticketsPasajero.add(ticket);
            }
        }
        return ticketsPasajero;
    }

    /**
     * Busca tickets por placa del vehículo.
     *
     * @param placa Placa del vehículo
     * @return Lista de tickets del vehículo
     */
    public List<Ticket> buscarPorVehiculo(String placa) {
        List<Ticket> ticketsVehiculo = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getVehiculo() != null &&
                ticket.getVehiculo().getPlaca().equals(placa)) {
                ticketsVehiculo.add(ticket);
            }
        }
        return ticketsVehiculo;
    }

    /**
     * Actualiza un ticket existente.
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param ticket Ticket con datos actualizados
     * @return true si se actualizó, false si no existe
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean actualizar(Ticket ticket) {
        if (ticket == null || ticket.getIdTicket() == null) {
            return false;
        }
        if (!tickets.containsKey(ticket.getIdTicket())) {
            return false; // No existe
        }
        tickets.put(ticket.getIdTicket(), ticket);
        guardarEnArchivo();
        return true;
    }

    /**
     * Elimina un ticket del almacenamiento.
     * Sincroniza automáticamente con el archivo de persistencia.
     *
     * @param idTicket ID del ticket a eliminar
     * @return true si se eliminó, false si no existía
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public boolean eliminar(String idTicket) {
        boolean eliminado = tickets.remove(idTicket) != null;
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
    }

    /**
     * Verifica si existe un ticket con el ID especificado.
     *
     * @param idTicket ID a verificar
     * @return true si existe, false si no
     */
    public boolean existe(String idTicket) {
        return tickets.containsKey(idTicket);
    }

    /**
     * Obtiene la cantidad de tickets registrados.
     *
     * @return Número de tickets
     */
    public int contarTickets() {
        return tickets.size();
    }

    /**
     * Limpia todos los tickets del almacenamiento y reinicia el contador.
     * Sincroniza automáticamente con el archivo de persistencia.
     * Útil para pruebas.
     *
     * @throws RuntimeException si ocurre un error al guardar en el archivo
     */
    public void limpiar() {
        tickets.clear();
        contadorId = 1;
        guardarEnArchivo();
    }

    /**
     * Carga los tickets desde el archivo de texto.
     * Este método se ejecuta automáticamente al inicializar el DAO.
     *
     * Si el archivo no existe, se crea vacío.
     * Si el archivo está corrupto, se lanza una excepción.
     *
     * @throws RuntimeException si ocurre un error al leer el archivo o si los datos están corruptos
     */
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO_TICKETS);

        // Si el archivo no existe, crearlo
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                return;
            } catch (IOException e) {
                throw new RuntimeException("Error al crear el archivo de tickets: " + ARCHIVO_TICKETS, e);
            }
        }

        // Obtener instancias de los DAOs necesarios para deserialización
        VehiculoDAO vehiculoDAO = VehiculoDAO.getInstancia();
        PasajeroDAO pasajeroDAO = PasajeroDAO.getInstancia();

        // Leer el archivo línea por línea
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            int numeroLinea = 0;
            tickets.clear();
            int maxId = 0;

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();

                // Saltar líneas vacías
                if (linea.isEmpty()) {
                    continue;
                }

                try {
                    Ticket ticket = SerializadorTicket.deserializar(linea, vehiculoDAO, pasajeroDAO);
                    tickets.put(ticket.getIdTicket(), ticket);

                    // Extraer el número del ID para actualizar el contador
                    String id = ticket.getIdTicket();
                    if (id.startsWith("TK-")) {
                        try {
                            int numeroId = Integer.parseInt(id.substring(3));
                            if (numeroId > maxId) {
                                maxId = numeroId;
                            }
                        } catch (NumberFormatException e) {
                            // Si no se puede parsear, ignorar
                        }
                    }
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(
                        "Error al parsear línea " + numeroLinea + " del archivo " + ARCHIVO_TICKETS + ": " + linea, e
                    );
                }
            }

            // Actualizar el contador para que el próximo ID sea único
            if (maxId > 0) {
                contadorId = maxId + 1;
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de tickets: " + ARCHIVO_TICKETS, e);
        }
    }

    /**
     * Guarda todos los tickets en el archivo de texto.
     * Este método se ejecuta automáticamente después de cada operación CRUD.
     *
     * @throws RuntimeException si ocurre un error al escribir en el archivo
     */
    private void guardarEnArchivo() {
        File archivo = new File(ARCHIVO_TICKETS);

        // Crear directorio si no existe
        if (!archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            for (Ticket ticket : tickets.values()) {
                String lineaCSV = SerializadorTicket.serializar(ticket);
                writer.write(lineaCSV);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de tickets: " + ARCHIVO_TICKETS, e);
        }
    }
}
