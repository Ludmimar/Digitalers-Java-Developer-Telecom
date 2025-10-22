-- ========================================
-- PROYECTO FINAL: SISTEMA DE GESTIÓN EDUCATIVA
-- Autor: Ludmila Martos
-- Base de Datos: MySQL/MariaDB
-- Herramienta: MySQL Workbench
-- ========================================

-- ========================================
-- PASO 1: CREAR Y USAR LA BASE DE DATOS
-- ========================================

DROP DATABASE IF EXISTS sistema_educativo;
CREATE DATABASE IF NOT EXISTS sistema_educativo 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE sistema_educativo;

-- ========================================
-- PASO 2: CREAR TABLAS PRINCIPALES
-- ========================================

-- Tabla: Personas (Tabla base para herencia)
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
    INDEX idx_nombre (nombre, apellido)
) ENGINE=InnoDB;

-- Tabla: Estudiantes
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
    INDEX idx_estado (estado_academico)
) ENGINE=InnoDB;

-- Tabla: Profesores
CREATE TABLE profesores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    codigo_profesor VARCHAR(20) NOT NULL UNIQUE,
    fecha_contratacion DATE NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL,
    especialidad VARCHAR(100),
    grado_academico VARCHAR(50),
    estado_laboral ENUM('ACTIVO', 'LICENCIA', 'RETIRADO') DEFAULT 'ACTIVO',
    FOREIGN KEY (persona_id) REFERENCES personas(id) ON DELETE CASCADE,
    INDEX idx_codigo (codigo_profesor),
    INDEX idx_especialidad (especialidad)
) ENGINE=InnoDB;

-- Tabla: Administrativos
CREATE TABLE administrativos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    codigo_empleado VARCHAR(20) NOT NULL UNIQUE,
    fecha_contratacion DATE NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100),
    FOREIGN KEY (persona_id) REFERENCES personas(id) ON DELETE CASCADE,
    INDEX idx_cargo (cargo)
) ENGINE=InnoDB;

-- Tabla: Cursos
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
    INDEX idx_nombre (nombre)
) ENGINE=InnoDB;

-- Tabla: Periodos Académicos
CREATE TABLE periodos_academicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    semestre ENUM('1', '2') NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    activo BOOLEAN DEFAULT FALSE,
    UNIQUE KEY uk_periodo (anio, semestre),
    INDEX idx_activo (activo)
) ENGINE=InnoDB;

-- Tabla: Cursos Ofrecidos (Curso en un periodo específico)
CREATE TABLE cursos_ofrecidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT NOT NULL,
    periodo_id INT NOT NULL,
    profesor_id INT NOT NULL,
    aula VARCHAR(20),
    horario VARCHAR(100),
    cupos_disponibles INT DEFAULT 30,
    FOREIGN KEY (curso_id) REFERENCES cursos(id),
    FOREIGN KEY (periodo_id) REFERENCES periodos_academicos(id),
    FOREIGN KEY (profesor_id) REFERENCES profesores(id),
    UNIQUE KEY uk_curso_periodo (curso_id, periodo_id),
    INDEX idx_periodo (periodo_id),
    INDEX idx_profesor (profesor_id)
) ENGINE=InnoDB;

-- Tabla: Inscripciones
CREATE TABLE inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    curso_ofrecido_id INT NOT NULL,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('INSCRITO', 'CURSANDO', 'APROBADO', 'REPROBADO', 'RETIRADO') DEFAULT 'INSCRITO',
    nota_final DECIMAL(4,2),
    asistencia_porcentaje DECIMAL(5,2),
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_ofrecido_id) REFERENCES cursos_ofrecidos(id),
    UNIQUE KEY uk_estudiante_curso (estudiante_id, curso_ofrecido_id),
    INDEX idx_estudiante (estudiante_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB;

-- Tabla: Calificaciones (Evaluaciones parciales)
CREATE TABLE calificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inscripcion_id INT NOT NULL,
    tipo_evaluacion ENUM('PARCIAL', 'FINAL', 'TRABAJO', 'PROYECTO') NOT NULL,
    numero_evaluacion INT NOT NULL,
    fecha DATE NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    peso_porcentaje DECIMAL(5,2) DEFAULT 100.00,
    observaciones TEXT,
    FOREIGN KEY (inscripcion_id) REFERENCES inscripciones(id) ON DELETE CASCADE,
    INDEX idx_inscripcion (inscripcion_id),
    CHECK (nota >= 0 AND nota <= 10)
) ENGINE=InnoDB;

-- Tabla: Asistencias
CREATE TABLE asistencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inscripcion_id INT NOT NULL,
    fecha DATE NOT NULL,
    presente BOOLEAN DEFAULT FALSE,
    justificada BOOLEAN DEFAULT FALSE,
    observacion VARCHAR(200),
    FOREIGN KEY (inscripcion_id) REFERENCES inscripciones(id) ON DELETE CASCADE,
    UNIQUE KEY uk_asistencia (inscripcion_id, fecha),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB;

-- Tabla: Logs del Sistema
CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nivel ENUM('INFO', 'WARNING', 'ERROR', 'DEBUG') NOT NULL,
    modulo VARCHAR(50) NOT NULL,
    operacion VARCHAR(100) NOT NULL,
    usuario VARCHAR(100),
    descripcion TEXT,
    detalles_error TEXT,
    INDEX idx_fecha (fecha_hora),
    INDEX idx_nivel (nivel),
    INDEX idx_modulo (modulo)
) ENGINE=InnoDB;

-- ========================================
-- PASO 3: CREAR VISTAS ÚTILES
-- ========================================

-- Vista: Estudiantes Completos (con datos de persona)
CREATE OR REPLACE VIEW vista_estudiantes AS
SELECT 
    e.id,
    e.matricula,
    p.tipo_documento,
    p.numero_documento,
    p.nombre,
    p.apellido,
    p.fecha_nacimiento,
    YEAR(CURDATE()) - YEAR(p.fecha_nacimiento) AS edad,
    p.email,
    p.telefono,
    e.fecha_ingreso,
    e.promedio_general,
    e.creditos_cursados,
    e.estado_academico
FROM estudiantes e
INNER JOIN personas p ON e.persona_id = p.id
WHERE p.activo = TRUE;

-- Vista: Profesores Completos
CREATE OR REPLACE VIEW vista_profesores AS
SELECT 
    pr.id,
    pr.codigo_profesor,
    p.tipo_documento,
    p.numero_documento,
    p.nombre,
    p.apellido,
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
         p.nombre, p.apellido, p.email, p.telefono, pr.fecha_contratacion, 
         pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral;

-- Vista: Inscripciones con Detalles
CREATE OR REPLACE VIEW vista_inscripciones_detalle AS
SELECT 
    i.id AS inscripcion_id,
    e.matricula,
    CONCAT(p.nombre, ' ', p.apellido) AS estudiante,
    c.codigo_curso,
    c.nombre AS curso,
    c.creditos,
    pa.nombre AS periodo,
    CONCAT(pp.nombre, ' ', pp.apellido) AS profesor,
    i.fecha_inscripcion,
    i.estado,
    i.nota_final,
    i.asistencia_porcentaje
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas p ON e.persona_id = p.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN periodos_academicos pa ON co.periodo_id = pa.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas pp ON pr.persona_id = pp.id;

-- Vista: Estadísticas por Curso
CREATE OR REPLACE VIEW vista_estadisticas_cursos AS
SELECT 
    c.id,
    c.codigo_curso,
    c.nombre,
    COUNT(DISTINCT i.id) AS total_inscripciones,
    AVG(i.nota_final) AS promedio_notas,
    COUNT(CASE WHEN i.estado = 'APROBADO' THEN 1 END) AS aprobados,
    COUNT(CASE WHEN i.estado = 'REPROBADO' THEN 1 END) AS reprobados,
    ROUND(COUNT(CASE WHEN i.estado = 'APROBADO' THEN 1 END) * 100.0 / COUNT(i.id), 2) AS tasa_aprobacion
FROM cursos c
LEFT JOIN cursos_ofrecidos co ON c.id = co.curso_id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
GROUP BY c.id, c.codigo_curso, c.nombre;

-- ========================================
-- PASO 4: CREAR STORED PROCEDURES
-- ========================================

DELIMITER //

-- Procedure: Inscribir Estudiante en Curso
CREATE PROCEDURE sp_inscribir_estudiante(
    IN p_estudiante_id INT,
    IN p_curso_ofrecido_id INT,
    OUT p_resultado VARCHAR(100)
)
BEGIN
    DECLARE v_cupos INT;
    DECLARE v_ya_inscrito INT;
    
    -- Verificar si ya está inscrito
    SELECT COUNT(*) INTO v_ya_inscrito
    FROM inscripciones
    WHERE estudiante_id = p_estudiante_id 
    AND curso_ofrecido_id = p_curso_ofrecido_id;
    
    IF v_ya_inscrito > 0 THEN
        SET p_resultado = 'ERROR: Estudiante ya inscrito en este curso';
    ELSE
        -- Verificar cupos disponibles
        SELECT cupos_disponibles INTO v_cupos
        FROM cursos_ofrecidos
        WHERE id = p_curso_ofrecido_id;
        
        IF v_cupos > 0 THEN
            -- Inscribir
            INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado)
            VALUES (p_estudiante_id, p_curso_ofrecido_id, 'INSCRITO');
            
            -- Actualizar cupos
            UPDATE cursos_ofrecidos 
            SET cupos_disponibles = cupos_disponibles - 1
            WHERE id = p_curso_ofrecido_id;
            
            SET p_resultado = 'OK: Inscripción exitosa';
        ELSE
            SET p_resultado = 'ERROR: No hay cupos disponibles';
        END IF;
    END IF;
END //

-- Procedure: Calcular Promedio de Estudiante
CREATE PROCEDURE sp_calcular_promedio_estudiante(
    IN p_estudiante_id INT,
    OUT p_promedio DECIMAL(4,2)
)
BEGIN
    SELECT AVG(nota_final) INTO p_promedio
    FROM inscripciones
    WHERE estudiante_id = p_estudiante_id
    AND nota_final IS NOT NULL
    AND estado IN ('APROBADO', 'REPROBADO');
    
    -- Actualizar promedio general del estudiante
    UPDATE estudiantes
    SET promedio_general = IFNULL(p_promedio, 0.00)
    WHERE id = p_estudiante_id;
END //

-- Procedure: Registrar Calificación
CREATE PROCEDURE sp_registrar_calificacion(
    IN p_inscripcion_id INT,
    IN p_tipo_evaluacion VARCHAR(20),
    IN p_numero INT,
    IN p_nota DECIMAL(4,2),
    IN p_peso DECIMAL(5,2),
    OUT p_resultado VARCHAR(100)
)
BEGIN
    IF p_nota < 0 OR p_nota > 10 THEN
        SET p_resultado = 'ERROR: Nota debe estar entre 0 y 10';
    ELSE
        INSERT INTO calificaciones (inscripcion_id, tipo_evaluacion, numero_evaluacion, fecha, nota, peso_porcentaje)
        VALUES (p_inscripcion_id, p_tipo_evaluacion, p_numero, CURDATE(), p_nota, p_peso);
        
        SET p_resultado = 'OK: Calificación registrada';
    END IF;
END //

DELIMITER ;

-- ========================================
-- PASO 5: CREAR TRIGGERS
-- ========================================

-- Trigger: Actualizar promedio después de insertar calificación
DELIMITER //

CREATE TRIGGER trg_after_calificacion_insert
AFTER INSERT ON calificaciones
FOR EACH ROW
BEGIN
    DECLARE v_promedio DECIMAL(4,2);
    DECLARE v_estudiante_id INT;
    
    -- Calcular promedio ponderado del curso
    SELECT 
        SUM(nota * peso_porcentaje / 100) / SUM(peso_porcentaje / 100)
    INTO v_promedio
    FROM calificaciones
    WHERE inscripcion_id = NEW.inscripcion_id;
    
    -- Actualizar nota final de la inscripción
    UPDATE inscripciones
    SET nota_final = v_promedio
    WHERE id = NEW.inscripcion_id;
    
    -- Actualizar estado según nota
    UPDATE inscripciones
    SET estado = CASE 
        WHEN v_promedio >= 6.0 THEN 'APROBADO'
        ELSE 'REPROBADO'
    END
    WHERE id = NEW.inscripcion_id;
END //

-- Trigger: Calcular porcentaje de asistencia
CREATE TRIGGER trg_after_asistencia_insert
AFTER INSERT ON asistencias
FOR EACH ROW
BEGIN
    DECLARE v_total INT;
    DECLARE v_presentes INT;
    DECLARE v_porcentaje DECIMAL(5,2);
    
    -- Contar total de clases
    SELECT COUNT(*) INTO v_total
    FROM asistencias
    WHERE inscripcion_id = NEW.inscripcion_id;
    
    -- Contar presentes
    SELECT COUNT(*) INTO v_presentes
    FROM asistencias
    WHERE inscripcion_id = NEW.inscripcion_id
    AND presente = TRUE;
    
    -- Calcular porcentaje
    SET v_porcentaje = (v_presentes * 100.0) / v_total;
    
    -- Actualizar inscripción
    UPDATE inscripciones
    SET asistencia_porcentaje = v_porcentaje
    WHERE id = NEW.inscripcion_id;
END //

-- Trigger: Registrar log de operaciones importantes
CREATE TRIGGER trg_log_inscripcion
AFTER INSERT ON inscripciones
FOR EACH ROW
BEGIN
    INSERT INTO logs (nivel, modulo, operacion, descripcion)
    VALUES ('INFO', 'INSCRIPCIONES', 'NUEVA_INSCRIPCION', 
            CONCAT('Estudiante ID: ', NEW.estudiante_id, ' inscrito en curso ID: ', NEW.curso_ofrecido_id));
END //

DELIMITER ;

-- ========================================
-- PASO 6: CREAR FUNCIONES ÚTILES
-- ========================================

DELIMITER //

-- Función: Calcular edad
CREATE FUNCTION fn_calcular_edad(p_fecha_nacimiento DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN YEAR(CURDATE()) - YEAR(p_fecha_nacimiento);
END //

-- Función: Obtener nombre completo
CREATE FUNCTION fn_nombre_completo(p_persona_id INT)
RETURNS VARCHAR(200)
READS SQL DATA
BEGIN
    DECLARE v_nombre_completo VARCHAR(200);
    
    SELECT CONCAT(nombre, ' ', apellido) INTO v_nombre_completo
    FROM personas
    WHERE id = p_persona_id;
    
    RETURN v_nombre_completo;
END //

-- Función: Verificar si estudiante puede inscribirse
CREATE FUNCTION fn_puede_inscribirse(p_estudiante_id INT, p_curso_ofrecido_id INT)
RETURNS BOOLEAN
READS SQL DATA
BEGIN
    DECLARE v_ya_inscrito INT;
    DECLARE v_cupos INT;
    
    -- Verificar si ya está inscrito
    SELECT COUNT(*) INTO v_ya_inscrito
    FROM inscripciones
    WHERE estudiante_id = p_estudiante_id
    AND curso_ofrecido_id = p_curso_ofrecido_id;
    
    -- Verificar cupos
    SELECT cupos_disponibles INTO v_cupos
    FROM cursos_ofrecidos
    WHERE id = p_curso_ofrecido_id;
    
    RETURN (v_ya_inscrito = 0 AND v_cupos > 0);
END //

DELIMITER ;

-- ========================================
-- FIN DEL SCRIPT DE SCHEMA
-- ========================================

-- Mostrar tablas creadas
SHOW TABLES;

-- Mensaje de confirmación
SELECT 'Base de datos sistema_educativo creada exitosamente!' AS Resultado;


