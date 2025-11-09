# Diagramas UML - Sistema Barceloneta

**Sistema de Gestión de Tickets de Viaje**
**Versión:** 1.0-FASE2
**Autor:** Alfredo David Bautista Romero
**Universidad Popular del Cesar - POO II**

---

## Tabla de Contenidos

1. [Diagrama de Clases](#diagrama-de-clases)
2. [Diagrama de Casos de Uso](#diagrama-de-casos-de-uso)
3. [Diagrama de Secuencia](#diagrama-de-secuencia)
4. [Diagrama de Componentes](#diagrama-de-componentes)
5. [Diagrama de Actividad](#diagrama-de-actividad)

---

## Diagrama de Clases

### Capa de Modelo

```mermaid
classDiagram
    class Calculable {
        <<interface>>
        +calcularPrecio() double
        +aplicarDescuento(double porcentaje) double
    }

    class Imprimible {
        <<interface>>
        +imprimir() String
        +generarRecibo() String
    }

    class TipoPasajero {
        <<enumeration>>
        REGULAR
        ESTUDIANTE
        ADULTO_MAYOR
        -descripcion String
        -porcentajeDescuento double
        +getDescripcion() String
        +getPorcentajeDescuento() double
    }

    class Vehiculo {
        <<abstract>>
        #placa String
        #capacidad int
        #modelo String
        #tarifaBase double
        +Vehiculo()
        +Vehiculo(placa, capacidad, modelo, tarifaBase)
        +calcularTarifaFinal(distanciaKm)* double
        +getPlaca() String
        +getCapacidad() int
        +getModelo() String
        +getTarifaBase() double
        +getTipoVehiculo() String
    }

    class Buseta {
        -MULTIPLICADOR_TARIFA double = 1.0
        +Buseta()
        +Buseta(placa, capacidad, modelo, tarifaBase)
        +calcularTarifaFinal(distanciaKm) double
    }

    class MicroBus {
        -MULTIPLICADOR_TARIFA double = 0.9
        +MicroBus()
        +MicroBus(placa, capacidad, modelo, tarifaBase)
        +calcularTarifaFinal(distanciaKm) double
    }

    class Bus {
        -MULTIPLICADOR_TARIFA double = 0.8
        +Bus()
        +Bus(placa, capacidad, modelo, tarifaBase)
        +calcularTarifaFinal(distanciaKm) double
    }

    class Pasajero {
        -cedula String
        -nombre String
        -tipoPasajero TipoPasajero
        +Pasajero()
        +Pasajero(cedula, nombre, tipoPasajero)
        +getDescuento() double
        +getCedula() String
        +getNombre() String
        +getTipoPasajero() TipoPasajero
    }

    class Ticket {
        -idTicket String
        -vehiculo Vehiculo
        -pasajero Pasajero
        -origen String
        -destino String
        -fecha LocalDateTime
        -distanciaKm double
        +Ticket()
        +Ticket(idTicket, vehiculo, pasajero, origen, destino, distanciaKm)
        +calcularPrecio() double
        +aplicarDescuento(porcentaje) double
        +calcularPrecioFinal() double
        +imprimir() String
        +generarRecibo() String
    }

    Vehiculo <|-- Buseta
    Vehiculo <|-- MicroBus
    Vehiculo <|-- Bus
    Pasajero --> TipoPasajero
    Ticket --> Vehiculo
    Ticket --> Pasajero
    Ticket ..|> Calculable
    Ticket ..|> Imprimible
```

### Capa de Persistencia

```mermaid
classDiagram
    class VehiculoDAO {
        <<singleton>>
        -vehiculos Map~String, Vehiculo~
        -instancia VehiculoDAO
        -ARCHIVO_VEHICULOS String
        -VehiculoDAO()
        +getInstancia()$ VehiculoDAO
        +guardar(vehiculo) boolean
        +buscarPorPlaca(placa) Vehiculo
        +obtenerTodos() List~Vehiculo~
        +actualizar(vehiculo) boolean
        +eliminar(placa) boolean
        +existe(placa) boolean
        +contarVehiculos() int
        +limpiar() void
        -cargarDesdeArchivo() void
        -guardarEnArchivo() void
    }

    class PasajeroDAO {
        <<singleton>>
        -pasajeros Map~String, Pasajero~
        -instancia PasajeroDAO
        -ARCHIVO_PASAJEROS String
        -PasajeroDAO()
        +getInstancia()$ PasajeroDAO
        +guardar(pasajero) boolean
        +buscarPorCedula(cedula) Pasajero
        +obtenerTodos() List~Pasajero~
        +actualizar(pasajero) boolean
        +eliminar(cedula) boolean
        +existe(cedula) boolean
        +contarPasajeros() int
        +limpiar() void
        -cargarDesdeArchivo() void
        -guardarEnArchivo() void
    }

    class TicketDAO {
        <<singleton>>
        -tickets Map~String, Ticket~
        -contadorId int
        -instancia TicketDAO
        -ARCHIVO_TICKETS String
        -TicketDAO()
        +getInstancia()$ TicketDAO
        +generarId() String
        +guardar(ticket) boolean
        +buscarPorId(idTicket) Ticket
        +obtenerTodos() List~Ticket~
        +buscarPorPasajero(cedula) List~Ticket~
        +buscarPorVehiculo(placa) List~Ticket~
        +actualizar(ticket) boolean
        +eliminar(idTicket) boolean
        +existe(idTicket) boolean
        +contarTickets() int
        +limpiar() void
        -cargarDesdeArchivo() void
        -guardarEnArchivo() void
    }

    class SerializadorVehiculo {
        -SEPARADOR String
        +serializar(vehiculo)$ String
        +deserializar(lineaCSV)$ Vehiculo
    }

    class SerializadorPasajero {
        -SEPARADOR String
        +serializar(pasajero)$ String
        +deserializar(lineaCSV)$ Pasajero
    }

    class SerializadorTicket {
        -SEPARADOR String
        -FORMATTER_FECHA DateTimeFormatter
        +serializar(ticket)$ String
        +deserializar(lineaCSV, vehiculoDAO, pasajeroDAO)$ Ticket
    }

    VehiculoDAO --> SerializadorVehiculo
    PasajeroDAO --> SerializadorPasajero
    TicketDAO --> SerializadorTicket
    TicketDAO --> VehiculoDAO
    TicketDAO --> PasajeroDAO
```

### Capa de Lógica y Presentación

```mermaid
classDiagram
    class GestorTickets {
        <<facade>>
        -vehiculoDAO VehiculoDAO
        -pasajeroDAO PasajeroDAO
        -ticketDAO TicketDAO
        +GestorTickets()
        +registrarVehiculo(vehiculo) boolean
        +buscarVehiculo(placa) Vehiculo
        +listarVehiculos() List~Vehiculo~
        +registrarPasajero(pasajero) boolean
        +buscarPasajero(cedula) Pasajero
        +listarPasajeros() List~Pasajero~
        +venderTicket(cedulaPasajero, placaVehiculo, origen, destino, distanciaKm) Ticket
        +buscarTicket(idTicket) Ticket
        +listarTickets() List~Ticket~
        +listarTicketsPorPasajero(cedula) List~Ticket~
        +listarTicketsPorVehiculo(placa) List~Ticket~
        +calcularIngresosTotal() double
        +obtenerEstadisticas() String
        +cargarDatosPrueba() void
    }

    class Main {
        -gestor GestorTickets
        -scanner Scanner
        +main(args)$ void
        -menuPrincipal() void
        -menuVehiculos() void
        -menuPasajeros() void
        -menuTickets() void
        -menuConsultas() void
        -mostrarEstadisticas() void
        -leerEntero() int
        -leerTexto() String
        -leerDouble() double
    }

    Main --> GestorTickets
    GestorTickets --> VehiculoDAO
    GestorTickets --> PasajeroDAO
    GestorTickets --> TicketDAO
```

---

## Diagrama de Casos de Uso

```mermaid
graph TB
    subgraph "Sistema Barceloneta"
        UC1[Registrar Vehículo]
        UC2[Buscar Vehículo]
        UC3[Listar Vehículos]
        UC4[Registrar Pasajero]
        UC5[Buscar Pasajero]
        UC6[Listar Pasajeros]
        UC7[Vender Ticket]
        UC8[Consultar Tickets]
        UC9[Ver Estadísticas]
        UC10[Generar Recibo]
        UC11[Persistir Datos]
    end

    Usuario((Usuario/Operador))

    Usuario --> UC1
    Usuario --> UC2
    Usuario --> UC3
    Usuario --> UC4
    Usuario --> UC5
    Usuario --> UC6
    Usuario --> UC7
    Usuario --> UC8
    Usuario --> UC9

    UC7 --> UC10
    UC7 -.include.-> UC11
    UC1 -.include.-> UC11
    UC4 -.include.-> UC11

    UC7 -.extend.-> UC5
    UC7 -.extend.-> UC2
```

**Descripción de Casos de Uso:**

| ID | Caso de Uso | Descripción |
|----|-------------|-------------|
| UC1 | Registrar Vehículo | El usuario registra un nuevo vehículo en el sistema |
| UC2 | Buscar Vehículo | El usuario busca un vehículo por placa |
| UC3 | Listar Vehículos | El usuario visualiza todos los vehículos registrados |
| UC4 | Registrar Pasajero | El usuario registra un nuevo pasajero |
| UC5 | Buscar Pasajero | El usuario busca un pasajero por cédula |
| UC6 | Listar Pasajeros | El usuario visualiza todos los pasajeros |
| UC7 | Vender Ticket | El usuario vende un ticket de viaje |
| UC8 | Consultar Tickets | El usuario consulta tickets vendidos |
| UC9 | Ver Estadísticas | El usuario visualiza estadísticas del sistema |
| UC10 | Generar Recibo | El sistema genera un recibo de compra |
| UC11 | Persistir Datos | El sistema guarda datos en archivos CSV |

---

## Diagrama de Secuencia

### Secuencia: Vender Ticket

```mermaid
sequenceDiagram
    actor Usuario
    participant Main
    participant GestorTickets
    participant PasajeroDAO
    participant VehiculoDAO
    participant TicketDAO
    participant Ticket
    participant CSV as Archivo CSV

    Usuario->>Main: Seleccionar "Vender Ticket"
    Main->>Usuario: Solicitar cédula pasajero
    Usuario->>Main: Ingresar cédula
    Main->>GestorTickets: venderTicket(cedula, placa, origen, destino, distancia)

    GestorTickets->>PasajeroDAO: buscarPorCedula(cedula)
    PasajeroDAO-->>GestorTickets: pasajero

    alt Pasajero no existe
        GestorTickets-->>Main: null (Error)
        Main-->>Usuario: "Pasajero no encontrado"
    else Pasajero existe
        GestorTickets->>VehiculoDAO: buscarPorPlaca(placa)
        VehiculoDAO-->>GestorTickets: vehiculo

        alt Vehículo no existe
            GestorTickets-->>Main: null (Error)
            Main-->>Usuario: "Vehículo no encontrado"
        else Vehículo existe
            GestorTickets->>TicketDAO: generarId()
            TicketDAO-->>GestorTickets: "TK-00001"

            GestorTickets->>Ticket: new Ticket(id, vehiculo, pasajero, origen, destino, distancia)
            Ticket-->>GestorTickets: ticket

            GestorTickets->>TicketDAO: guardar(ticket)
            TicketDAO->>CSV: escribir línea CSV
            CSV-->>TicketDAO: confirmación
            TicketDAO-->>GestorTickets: true

            GestorTickets-->>Main: ticket
            Main->>Ticket: generarRecibo()
            Ticket-->>Main: recibo (String)
            Main-->>Usuario: Mostrar recibo
        end
    end
```

### Secuencia: Cargar Datos al Iniciar

```mermaid
sequenceDiagram
    participant Main
    participant GestorTickets
    participant VehiculoDAO
    participant PasajeroDAO
    participant TicketDAO
    participant CSV_V as vehiculos.txt
    participant CSV_P as pasajeros.txt
    participant CSV_T as tickets.txt

    Main->>GestorTickets: new GestorTickets()
    GestorTickets->>VehiculoDAO: getInstancia()

    VehiculoDAO->>VehiculoDAO: cargarDesdeArchivo()
    VehiculoDAO->>CSV_V: leer archivo
    CSV_V-->>VehiculoDAO: líneas CSV

    loop Por cada línea
        VehiculoDAO->>VehiculoDAO: deserializar(linea)
        VehiculoDAO->>VehiculoDAO: agregar a HashMap
    end

    VehiculoDAO-->>GestorTickets: instancia DAO

    GestorTickets->>PasajeroDAO: getInstancia()
    PasajeroDAO->>PasajeroDAO: cargarDesdeArchivo()
    PasajeroDAO->>CSV_P: leer archivo
    CSV_P-->>PasajeroDAO: líneas CSV
    PasajeroDAO-->>GestorTickets: instancia DAO

    GestorTickets->>TicketDAO: getInstancia()
    TicketDAO->>TicketDAO: cargarDesdeArchivo()
    TicketDAO->>CSV_T: leer archivo
    CSV_T-->>TicketDAO: líneas CSV

    loop Por cada línea
        TicketDAO->>VehiculoDAO: buscarPorPlaca(placa)
        VehiculoDAO-->>TicketDAO: vehiculo
        TicketDAO->>PasajeroDAO: buscarPorCedula(cedula)
        PasajeroDAO-->>TicketDAO: pasajero
        TicketDAO->>TicketDAO: crear Ticket con referencias
    end

    TicketDAO-->>GestorTickets: instancia DAO
    GestorTickets-->>Main: gestor listo
```

---

## Diagrama de Componentes

### Arquitectura en 3 Capas

```mermaid
graph TB
    subgraph "Capa de Presentación"
        Main[Main.java<br/>Interfaz de Usuario]
    end

    subgraph "Capa de Lógica de Negocio"
        Gestor[GestorTickets.java<br/>Facade Pattern]
    end

    subgraph "Capa de Modelo"
        Vehiculo[Vehiculo<br/>Buseta, MicroBus, Bus]
        Pasajero[Pasajero<br/>TipoPasajero]
        Ticket[Ticket<br/>Calculable, Imprimible]
    end

    subgraph "Capa de Persistencia"
        VDAO[VehiculoDAO<br/>Singleton]
        PDAO[PasajeroDAO<br/>Singleton]
        TDAO[TicketDAO<br/>Singleton]
        SerV[SerializadorVehiculo]
        SerP[SerializadorPasajero]
        SerT[SerializadorTicket]
    end

    subgraph "Almacenamiento"
        CSV_V[(vehiculos.txt)]
        CSV_P[(pasajeros.txt)]
        CSV_T[(tickets.txt)]
    end

    Main --> Gestor
    Gestor --> VDAO
    Gestor --> PDAO
    Gestor --> TDAO

    VDAO --> Vehiculo
    PDAO --> Pasajero
    TDAO --> Ticket

    VDAO --> SerV
    PDAO --> SerP
    TDAO --> SerT

    SerV --> CSV_V
    SerP --> CSV_P
    SerT --> CSV_T

    style Main fill:#e1f5ff
    style Gestor fill:#fff4e1
    style Vehiculo fill:#f0f0f0
    style Pasajero fill:#f0f0f0
    style Ticket fill:#f0f0f0
    style VDAO fill:#e8f5e9
    style PDAO fill:#e8f5e9
    style TDAO fill:#e8f5e9
    style CSV_V fill:#fff9c4
    style CSV_P fill:#fff9c4
    style CSV_T fill:#fff9c4
```

### Patrones de Diseño

```mermaid
graph LR
    subgraph "Patrones Implementados"
        Singleton[Singleton<br/>VehiculoDAO, PasajeroDAO, TicketDAO]
        Facade[Facade<br/>GestorTickets]
        DAO[DAO Pattern<br/>Separación de persistencia]
        Strategy[Strategy<br/>Calculable, Imprimible]
    end

    Singleton -.-> |"Instancia única"| Facade
    Facade -.-> |"Interfaz simplificada"| DAO
    DAO -.-> |"Acceso a datos"| Strategy
```

---

## Diagrama de Actividad

### Actividad: Proceso de Venta de Ticket

```mermaid
flowchart TD
    Start([Inicio: Vender Ticket]) --> Input1[Solicitar cédula del pasajero]
    Input1 --> Check1{¿Pasajero<br/>existe?}

    Check1 -->|No| Error1[Mostrar error:<br/>'Pasajero no encontrado']
    Error1 --> End1([Fin])

    Check1 -->|Sí| Input2[Solicitar placa del vehículo]
    Input2 --> Check2{¿Vehículo<br/>existe?}

    Check2 -->|No| Error2[Mostrar error:<br/>'Vehículo no encontrado']
    Error2 --> End1

    Check2 -->|Sí| Input3[Solicitar: origen, destino, distancia]
    Input3 --> Check3{¿Distancia<br/>> 0?}

    Check3 -->|No| Error3[Mostrar error:<br/>'Distancia inválida']
    Error3 --> End1

    Check3 -->|Sí| Calc1[Generar ID único del ticket]
    Calc1 --> Calc2[Crear objeto Ticket]
    Calc2 --> Calc3[Calcular precio base<br/>tarifaBase × distancia × multiplicador]
    Calc3 --> Calc4[Aplicar descuento del pasajero<br/>según tipo]
    Calc4 --> Calc5[Calcular precio final]
    Calc5 --> Save[Guardar ticket en TicketDAO]
    Save --> Persist[Sincronizar con archivo CSV]
    Persist --> Receipt[Generar recibo detallado]
    Receipt --> Display[Mostrar recibo al usuario]
    Display --> Stats[Actualizar estadísticas]
    Stats --> End2([Fin: Ticket vendido exitosamente])

    style Start fill:#e3f2fd
    style End1 fill:#ffebee
    style End2 fill:#e8f5e9
    style Error1 fill:#ffcdd2
    style Error2 fill:#ffcdd2
    style Error3 fill:#ffcdd2
    style Check1 fill:#fff9c4
    style Check2 fill:#fff9c4
    style Check3 fill:#fff9c4
    style Persist fill:#c8e6c9
```

### Actividad: Persistencia Automática

```mermaid
flowchart TD
    Start([Operación CRUD solicitada]) --> Type{Tipo de<br/>operación}

    Type -->|CREATE| Create[Agregar a HashMap]
    Type -->|UPDATE| Update[Actualizar en HashMap]
    Type -->|DELETE| Delete[Remover de HashMap]
    Type -->|READ| Read[Leer de HashMap]

    Create --> Write[Llamar guardarEnArchivo]
    Update --> Write
    Delete --> Write
    Read --> Return[Retornar datos]

    Write --> Open[Abrir archivo CSV en modo escritura]
    Open --> Clear[Limpiar contenido del archivo]
    Clear --> Loop{¿Hay más<br/>elementos?}

    Loop -->|Sí| Serialize[Serializar elemento a CSV]
    Serialize --> WriteLine[Escribir línea en archivo]
    WriteLine --> Loop

    Loop -->|No| Close[Cerrar archivo]
    Close --> Return

    Return --> Success{¿Operación<br/>exitosa?}
    Success -->|Sí| End1([Fin: Datos persistidos])
    Success -->|No| Error[Lanzar RuntimeException]
    Error --> End2([Fin: Error])

    style Start fill:#e3f2fd
    style End1 fill:#e8f5e9
    style End2 fill:#ffebee
    style Write fill:#fff9c4
    style Serialize fill:#c8e6c9
```

---

## Relaciones entre Diagramas

### Trazabilidad

| Componente | Diagrama de Clases | Diagrama de Secuencia | Diagrama de Componentes |
|------------|-------------------|----------------------|------------------------|
| GestorTickets | Clase Facade | Actor principal | Capa de Lógica |
| VehiculoDAO | Clase Singleton | Participante | Capa de Persistencia |
| PasajeroDAO | Clase Singleton | Participante | Capa de Persistencia |
| TicketDAO | Clase Singleton | Participante | Capa de Persistencia |
| Serializadores | Clases estáticas | - | Capa de Persistencia |
| Archivos CSV | - | Participante | Almacenamiento |

---

## Conceptos POO Demostrados

### 1. Herencia

```mermaid
graph TD
    Vehiculo[Vehiculo<br/>clase abstracta] --> Buseta
    Vehiculo --> MicroBus
    Vehiculo --> Bus

    style Vehiculo fill:#ffeb3b
    style Buseta fill:#8bc34a
    style MicroBus fill:#8bc34a
    style Bus fill:#8bc34a
```

### 2. Polimorfismo

```mermaid
flowchart LR
    subgraph "Llamada polimórfica"
        Call[vehiculo.calcularTarifaFinal] --> Runtime{Tipo en<br/>runtime}
        Runtime -->|Buseta| Impl1[tarifaBase × distancia × 1.0]
        Runtime -->|MicroBus| Impl2[tarifaBase × distancia × 0.9]
        Runtime -->|Bus| Impl3[tarifaBase × distancia × 0.8]
    end
```

### 3. Interfaces

```mermaid
classDiagram
    class Calculable {
        <<interface>>
        +calcularPrecio()
        +aplicarDescuento()
    }

    class Imprimible {
        <<interface>>
        +imprimir()
        +generarRecibo()
    }

    class Ticket {
        +calcularPrecio()
        +aplicarDescuento()
        +imprimir()
        +generarRecibo()
    }

    Calculable <|.. Ticket
    Imprimible <|.. Ticket
```

---

## Notas de Implementación

### Convenciones Usadas en los Diagramas

| Símbolo | Significado |
|---------|-------------|
| `+` | public |
| `-` | private |
| `#` | protected |
| `*` | método abstracto |
| `$` | método estático |
| `<<interface>>` | interfaz |
| `<<abstract>>` | clase abstracta |
| `<<singleton>>` | patrón singleton |
| `<<facade>>` | patrón facade |

### Colores en Diagramas

- 🔵 **Azul**: Capa de Presentación
- 🟡 **Amarillo**: Capa de Lógica
- ⚪ **Gris**: Capa de Modelo
- 🟢 **Verde**: Capa de Persistencia
- 🟨 **Amarillo claro**: Almacenamiento

---

## Referencias

- **Documentación oficial:** README.md
- **Manual de usuario:** MANUAL_USUARIO.md
- **Pruebas:** PRUEBAS_FASE2.md
- **Repositorio:** https://github.com/Alfredobau08/Barceloneta

---

**Última actualización:** Noviembre 2025
**Versión:** 1.0-FASE2
**Herramienta:** Mermaid Chart
