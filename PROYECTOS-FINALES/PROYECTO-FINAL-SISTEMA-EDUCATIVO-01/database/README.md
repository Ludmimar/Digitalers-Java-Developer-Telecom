# 🗄️ Base de Datos - Sistema de Gestión Educativa v2.0

## 📋 Descripción

Esta carpeta contiene los scripts SQL necesarios para crear y poblar la base de datos del **Sistema de Gestión Educativa**.

---

## 📁 Archivos Disponibles

### **1. `01_CREAR_BASE_DATOS_COMPLETA.sql`**
- **Propósito:** Crear la estructura completa de la base de datos desde cero
- **Contenido:**
  - Creación de la base de datos `sistema_educativo`
  - 10 tablas principales
  - 6 vistas SQL
  - Índices y restricciones
  - Claves foráneas y relaciones

### **2. `02_CARGAR_DATOS_COMPLETOS.sql`**
- **Propósito:** Cargar datos de ejemplo coherentes y relacionados
- **Contenido:**
  - 25 personas (15 estudiantes + 10 profesores)
  - 15 estudiantes activos
  - 10 profesores activos
  - 14 cursos con correlatividades
  - 10 aulas (aulas, laboratorios, auditorio)
  - 4 períodos académicos (2 finalizados, 1 activo, 1 futuro)
  - 14 cursos ofrecidos en el período activo
  - 45 inscripciones distribuidas
  - 16 horarios detallados

---

## 🚀 Instrucciones de Instalación

### **Opción 1: Usando MySQL Workbench** (Recomendado)

#### **Paso 1: Abrir MySQL Workbench**
1. Ejecutar **MySQL Workbench**
2. Conectarse a su servidor MySQL local

#### **Paso 2: Crear la Base de Datos**
1. Click en **File** → **Open SQL Script...**
2. Seleccionar: `01_CREAR_BASE_DATOS_COMPLETA.sql`
3. Click en el icono del ⚡ (**Execute**) o presionar `Ctrl+Shift+Enter`
4. Esperar mensaje: **"✅ BASE DE DATOS CREADA EXITOSAMENTE"**

#### **Paso 3: Cargar los Datos**
1. Click en **File** → **Open SQL Script...**
2. Seleccionar: `02_CARGAR_DATOS_COMPLETOS.sql`
3. Click en el icono del ⚡ (**Execute**) o presionar `Ctrl+Shift+Enter`
4. Esperar mensaje: **"✅ CARGA DE DATOS COMPLETADA EXITOSAMENTE"**

#### **Paso 4: Verificar**
1. En el panel izquierdo, expandir **SCHEMAS**
2. Refrescar la lista (botón 🔄)
3. Verificar que aparezca `sistema_educativo` con todas sus tablas

---

### **Opción 2: Usando Línea de Comandos**

#### **Windows:**
```batch
# Navegar a la carpeta database
cd "D:\JAVA DEVELOPERS\Digitalers-Java-Developer-Telecom\PROYECTO-FINAL-SISTEMA-EDUCATIVO 01\database"

# Crear la base de datos
mysql -u root -p < 01_CREAR_BASE_DATOS_COMPLETA.sql

# Cargar los datos
mysql -u root -p < 02_CARGAR_DATOS_COMPLETOS.sql
```

#### **Linux/Mac:**
```bash
# Navegar a la carpeta database
cd "/path/to/PROYECTO-FINAL-SISTEMA-EDUCATIVO 01/database"

# Crear la base de datos
mysql -u root -p < 01_CREAR_BASE_DATOS_COMPLETA.sql

# Cargar los datos
mysql -u root -p < 02_CARGAR_DATOS_COMPLETOS.sql
```

---

## 📊 Estructura de la Base de Datos

### **Tablas Principales:**

```
┌─────────────────────┐
│      PERSONAS       │ ← Tabla base (herencia)
└─────────┬───────────┘
          │
    ┌─────┴─────┐
    │           │
┌───▼────┐ ┌───▼────┐
│ESTUDIANTES│ │PROFESORES│
└────┬───┘ └────┬────┘
     │          │
     │     ┌────▼────────┐
     │     │CURSOS_OFRECIDOS│◄─────┐
     │     └─────┬───────┘       │
     │           │               │
┌────▼───────────▼──┐      ┌────┴────┐
│  INSCRIPCIONES    │      │ CURSOS  │
└───────────────────┘      └────┬────┘
                                │
                      ┌─────────┴─────────┐
                      │                   │
              ┌───────▼────┐    ┌────────▼────────┐
              │CORRELATIVIDADES│ │PERIODOS_ACADEMICOS│
              └─────────────┘    └─────────────────┘
```

### **Tablas Nuevas (v2.0):**
- ✨ **`aulas`** - Gestión de espacios físicos
- ✨ **`horarios`** - Horarios detallados por día
- ✨ **`correlatividades`** - Prerrequisitos entre cursos

### **Tablas Extendidas (v2.0):**
- 🔧 **`periodos_academicos`** - Ahora con fechas de inscripción y estados
- 🔧 **`cursos_ofrecidos`** - Ahora vinculado a `aulas`
- 🔧 **`inscripciones`** - Estados actualizados (PENDIENTE, CURSANDO, APROBADO, REPROBADO)

---

## 📈 Datos Cargados

### **Resumen de Registros:**

| Tabla | Registros | Descripción |
|-------|-----------|-------------|
| **personas** | 25 | 15 estudiantes + 10 profesores |
| **estudiantes** | 15 | Distribuidos en 3 años |
| **profesores** | 10 | Todos activos con especialidades |
| **cursos** | 14 | 4 de 1er año, 4 de 2do, 6 de 3er año |
| **correlatividades** | 8 | Relaciones de prerrequisitos |
| **aulas** | 10 | 5 aulas + 4 labs + 1 auditorio |
| **periodos_academicos** | 4 | 2 pasados, 1 activo, 1 futuro |
| **cursos_ofrecidos** | 14 | Todos en período activo 2025-1 |
| **horarios** | 16+ | Horarios detallados por día |
| **inscripciones** | 45 | Distribuidas por niveles |

### **Distribución de Inscripciones:**

```
Primer Año (IDs 11-15):  18 inscripciones (promedio 21 créditos/estudiante)
Segundo Año (IDs 6-10):  17 inscripciones (promedio 18 créditos/estudiante)
Tercer Año (IDs 1-5):    14 inscripciones (promedio 18 créditos/estudiante)

Total: 45 inscripciones activas
```

### **Período Activo: 2025-1**
- **Estado:** INSCRIPCION
- **Fecha inscripciones:** 01/02/2025 - 28/02/2025
- **Fecha clases:** 01/03/2025 - 31/07/2025
- **Cursos ofrecidos:** 14
- **Estudiantes inscritos:** 15
- **Profesores activos:** 10

---

## 🔐 Validaciones Implementadas

### **Restricciones de Integridad:**
✅ Claves foráneas con `ON DELETE CASCADE` o `RESTRICT`  
✅ Valores únicos (DNI, matrícula, código)  
✅ Checks de valores válidos (notas 0-10, capacidades > 0)  
✅ Índices para optimización de consultas

### **Correlatividades:**
- **PROG-102** requiere **PROG-101** (APROBADA)
- **ALG-101** requiere **PROG-101** (APROBADA)
- **BD-201** requiere **PROG-101** (REGULAR)
- **BD-202** requiere **BD-201** (APROBADA)
- **WEB-202** requiere **WEB-201** (APROBADA) + **PROG-102** (REGULAR)
- **IA-301** requiere **PROG-102** + **MAT-101** (ambas APROBADAS)

### **Límite de Créditos:**
- **Máximo por período:** 30 créditos
- Todos los estudiantes están dentro del límite

---

## 🔍 Consultas Útiles

### **Ver todos los estudiantes activos:**
```sql
SELECT * FROM vista_estudiantes_completos 
WHERE estado_academico = 'ACTIVO'
ORDER BY matricula;
```

### **Ver cursos del período activo:**
```sql
SELECT * FROM cursos_ofrecidos 
WHERE periodo_id = (SELECT id FROM periodos_academicos WHERE activo = TRUE);
```

### **Ver inscripciones con detalles:**
```sql
SELECT * FROM vista_inscripciones_detalle
WHERE periodo_nombre = '2025-1'
ORDER BY estudiante_nombre, curso_nombre;
```

### **Ver ocupación de aulas:**
```sql
SELECT * FROM vista_ocupacion_aulas
ORDER BY porcentaje_ocupacion DESC;
```

### **Ver correlatividades de un curso:**
```sql
SELECT 
    c1.nombre AS curso,
    c2.nombre AS correlativa_requerida,
    corr.tipo
FROM correlatividades corr
INNER JOIN cursos c1 ON corr.curso_id = c1.id
INNER JOIN cursos c2 ON corr.correlativa_id = c2.id
WHERE c1.codigo_curso = 'PROG-102';
```

---

## 🛠️ Mantenimiento

### **Limpiar TODOS los datos (mantener estructura):**
```sql
USE sistema_educativo;
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM horarios WHERE id > 0;
DELETE FROM inscripciones WHERE id > 0;
DELETE FROM correlatividades WHERE id > 0;
DELETE FROM cursos_ofrecidos WHERE id > 0;
DELETE FROM estudiantes WHERE id > 0;
DELETE FROM profesores WHERE id > 0;
DELETE FROM cursos WHERE id > 0;
DELETE FROM aulas WHERE id > 0;
DELETE FROM periodos_academicos WHERE id > 0;
DELETE FROM personas WHERE id > 0;

ALTER TABLE personas AUTO_INCREMENT = 1;
ALTER TABLE estudiantes AUTO_INCREMENT = 1;
ALTER TABLE profesores AUTO_INCREMENT = 1;
ALTER TABLE cursos AUTO_INCREMENT = 1;
ALTER TABLE aulas AUTO_INCREMENT = 1;
ALTER TABLE periodos_academicos AUTO_INCREMENT = 1;
ALTER TABLE cursos_ofrecidos AUTO_INCREMENT = 1;
ALTER TABLE inscripciones AUTO_INCREMENT = 1;
ALTER TABLE horarios AUTO_INCREMENT = 1;
ALTER TABLE correlatividades AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;
```

### **Eliminar TODA la base de datos:**
```sql
DROP DATABASE IF EXISTS sistema_educativo;
```

---

## 📞 Soporte

### **Problemas Comunes:**

#### **1. Error: "Access denied for user"**
**Solución:** Verificar usuario y contraseña de MySQL
```bash
mysql -u root -p
# Ingresar contraseña
```

#### **2. Error: "Database exists"**
**Solución:** El script incluye `DROP DATABASE IF EXISTS`, ejecutarlo completo

#### **3. Error: "Foreign key constraint fails"**
**Solución:** Ejecutar los scripts EN ORDEN (01 primero, luego 02)

#### **4. Error: "Table doesn't exist"**
**Solución:** Asegurarse de ejecutar `01_CREAR_BASE_DATOS_COMPLETA.sql` primero

---

## 📝 Notas Importantes

### **Características v2.0:**
✨ **Nuevas tablas:** aulas, horarios, correlatividades  
✨ **Validación de límite de créditos** (30 por período)  
✨ **Validación de correlatividades** (REGULAR y APROBADA)  
✨ **Gestión de horarios detallados** por día y hora  
✨ **Estados de períodos** (PLANIFICACION, INSCRIPCION, CURSANDO, FINALIZADO)  
✨ **Fechas de inscripción** configurables por período  

### **Compatibilidad:**
- MySQL 8.0+
- MariaDB 10.5+
- Codificación: UTF-8 (utf8mb4)

### **Seguridad:**
- ⚠️ **IMPORTANTE:** Los datos son de ejemplo para desarrollo
- ⚠️ Para producción, cambiar contraseñas y datos personales
- ⚠️ Agregar encriptación de contraseñas (BCrypt)
- ⚠️ Implementar roles y permisos

---

## 📚 Documentación Relacionada

- **Análisis Completo:** `../ANALISIS-ESTADO-ACTUAL-FINAL.md`
- **Casos de Uso:** `../CASOS-DE-USO-IMPLEMENTADOS.md`
- **Mejoras Implementadas:** `../MEJORAS-FINALES-IMPLEMENTADAS.md`
- **Resumen Ejecutivo:** `../RESUMEN-EJECUTIVO-FINAL.md`

---

## ✅ Verificación de Instalación

Después de ejecutar ambos scripts, verificar con:

```sql
USE sistema_educativo;

-- Ver todas las tablas
SHOW TABLES;

-- Contar registros
SELECT 
    'personas' AS Tabla, COUNT(*) AS Total FROM personas
UNION ALL
SELECT 'estudiantes', COUNT(*) FROM estudiantes
UNION ALL
SELECT 'profesores', COUNT(*) FROM profesores
UNION ALL
SELECT 'cursos', COUNT(*) FROM cursos
UNION ALL
SELECT 'aulas', COUNT(*) FROM aulas
UNION ALL
SELECT 'inscripciones', COUNT(*) FROM inscripciones;

-- Resultado esperado:
-- personas:     25
-- estudiantes:  15
-- profesores:   10
-- cursos:       14
-- aulas:        10
-- inscripciones:45
```

---

**Autor:** Ludmila Martos  
**Versión:** 2.0  
**Fecha:** 21 de Octubre 2025  
**Curso:** Digitalers Java Developer Telecom

---

## 👨‍💻 Autor

**Desarrolladora**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**¡Base de datos lista para usar! 🎓✨**


