-- =====================================================
-- SISTEMA DE GESTIÓN EDUCATIVA v2.0
-- Script de Creación de Base de Datos Completa
-- =====================================================
-- Autor: Ludmila Martos
-- Fecha: 21 de Octubre 2025
-- Base de Datos: MySQL 8.0+
-- Codificación: UTF-8
-- =====================================================

-- =====================================================
-- PASO 1: CREAR Y USAR LA BASE DE DATOS
-- =====================================================

DROP DATABASE IF EXISTS sistema_educativo;
CREATE DATABASE IF NOT EXISTS sistema_educativo 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE sistema_educativo;

SELECT '🚀 Base de datos sistema_educativo creada' AS Status;

-- =====================================================
-- PASO 2: CREAR TABLAS PRINCIPALES
-- =====================================================

-- -----------------------------------------------------
-- Tabla: personas (Tabla base para herencia)
-- -----------------------------------------------------
CREATE TABLE personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(20) NOT NULL,
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(150),
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,
    INDEX idx_documento (tipo_documento, numero_documento),
    INDEX idx_nombre (nombre, apellido),
    INDEX idx_email (email)
) ENGINE=InnoDB;

SELECT '✅ Tabla personas creada' AS Status;

-- -----------------------------------------------------
-- Tabla: estudiantes
-- -----------------------------------------------------
CREATE TABLE estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL,
    promedio_general DECIMAL(4,2) DEFAULT 0.00,
    creditos_cursados INT DEFAULT 0,
    estado_academico ENUM('ACTIVO', 'INACTIVO', 'GRADUADO', 'SUSPENDIDO') DEFAULT 'ACTIVO',
    FOREIGN KEY (persona_id) REFERENCES personas(id) ON DELETE CASCADE,
    INDEX idx_matricula (matricula),
    INDEX idx_estado (estado_academico),
    CHECK (promedio_general >= 0 AND promedio_general <= 10)
) ENGINE=InnoDB;

SELECT '✅ Tabla estudiantes creada' AS Status;

-- -----------------------------------------------------
-- Tabla: profesores
-- -----------------------------------------------------
CREATE TABLE profesores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    codigo_profesor VARCHAR(20) NOT NULL UNIQUE,
    fecha_contratacion DATE NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL,
    especialidad VARCHAR(100),
    grado_academico VARCHAR(50),
    estado_laboral ENUM('ACTIVO', 'INACTIVO', 'LICENCIA', 'JUBILADO') DEFAULT 'ACTIVO',
    FOREIGN KEY (persona_id) REFERENCES personas(id) ON DELETE CASCADE,
    INDEX idx_codigo (codigo_profesor),
    INDEX idx_especialidad (especialidad),
    INDEX idx_estado (estado_laboral)
) ENGINE=InnoDB;

SELECT '✅ Tabla profesores creada' AS Status;

-- -----------------------------------------------------
-- Tabla: cursos
-- -----------------------------------------------------
CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_curso VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    creditos INT NOT NULL DEFAULT 3,
    horas_semanales INT NOT NULL DEFAULT 4,
    cupo_maximo INT DEFAULT 30,
    estado ENUM('ACTIVO', 'INACTIVO', 'COMPLETO') DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_codigo (codigo_curso),
    INDEX idx_nombre (nombre),
    INDEX idx_estado (estado),
    CHECK (creditos > 0),
    CHECK (horas_semanales > 0),
    CHECK (cupo_maximo > 0)
) ENGINE=InnoDB;

SELECT '✅ Tabla cursos creada' AS Status;

-- -----------------------------------------------------
-- Tabla: correlatividades (NUEVA - v2.0)
-- -----------------------------------------------------
CREATE TABLE correlatividades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    curso_id INT NOT NULL COMMENT 'Curso que tiene la correlativa',
    correlativa_id INT NOT NULL COMMENT 'Curso que es correlativa (prerequisito)',
    tipo ENUM('REGULAR', 'APROBADA') DEFAULT 'APROBADA' COMMENT 'REGULAR: solo cursada, APROBADA: cursada y aprobada',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE CASCADE,
    FOREIGN KEY (correlativa_id) REFERENCES cursos(id) ON DELETE CASCADE,
    UNIQUE KEY uk_correlativa (curso_id, correlativa_id),
    INDEX idx_curso (curso_id),
    INDEX idx_correlativa (correlativa_id),
    CHECK (curso_id != correlativa_id)
) ENGINE=InnoDB;

SELECT '✅ Tabla correlatividades creada' AS Status;

-- -----------------------------------------------------
-- Tabla: aulas (NUEVA - v2.0)
-- -----------------------------------------------------
CREATE TABLE aulas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    capacidad INT NOT NULL,
    edificio VARCHAR(50),
    piso INT,
    tipo ENUM('AULA', 'LABORATORIO', 'AUDITORIO', 'TALLER') DEFAULT 'AULA',
    equipamiento TEXT,
    estado ENUM('DISPONIBLE', 'EN_USO', 'MANTENIMIENTO', 'INACTIVA') DEFAULT 'DISPONIBLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_codigo (codigo),
    INDEX idx_estado (estado),
    INDEX idx_tipo (tipo),
    CHECK (capacidad > 0)
) ENGINE=InnoDB;

SELECT '✅ Tabla aulas creada' AS Status;

-- -----------------------------------------------------
-- Tabla: periodos_academicos (EXTENDIDA - v2.0)
-- -----------------------------------------------------
CREATE TABLE periodos_academicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    semestre ENUM('1', '2') NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    -- Nuevos campos v2.0
    fecha_inicio_inscripciones DATE,
    fecha_fin_inscripciones DATE,
    fecha_inicio_clases DATE,
    fecha_fin_clases DATE,
    descripcion TEXT,
    estado ENUM('PLANIFICACION', 'INSCRIPCION', 'CURSANDO', 'FINALIZADO') DEFAULT 'PLANIFICACION',
    activo BOOLEAN DEFAULT FALSE,
    UNIQUE KEY uk_periodo (anio, semestre),
    INDEX idx_activo (activo),
    INDEX idx_estado (estado),
    INDEX idx_anio_semestre (anio, semestre)
) ENGINE=InnoDB;

SELECT '✅ Tabla periodos_academicos creada' AS Status;

-- -----------------------------------------------------
-- Tabla: cursos_ofrecidos (EXTENDIDA - v2.0)
-- -----------------------------------------------------
CREATE TABLE cursos_ofrecidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT NOT NULL,
    periodo_id INT NOT NULL,
    profesor_id INT NOT NULL,
    aula VARCHAR(20),
    aula_id INT, -- Nuevo v2.0
    horario VARCHAR(100),
    cupos_disponibles INT DEFAULT 30,
    FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE RESTRICT,
    FOREIGN KEY (periodo_id) REFERENCES periodos_academicos(id) ON DELETE RESTRICT,
    FOREIGN KEY (profesor_id) REFERENCES profesores(id) ON DELETE RESTRICT,
    FOREIGN KEY (aula_id) REFERENCES aulas(id) ON DELETE RESTRICT, -- Nuevo v2.0
    UNIQUE KEY uk_curso_periodo (curso_id, periodo_id),
    INDEX idx_periodo (periodo_id),
    INDEX idx_profesor (profesor_id),
    INDEX idx_aula (aula_id),
    CHECK (cupos_disponibles >= 0)
) ENGINE=InnoDB;

SELECT '✅ Tabla cursos_ofrecidos creada' AS Status;

-- -----------------------------------------------------
-- Tabla: horarios (NUEVA - v2.0)
-- -----------------------------------------------------
CREATE TABLE horarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    curso_ofrecido_id INT NOT NULL,
    dia_semana ENUM('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO') NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (curso_ofrecido_id) REFERENCES cursos_ofrecidos(id) ON DELETE CASCADE,
    INDEX idx_curso_ofrecido (curso_ofrecido_id),
    INDEX idx_dia_semana (dia_semana),
    INDEX idx_horario (hora_inicio, hora_fin),
    CHECK (hora_fin > hora_inicio)
) ENGINE=InnoDB;

SELECT '✅ Tabla horarios creada' AS Status;

-- -----------------------------------------------------
-- Tabla: inscripciones (EXTENDIDA - v2.0)
-- -----------------------------------------------------
CREATE TABLE inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    curso_ofrecido_id INT NOT NULL,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE', 'CURSANDO', 'APROBADO', 'REPROBADO') DEFAULT 'PENDIENTE',
    nota_final DECIMAL(4,2),
    fecha_aprobacion DATE,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_ofrecido_id) REFERENCES cursos_ofrecidos(id) ON DELETE RESTRICT,
    UNIQUE KEY uk_estudiante_curso (estudiante_id, curso_ofrecido_id),
    INDEX idx_estudiante (estudiante_id),
    INDEX idx_curso_ofrecido (curso_ofrecido_id),
    INDEX idx_estado (estado),
    INDEX idx_fecha (fecha_inscripcion),
    CHECK (nota_final IS NULL OR (nota_final >= 0 AND nota_final <= 10))
) ENGINE=InnoDB;

SELECT '✅ Tabla inscripciones creada' AS Status;

-- =====================================================
-- PASO 3: CREAR VISTAS
-- =====================================================

-- Vista: Estudiantes Completos
CREATE OR REPLACE VIEW vista_estudiantes_completos AS
SELECT 
    e.id,
    e.matricula,
    p.tipo_documento,
    p.numero_documento,
    p.nombre,
    p.apellido,
    CONCAT(p.nombre, ' ', p.apellido) AS nombre_completo,
    p.fecha_nacimiento,
    YEAR(CURDATE()) - YEAR(p.fecha_nacimiento) AS edad,
    p.email,
    p.telefono,
    p.direccion,
    e.fecha_ingreso,
    e.promedio_general,
    e.creditos_cursados,
    e.estado_academico,
    COUNT(DISTINCT i.id) AS total_inscripciones
FROM estudiantes e
INNER JOIN personas p ON e.persona_id = p.id
LEFT JOIN inscripciones i ON e.id = i.estudiante_id
WHERE p.activo = TRUE
GROUP BY e.id, e.matricula, p.tipo_documento, p.numero_documento, 
         p.nombre, p.apellido, p.fecha_nacimiento, p.email, p.telefono,
         p.direccion, e.fecha_ingreso, e.promedio_general, 
         e.creditos_cursados, e.estado_academico;

SELECT '✅ Vista vista_estudiantes_completos creada' AS Status;

-- Vista: Profesores Completos
CREATE OR REPLACE VIEW vista_profesores_completos AS
SELECT 
    pr.id,
    pr.codigo_profesor,
    p.tipo_documento,
    p.numero_documento,
    p.nombre,
    p.apellido,
    CONCAT(p.nombre, ' ', p.apellido) AS nombre_completo,
    p.fecha_nacimiento,
    p.email,
    p.telefono,
    pr.fecha_contratacion,
    pr.sueldo,
    pr.especialidad,
    pr.grado_academico,
    pr.estado_laboral,
    COUNT(DISTINCT co.id) AS cursos_asignados
FROM profesores pr
INNER JOIN personas p ON pr.persona_id = p.id
LEFT JOIN cursos_ofrecidos co ON pr.id = co.profesor_id
WHERE p.activo = TRUE
GROUP BY pr.id, pr.codigo_profesor, p.tipo_documento, p.numero_documento, 
         p.nombre, p.apellido, p.fecha_nacimiento, p.email, p.telefono, 
         pr.fecha_contratacion, pr.sueldo, pr.especialidad, 
         pr.grado_academico, pr.estado_laboral;

SELECT '✅ Vista vista_profesores_completos creada' AS Status;

-- Vista: Inscripciones con Detalles
CREATE OR REPLACE VIEW vista_inscripciones_detalle AS
SELECT 
    i.id AS inscripcion_id,
    e.id AS estudiante_id,
    e.matricula,
    CONCAT(pe.nombre, ' ', pe.apellido) AS estudiante_nombre,
    c.id AS curso_id,
    c.codigo_curso,
    c.nombre AS curso_nombre,
    c.creditos,
    pa.id AS periodo_id,
    pa.nombre AS periodo_nombre,
    pr.id AS profesor_id,
    pr.codigo_profesor,
    CONCAT(pp.nombre, ' ', pp.apellido) AS profesor_nombre,
    co.aula,
    co.horario,
    i.fecha_inscripcion,
    i.estado,
    i.nota_final,
    i.fecha_aprobacion
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas pe ON e.persona_id = pe.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN periodos_academicos pa ON co.periodo_id = pa.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas pp ON pr.persona_id = pp.id;

SELECT '✅ Vista vista_inscripciones_detalle creada' AS Status;

-- Vista: Ocupación de Aulas (NUEVA - v2.0)
CREATE OR REPLACE VIEW vista_ocupacion_aulas AS
SELECT 
    a.id,
    a.codigo,
    a.nombre,
    a.capacidad,
    a.edificio,
    a.piso,
    a.tipo,
    a.estado,
    COUNT(DISTINCT co.id) AS cursos_asignados,
    COALESCE(SUM(co.cupos_disponibles), 0) AS estudiantes_total,
    CASE 
        WHEN a.capacidad > 0 THEN ROUND((COALESCE(SUM(co.cupos_disponibles), 0) / a.capacidad) * 100, 2)
        ELSE 0
    END AS porcentaje_ocupacion
FROM aulas a
LEFT JOIN cursos_ofrecidos co ON a.id = co.aula_id
GROUP BY a.id, a.codigo, a.nombre, a.capacidad, a.edificio, 
         a.piso, a.tipo, a.estado
ORDER BY porcentaje_ocupacion DESC;

SELECT '✅ Vista vista_ocupacion_aulas creada' AS Status;

-- Vista: Períodos Completos (NUEVA - v2.0)
CREATE OR REPLACE VIEW vista_periodos_completos AS
SELECT 
    pa.id,
    pa.nombre,
    pa.anio,
    pa.semestre,
    pa.activo,
    pa.estado,
    pa.fecha_inicio,
    pa.fecha_fin,
    pa.fecha_inicio_inscripciones,
    pa.fecha_fin_inscripciones,
    pa.fecha_inicio_clases,
    pa.fecha_fin_clases,
    pa.descripcion,
    COUNT(DISTINCT co.id) AS cursos_ofrecidos,
    COUNT(DISTINCT i.id) AS inscripciones_total,
    COUNT(DISTINCT i.estudiante_id) AS estudiantes_inscritos
FROM periodos_academicos pa
LEFT JOIN cursos_ofrecidos co ON pa.id = co.periodo_id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
GROUP BY pa.id, pa.nombre, pa.anio, pa.semestre, pa.activo, pa.estado,
         pa.fecha_inicio, pa.fecha_fin, pa.fecha_inicio_inscripciones, 
         pa.fecha_fin_inscripciones, pa.fecha_inicio_clases, 
         pa.fecha_fin_clases, pa.descripcion
ORDER BY pa.anio DESC, pa.semestre DESC;

SELECT '✅ Vista vista_periodos_completos creada' AS Status;

-- Vista: Estadísticas por Curso
CREATE OR REPLACE VIEW vista_estadisticas_cursos AS
SELECT 
    c.id,
    c.codigo_curso,
    c.nombre,
    c.creditos,
    c.estado AS estado_curso,
    COUNT(DISTINCT i.id) AS total_inscripciones,
    AVG(i.nota_final) AS promedio_notas,
    COUNT(CASE WHEN i.estado = 'APROBADO' THEN 1 END) AS aprobados,
    COUNT(CASE WHEN i.estado = 'REPROBADO' THEN 1 END) AS reprobados,
    COUNT(CASE WHEN i.estado = 'CURSANDO' THEN 1 END) AS cursando,
    CASE 
        WHEN COUNT(i.id) > 0 THEN
            ROUND(COUNT(CASE WHEN i.estado = 'APROBADO' THEN 1 END) * 100.0 / COUNT(i.id), 2)
        ELSE 0
    END AS tasa_aprobacion
FROM cursos c
LEFT JOIN cursos_ofrecidos co ON c.id = co.curso_id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
GROUP BY c.id, c.codigo_curso, c.nombre, c.creditos, c.estado;

SELECT '✅ Vista vista_estadisticas_cursos creada' AS Status;

-- =====================================================
-- PASO 4: VERIFICACIÓN FINAL
-- =====================================================

-- Mostrar todas las tablas creadas
SELECT '' AS '';
SELECT '📊 TABLAS CREADAS:' AS Resultado;
SHOW TABLES;

SELECT '' AS '';
SELECT '👁️ VISTAS CREADAS:' AS Resultado;
SELECT TABLE_NAME AS Vista
FROM information_schema.VIEWS
WHERE TABLE_SCHEMA = 'sistema_educativo'
ORDER BY TABLE_NAME;

SELECT '' AS '';
SELECT '🔗 RELACIONES (FOREIGN KEYS):' AS Resultado;
SELECT 
    TABLE_NAME AS Tabla,
    CONSTRAINT_NAME AS `Constraint`,
    REFERENCED_TABLE_NAME AS Referencia
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'sistema_educativo'
AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME, CONSTRAINT_NAME;

SELECT '' AS '';
SELECT '✅ BASE DE DATOS CREADA EXITOSAMENTE' AS Resultado;
SELECT '🎓 Sistema de Gestión Educativa v2.0' AS Sistema;
SELECT 'Listo para cargar datos' AS Estado;

-- =====================================================
-- FIN DEL SCRIPT
-- =====================================================

