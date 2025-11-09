# Índice de Documentación - Sistema Barceloneta

**Sistema de Gestión de Tickets de Viaje**
**Versión:** 1.0-FASE2
**Universidad Popular del Cesar - POO II**

---

## 📚 Documentación Disponible

Esta es la documentación completa del proyecto Sistema Barceloneta. Todos los documentos están en formato Markdown para fácil lectura en GitHub.

### 📖 Documentos Principales

| Documento | Descripción | Tamaño | Enlace |
|-----------|-------------|--------|--------|
| **README.md** | Descripción general del proyecto, instalación y uso básico | 11 KB | [Ver](README.md) |
| **MANUAL_USUARIO.md** | Manual completo para usuarios finales | 14 KB | [Ver](MANUAL_USUARIO.md) |
| **DIAGRAMAS_UML.md** | Diagramas UML en formato Mermaid | 19 KB | [Ver](DIAGRAMAS_UML.md) |
| **PRUEBAS_FASE2.md** | Resultados de pruebas de la Fase 2 | 5.6 KB | [Ver](PRUEBAS_FASE2.md) |
| **DOCUMENTACION_INDEX.md** | Este documento (índice) | - | [Ver](DOCUMENTACION_INDEX.md) |

---

## 🎯 Para Empezar

### Si eres nuevo en el proyecto:

1. **Primero lee:** [README.md](README.md)
   - Visión general del proyecto
   - Características principales
   - Instalación rápida

2. **Luego revisa:** [MANUAL_USUARIO.md](MANUAL_USUARIO.md)
   - Guía paso a paso
   - Casos de uso detallados
   - Resolución de problemas

3. **Para entender la arquitectura:** [DIAGRAMAS_UML.md](DIAGRAMAS_UML.md)
   - Diagrama de clases
   - Diagrama de secuencia
   - Arquitectura del sistema

---

## 📋 README.md

**Contenido:**
- Descripción del proyecto
- Fases del proyecto (1, 2, 3)
- Arquitectura del sistema (3 capas)
- Conceptos POO demostrados
- Instalación y ejecución
- Estructura del proyecto
- Patrones de diseño
- Novedades de Fase 2

**Ideal para:**
- Desarrolladores que desean entender el proyecto
- Profesores evaluando el trabajo
- Contribuidores potenciales

---

## 📖 MANUAL_USUARIO.md

**Contenido:**
- Introducción al sistema
- Requisitos del sistema
- Guía de instalación detallada
- Tutorial paso a paso de todas las funcionalidades
- Gestión de archivos CSV
- Resolución de problemas comunes
- Preguntas frecuentes (FAQ)

**Secciones principales:**
1. ¿Qué es Sistema Barceloneta?
2. Instalación (3 opciones diferentes)
3. Gestión de Vehículos
4. Gestión de Pasajeros
5. Venta de Tickets
6. Consultas y Reportes
7. Manejo de archivos CSV
8. Troubleshooting

**Ideal para:**
- Usuarios finales del sistema
- Operadores de terminal
- Personal de soporte técnico

**Casos de uso incluidos:**
- ✅ Cómo registrar un vehículo
- ✅ Cómo registrar un pasajero
- ✅ Cómo vender un ticket
- ✅ Cómo consultar tickets
- ✅ Cómo ver estadísticas
- ✅ Cómo hacer respaldos

---

## 🎨 DIAGRAMAS_UML.md

**Contenido:**
- Diagrama de Clases (completo, 3 capas)
- Diagrama de Casos de Uso
- Diagrama de Secuencia (2 flujos)
- Diagrama de Componentes
- Diagrama de Actividad (2 procesos)

**Tecnología:** Mermaid Chart (se renderiza automáticamente en GitHub)

**Diagramas incluidos:**

### 1. Diagrama de Clases
- **Capa de Modelo:** Vehiculo, Buseta, MicroBus, Bus, Pasajero, Ticket, TipoPasajero, Calculable, Imprimible
- **Capa de Persistencia:** VehiculoDAO, PasajeroDAO, TicketDAO, Serializadores
- **Capa de Lógica:** GestorTickets, Main

### 2. Diagrama de Casos de Uso
- 11 casos de uso identificados
- Relaciones include y extend
- Actor: Usuario/Operador

### 3. Diagramas de Secuencia
- **Vender Ticket:** Flujo completo con validaciones
- **Cargar Datos:** Proceso de inicialización del sistema

### 4. Diagrama de Componentes
- Arquitectura en 3 capas
- Patrones de diseño (Singleton, Facade, DAO, Strategy)
- Flujo de datos

### 5. Diagramas de Actividad
- **Venta de Ticket:** Flujo con validaciones y cálculos
- **Persistencia Automática:** Proceso CRUD a CSV

**Ideal para:**
- Estudiantes de POO
- Arquitectos de software
- Documentación técnica
- Presentaciones académicas

---

## 🧪 PRUEBAS_FASE2.md

**Contenido:**
- Resumen ejecutivo de pruebas
- Resultados de 5 pruebas principales
- Verificación de archivos CSV
- Estadísticas finales
- Conclusiones

**Pruebas documentadas:**
1. ✅ Push al repositorio remoto
2. ✅ Registro de vehículo con persistencia
3. ✅ Registro de pasajero con UTF-8
4. ✅ Venta de ticket con cálculos
5. ✅ Persistencia al reiniciar sistema

**Ideal para:**
- Validación de funcionalidad
- Evidencia de pruebas
- Reporte de calidad
- Documentación de QA

---

## 🎓 Guía por Rol

### Para Estudiantes

**Orden de lectura recomendado:**
1. README.md (entender el proyecto)
2. DIAGRAMAS_UML.md (estudiar diseño)
3. MANUAL_USUARIO.md (aprender a usar)
4. PRUEBAS_FASE2.md (ver resultados)

**Conceptos POO a estudiar:**
- Herencia (Vehiculo → Buseta, MicroBus, Bus)
- Interfaces (Calculable, Imprimible)
- Polimorfismo (calcularTarifaFinal)
- Enumeraciones (TipoPasajero)
- Patrones de diseño (Singleton, DAO, Facade)

### Para Profesores

**Documentos clave:**
1. README.md → Visión general
2. DIAGRAMAS_UML.md → Arquitectura y diseño
3. PRUEBAS_FASE2.md → Evidencia de funcionamiento

**Criterios de evaluación cubiertos:**
- ✅ Herencia y clases abstractas
- ✅ Interfaces múltiples
- ✅ Polimorfismo en acción
- ✅ Patrones de diseño
- ✅ Arquitectura en capas
- ✅ Persistencia de datos
- ✅ Documentación completa

### Para Usuarios Finales

**Documentos esenciales:**
1. MANUAL_USUARIO.md (guía completa)
2. README.md sección "Uso del Sistema"

**No necesitas leer:**
- DIAGRAMAS_UML.md (técnico)
- PRUEBAS_FASE2.md (interno)

### Para Desarrolladores

**Todo es relevante:**
1. README.md → Arquitectura general
2. DIAGRAMAS_UML.md → Diseño detallado
3. MANUAL_USUARIO.md → Funcionalidades
4. PRUEBAS_FASE2.md → Casos de prueba
5. Código fuente en `/src`

---

## 📊 Estadísticas de Documentación

| Métrica | Valor |
|---------|-------|
| Total de documentos | 5 |
| Páginas equivalentes | ~50 |
| Diagramas UML | 10+ |
| Casos de uso documentados | 11 |
| Ejemplos de código | 15+ |
| Screenshots/Ejemplos | 20+ |
| Preguntas frecuentes | 10 |

---

## 🔄 Historial de Versiones

| Versión | Fecha | Documento | Cambios |
|---------|-------|-----------|---------|
| 1.0 | Oct 2025 | README.md | Versión inicial Fase 1 |
| 1.1 | Nov 2025 | README.md | Actualización Fase 2 |
| 1.0 | Nov 2025 | MANUAL_USUARIO.md | Creación inicial |
| 1.0 | Nov 2025 | DIAGRAMAS_UML.md | Creación inicial |
| 1.0 | Nov 2025 | PRUEBAS_FASE2.md | Resultados de pruebas |
| 1.0 | Nov 2025 | DOCUMENTACION_INDEX.md | Creación del índice |

---

## 🔗 Enlaces Útiles

- **Repositorio GitHub:** https://github.com/Alfredobau08/Barceloneta
- **Issues:** https://github.com/Alfredobau08/Barceloneta/issues
- **Releases:** https://github.com/Alfredobau08/Barceloneta/releases
- **Tag Fase 2:** https://github.com/Alfredobau08/Barceloneta/releases/tag/v1.0-fase2

---

## 📝 Notas Importantes

### Formato Markdown

Todos los documentos están en formato Markdown (.md) que:
- ✅ Se renderiza automáticamente en GitHub
- ✅ Es fácil de leer en cualquier editor de texto
- ✅ Soporta código con syntax highlighting
- ✅ Permite tablas, listas y enlaces

### Diagramas Mermaid

Los diagramas UML usan Mermaid porque:
- ✅ Se renderizan automáticamente en GitHub
- ✅ No requieren imágenes externas
- ✅ Son fáciles de modificar (solo texto)
- ✅ Se mantienen sincronizados con el código

### Actualización

Esta documentación se actualiza con cada fase del proyecto:
- **Fase 1:** Documentación básica
- **Fase 2:** Documentación completa ← *Estamos aquí*
- **Fase 3:** Se agregará documentación de interfaz gráfica

---

## 🤝 Contribuir

Para mejorar la documentación:

1. **Reportar errores:**
   - Crear un issue en GitHub
   - Etiquetar como "documentation"

2. **Sugerir mejoras:**
   - Fork del repositorio
   - Modificar documentación
   - Crear Pull Request

3. **Agregar ejemplos:**
   - Los ejemplos son bienvenidos
   - Mantener el formato consistente

---

## 📧 Contacto

**Proyecto Educativo**
Universidad Popular del Cesar
Programación Orientada a Objetos II

**Autor:** Alfredo David Bautista Romero

---

## 📜 Licencia

Proyecto educativo para fines académicos.
Universidad Popular del Cesar © 2025

---

**Última actualización:** Noviembre 2025
**Versión del índice:** 1.0
