# Guía de Presentación - Sistema Barceloneta

**Archivo:** `PRESENTACION.md`
**Formato:** Marp (Markdown Presentation)
**Slides:** 32 slides + notas para el presentador
**Duración estimada:** 15-20 minutos + Q&A

---

## 📊 ¿Qué es Marp?

**Marp** (Markdown Presentation Ecosystem) es una herramienta que convierte archivos Markdown en presentaciones profesionales.

**Ventajas:**
- ✅ Escrito en Markdown puro (como toda la documentación del proyecto)
- ✅ Control de versiones con Git
- ✅ Exporta a PDF, PowerPoint (PPTX), y HTML
- ✅ Soporta diagramas Mermaid
- ✅ Temas profesionales incluidos
- ✅ Completamente gratis y open source

---

## 🚀 Cómo Ver la Presentación

### Opción 1: VS Code (Recomendada)

1. **Instalar VS Code:**
   https://code.visualstudio.com/

2. **Instalar extensión Marp:**
   - Abrir VS Code
   - Ir a Extensions (Ctrl+Shift+X)
   - Buscar "Marp for VS Code"
   - Instalar la extensión de Marp Team

3. **Abrir la presentación:**
   ```bash
   code PRESENTACION.md
   ```

4. **Ver preview:**
   - Clic derecho en el archivo
   - Seleccionar "Open Preview" o presionar `Ctrl+K V`
   - Ver slides en tiempo real

5. **Navegar:**
   - Flechas para cambiar slides
   - Modo presentación: clic en ícono de presentación

---

### Opción 2: Marp CLI (Línea de Comandos)

1. **Instalar Node.js:**
   https://nodejs.org/

2. **Instalar Marp CLI:**
   ```bash
   npm install -g @marp-team/marp-cli
   ```

3. **Ver la presentación:**
   ```bash
   marp PRESENTACION.md
   ```

---

### Opción 3: Marp Web (Online)

1. **Ir a:**
   https://web.marp.app/

2. **Copiar y pegar** el contenido de `PRESENTACION.md`

3. **Ver** la presentación en el navegador

---

## 📤 Exportar a Diferentes Formatos

### Exportar a PDF (Para Proyectar)

**Con VS Code:**
1. Abrir `PRESENTACION.md` en VS Code
2. Abrir Command Palette (`Ctrl+Shift+P`)
3. Escribir "Marp: Export slide deck"
4. Seleccionar "PDF"
5. Guardar como `presentacion.pdf`

**Con Marp CLI:**
```bash
marp PRESENTACION.md -o presentacion.pdf
```

**Configurar tamaño:**
```bash
marp PRESENTACION.md -o presentacion.pdf --pdf --allow-local-files
```

---

### Exportar a PowerPoint (PPTX)

**Con Marp CLI:**
```bash
marp PRESENTACION.md -o presentacion.pptx
```

**Ventaja:**
- Puedes editar después en PowerPoint
- Compatible con Google Slides
- Ideal para compartir con otros

---

### Exportar a HTML (Para Web)

**Con Marp CLI:**
```bash
marp PRESENTACION.md -o presentacion.html
```

**Abrir en navegador:**
```bash
# Windows
start presentacion.html

# Mac
open presentacion.html

# Linux
xdg-open presentacion.html
```

**Ventaja:**
- Funciona en cualquier navegador
- No requiere software adicional
- Puedes publicar en web

---

### Exportar Todo a la Vez

```bash
# Crear carpeta de exports
mkdir exports

# Exportar a todos los formatos
marp PRESENTACION.md -o exports/presentacion.pdf
marp PRESENTACION.md -o exports/presentacion.pptx
marp PRESENTACION.md -o exports/presentacion.html

# Resultado:
# exports/
#   ├── presentacion.pdf
#   ├── presentacion.pptx
#   └── presentacion.html
```

---

## 🎨 Personalizar la Presentación

### Cambiar el Tema

En la cabecera de `PRESENTACION.md`, cambiar:

```markdown
---
theme: gaia    ← Actual (elegante y moderno)
---
```

**Temas disponibles:**
- `default` - Clásico y limpio
- `gaia` - Moderno y elegante (actual)
- `uncover` - Minimalista

### Cambiar Colores

```markdown
---
backgroundColor: #fff
color: #000
---
```

### Agregar Logo

```markdown
![bg left:40% 80%](ruta/a/logo.png)
```

---

## 📋 Estructura de la Presentación

| Sección | Slides | Tiempo | Contenido |
|---------|--------|--------|-----------|
| Introducción | 1-4 | 2 min | Portada, agenda, qué es, problema |
| Arquitectura | 5-10 | 5 min | 3 capas, POO, patrones |
| Fases | 11-15 | 4 min | Fase 1, 2, demo |
| Resultados | 16-21 | 3 min | Pruebas, estadísticas |
| Futuro | 22-23 | 1 min | Fase 3, documentación |
| Conclusiones | 24-29 | 2 min | Aprendizajes, logros |
| Cierre | 30-32 | 3 min | Agradecimientos, Q&A |

**Total:** ~20 minutos + 5 min Q&A = 25 minutos

---

## 🎤 Consejos para Presentar

### Antes de la Presentación

1. **Practicar:** Lee la presentación 2-3 veces
2. **Timing:** Cronometra para no pasarte de tiempo
3. **Demo:** Prepara y prueba la demo en vivo
4. **Backup:** Lleva PDF en USB por si falla la tecnología
5. **Equipo:** Llega 10 min antes para probar proyector

### Durante la Presentación

1. **No leas:** Las slides son apoyo visual, no el guión
2. **Contacto visual:** Mira a la audiencia, no a la pantalla
3. **Pausas:** Respira, no te apresures
4. **Pointer:** Usa puntero láser si está disponible
5. **Agua:** Ten agua cerca

### Notas del Presentador

El archivo incluye **notas detalladas** al final para cada slide:
- Qué decir en cada slide
- Tiempo sugerido
- Énfasis importantes
- Posibles preguntas y respuestas

**Ver las notas:**
- Están en comentarios HTML al final del archivo
- Comienzan con `<!-- Notas para el presentador:`

---

## 🎯 Contenido de los Slides

### Principales Highlights

1. **Slide 5 (Arquitectura):** CRUCIAL - explica las 3 capas
2. **Slide 8 (Polimorfismo):** Muestra código en acción
3. **Slide 12 (Fase 2):** Tu contribución principal
4. **Slide 13 (Secuencia):** Diagrama Mermaid animado
5. **Slide 15 (Demo):** Demo en vivo del sistema
6. **Slide 28-29 (Conclusiones):** Resumen de logros

### Diagramas Incluidos

- ✅ Arquitectura en 3 capas
- ✅ Diagrama de herencia
- ✅ Diagrama de interfaces
- ✅ Diagrama de clases simplificado
- ✅ Diagrama de secuencia
- ✅ Diagrama de patrones

**Todos en formato Mermaid** (se renderizan automáticamente)

---

## 🔧 Solución de Problemas

### Los diagramas Mermaid no se ven

**En VS Code:**
1. Verificar que la extensión Marp esté actualizada
2. Reiniciar VS Code
3. Cerrar y abrir el preview

**En PDF:**
```bash
# Exportar con soporte para Mermaid
marp PRESENTACION.md -o presentacion.pdf --allow-local-files
```

### Fuentes se ven mal

```bash
# Especificar fuente del sistema
marp PRESENTACION.md -o presentacion.pdf --pdf-notes
```

### El HTML no funciona offline

```bash
# Empaquetar todo en un HTML
marp PRESENTACION.md -o presentacion.html --html
```

---

## 📱 Modo Presentador

### Con Marp CLI

```bash
# Servidor local con modo presentador
marp --server PRESENTACION.md
```

**Abre en navegador:**
- http://localhost:8080

**Características:**
- Vista de presentador con notas
- Siguiente slide visible
- Timer
- Controles de navegación

### Atajos de Teclado

| Tecla | Acción |
|-------|--------|
| `→` o `Space` | Siguiente slide |
| `←` | Slide anterior |
| `Home` | Primer slide |
| `End` | Último slide |
| `F` | Pantalla completa |
| `Esc` | Salir de pantalla completa |

---

## 📊 Checklist Pre-Presentación

```markdown
Preparación:
- [ ] Practicar presentación 2-3 veces
- [ ] Cronometrar tiempo total
- [ ] Exportar a PDF de backup
- [ ] Preparar demo en vivo
- [ ] Cargar datos de prueba en el sistema
- [ ] Probar en computador de presentación

Día de presentación:
- [ ] Llegar 10 minutos antes
- [ ] Probar proyector/pantalla
- [ ] Verificar resolución (1920x1080 recomendada)
- [ ] Probar sonido (si usa videos)
- [ ] Tener agua disponible
- [ ] Llevar USB con PDF de backup
- [ ] Poner celular en silencio

Durante:
- [ ] Hablar claro y pausado
- [ ] Hacer contacto visual
- [ ] Usar puntero láser
- [ ] Controlar timing
- [ ] Respirar y relajarse
```

---

## 🎓 Estructura Detallada

### Introducción (Slides 1-4)
Objetivo: Captar atención y establecer contexto

- **Slide 1:** Portada profesional
- **Slide 2:** Agenda clara
- **Slide 3:** ¿Qué es el sistema?
- **Slide 4:** Problema que resuelve

### Desarrollo Técnico (Slides 5-21)
Objetivo: Mostrar conocimientos técnicos

- **Slides 5-10:** Arquitectura y conceptos POO
- **Slides 11-15:** Fases del proyecto y demo
- **Slides 16-21:** Resultados y métricas

### Cierre (Slides 22-32)
Objetivo: Resumir y dejar buena impresión

- **Slides 22-27:** Futuro y reflexión
- **Slides 28-29:** Conclusiones
- **Slides 30-32:** Agradecimientos y Q&A

---

## 💡 Tips Avanzados

### Añadir Transiciones

Marp no soporta transiciones nativas, pero en HTML puedes agregar:

```markdown
<style>
section {
  animation: fadeIn 0.5s;
}
</style>
```

### Añadir Números de Slide

Ya incluido con:
```markdown
---
paginate: true
---
```

### Cambiar Layout por Slide

```markdown
<!-- _class: lead -->
# Slide Centrado
```

### Ocultar Elementos

```markdown
<!--
Este contenido no se mostrará en la presentación
pero está en el código fuente
-->
```

---

## 📚 Recursos Adicionales

### Documentación Oficial

- **Marp:** https://marp.app/
- **Marp CLI:** https://github.com/marp-team/marp-cli
- **Marp for VS Code:** https://marketplace.visualstudio.com/items?itemName=marp-team.marp-vscode

### Tutoriales

- **Guía oficial:** https://marpit.marp.app/
- **Ejemplos:** https://github.com/marp-team/marp-cli/tree/main/examples

### Comunidad

- **GitHub Issues:** https://github.com/marp-team/marp/issues
- **Discussions:** https://github.com/marp-team/marp/discussions

---

## ❓ Preguntas Frecuentes

### ¿Puedo editar el PPTX después de exportar?

Sí, el PPTX exportado es completamente editable en PowerPoint, Google Slides, o LibreOffice Impress.

### ¿Los diagramas Mermaid funcionan en todos los formatos?

- **PDF:** ✅ Sí (como imágenes)
- **PPTX:** ✅ Sí (como imágenes)
- **HTML:** ✅ Sí (renderizados dinámicamente)

### ¿Puedo presentar sin internet?

Sí, el PDF y PPTX funcionan completamente offline. El HTML también si lo exportas con `--html`.

### ¿Cómo agrego mi logo?

```markdown
![bg right:30%](ruta/a/logo.png)
```

### ¿Puedo cambiar las fuentes?

```markdown
<style>
section {
  font-family: 'Arial', sans-serif;
}
</style>
```

---

## 🎬 Demo en Vivo (Slide 15)

### Preparación de la Demo

1. **Tener el sistema compilado y listo:**
   ```bash
   mvn clean compile
   ```

2. **Abrir terminal preparada:**
   - Tamaño de fuente grande (16-20pt)
   - Fondo oscuro si es posible
   - Terminal en pantalla completa

3. **Datos preparados:**
   - Vehículo: XYZ999, Buseta, 18, 2025, 3000
   - Pasajero: 9999999, [Tu nombre], ESTUDIANTE
   - Viaje: Valledupar → Santa Marta, 150 km

4. **Flujo de demo:**
   ```
   1. Mostrar archivos CSV vacíos (o con datos base)
   2. Registrar vehículo → mostrar que se guardó en CSV
   3. Registrar pasajero → mostrar CSV actualizado
   4. Vender ticket → mostrar recibo y CSV
   5. Cerrar sistema
   6. Abrir de nuevo → mostrar que datos persisten
   ```

5. **Tiempo:** Max 4 minutos

### Backup si Falla la Demo

Tener screenshots preparados de:
- Menú principal
- Registro exitoso
- Recibo de ticket
- Archivos CSV

---

## 🎯 Objetivos de la Presentación

Al final, la audiencia debe entender:

1. ✅ **Qué hace el sistema** (gestión de tickets)
2. ✅ **Cómo está diseñado** (3 capas, POO)
3. ✅ **Qué conceptos usa** (herencia, polimorfismo, interfaces)
4. ✅ **Qué patrones aplica** (Singleton, DAO, Facade)
5. ✅ **Cómo persiste** (CSV con UTF-8)
6. ✅ **Que funciona** (demo en vivo)

---

## 📞 Soporte

Si tienes problemas con Marp:

1. **Revisar documentación:** https://marp.app/
2. **Buscar en GitHub Issues:** https://github.com/marp-team/marp/issues
3. **Usar alternativa:** Exportar a PDF y usar visualizador de PDF

---

## ✅ Checklist Final

```markdown
Antes de presentar:
- [ ] Archivo PRESENTACION.md abierto en VS Code o Marp
- [ ] PDF de backup en USB
- [ ] Demo preparada y probada
- [ ] Datos de prueba listos
- [ ] Notas del presentador revisadas
- [ ] Timing practicado (20 min)
- [ ] Equipo probado 10 min antes

Durante:
- [ ] Respirar y hablar pausado
- [ ] No leer las slides
- [ ] Usar gestos y contacto visual
- [ ] Manejar el tiempo
- [ ] Disfrutar la presentación

Después:
- [ ] Responder preguntas con confianza
- [ ] Agradecer la atención
- [ ] Compartir link del repositorio
```

---

**¡Mucha suerte con la presentación!** 🎉

**Sistema Barceloneta v1.0-FASE2**
Universidad Popular del Cesar
2025
