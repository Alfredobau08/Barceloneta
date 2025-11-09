# Manual de Usuario - Sistema Barceloneta

**Sistema de Gestión de Tickets de Viaje**
**Versión:** 1.0-FASE2
**Fecha:** Noviembre 2025
**Autor:** Alfredo David Bautista Romero
**Universidad Popular del Cesar - POO II**

---

## Tabla de Contenidos

1. [Introducción](#introducción)
2. [Requisitos del Sistema](#requisitos-del-sistema)
3. [Instalación](#instalación)
4. [Inicio del Sistema](#inicio-del-sistema)
5. [Funcionalidades](#funcionalidades)
6. [Guía de Uso Paso a Paso](#guía-de-uso-paso-a-paso)
7. [Gestión de Archivos CSV](#gestión-de-archivos-csv)
8. [Resolución de Problemas](#resolución-de-problemas)
9. [Preguntas Frecuentes](#preguntas-frecuentes)

---

## Introducción

### ¿Qué es Sistema Barceloneta?

Sistema Barceloneta es una aplicación de consola para la gestión de venta de tickets de viaje. Permite administrar vehículos, pasajeros y la venta de tickets con cálculo automático de tarifas y descuentos.

### Características Principales

- ✅ Gestión completa de vehículos (Busetas, MicroBuses, Buses)
- ✅ Registro de pasajeros con diferentes tipos y descuentos
- ✅ Venta de tickets con cálculo automático de precios
- ✅ Persistencia de datos en archivos CSV
- ✅ Consultas y reportes detallados
- ✅ Estadísticas del sistema en tiempo real

### Ventajas

- **Fácil de usar**: Interfaz de menús intuitiva
- **Persistente**: Los datos no se pierden al cerrar
- **Portable**: Archivos CSV editables en Excel
- **Educativo**: Demuestra conceptos avanzados de POO

---

## Requisitos del Sistema

### Hardware Mínimo

- **Procesador:** Intel Core i3 o equivalente
- **Memoria RAM:** 2 GB mínimo (4 GB recomendado)
- **Espacio en disco:** 100 MB libres
- **Pantalla:** Resolución mínima 1024x768

### Software Requerido

- **Sistema Operativo:** Windows 10/11, macOS 10.14+, o Linux
- **Java:** JDK 11 o superior
- **Maven:** 3.6 o superior (opcional, para compilación)

### Verificar Instalación de Java

```bash
java -version
```

Debe mostrar algo como:
```
java version "11.0.x" o superior
```

---

## Instalación

### Opción 1: Usando JAR Precompilado

1. Descargar `barceloneta-1.0-FASE2.jar`
2. Colocarlo en una carpeta de su preferencia
3. Ejecutar desde consola:
   ```bash
   java -jar barceloneta-1.0-FASE2.jar
   ```

### Opción 2: Compilar desde Código Fuente

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Alfredobau08/Barceloneta.git
   cd Barceloneta
   ```

2. **Compilar con Maven:**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar:**
   ```bash
   mvn exec:java
   ```

### Opción 3: Compilación Manual

```bash
# Compilar todas las clases
javac -d target/classes -encoding UTF-8 src/main/java/com/upc/**/*.java

# Ejecutar
java -cp target/classes com.upc.presentacion.Main
```

---

## Inicio del Sistema

### Primera Ejecución

Al ejecutar el sistema por primera vez:

1. Se creará automáticamente la carpeta `data/`
2. Se generarán los archivos CSV vacíos
3. Se cargarán datos de prueba (opcional)

### Pantalla de Bienvenida

```
════════════════════════════════════════════════
   SISTEMA DE TICKETS BARCELONETA
   Universidad Popular del Cesar
   POO II - Fase 2 (Persistencia CSV)
════════════════════════════════════════════════

Datos de prueba cargados exitosamente.
```

### Estructura de Archivos

```
Barceloneta/
├── data/
│   ├── vehiculos.txt    ← Datos de vehículos
│   ├── pasajeros.txt    ← Datos de pasajeros
│   └── tickets.txt      ← Datos de tickets
└── barceloneta-1.0-FASE2.jar
```

---

## Funcionalidades

### 1. Gestión de Vehículos

**Tipos de vehículos disponibles:**

| Tipo | Capacidad | Multiplicador | Descuento |
|------|-----------|---------------|-----------|
| Buseta | 16-19 pasajeros | 1.0 | 0% |
| MicroBus | 20-30 pasajeros | 0.9 | 10% |
| Bus | 30+ pasajeros | 0.8 | 20% |

**Operaciones:**
- Registrar nuevo vehículo
- Buscar vehículo por placa
- Listar todos los vehículos

### 2. Gestión de Pasajeros

**Tipos de pasajeros:**

| Tipo | Descripción | Descuento |
|------|-------------|-----------|
| REGULAR | Pasajero sin descuento | 0% |
| ESTUDIANTE | Estudiante con carnet | 20% |
| ADULTO_MAYOR | Persona mayor de 60 años | 30% |

**Operaciones:**
- Registrar nuevo pasajero
- Buscar pasajero por cédula
- Listar todos los pasajeros

### 3. Venta de Tickets

**Información requerida:**
- Cédula del pasajero (debe estar registrado)
- Placa del vehículo (debe estar registrado)
- Ciudad de origen
- Ciudad de destino
- Distancia en kilómetros

**Cálculo automático:**
```
Precio Base = Tarifa Base × Distancia × Multiplicador del Vehículo
Descuento = Precio Base × Porcentaje de Descuento del Pasajero
PRECIO FINAL = Precio Base - Descuento
```

### 4. Consultas y Reportes

- Ver todos los tickets vendidos
- Buscar ticket por ID
- Ver tickets de un pasajero específico
- Ver tickets de un vehículo específico

### 5. Estadísticas

- Total de vehículos registrados
- Total de pasajeros registrados
- Total de tickets vendidos
- Ingresos totales acumulados

---

## Guía de Uso Paso a Paso

### Caso 1: Registrar un Nuevo Vehículo

1. **Iniciar el sistema**
   ```
   java -jar barceloneta-1.0-FASE2.jar
   ```

2. **Seleccionar opción 1** (Gestión de Vehículos)
   ```
   ════════ MENÚ PRINCIPAL ════════
   1. Gestión de Vehículos
   2. Gestión de Pasajeros
   3. Venta de Tickets
   4. Consultas y Reportes
   5. Estadísticas
   0. Salir
   ════════════════════════════════
   Seleccione una opción: 1
   ```

3. **Seleccionar opción 1** (Registrar vehículo)
   ```
   ════ GESTIÓN DE VEHÍCULOS ════
   1. Registrar vehículo
   2. Buscar vehículo
   3. Listar todos los vehículos
   0. Volver al menú principal
   ══════════════════════════════
   Seleccione una opción: 1
   ```

4. **Ingresar datos del vehículo**
   ```
   Tipo de vehículo:
   1. Buseta
   2. MicroBus
   3. Bus
   Seleccione: 1

   Placa del vehículo: ABC123
   Capacidad de pasajeros: 19
   Modelo (año): 2024
   Tarifa base por km: 2500
   ```

5. **Confirmación**
   ```
   ✓ Vehículo registrado exitosamente
   ```

### Caso 2: Registrar un Nuevo Pasajero

1. **Menú principal → Opción 2** (Gestión de Pasajeros)

2. **Opción 1** (Registrar pasajero)

3. **Ingresar datos**
   ```
   Cédula: 1000000001
   Nombre completo: Juan Pérez

   Tipo de pasajero:
   1. Regular (sin descuento)
   2. Estudiante (20% descuento)
   3. Adulto Mayor (30% descuento)
   Seleccione: 2
   ```

4. **Confirmación**
   ```
   ✓ Pasajero registrado exitosamente
   ```

### Caso 3: Vender un Ticket

1. **Menú principal → Opción 3** (Venta de Tickets)

2. **Ingresar información del viaje**
   ```
   Cédula del pasajero: 1000000001
   Placa del vehículo: ABC123
   Ciudad de origen: Valledupar
   Ciudad de destino: Barranquilla
   Distancia en kilómetros: 200
   ```

3. **El sistema muestra el recibo**
   ```
   ========================================
            TICKET DE VIAJE BARCELONETA
   ========================================

   Ticket No: TK-00001
   Fecha: 08/11/2025 10:30:45

   --- INFORMACIÓN DEL PASAJERO ---
   Nombre: Juan Pérez
   Cédula: 1000000001
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

4. **El ticket se guarda automáticamente**

### Caso 4: Consultar Tickets Vendidos

1. **Menú principal → Opción 4** (Consultas y Reportes)

2. **Opción 1** (Ver todos los tickets)

3. **El sistema muestra la lista**
   ```
   ═══ TICKETS VENDIDOS ═══

   1. Ticket #TK-00001 | Valledupar -> Barranquilla | Buseta | 200 km | $400000.00
   2. Ticket #TK-00002 | Santa Marta -> Cartagena | Bus | 250 km | $350000.00

   Total: 2 tickets
   ```

### Caso 5: Ver Estadísticas

1. **Menú principal → Opción 5** (Estadísticas)

2. **El sistema muestra las estadísticas**
   ```
   ═════ ESTADÍSTICAS DEL SISTEMA ═════
   Vehículos registrados: 5
   Pasajeros registrados: 8
   Tickets vendidos: 12
   Ingresos totales: $4500000.00
   ════════════════════════════════════
   ```

---

## Gestión de Archivos CSV

### Ubicación de Archivos

Los datos se almacenan en la carpeta `data/`:

```
data/
├── vehiculos.txt
├── pasajeros.txt
└── tickets.txt
```

### Formato de Archivos

#### vehiculos.txt

**Formato:** `TipoVehiculo,placa,capacidad,modelo,tarifaBase`

**Ejemplo:**
```csv
Buseta,ABC123,19,2024,2500.0
MicroBus,DEF456,25,2023,2500.0
Bus,GHI789,40,2022,2500.0
```

#### pasajeros.txt

**Formato:** `cedula,nombre,tipoPasajero`

**Ejemplo:**
```csv
1000000001,Juan Pérez,REGULAR
1000000002,María López,ESTUDIANTE
1000000003,Carlos Gómez,ADULTO_MAYOR
```

#### tickets.txt

**Formato:** `idTicket,placaVehiculo,cedulaPasajero,origen,destino,fecha,distanciaKm`

**Ejemplo:**
```csv
TK-00001,ABC123,1000000002,Valledupar,Barranquilla,2025-11-08T10:30:45,200.0
TK-00002,DEF456,1000000003,Santa Marta,Cartagena,2025-11-08T11:15:20,250.0
```

### Edición Manual

**⚠️ PRECAUCIÓN:** Si editas los archivos CSV manualmente:

1. **Mantén el formato exacto** (comas, sin espacios extra)
2. **Respeta los tipos de datos** (números sin símbolos de moneda)
3. **Usa codificación UTF-8** para caracteres especiales
4. **Cierra el sistema** antes de editar
5. **Haz respaldo** antes de modificar

### Respaldo de Datos

**Crear respaldo:**
```bash
# Copiar la carpeta data completa
cp -r data/ data_backup_$(date +%Y%m%d)
```

**Restaurar respaldo:**
```bash
# Restaurar desde respaldo
cp -r data_backup_20251108/ data/
```

---

## Resolución de Problemas

### Problema 1: "Error al crear archivo de vehículos"

**Causa:** No hay permisos de escritura en la carpeta

**Solución:**
```bash
# En Linux/Mac
chmod 755 data/

# En Windows: Clic derecho en la carpeta → Propiedades → Seguridad → Editar permisos
```

### Problema 2: "Pasajero no encontrado"

**Causa:** El pasajero no está registrado en el sistema

**Solución:**
1. Verificar que la cédula sea correcta
2. Registrar el pasajero primero (Opción 2 → Opción 1)

### Problema 3: "Formato CSV inválido"

**Causa:** El archivo CSV está corrupto o mal formateado

**Solución:**
1. Cerrar el sistema
2. Revisar el archivo en un editor de texto
3. Corregir el formato o restaurar desde respaldo
4. Reiniciar el sistema

### Problema 4: Caracteres extraños (�) en nombres

**Causa:** Problema de codificación

**Solución:**
```bash
# Ejecutar con codificación UTF-8 explícita
java -Dfile.encoding=UTF-8 -jar barceloneta-1.0-FASE2.jar
```

### Problema 5: "java: command not found"

**Causa:** Java no está instalado o no está en el PATH

**Solución:**
1. Instalar JDK 11 o superior
2. Configurar variable de entorno JAVA_HOME
3. Agregar Java al PATH del sistema

---

## Preguntas Frecuentes

### ¿Los datos se guardan automáticamente?

Sí, todos los datos se guardan automáticamente en archivos CSV después de cada operación (crear, actualizar, eliminar).

### ¿Puedo abrir los archivos CSV en Excel?

Sí, los archivos CSV son totalmente compatibles con Excel, LibreOffice Calc, y Google Sheets.

### ¿Qué pasa si cierro el sistema sin guardar?

No hay problema. Los datos se guardan automáticamente en cada operación, no hay botón de "Guardar".

### ¿Puedo tener varios vehículos con la misma placa?

No, las placas son identificadores únicos. El sistema no permite duplicados.

### ¿Cuál es la distancia máxima permitida?

No hay límite técnico, pero se recomienda usar distancias realistas (hasta 1000 km).

### ¿Puedo eliminar tickets ya vendidos?

Técnicamente sí (editando el archivo CSV), pero no se recomienda por temas de auditoría.

### ¿El sistema funciona sin internet?

Sí, el sistema es 100% offline. No requiere conexión a internet.

### ¿Puedo usar el sistema en varios computadores?

Sí, solo necesitas copiar la carpeta `data/` al otro computador para mantener los datos sincronizados.

### ¿Cómo reseteo el sistema a datos de fábrica?

```bash
# Eliminar la carpeta data
rm -rf data/

# Al reiniciar, se crearán archivos vacíos y se cargarán datos de prueba
```

---

## Soporte y Contacto

**Proyecto Educativo**
Universidad Popular del Cesar
Programación Orientada a Objetos II

**Autor:** Alfredo David Bautista Romero

**Repositorio:** https://github.com/Alfredobau08/Barceloneta

**Reportar Problemas:**
Crear un issue en GitHub con descripción detallada del problema.

---

## Licencia

Proyecto educativo para fines académicos.
Universidad Popular del Cesar © 2025

---

## Historial de Versiones

| Versión | Fecha | Cambios |
|---------|-------|---------|
| 1.0-FASE1 | Oct 2025 | Versión inicial con almacenamiento en memoria |
| 1.0-FASE2 | Nov 2025 | Persistencia en archivos CSV, codificación UTF-8 |

---

**Última actualización:** Noviembre 2025
**Versión del manual:** 1.0
