package com.upc.persistencia;

import com.upc.modelo.Ticket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase TicketDAO (Data Access Object)
 * Maneja la persistencia de tickets en memoria (Fase 1).
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
     * Constructor privado para implementar Singleton
     */
    private TicketDAO() {
        this.tickets = new HashMap<>();
        this.contadorId = 1;
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
     *
     * @param ticket Ticket a guardar
     * @return true si se guardó exitosamente, false si ya existía
     */
    public boolean guardar(Ticket ticket) {
        if (ticket == null || ticket.getIdTicket() == null) {
            return false;
        }
        if (tickets.containsKey(ticket.getIdTicket())) {
            return false; // Ya existe
        }
        tickets.put(ticket.getIdTicket(), ticket);
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
     *
     * @param ticket Ticket con datos actualizados
     * @return true si se actualizó, false si no existe
     */
    public boolean actualizar(Ticket ticket) {
        if (ticket == null || ticket.getIdTicket() == null) {
            return false;
        }
        if (!tickets.containsKey(ticket.getIdTicket())) {
            return false; // No existe
        }
        tickets.put(ticket.getIdTicket(), ticket);
        return true;
    }

    /**
     * Elimina un ticket del almacenamiento.
     *
     * @param idTicket ID del ticket a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(String idTicket) {
        return tickets.remove(idTicket) != null;
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
     * Útil para pruebas.
     */
    public void limpiar() {
        tickets.clear();
        contadorId = 1;
    }
}
