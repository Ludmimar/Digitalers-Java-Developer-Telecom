# 🗄️ DIAGRAMA DE BASE DE DATOS - Sistema de Gestión Educativa v2.0

## 📊 **DIAGRAMA ENTIDAD-RELACIÓN**

```
                                    ┌─────────────────────┐
                                    │      PERSONAS       │
                                    │  (Tabla Base)       │
                                    ├─────────────────────┤
                                    │ PK: id              │
                                    │ UK: numero_documento│
                                    │     tipo_documento  │
                                    │     nombre          │
                                    │     apellido        │
                                    │     fecha_nacimiento│
                                    │     email           │
                                    │     telefono        │
                                    │     direccion       │
                                    │     activo          │
                                    └──────────┬──────────┘
                                               │
                        ┌──────────────────────┴──────────────────────┐
                        │                                             │
                        │ 1:1                                    1:1  │
                        ▼                                             ▼
            ┌─────────────────────┐                     ┌─────────────────────┐
            │   ESTUDIANTES       │                     │    PROFESORES       │
            ├─────────────────────┤                     ├─────────────────────┤
            │ PK: id              │                     │ PK: id              │
            │ FK: persona_id      │──────┐              │ FK: persona_id      │
            │ UK: matricula       │      │              │ UK: codigo_profesor │
            │     fecha_ingreso   │      │              │     fecha_contratac │
            │     promedio_general│      │              │     sueldo          │
            │     creditos_cursados│     │              │     especialidad    │
            │     estado_academico│      │              │     grado_academico │
            └──────────┬──────────┘      │              │     estado_laboral  │
                       │                  │              └──────────┬──────────┘
                       │                  │                         │
                       │ 1:N              │                         │ 1:N
                       │                  │                         │
                       ▼                  │                         ▼
            ┌─────────────────────┐      │              ┌─────────────────────┐
            │   INSCRIPCIONES     │      │              │  CURSOS_OFRECIDOS   │
            ├─────────────────────┤      │              ├─────────────────────┤
            │ PK: id              │      │        ┌─────│ PK: id              │
            │ FK: estudiante_id   │──────┘        │     │ FK: curso_id        │
            │ FK: curso_ofrecido_id│───────────────┘    │ FK: periodo_id      │
            │ UK: (estudiante_id, │                     │ FK: profesor_id     │
            │      curso_ofrecido)│                     │ FK: aula_id         │
            │     fecha_inscripc  │                     │ UK: (curso_id,      │
            │     estado          │                     │      periodo_id)    │
            │     nota_final      │                     │     aula (código)   │
            │     fecha_aprobacion│                     │     horario         │
            └─────────────────────┘                     │     cupos_disponibles│
                                                        └──────────┬──────────┘
                                                                   │
                        ┌──────────────────────────────────────────┼──────────────┐
                        │                                          │              │
                        │ N:1                                 N:1  │         N:1  │
                        ▼                                          ▼              ▼
            ┌─────────────────────┐                     ┌──────────────┐  ┌──────────────┐
            │      CURSOS         │                     │   PERÍODOS   │  │    AULAS     │
            ├─────────────────────┤                     │  ACADÉMICOS  │  ├──────────────┤
            │ PK: id              │                     ├──────────────┤  │ PK: id       │
            │ UK: codigo_curso    │                     │ PK: id       │  │ UK: codigo   │
            │     nombre          │                     │ UK: (anio,   │  │    nombre    │
            │     descripcion     │                     │     semestre)│  │    capacidad │
            │     creditos        │                     │    nombre    │  │    edificio  │
            │     horas_semanales │                     │    anio      │  │    piso      │
            │     cupo_maximo     │                     │    semestre  │  │    tipo      │
            │     estado          │                     │    activo    │  │    equipamiento│
            └──────────┬──────────┘                     │    estado    │  │    estado    │
                       │                                │    fechas... │  └──────────────┘
                       │ 1:N                            └──────────────┘
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼ N:1              N:1   ▼
    ┌──────────────────┐    ┌──────────────────┐
    │CORRELATIVIDADES  │    │    HORARIOS      │
    ├──────────────────┤    ├──────────────────┤
    │ PK: id           │    │ PK: id           │
    │ FK: curso_id     │    │ FK: curso_ofrecido_id │
    │ FK: correlativa_id│   │     dia_semana   │
    │ UK: (curso_id,   │    │     hora_inicio  │
    │   correlativa_id)│    │     hora_fin     │
    │     tipo         │    │ CHK: hora_fin >  │
    │ CHK: curso_id != │    │      hora_inicio │
    │   correlativa_id │    └──────────────────┘
    └──────────────────┘              │
          │                            │
          └────────────────────────────┘
           Ambas apuntan a CURSOS
```

---

## 🔗 **RELACIONES DETALLADAS**

### **1. PERSONAS → ESTUDIANTES (1:1)**
```
personas.id ──→ estudiantes.persona_id
ON DELETE CASCADE
```

### **2. PERSONAS → PROFESORES (1:1)**
```
personas.id ──→ profesores.persona_id
ON DELETE CASCADE
```

### **3. ESTUDIANTES → INSCRIPCIONES (1:N)**
```
estudiantes.id ──→ inscripciones.estudiante_id
ON DELETE CASCADE
```

### **4. CURSOS_OFRECIDOS → INSCRIPCIONES (1:N)**
```
cursos_ofrecidos.id ──→ inscripciones.curso_ofrecido_id
ON DELETE RESTRICT
```

### **5. CURSOS → CURSOS_OFRECIDOS (1:N)**
```
cursos.id ──→ cursos_ofrecidos.curso_id
ON DELETE RESTRICT
```

### **6. PERIODOS → CURSOS_OFRECIDOS (1:N)**
```
periodos_academicos.id ──→ cursos_ofrecidos.periodo_id
ON DELETE RESTRICT
```

### **7. PROFESORES → CURSOS_OFRECIDOS (1:N)**
```
profesores.id ──→ cursos_ofrecidos.profesor_id
ON DELETE RESTRICT
```

### **8. AULAS → CURSOS_OFRECIDOS (1:N)**
```
aulas.id ──→ cursos_ofrecidos.aula_id
ON DELETE RESTRICT
```

### **9. CURSOS → CORRELATIVIDADES (1:N)**
```
cursos.id ──→ correlatividades.curso_id
cursos.id ──→ correlatividades.correlativa_id
ON DELETE CASCADE (ambas)
```

### **10. CURSOS_OFRECIDOS → HORARIOS (1:N)**
```
cursos_ofrecidos.id ──→ horarios.curso_ofrecido_id
ON DELETE CASCADE
```

---

## 📋 **TABLA DE RELACIONES**

| Tabla Hijo | Tabla Padre | Tipo | Cascade |
|------------|-------------|------|---------|
| estudiantes | personas | 1:1 | CASCADE |
| profesores | personas | 1:1 | CASCADE |
| inscripciones | estudiantes | N:1 | CASCADE |
| inscripciones | cursos_ofrecidos | N:1 | RESTRICT |
| cursos_ofrecidos | cursos | N:1 | RESTRICT |
| cursos_ofrecidos | periodos_academicos | N:1 | RESTRICT |
| cursos_ofrecidos | profesores | N:1 | RESTRICT |
| cursos_ofrecidos | aulas | N:1 | RESTRICT |
| correlatividades | cursos (x2) | N:1 | CASCADE |
| horarios | cursos_ofrecidos | N:1 | CASCADE |

---

## 🎨 **DIAGRAMA VISUAL SIMPLIFICADO**

```
                    PERSONAS (25)
                    /         \
                   /           \
            ESTUDIANTES(15)  PROFESORES(10)
                  |              |
                  |              |
            INSCRIPCIONES(45)    |
                  |              |
                  └──────┬───────┘
                         │
                  CURSOS_OFRECIDOS(14)
                         │
              ┌──────────┼──────────┬──────────┐
              │          │          │          │
           CURSOS(14) PERÍODOS(4) AULAS(10) HORARIOS(16+)
              │
        CORRELATIVIDADES(8)
```

---

## 💾 **COMANDO SQL PARA GENERAR DIAGRAMA**

### **En MySQL Workbench:**

1. Conectarse a `sistema_educativo`
2. Menú: **Database** → **Reverse Engineer...**
3. Seleccionar conexión (localhost)
4. Next → Seleccionar `sistema_educativo`
5. Next → Next → Execute
6. **Se generará un diagrama EER automáticamente** ⭐

### **Exportar Diagrama:**

1. En el diagrama EER generado
2. **File** → **Export** → **Export as PNG...**
3. Guardar como: `docs/diagrama-base-datos.png`

---

## 🔍 **CONSULTA PARA VER TODO:**

```sql
USE sistema_educativo;

-- Ver todo el esquema
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

## 📊 **RESUMEN DE LA BASE DE DATOS**

```
TABLAS:           10
VISTAS:           6
FOREIGN KEYS:     10
UNIQUE KEYS:      9
CHECKS:           7
ÍNDICES:          25+
REGISTROS:        180+

ESTADO: ✅ COMPLETA Y RELACIONAL
```

---

**Para un diagrama visual profesional, usa el Reverse Engineer de MySQL Workbench.** ⭐


