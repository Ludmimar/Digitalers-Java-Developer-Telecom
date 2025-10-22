-- ========================================
-- DATOS DE PRUEBA - SISTEMA EDUCATIVO
-- Ejecutar DESPUÉS del script 01_schema.sql
-- ========================================

USE sistema_educativo;

-- ========================================
-- INSERCIÓN DE PERSONAS
-- ========================================

-- Personas que serán Estudiantes
INSERT INTO personas (tipo_documento, numero_documento, nombre, apellido, fecha_nacimiento, email, telefono, direccion) VALUES
('DNI', '12345678', 'Juan', 'Pérez', '2000-05-15', 'juan.perez@email.com', '1134567890', 'Av. Corrientes 1234'),
('DNI', '23456789', 'María', 'González', '2001-08-20', 'maria.gonzalez@email.com', '1145678901', 'Calle Falsa 123'),
('DNI', '34567890', 'Carlos', 'Rodríguez', '1999-12-10', 'carlos.rodriguez@email.com', '1156789012', 'Belgrano 456'),
('DNI', '45678901', 'Ana', 'Martínez', '2002-03-25', 'ana.martinez@email.com', '1167890123', 'San Martín 789'),
('DNI', '56789012', 'Luis', 'Fernández', '2000-11-05', 'luis.fernandez@email.com', '1178901234', 'Rivadavia 321');

-- Personas que serán Profesores
INSERT INTO personas (tipo_documento, numero_documento, nombre, apellido, fecha_nacimiento, email, telefono, direccion) VALUES
('DNI', '20123456', 'Roberto', 'Silva', '1980-04-12', 'roberto.silva@educacion.com', '1123456789', 'Av. Libertador 1000'),
('DNI', '21234567', 'Laura', 'García', '1985-07-18', 'laura.garcia@educacion.com', '1134567890', 'Av. Santa Fe 2000'),
('DNI', '22345678', 'Diego', 'López', '1978-09-22', 'diego.lopez@educacion.com', '1145678901', 'Cabildo 3000'),
('DNI', '23456780', 'Patricia', 'Romero', '1982-11-30', 'patricia.romero@educacion.com', '1156789012', 'Córdoba 4000');

-- Personas que serán Administrativos
INSERT INTO personas (tipo_documento, numero_documento, nombre, apellido, fecha_nacimiento, email, telefono, direccion) VALUES
('DNI', '30123456', 'Carmen', 'Torres', '1975-06-08', 'carmen.torres@admin.com', '1167890123', 'Callao 500'),
('DNI', '31234567', 'Jorge', 'Díaz', '1980-02-14', 'jorge.diaz@admin.com', '1178901234', 'Pueyrredón 600');

-- ========================================
-- CREAR ESTUDIANTES
-- ========================================

INSERT INTO estudiantes (persona_id, matricula, fecha_ingreso, estado_academico) VALUES
(1, 'EST-2024-001', '2024-03-01', 'ACTIVO'),
(2, 'EST-2024-002', '2024-03-01', 'ACTIVO'),
(3, 'EST-2024-003', '2024-03-01', 'ACTIVO'),
(4, 'EST-2024-004', '2024-03-01', 'ACTIVO'),
(5, 'EST-2024-005', '2024-03-01', 'ACTIVO');

-- ========================================
-- CREAR PROFESORES
-- ========================================

INSERT INTO profesores (persona_id, codigo_profesor, fecha_contratacion, sueldo, especialidad, grado_academico, estado_laboral) VALUES
(6, 'PROF-001', '2020-02-01', 75000.00, 'Programación', 'Magister', 'ACTIVO'),
(7, 'PROF-002', '2019-03-15', 80000.00, 'Bases de Datos', 'Doctor', 'ACTIVO'),
(8, 'PROF-003', '2021-08-01', 70000.00, 'Desarrollo Web', 'Licenciado', 'ACTIVO'),
(9, 'PROF-004', '2018-01-10', 85000.00, 'Matemáticas', 'Doctor', 'ACTIVO');

-- ========================================
-- CREAR ADMINISTRATIVOS
-- ========================================

INSERT INTO administrativos (persona_id, codigo_empleado, fecha_contratacion, sueldo, cargo, departamento) VALUES
(10, 'ADM-001', '2018-01-15', 60000.00, 'Secretaria Académica', 'Administración'),
(11, 'ADM-002', '2019-06-01', 55000.00, 'Asistente de Dirección', 'Dirección');

-- ========================================
-- CREAR CURSOS
-- ========================================

INSERT INTO cursos (codigo_curso, nombre, descripcion, creditos, horas_semanales, cupo_maximo, estado) VALUES
('PROG-101', 'Introducción a la Programación', 'Fundamentos de programación con Java', 4, 6, 30, 'ACTIVO'),
('PROG-102', 'Programación Orientada a Objetos', 'POO avanzada con Java', 4, 6, 30, 'ACTIVO'),
('BD-101', 'Bases de Datos I', 'Introducción a SQL y diseño de BD', 4, 6, 25, 'ACTIVO'),
('BD-102', 'Bases de Datos II', 'SQL avanzado y JDBC', 4, 6, 25, 'ACTIVO'),
('WEB-101', 'Desarrollo Web Frontend', 'HTML, CSS y JavaScript', 3, 5, 30, 'ACTIVO'),
('WEB-102', 'Desarrollo Web Backend', 'Servlets y JSP', 4, 6, 25, 'ACTIVO'),
('MAT-101', 'Matemáticas Discretas', 'Lógica y álgebra para programación', 3, 4, 30, 'ACTIVO'),
('JAVA-ADV', 'Java Avanzado', 'Colecciones, Genéricos y Patrones', 4, 6, 20, 'ACTIVO');

-- ========================================
-- CREAR PERIODOS ACADÉMICOS
-- ========================================

INSERT INTO periodos_academicos (nombre, anio, semestre, fecha_inicio, fecha_fin, activo) VALUES
('Primer Semestre 2024', 2024, '1', '2024-03-01', '2024-07-31', TRUE),
('Segundo Semestre 2024', 2024, '2', '2024-08-01', '2024-12-31', FALSE),
('Primer Semestre 2025', 2025, '1', '2025-03-01', '2025-07-31', FALSE);

-- ========================================
-- CREAR CURSOS OFRECIDOS
-- ========================================

-- Cursos del Primer Semestre 2024
INSERT INTO cursos_ofrecidos (curso_id, periodo_id, profesor_id, aula, horario, cupos_disponibles) VALUES
(1, 1, 1, 'AULA-101', 'Lunes y Miércoles 08:00-10:00', 25),
(2, 1, 1, 'AULA-102', 'Martes y Jueves 10:00-12:00', 28),
(3, 1, 2, 'LAB-201', 'Lunes y Miércoles 14:00-16:00', 20),
(4, 1, 2, 'LAB-202', 'Martes y Jueves 16:00-18:00', 22),
(5, 1, 3, 'AULA-103', 'Viernes 08:00-13:00', 28),
(7, 1, 4, 'AULA-104', 'Martes y Jueves 08:00-10:00', 29),
(8, 1, 1, 'LAB-203', 'Miércoles 14:00-18:00', 18);

-- ========================================
-- CREAR INSCRIPCIONES
-- ========================================

-- Estudiante 1: Juan Pérez (4 cursos)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado) VALUES
(1, 1, 'CURSANDO'),
(1, 3, 'CURSANDO'),
(1, 5, 'CURSANDO'),
(1, 7, 'CURSANDO');

-- Estudiante 2: María González (3 cursos)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado) VALUES
(2, 1, 'CURSANDO'),
(2, 2, 'CURSANDO'),
(2, 5, 'CURSANDO');

-- Estudiante 3: Carlos Rodríguez (5 cursos)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado) VALUES
(3, 2, 'CURSANDO'),
(3, 3, 'CURSANDO'),
(3, 4, 'CURSANDO'),
(3, 5, 'CURSANDO'),
(3, 7, 'CURSANDO');

-- Estudiante 4: Ana Martínez (3 cursos)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado) VALUES
(4, 1, 'CURSANDO'),
(4, 4, 'CURSANDO'),
(4, 7, 'CURSANDO');

-- Estudiante 5: Luis Fernández (4 cursos)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, estado) VALUES
(5, 2, 'CURSANDO'),
(5, 3, 'CURSANDO'),
(5, 5, 'CURSANDO'),
(5, 7, 'CURSANDO');

-- ========================================
-- INSERTAR CALIFICACIONES
-- ========================================

-- Calificaciones para Juan Pérez (Estudiante 1)
-- Inscripción 1: Introducción a la Programación
INSERT INTO calificaciones (inscripcion_id, tipo_evaluacion, numero_evaluacion, fecha, nota, peso_porcentaje) VALUES
(1, 'PARCIAL', 1, '2024-04-15', 8.5, 30.00),
(1, 'PARCIAL', 2, '2024-06-10', 7.8, 30.00),
(1, 'FINAL', 1, '2024-07-20', 9.0, 40.00);

-- Inscripción 2: Bases de Datos I
INSERT INTO calificaciones (inscripcion_id, tipo_evaluacion, numero_evaluacion, fecha, nota, peso_porcentaje) VALUES
(2, 'PARCIAL', 1, '2024-04-20', 9.0, 30.00),
(2, 'PARCIAL', 2, '2024-06-15', 8.5, 30.00);

-- Calificaciones para María González
INSERT INTO calificaciones (inscripcion_id, tipo_evaluacion, numero_evaluacion, fecha, nota, peso_porcentaje) VALUES
(5, 'PARCIAL', 1, '2024-04-15', 7.5, 30.00),
(5, 'PARCIAL', 2, '2024-06-10', 8.0, 30.00),
(6, 'PARCIAL', 1, '2024-04-18', 9.5, 30.00);

-- Calificaciones para Carlos Rodríguez
INSERT INTO calificaciones (inscripcion_id, tipo_evaluacion, numero_evaluacion, fecha, nota, peso_porcentaje) VALUES
(8, 'PARCIAL', 1, '2024-04-18', 6.5, 30.00),
(8, 'PARCIAL', 2, '2024-06-12', 7.0, 30.00),
(9, 'PARCIAL', 1, '2024-04-20', 8.5, 30.00);

-- ========================================
-- INSERTAR ASISTENCIAS (Ejemplo)
-- ========================================

-- Asistencias de Juan Pérez al curso de Programación (20 clases de ejemplo)
INSERT INTO asistencias (inscripcion_id, fecha, presente, justificada) VALUES
(1, '2024-03-04', TRUE, FALSE),
(1, '2024-03-06', TRUE, FALSE),
(1, '2024-03-11', TRUE, FALSE),
(1, '2024-03-13', FALSE, TRUE),
(1, '2024-03-18', TRUE, FALSE),
(1, '2024-03-20', TRUE, FALSE),
(1, '2024-03-25', TRUE, FALSE),
(1, '2024-03-27', TRUE, FALSE),
(1, '2024-04-01', TRUE, FALSE),
(1, '2024-04-03', TRUE, FALSE);

-- ========================================
-- INSERTAR LOGS INICIALES
-- ========================================

INSERT INTO logs (nivel, modulo, operacion, usuario, descripcion) VALUES
('INFO', 'SISTEMA', 'INICIO', 'ADMIN', 'Sistema inicializado correctamente'),
('INFO', 'ESTUDIANTES', 'CARGA_INICIAL', 'ADMIN', '5 estudiantes cargados'),
('INFO', 'PROFESORES', 'CARGA_INICIAL', 'ADMIN', '4 profesores cargados'),
('INFO', 'CURSOS', 'CARGA_INICIAL', 'ADMIN', '8 cursos creados'),
('INFO', 'INSCRIPCIONES', 'CARGA_INICIAL', 'ADMIN', 'Inscripciones del semestre procesadas');

-- ========================================
-- CONSULTAS DE VERIFICACIÓN
-- ========================================

-- Ver estudiantes con sus datos completos
SELECT * FROM vista_estudiantes;

-- Ver profesores con cantidad de cursos
SELECT * FROM vista_profesores;

-- Ver inscripciones con detalles
SELECT * FROM vista_inscripciones_detalle;

-- Ver estadísticas de cursos
SELECT * FROM vista_estadisticas_cursos;

-- Verificar cupos disponibles
SELECT 
    c.codigo_curso,
    c.nombre,
    co.aula,
    co.horario,
    co.cupos_disponibles,
    CONCAT(p.nombre, ' ', p.apellido) AS profesor
FROM cursos_ofrecidos co
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas p ON pr.persona_id = p.id
WHERE co.periodo_id = 1
ORDER BY c.codigo_curso;

-- Ver estudiantes inscritos por curso
SELECT 
    c.nombre AS curso,
    COUNT(i.id) AS total_inscriptos,
    c.cupo_maximo - co.cupos_disponibles AS inscriptos_reales
FROM cursos_ofrecidos co
INNER JOIN cursos c ON co.curso_id = c.id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
WHERE co.periodo_id = 1
GROUP BY c.nombre, c.cupo_maximo, co.cupos_disponibles;

-- Ver calificaciones de un estudiante
SELECT 
    c.nombre AS curso,
    cal.tipo_evaluacion,
    cal.numero_evaluacion,
    cal.fecha,
    cal.nota,
    cal.peso_porcentaje,
    i.nota_final,
    i.estado
FROM calificaciones cal
INNER JOIN inscripciones i ON cal.inscripcion_id = i.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
WHERE i.estudiante_id = 1
ORDER BY c.nombre, cal.fecha;

-- ========================================
-- MENSAJE DE CONFIRMACIÓN
-- ========================================

SELECT CONCAT(
    'Base de datos cargada con: ',
    (SELECT COUNT(*) FROM personas), ' personas, ',
    (SELECT COUNT(*) FROM estudiantes), ' estudiantes, ',
    (SELECT COUNT(*) FROM profesores), ' profesores, ',
    (SELECT COUNT(*) FROM cursos), ' cursos, ',
    (SELECT COUNT(*) FROM inscripciones), ' inscripciones'
) AS 'Resumen de Datos';


