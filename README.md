# Sistema Barceloneta - Gestión de Tickets de Viaje

## Descripción del Proyecto

Sistema educativo de gestión de venta de tickets de viaje desarrollado para la asignatura **Programación Orientada a Objetos II** de la **Universidad Popular del Cesar**.

El proyecto demuestra conceptos avanzados de POO incluyendo:
- Herencia con clases abstractas
- Implementación de interfaces
- Polimorfismo
- Arquitectura en capas (3-tier architecture)
- Patrones de diseño (DAO, Singleton, Facade)

## Fases del Proyecto

### Fase 1 (Actual) - Consola + Memoria
- Interfaz de usuario por consola
- Almacenamiento en memoria (HashMap)
- Sistema completamente funcional

### Fase 2 (Próxima)
- Interfaz de usuario por terminal
- Persistencia en archivos de texto

### Fase 3 (Final)
- Interfaz gráfica (Swing/JavaFX)
- Persistencia en archivos de texto

## Arquitectura del Sistema

### Capa de Modelo (`com.upc.modelo`)
Clases de negocio que representan las entidades del sistema.

**Interfaces:**
- `Calculable` - Define métodos para cálculo de precios y descuentos
- `Imprimible` - Define métodos para generar representaciones imprimibles

**Enumeraciones:**
- `TipoPasajero` - Define tipos de pasajeros y sus descuentos (REGULAR, ESTUDIANTE, ADULTO_MAYOR)

**Jerarquía de Vehículos:**
```
Vehiculo (abstracta)
├── Buseta (tarifa base × 1.0)
├── MicroBus (tarifa base × 0.9)
└── Bus (tarifa base × 0.8)
```

**Otras Clases:**
- `Pasajero` - Información del pasajero
- `Ticket` - Implementa Calculable e Imprimible

### Capa de Persistencia (`com.upc.persistencia`)
Clases DAO (Data Access Object) para manejo de datos.

- `VehiculoDAO` - CRUD de vehículos
- `PasajeroDAO` - CRUD de pasajeros
- `TicketDAO` - CRUD de tickets

**Patrón Singleton:** Todos los DAOs implementan el patrón Singleton para garantizar una única instancia.

### Capa de Lógica (`com.upc.logica`)
Contiene la lógica de negocio del sistema.

- `GestorTickets` - Clase facade que coordina las operaciones entre capas

### Capa de Presentación (`com.upc.presentacion`)
Interfaz de usuario.

- `Main` - Punto de entrada con menús interactivos por consola

## Conceptos POO Demostrados

### 1. Herencia y Abstracción
La clase abstracta `Vehiculo` define el contrato básico, y las subclases concretas (`Buseta`, `MicroBus`, `Bus`) implementan el método abstracto `calcularTarifaFinal()` con diferentes lógicas.

```java
public abstract class Vehiculo {
    public abstract double calcularTarifaFinal(double distanciaKm);
}
```

### 2. Interfaces
`Ticket` implementa dos interfaces:

```java
public class Ticket implements Calculable, Imprimible {
    @Override
    public double calcularPrecio() { ... }

    @Override
    public String imprimir() { ... }
}
```

### 3. Polimorfismo
El mismo método `calcularTarifaFinal()` produce resultados diferentes según el tipo de vehículo:

```java
Vehiculo v1 = new Buseta(...);  // 100%
Vehiculo v2 = new MicroBus(...); // 90%
Vehiculo v3 = new Bus(...);      // 80%
```

### 4. Enumeraciones con Comportamiento
```java
public enum TipoPasajero {
    REGULAR("Regular", 0.0),
    ESTUDIANTE("Estudiante", 20.0),
    ADULTO_MAYOR("Adulto Mayor", 30.0);
}
```

## Requisitos

- **Java:** JDK 11 o superior
- **Maven:** 3.6 o superior
- **IDE recomendado:** IntelliJ IDEA, Eclipse o NetBeans

## Instalación y Ejecución

### Opción 1: Con Maven

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java

# Generar JAR ejecutable
mvn clean package

# Ejecutar el JAR
java -jar target/barceloneta-1.0-FASE1.jar
```

### Opción 2: Con IDE

1. Importar el proyecto como proyecto Maven
2. Ejecutar la clase `com.upc.presentacion.Main`

### Opción 3: Compilación Manual

```bash
# Compilar
javac -d bin -sourcepath src/main/java src/main/java/com/upc/presentacion/Main.java

# Ejecutar
java -cp bin com.upc.presentacion.Main
```

## Generar Javadoc

```bash
mvn javadoc:javadoc
```

El Javadoc se generará en `target/site/apidocs/`

## Estructura del Proyecto

```
Barceloneta/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── upc/
│   │   │           ├── modelo/
│   │   │           │   ├── Calculable.java
│   │   │           │   ├── Imprimible.java
│   │   │           │   ├── TipoPasajero.java
│   │   │           │   ├── Vehiculo.java
│   │   │           │   ├── Buseta.java
│   │   │           │   ├── MicroBus.java
│   │   │           │   ├── Bus.java
│   │   │           │   ├── Pasajero.java
│   │   │           │   └── Ticket.java
│   │   │           ├── persistencia/
│   │   │           │   ├── VehiculoDAO.java
│   │   │           │   ├── PasajeroDAO.java
│   │   │           │   └── TicketDAO.java
│   │   │           ├── logica/
│   │   │           │   └── GestorTickets.java
│   │   │           └── presentacion/
│   │   │               └── Main.java
│   │   └── resources/
│   └── test/
│       └── java/
├── pom.xml
├── .gitignore
└── README.md
```

## Uso del Sistema

El sistema inicia con datos de prueba precargados:

**Vehículos:**
- Buseta ABC123 (19 pasajeros, $2500/km)
- MicroBus DEF456 (25 pasajeros, $2500/km)
- Bus GHI789 (40 pasajeros, $2500/km)

**Pasajeros:**
- Juan Pérez (Regular, 0% desc)
- María López (Estudiante, 20% desc)
- Carlos Gómez (Adulto Mayor, 30% desc)

### Menú Principal

1. **Gestión de Vehículos** - Registrar, listar y buscar vehículos
2. **Gestión de Pasajeros** - Registrar, listar y buscar pasajeros
3. **Venta de Tickets** - Procesar venta y generar ticket
4. **Consultas y Reportes** - Ver tickets vendidos
5. **Estadísticas** - Resumen del sistema

## Ejemplo de Venta de Ticket

```
Cédula del pasajero: 1000000002
Placa del vehículo: ABC123
Ciudad de origen: Valledupar
Ciudad de destino: Barranquilla
Distancia (km): 200

========================================
         TICKET DE VIAJE BARCELONETA
========================================

Ticket No: TK-00001
Fecha: 31/10/2025 10:30:45

--- INFORMACIÓN DEL PASAJERO ---
Nombre: María López
Cédula: 1000000002
Tipo: Estudiante

--- INFORMACIÓN DEL VIAJE ---
Origen: Valledupar
Destino: Barranquilla
Distancia: 200.00 km

--- INFORMACIÓN DEL VEHÍCULO ---
Tipo: Buseta
Placa: ABC123
Capacidad: 19 pasajeros

--- DESGLOSE DE PRECIOS ---
Precio base: $500000.00
Descuento (20%): -$100000.00
----------------------------------------
TOTAL A PAGAR: $400000.00
========================================
     ¡Gracias por viajar con nosotros!
========================================
```

## Cálculo de Tarifas

### Fórmula Base
```
Precio Base = Tarifa Base × Distancia × Multiplicador del Vehículo
```

### Multiplicadores por Tipo de Vehículo
- **Buseta:** 1.0 (sin modificación)
- **MicroBus:** 0.9 (10% descuento)
- **Bus:** 0.8 (20% descuento)

### Descuentos por Tipo de Pasajero
- **Regular:** 0%
- **Estudiante:** 20%
- **Adulto Mayor:** 30%

### Ejemplo de Cálculo
```
Vehículo: Buseta (tarifa base $2500/km, multiplicador 1.0)
Distancia: 200 km
Pasajero: Estudiante (20% descuento)

Precio Base = $2500 × 200 × 1.0 = $500,000
Descuento = $500,000 × 20% = $100,000
PRECIO FINAL = $400,000
```

## Patrones de Diseño Implementados

### 1. DAO (Data Access Object)
Separa la lógica de acceso a datos del resto de la aplicación.

### 2. Singleton
Garantiza una única instancia de cada DAO.

### 3. Facade
`GestorTickets` actúa como fachada simplificando el acceso a múltiples DAOs.

### 4. Strategy
Las interfaces `Calculable` e `Imprimible` permiten diferentes estrategias de cálculo e impresión.

## Autor

**Alfredo David Bautista Romero**
Universidad Popular del Cesar
Programación Orientada a Objetos II

## Versión

**1.0-FASE1** - Sistema funcional por consola con almacenamiento en memoria

## Licencia

Proyecto educativo - Universidad Popular del Cesar