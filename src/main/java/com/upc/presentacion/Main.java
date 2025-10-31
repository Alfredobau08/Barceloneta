package com.upc.presentacion;

import com.upc.logica.GestorTickets;
import com.upc.modelo.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Main - Punto de entrada de la aplicación
 * Implementa la capa de presentación con interfaz de consola.
 *
 * <p>Esta clase maneja toda la interacción con el usuario mediante menús
 * y captura de datos por consola.</p>
 *
 * <p><b>Arquitectura:</b> Capa de Presentación</p>
 *
 * @author Universidad Popular del Cesar
 * @version 1.0 - Fase 1
 * @since 2025-10-31
 */
public class Main {

    private static GestorTickets gestor = new GestorTickets();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        mostrarBienvenida();
        gestor.cargarDatosPrueba();
        menuPrincipal();
    }

    /**
     * Muestra el mensaje de bienvenida.
     */
    private static void mostrarBienvenida() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE TICKETS BARCELONETA         ║");
        System.out.println("║   Universidad Popular del Cesar          ║");
        System.out.println("║   POO II - Fase 1 (Consola + Memoria)    ║");
        System.out.println("╚══════════════════════════════════════════╝\n");
    }

    /**
     * Muestra y gestiona el menú principal.
     */
    private static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n════════ MENÚ PRINCIPAL ════════");
            System.out.println("1. Gestión de Vehículos");
            System.out.println("2. Gestión de Pasajeros");
            System.out.println("3. Venta de Tickets");
            System.out.println("4. Consultas y Reportes");
            System.out.println("5. Estadísticas");
            System.out.println("0. Salir");
            System.out.println("════════════════════════════════");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuVehiculos();
                    break;
                case 2:
                    menuPasajeros();
                    break;
                case 3:
                    menuVentaTickets();
                    break;
                case 4:
                    menuConsultas();
                    break;
                case 5:
                    mostrarEstadisticas();
                    break;
                case 0:
                    System.out.println("\n¡Gracias por usar el sistema Barceloneta!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // ========== MENÚ DE VEHÍCULOS ==========

    /**
     * Menú de gestión de vehículos.
     */
    private static void menuVehiculos() {
        int opcion;
        do {
            System.out.println("\n═══ GESTIÓN DE VEHÍCULOS ═══");
            System.out.println("1. Registrar Vehículo");
            System.out.println("2. Listar Vehículos");
            System.out.println("3. Buscar Vehículo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    buscarVehiculo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /**
     * Registra un nuevo vehículo.
     */
    private static void registrarVehiculo() {
        System.out.println("\n--- Registrar Vehículo ---");
        System.out.println("Tipo de vehículo:");
        System.out.println("1. Buseta (16-19 pasajeros)");
        System.out.println("2. MicroBus (20-30 pasajeros)");
        System.out.println("3. Bus (más de 30 pasajeros)");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = leerEntero();
        System.out.print("Modelo (año): ");
        String modelo = scanner.nextLine();
        System.out.print("Tarifa base ($/km): ");
        double tarifaBase = leerDouble();

        Vehiculo vehiculo = null;
        switch (tipo) {
            case 1:
                vehiculo = new Buseta(placa, capacidad, modelo, tarifaBase);
                break;
            case 2:
                vehiculo = new MicroBus(placa, capacidad, modelo, tarifaBase);
                break;
            case 3:
                vehiculo = new Bus(placa, capacidad, modelo, tarifaBase);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        if (gestor.registrarVehiculo(vehiculo)) {
            System.out.println("✓ Vehículo registrado exitosamente.");
        } else {
            System.out.println("✗ Error: Ya existe un vehículo con esa placa.");
        }
    }

    /**
     * Lista todos los vehículos registrados.
     */
    private static void listarVehiculos() {
        System.out.println("\n--- Lista de Vehículos ---");
        List<Vehiculo> vehiculos = gestor.listarVehiculos();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo v : vehiculos) {
                System.out.println(v);
            }
        }
    }

    /**
     * Busca un vehículo por placa.
     */
    private static void buscarVehiculo() {
        System.out.print("\nIngrese la placa: ");
        String placa = scanner.nextLine();
        Vehiculo vehiculo = gestor.buscarVehiculo(placa);
        if (vehiculo != null) {
            System.out.println("\nVehículo encontrado:");
            System.out.println(vehiculo);
        } else {
            System.out.println("✗ Vehículo no encontrado.");
        }
    }

    // ========== MENÚ DE PASAJEROS ==========

    /**
     * Menú de gestión de pasajeros.
     */
    private static void menuPasajeros() {
        int opcion;
        do {
            System.out.println("\n═══ GESTIÓN DE PASAJEROS ═══");
            System.out.println("1. Registrar Pasajero");
            System.out.println("2. Listar Pasajeros");
            System.out.println("3. Buscar Pasajero");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPasajero();
                    break;
                case 2:
                    listarPasajeros();
                    break;
                case 3:
                    buscarPasajero();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /**
     * Registra un nuevo pasajero.
     */
    private static void registrarPasajero() {
        System.out.println("\n--- Registrar Pasajero ---");
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.println("Tipo de pasajero:");
        System.out.println("1. Regular (0% descuento)");
        System.out.println("2. Estudiante (20% descuento)");
        System.out.println("3. Adulto Mayor (30% descuento)");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();

        TipoPasajero tipoPasajero = null;
        switch (tipo) {
            case 1:
                tipoPasajero = TipoPasajero.REGULAR;
                break;
            case 2:
                tipoPasajero = TipoPasajero.ESTUDIANTE;
                break;
            case 3:
                tipoPasajero = TipoPasajero.ADULTO_MAYOR;
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        Pasajero pasajero = new Pasajero(cedula, nombre, tipoPasajero);
        if (gestor.registrarPasajero(pasajero)) {
            System.out.println("✓ Pasajero registrado exitosamente.");
        } else {
            System.out.println("✗ Error: Ya existe un pasajero con esa cédula.");
        }
    }

    /**
     * Lista todos los pasajeros registrados.
     */
    private static void listarPasajeros() {
        System.out.println("\n--- Lista de Pasajeros ---");
        List<Pasajero> pasajeros = gestor.listarPasajeros();
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            for (Pasajero p : pasajeros) {
                System.out.println(p);
            }
        }
    }

    /**
     * Busca un pasajero por cédula.
     */
    private static void buscarPasajero() {
        System.out.print("\nIngrese la cédula: ");
        String cedula = scanner.nextLine();
        Pasajero pasajero = gestor.buscarPasajero(cedula);
        if (pasajero != null) {
            System.out.println("\nPasajero encontrado:");
            System.out.println(pasajero);
        } else {
            System.out.println("✗ Pasajero no encontrado.");
        }
    }

    // ========== MENÚ DE VENTA DE TICKETS ==========

    /**
     * Menú de venta de tickets.
     */
    private static void menuVentaTickets() {
        System.out.println("\n═══ VENTA DE TICKETS ═══");
        System.out.print("Cédula del pasajero: ");
        String cedula = scanner.nextLine();
        System.out.print("Placa del vehículo: ");
        String placa = scanner.nextLine();
        System.out.print("Ciudad de origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ciudad de destino: ");
        String destino = scanner.nextLine();
        System.out.print("Distancia (km): ");
        double distancia = leerDouble();

        Ticket ticket = gestor.venderTicket(cedula, placa, origen, destino, distancia);
        if (ticket != null) {
            System.out.println("\n✓ Ticket vendido exitosamente!");
            System.out.println(ticket.generarRecibo());
        } else {
            System.out.println("\n✗ No se pudo vender el ticket.");
        }
    }

    // ========== MENÚ DE CONSULTAS ==========

    /**
     * Menú de consultas y reportes.
     */
    private static void menuConsultas() {
        int opcion;
        do {
            System.out.println("\n═══ CONSULTAS Y REPORTES ═══");
            System.out.println("1. Listar Todos los Tickets");
            System.out.println("2. Tickets por Pasajero");
            System.out.println("3. Tickets por Vehículo");
            System.out.println("4. Buscar Ticket por ID");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    listarTodosTickets();
                    break;
                case 2:
                    listarTicketsPorPasajero();
                    break;
                case 3:
                    listarTicketsPorVehiculo();
                    break;
                case 4:
                    buscarTicketPorId();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /**
     * Lista todos los tickets vendidos.
     */
    private static void listarTodosTickets() {
        System.out.println("\n--- Lista de Tickets ---");
        List<Ticket> tickets = gestor.listarTickets();
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets vendidos.");
        } else {
            for (Ticket t : tickets) {
                System.out.println(t.imprimir());
            }
        }
    }

    /**
     * Lista tickets por pasajero.
     */
    private static void listarTicketsPorPasajero() {
        System.out.print("\nIngrese la cédula del pasajero: ");
        String cedula = scanner.nextLine();
        List<Ticket> tickets = gestor.listarTicketsPorPasajero(cedula);
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets para este pasajero.");
        } else {
            System.out.println("\n--- Tickets del Pasajero ---");
            for (Ticket t : tickets) {
                System.out.println(t.imprimir());
            }
        }
    }

    /**
     * Lista tickets por vehículo.
     */
    private static void listarTicketsPorVehiculo() {
        System.out.print("\nIngrese la placa del vehículo: ");
        String placa = scanner.nextLine();
        List<Ticket> tickets = gestor.listarTicketsPorVehiculo(placa);
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets para este vehículo.");
        } else {
            System.out.println("\n--- Tickets del Vehículo ---");
            for (Ticket t : tickets) {
                System.out.println(t.imprimir());
            }
        }
    }

    /**
     * Busca un ticket por su ID.
     */
    private static void buscarTicketPorId() {
        System.out.print("\nIngrese el ID del ticket: ");
        String id = scanner.nextLine();
        Ticket ticket = gestor.buscarTicket(id);
        if (ticket != null) {
            System.out.println(ticket.generarRecibo());
        } else {
            System.out.println("✗ Ticket no encontrado.");
        }
    }

    // ========== ESTADÍSTICAS ==========

    /**
     * Muestra estadísticas del sistema.
     */
    private static void mostrarEstadisticas() {
        System.out.println(gestor.obtenerEstadisticas());
    }

    // ========== MÉTODOS AUXILIARES ==========

    /**
     * Lee un número entero de forma segura.
     *
     * @return Número entero ingresado
     */
    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor ingrese un número válido: ");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return numero;
    }

    /**
     * Lee un número decimal de forma segura.
     *
     * @return Número decimal ingresado
     */
    private static double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Por favor ingrese un número válido: ");
            scanner.next();
        }
        double numero = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return numero;
    }
}
