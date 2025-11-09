package com.upc.logica;

import com.upc.modelo.*;
import com.upc.persistencia.*;
import java.util.List;

/**
 * Clase GestorTickets
 * Contiene la lógica de negocio para la gestión de tickets de viaje.
 *
 * Esta clase actúa como fachada (Facade pattern) para coordinar
 * las operaciones entre las capas de modelo y persistencia.
 *
 * Arquitectura: Capa de Lógica de Negocio
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class GestorTickets {

    private VehiculoDAO vehiculoDAO;
    private PasajeroDAO pasajeroDAO;
    private TicketDAO ticketDAO;

    /**
     * Constructor que inicializa los DAOs
     */
    public GestorTickets() {
        this.vehiculoDAO = VehiculoDAO.getInstancia();
        this.pasajeroDAO = PasajeroDAO.getInstancia();
        this.ticketDAO = TicketDAO.getInstancia();
    }

    // ========== GESTIÓN DE VEHÍCULOS ==========

    /**
     * Registra un nuevo vehículo en el sistema.
     *
     * @param vehiculo Vehículo a registrar
     * @return true si se registró exitosamente, false si ya existe
     */
    public boolean registrarVehiculo(Vehiculo vehiculo) {
        return vehiculoDAO.guardar(vehiculo);
    }

    /**
     * Busca un vehículo por su placa.
     *
     * @param placa Placa del vehículo
     * @return Vehículo encontrado o null
     */
    public Vehiculo buscarVehiculo(String placa) {
        return vehiculoDAO.buscarPorPlaca(placa);
    }

    /**
     * Lista todos los vehículos registrados.
     *
     * @return Lista de vehículos
     */
    public List<Vehiculo> listarVehiculos() {
        return vehiculoDAO.obtenerTodos();
    }

    // ========== GESTIÓN DE PASAJEROS ==========

    /**
     * Registra un nuevo pasajero en el sistema.
     *
     * @param pasajero Pasajero a registrar
     * @return true si se registró exitosamente, false si ya existe
     */
    public boolean registrarPasajero(Pasajero pasajero) {
        return pasajeroDAO.guardar(pasajero);
    }

    /**
     * Busca un pasajero por su cédula.
     *
     * @param cedula Cédula del pasajero
     * @return Pasajero encontrado o null
     */
    public Pasajero buscarPasajero(String cedula) {
        return pasajeroDAO.buscarPorCedula(cedula);
    }

    /**
     * Lista todos los pasajeros registrados.
     *
     * @return Lista de pasajeros
     */
    public List<Pasajero> listarPasajeros() {
        return pasajeroDAO.obtenerTodos();
    }

    // ========== GESTIÓN DE TICKETS ==========

    /**
     * Vende un ticket de viaje.
     * Este método implementa la lógica de negocio principal.
     *
     * @param cedulaPasajero Cédula del pasajero
     * @param placaVehiculo Placa del vehículo
     * @param origen Ciudad de origen
     * @param destino Ciudad de destino
     * @param distanciaKm Distancia en kilómetros
     * @return Ticket generado o null si hay error
     */
    public Ticket venderTicket(String cedulaPasajero, String placaVehiculo,
                               String origen, String destino, double distanciaKm) {
        // Validaciones de negocio
        if (distanciaKm <= 0) {
            System.out.println("Error: La distancia debe ser mayor a 0");
            return null;
        }

        // Buscar pasajero
        Pasajero pasajero = pasajeroDAO.buscarPorCedula(cedulaPasajero);
        if (pasajero == null) {
            System.out.println("Error: Pasajero no encontrado");
            return null;
        }

        // Buscar vehículo
        Vehiculo vehiculo = vehiculoDAO.buscarPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            System.out.println("Error: Vehículo no encontrado");
            return null;
        }

        // Generar ID único para el ticket
        String idTicket = ticketDAO.generarId();

        // Crear el ticket
        Ticket ticket = new Ticket(idTicket, vehiculo, pasajero, origen, destino, distanciaKm);

        // Guardar el ticket
        if (ticketDAO.guardar(ticket)) {
            return ticket;
        } else {
            System.out.println("Error: No se pudo guardar el ticket");
            return null;
        }
    }

    /**
     * Busca un ticket por su ID.
     *
     * @param idTicket ID del ticket
     * @return Ticket encontrado o null
     */
    public Ticket buscarTicket(String idTicket) {
        return ticketDAO.buscarPorId(idTicket);
    }

    /**
     * Lista todos los tickets vendidos.
     *
     * @return Lista de tickets
     */
    public List<Ticket> listarTickets() {
        return ticketDAO.obtenerTodos();
    }

    /**
     * Lista los tickets de un pasajero específico.
     *
     * @param cedula Cédula del pasajero
     * @return Lista de tickets del pasajero
     */
    public List<Ticket> listarTicketsPorPasajero(String cedula) {
        return ticketDAO.buscarPorPasajero(cedula);
    }

    /**
     * Lista los tickets de un vehículo específico.
     *
     * @param placa Placa del vehículo
     * @return Lista de tickets del vehículo
     */
    public List<Ticket> listarTicketsPorVehiculo(String placa) {
        return ticketDAO.buscarPorVehiculo(placa);
    }

    // ========== ESTADÍSTICAS ==========

    /**
     * Calcula el total de ingresos por ventas de tickets.
     *
     * @return Total de ingresos
     */
    public double calcularIngresosTotal() {
        double total = 0;
        for (Ticket ticket : ticketDAO.obtenerTodos()) {
            total += ticket.calcularPrecioFinal();
        }
        return total;
    }

    /**
     * Obtiene estadísticas generales del sistema.
     *
     * @return String con estadísticas formateadas
     */
    public String obtenerEstadisticas() {
        int totalVehiculos = vehiculoDAO.contarVehiculos();
        int totalPasajeros = pasajeroDAO.contarPasajeros();
        int totalTickets = ticketDAO.contarTickets();
        double ingresos = calcularIngresosTotal();

        return String.format(
            "\n===== ESTADÍSTICAS DEL SISTEMA =====\n" +
            "Vehículos registrados: %d\n" +
            "Pasajeros registrados: %d\n" +
            "Tickets vendidos: %d\n" +
            "Ingresos totales: $%.2f\n" +
            "====================================\n",
            totalVehiculos, totalPasajeros, totalTickets, ingresos
        );
    }

    // ========== DATOS DE PRUEBA ==========

    /**
     * Inicializa el sistema con datos de prueba.
     * Útil para demostración y pruebas.
     */
    public void cargarDatosPrueba() {
        // Registrar vehículos
        registrarVehiculo(new Buseta("ABC123", 19, "2020", 2500.0));
        registrarVehiculo(new MicroBus("DEF456", 25, "2021", 2500.0));
        registrarVehiculo(new Bus("GHI789", 40, "2022", 2500.0));

        // Registrar pasajeros
        registrarPasajero(new Pasajero("1000000001", "Juan Pérez", TipoPasajero.REGULAR));
        registrarPasajero(new Pasajero("1000000002", "María López", TipoPasajero.ESTUDIANTE));
        registrarPasajero(new Pasajero("1000000003", "Carlos Gómez", TipoPasajero.ADULTO_MAYOR));

        System.out.println("Datos de prueba cargados exitosamente.");
    }
}
