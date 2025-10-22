# 🗄️ COMANDOS PARA VER LA BASE DE DATOS

Este documento contiene todos los comandos necesarios para visualizar las tablas y datos de la base de datos `sistema_educativo`.

---

## 📊 **OPCIÓN 1: MySQL Workbench (Recomendado)**

### **Generar Diagrama Visual:**

1. Abrir MySQL Workbench
2. Conectarse a `localhost`
3. Menú: **Database** → **Reverse Engineer...**
4. Seleccionar conexión
5. Next → Seleccionar `sistema_educativo`
6. Next → Next → Execute
7. **Se generará un diagrama EER automáticamente** ✅

### **Exportar Diagrama como Imagen:**

1. En el diagrama EER generado
2. **File** → **Export** → **Export as PNG...**
3. Guardar como: `diagrama-base-datos.png`

---

## 💻 **OPCIÓN 2: Línea de Comandos MySQL**

### **1. Conectarse a MySQL:**

```bash
mysql -u root -p
```
*Ingresa tu contraseña cuando se solicite*

---

### **2. Seleccionar la base de datos:**

```sql
USE sistema_educativo;
```

---

### **3. Ver todas las tablas:**

```sql
SHOW TABLES;
```

**Resultado esperado:**
```
+------------------------------+
| Tables_in_sistema_educativo  |
+------------------------------+
| aulas                        |
| correlatividades             |
| cursos                       |
| cursos_ofrecidos             |
| estudiantes                  |
| horarios                     |
| inscripciones                |
| periodos_academicos          |
| personas                     |
| profesores                   |
+------------------------------+
10 rows in set
```

---

### **4. Ver estructura de una tabla específica:**

```sql
DESCRIBE nombre_tabla;
```

**Ejemplos:**

```sql
DESCRIBE personas;
DESCRIBE estudiantes;
DESCRIBE cursos;
DESCRIBE inscripciones;
DESCRIBE aulas;
DESCRIBE periodos_academicos;
DESCRIBE correlatividades;
```

---

### **5. Ver información detallada de todas las tablas:**

```sql
SELECT 
    TABLE_NAME AS Tabla,
    TABLE_ROWS AS Filas,
    ROUND(DATA_LENGTH / 1024, 2) AS 'Tamaño (KB)'
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'sistema_educativo' 
  AND TABLE_TYPE = 'BASE TABLE'
ORDER BY TABLE_NAME;
```

---

### **6. Ver columnas de todas las tablas:**

```sql
SELECT 
    TABLE_NAME AS Tabla,
    COLUMN_NAME AS Columna,
    COLUMN_TYPE AS Tipo,
    IS_NULLABLE AS Nulo,
    COLUMN_KEY AS Llave,
    COLUMN_DEFAULT AS Defecto
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'sistema_educativo'
ORDER BY TABLE_NAME, ORDINAL_POSITION;
```

---

### **7. Ver datos de una tabla específica:**

```sql
-- Ver primeros 10 registros
SELECT * FROM nombre_tabla LIMIT 10;
```

**Ejemplos:**

```sql
-- Ver primeros 10 estudiantes
SELECT * FROM estudiantes LIMIT 10;

-- Ver primeros 10 cursos
SELECT * FROM cursos LIMIT 10;

-- Ver primeros 10 inscripciones con detalles
SELECT * FROM vista_inscripciones_completas LIMIT 10;
```

---

### **8. Ver relaciones (Foreign Keys):**

```sql
SELECT 
    TABLE_NAME AS Tabla,
    COLUMN_NAME AS Columna,
    CONSTRAINT_NAME AS `Constraint`,
    REFERENCED_TABLE_NAME AS 'Tabla Referenciada',
    REFERENCED_COLUMN_NAME AS 'Columna Referenciada'
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'sistema_educativo'
  AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME, CONSTRAINT_NAME;
```

---

### **9. Ver índices y constraints:**

```sql
SHOW INDEX FROM nombre_tabla;
```

**Ejemplos:**

```sql
SHOW INDEX FROM cursos;
SHOW INDEX FROM estudiantes;
SHOW INDEX FROM inscripciones;
```

---

### **10. Ver vistas SQL:**

```sql
SHOW FULL TABLES WHERE TABLE_TYPE = 'VIEW';
```

**Ver contenido de una vista:**

```sql
SELECT * FROM vista_estudiantes_completos LIMIT 10;
SELECT * FROM vista_inscripciones_completas LIMIT 10;
```

---

## 📋 **OPCIÓN 3: Consultas Específicas por Módulo**

### **A) PERSONAS Y ESTUDIANTES:**

```sql
-- Ver todos los estudiantes con datos personales
SELECT 
    e.matricula,
    p.nombre,
    p.apellido,
    p.email,
    e.fecha_ingreso,
    e.estado_academico
FROM estudiantes e
INNER JOIN personas p ON e.persona_id = p.id
ORDER BY p.apellido, p.nombre;
```

---

### **B) PROFESORES:**

```sql
-- Ver todos los profesores con datos personales
SELECT 
    pr.codigo_profesor,
    p.nombre,
    p.apellido,
    pr.especialidad,
    pr.estado_laboral,
    pr.sueldo
FROM profesores pr
INNER JOIN personas p ON pr.persona_id = p.id
ORDER BY p.apellido, p.nombre;
```

---

### **C) CURSOS Y CORRELATIVIDADES:**

```sql
-- Ver cursos con sus correlatividades
SELECT 
    c1.nombre AS Curso,
    c2.nombre AS Correlativa,
    co.tipo AS Tipo
FROM correlatividades co
INNER JOIN cursos c1 ON co.curso_id = c1.id
INNER JOIN cursos c2 ON co.correlativa_id = c2.id
ORDER BY c1.nombre, co.tipo;
```

---

### **D) AULAS:**

```sql
-- Ver aulas con información completa
SELECT 
    codigo,
    nombre,
    capacidad,
    tipo,
    CONCAT(edificio, ' - Piso ', piso) AS Ubicacion,
    estado
FROM aulas
ORDER BY codigo;
```

---

### **E) PERÍODOS ACADÉMICOS:**

```sql
-- Ver períodos con fechas
SELECT 
    nombre,
    anio,
    semestre,
    fecha_inicio_inscripciones,
    fecha_fin_inscripciones,
    fecha_inicio_clases,
    fecha_fin_clases,
    estado,
    activo
FROM periodos_academicos
ORDER BY anio DESC, semestre DESC;
```

---

### **F) CURSOS OFRECIDOS:**

```sql
-- Ver cursos ofrecidos con todos los detalles
SELECT 
    c.codigo_curso,
    c.nombre AS Curso,
    pa.nombre AS Periodo,
    CONCAT(p.nombre, ' ', p.apellido) AS Profesor,
    a.codigo AS Aula,
    co.horario,
    co.cupos_disponibles
FROM cursos_ofrecidos co
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN periodos_academicos pa ON co.periodo_id = pa.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas p ON pr.persona_id = p.id
INNER JOIN aulas a ON co.aula_id = a.id
ORDER BY pa.nombre, c.nombre;
```

---

### **G) INSCRIPCIONES:**

```sql
-- Ver inscripciones con datos completos
SELECT 
    CONCAT(pe.nombre, ' ', pe.apellido) AS Estudiante,
    e.matricula,
    c.nombre AS Curso,
    CONCAT(pp.nombre, ' ', pp.apellido) AS Profesor,
    a.codigo AS Aula,
    i.estado,
    i.nota_final,
    i.fecha_inscripcion
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas pe ON e.persona_id = pe.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas pp ON pr.persona_id = pp.id
INNER JOIN aulas a ON co.aula_id = a.id
ORDER BY i.fecha_inscripcion DESC
LIMIT 20;
```

---

## 📊 **OPCIÓN 4: Estadísticas Generales**

```sql
-- Ver estadísticas del sistema
SELECT 'Personas' AS Entidad, COUNT(*) AS Total FROM personas
UNION ALL
SELECT 'Estudiantes', COUNT(*) FROM estudiantes
UNION ALL
SELECT 'Profesores', COUNT(*) FROM profesores
UNION ALL
SELECT 'Cursos', COUNT(*) FROM cursos
UNION ALL
SELECT 'Aulas', COUNT(*) FROM aulas
UNION ALL
SELECT 'Períodos', COUNT(*) FROM periodos_academicos
UNION ALL
SELECT 'Cursos Ofrecidos', COUNT(*) FROM cursos_ofrecidos
UNION ALL
SELECT 'Correlatividades', COUNT(*) FROM correlatividades
UNION ALL
SELECT 'Horarios', COUNT(*) FROM horarios
UNION ALL
SELECT 'Inscripciones', COUNT(*) FROM inscripciones;
```

---

## 🔍 **OPCIÓN 5: Verificar Integridad Referencial**

```sql
-- Ver todas las Foreign Keys del sistema
SELECT 
    CONSTRAINT_NAME AS 'FK Name',
    TABLE_NAME AS 'Tabla Hija',
    COLUMN_NAME AS 'Columna',
    REFERENCED_TABLE_NAME AS 'Tabla Padre',
    REFERENCED_COLUMN_NAME AS 'Columna Padre'
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'sistema_educativo'
  AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME;
```

---

## ✅ **Resumen de la Base de Datos**

```
TABLAS:           10
├── personas          (base)
├── estudiantes       (hereda de personas)
├── profesores        (hereda de personas)
├── cursos
├── correlatividades  (relaciona cursos)
├── aulas
├── periodos_academicos
├── cursos_ofrecidos  (curso + periodo + profesor + aula)
├── horarios          (horarios de cursos ofrecidos)
└── inscripciones     (estudiante + curso ofrecido)

VISTAS:           6
FOREIGN KEYS:     10
UNIQUE KEYS:      9
CHECK CONSTRAINTS: 7
ÍNDICES:          25+
```

---

**Para más información, consultar:**
- `docs/03-BASE-DE-DATOS.md`
- `docs/DIAGRAMA-BASE-DATOS.md`
- `database/README.md`

---

**Fecha:** Octubre 2025  
**Versión:** 2.0

---

## 👨‍💻 Autor

**Desarrolladora**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)


