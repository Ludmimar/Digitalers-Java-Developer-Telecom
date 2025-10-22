-- =====================================================
-- SISTEMA DE GESTIÓN EDUCATIVA v2.0
-- Script de Carga de Datos Completos y Relacionados
-- =====================================================
-- Autor: Ludmila Martos
-- Fecha: 21 de Octubre 2025
-- Propósito: Cargar datos de ejemplo coherentes y relacionados
-- =====================================================

USE sistema_educativo;

-- Deshabilitar verificaciones temporalmente
SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

SELECT '🚀 Iniciando carga de datos...' AS Status;

-- =====================================================
-- PARTE 1: PERSONAS (BASE)
-- =====================================================

INSERT INTO personas (tipo_documento, numero_documento, nombre, apellido, fecha_nacimiento, email, telefono, direccion) VALUES
-- Estudiantes (IDs 1-15)
('DNI', '12345678', 'Juan', 'Pérez', '2003-05-15', 'juan.perez@estudiante.edu.ar', '1145678901', 'Av. Corrientes 1234'),
('DNI', '23456789', 'María', 'González', '2002-08-22', 'maria.gonzalez@estudiante.edu.ar', '1156789012', 'Av. Rivadavia 2345'),
('DNI', '34567890', 'Carlos', 'Rodríguez', '2003-01-10', 'carlos.rodriguez@estudiante.edu.ar', '1167890123', 'Av. Belgrano 3456'),
('DNI', '45678901', 'Ana', 'Martínez', '2002-12-05', 'ana.martinez@estudiante.edu.ar', '1178901234', 'Av. Santa Fe 4567'),
('DNI', '56789012', 'Luis', 'Fernández', '2003-03-18', 'luis.fernandez@estudiante.edu.ar', '1189012345', 'Av. Callao 5678'),
('DNI', '67890123', 'Laura', 'López', '2002-07-25', 'laura.lopez@estudiante.edu.ar', '1190123456', 'Av. Pueyrredón 6789'),
('DNI', '78901234', 'Diego', 'García', '2003-09-30', 'diego.garcia@estudiante.edu.ar', '1101234567', 'Av. Cabildo 7890'),
('DNI', '89012345', 'Sofía', 'Ramírez', '2002-04-12', 'sofia.ramirez@estudiante.edu.ar', '1112345678', 'Av. Córdoba 8901'),
('DNI', '90123456', 'Martín', 'Torres', '2003-11-08', 'martin.torres@estudiante.edu.ar', '1123456789', 'Av. Las Heras 9012'),
('DNI', '01234567', 'Valeria', 'Sánchez', '2002-06-20', 'valeria.sanchez@estudiante.edu.ar', '1134567890', 'Av. Libertador 0123'),
('DNI', '11111111', 'Pablo', 'Domínguez', '2003-02-14', 'pablo.dominguez@estudiante.edu.ar', '1145678902', 'Av. Independencia 1111'),
('DNI', '22222222', 'Camila', 'Ruiz', '2002-10-03', 'camila.ruiz@estudiante.edu.ar', '1156789013', 'Av. Jujuy 2222'),
('DNI', '33333333', 'Facundo', 'Morales', '2003-07-19', 'facundo.morales@estudiante.edu.ar', '1167890124', 'Av. Entre Ríos 3333'),
('DNI', '44444444', 'Luciana', 'Castro', '2002-09-27', 'luciana.castro@estudiante.edu.ar', '1178901235', 'Av. 9 de Julio 4444'),
('DNI', '55555555', 'Agustín', 'Romero', '2003-04-11', 'agustin.romero@estudiante.edu.ar', '1189012346', 'Av. Medrano 5555'),

-- Profesores (IDs 16-25)
('DNI', '20111111', 'Roberto', 'Silva', '1980-03-15', 'roberto.silva@profesor.edu.ar', '1145671111', 'Av. Cabildo 1111'),
('DNI', '20222222', 'Patricia', 'Gómez', '1978-07-22', 'patricia.gomez@profesor.edu.ar', '1156782222', 'Av. Córdoba 2222'),
('DNI', '20333333', 'Jorge', 'Vázquez', '1975-11-30', 'jorge.vazquez@profesor.edu.ar', '1167893333', 'Av. Santa Fe 3333'),
('DNI', '20444444', 'Claudia', 'Herrera', '1982-05-08', 'claudia.herrera@profesor.edu.ar', '1178904444', 'Av. Libertador 4444'),
('DNI', '20555555', 'Hernán', 'Molina', '1979-09-17', 'hernan.molina@profesor.edu.ar', '1189015555', 'Av. Rivadavia 5555'),
('DNI', '20666666', 'Gabriela', 'Mendoza', '1981-01-25', 'gabriela.mendoza@profesor.edu.ar', '1190126666', 'Av. Corrientes 6666'),
('DNI', '20777777', 'Fernando', 'Ortiz', '1977-06-12', 'fernando.ortiz@profesor.edu.ar', '1101237777', 'Av. Belgrano 7777'),
('DNI', '20888888', 'Silvia', 'Rojas', '1983-10-20', 'silvia.rojas@profesor.edu.ar', '1112348888', 'Av. Callao 8888'),
('DNI', '20999999', 'Marcelo', 'Cabrera', '1976-02-28', 'marcelo.cabrera@profesor.edu.ar', '1123459999', 'Av. Pueyrredón 9999'),
('DNI', '21000000', 'Andrea', 'Benítez', '1984-08-05', 'andrea.benitez@profesor.edu.ar', '1134560000', 'Av. Las Heras 10000');

SELECT '✅ Personas insertadas (25 registros)' AS Status;

-- =====================================================
-- PARTE 2: ESTUDIANTES
-- =====================================================

INSERT INTO estudiantes (persona_id, matricula, fecha_ingreso, promedio_general, creditos_cursados, estado_academico) VALUES
(1, 'EST-2023-001', '2023-03-01', 0.00, 0, 'ACTIVO'),
(2, 'EST-2023-002', '2023-03-01', 0.00, 0, 'ACTIVO'),
(3, 'EST-2023-003', '2023-03-01', 0.00, 0, 'ACTIVO'),
(4, 'EST-2023-004', '2023-03-01', 0.00, 0, 'ACTIVO'),
(5, 'EST-2023-005', '2023-03-01', 0.00, 0, 'ACTIVO'),
(6, 'EST-2023-006', '2023-03-01', 0.00, 0, 'ACTIVO'),
(7, 'EST-2023-007', '2023-03-01', 0.00, 0, 'ACTIVO'),
(8, 'EST-2023-008', '2023-03-01', 0.00, 0, 'ACTIVO'),
(9, 'EST-2023-009', '2023-03-01', 0.00, 0, 'ACTIVO'),
(10, 'EST-2023-010', '2023-03-01', 0.00, 0, 'ACTIVO'),
(11, 'EST-2024-001', '2024-03-01', 0.00, 0, 'ACTIVO'),
(12, 'EST-2024-002', '2024-03-01', 0.00, 0, 'ACTIVO'),
(13, 'EST-2024-003', '2024-03-01', 0.00, 0, 'ACTIVO'),
(14, 'EST-2024-004', '2024-03-01', 0.00, 0, 'ACTIVO'),
(15, 'EST-2024-005', '2024-03-01', 0.00, 0, 'ACTIVO');

SELECT '✅ Estudiantes insertados (15 registros)' AS Status;

-- =====================================================
-- PARTE 3: PROFESORES
-- =====================================================

INSERT INTO profesores (persona_id, codigo_profesor, fecha_contratacion, sueldo, especialidad, grado_academico, estado_laboral) VALUES
(16, 'PROF-001', '2015-03-01', 85000.00, 'Programación', 'Magíster en Ciencias de la Computación', 'ACTIVO'),
(17, 'PROF-002', '2016-08-15', 90000.00, 'Bases de Datos', 'Doctora en Sistemas de Información', 'ACTIVO'),
(18, 'PROF-003', '2014-02-10', 95000.00, 'Matemática', 'Doctor en Matemática Aplicada', 'ACTIVO'),
(19, 'PROF-004', '2017-05-20', 82000.00, 'Redes y Comunicaciones', 'Ingeniera en Telecomunicaciones', 'ACTIVO'),
(20, 'PROF-005', '2015-09-01', 88000.00, 'Desarrollo Web', 'Magíster en Ingeniería de Software', 'ACTIVO'),
(21, 'PROF-006', '2018-03-12', 80000.00, 'Inteligencia Artificial', 'Doctora en IA', 'ACTIVO'),
(22, 'PROF-007', '2016-11-05', 87000.00, 'Sistemas Operativos', 'Ingeniero en Sistemas', 'ACTIVO'),
(23, 'PROF-008', '2019-01-15', 83000.00, 'Arquitectura de Software', 'Magíster en Arquitectura', 'ACTIVO'),
(24, 'PROF-009', '2015-06-22', 92000.00, 'Seguridad Informática', 'Doctor en Ciberseguridad', 'ACTIVO'),
(25, 'PROF-010', '2017-10-30', 86000.00, 'Cloud Computing', 'Ingeniera Cloud', 'ACTIVO');

SELECT '✅ Profesores insertados (10 registros)' AS Status;

-- =====================================================
-- PARTE 4: CURSOS
-- =====================================================

INSERT INTO cursos (codigo_curso, nombre, descripcion, creditos, horas_semanales, cupo_maximo, estado) VALUES
-- Primer Año
('PROG-101', 'Programación I', 'Introducción a la programación con Java. Conceptos básicos, variables, estructuras de control.', 6, 8, 35, 'ACTIVO'),
('MAT-101', 'Matemática Discreta', 'Lógica, conjuntos, grafos y álgebra booleana aplicados a la informática.', 5, 6, 35, 'ACTIVO'),
('ALG-101', 'Algoritmos y Estructuras de Datos I', 'Algoritmos de búsqueda y ordenamiento. Estructuras lineales.', 6, 8, 30, 'ACTIVO'),
('SIS-101', 'Introducción a Sistemas', 'Conceptos fundamentales de sistemas operativos y arquitectura de computadoras.', 4, 6, 35, 'ACTIVO'),

-- Segundo Año
('PROG-102', 'Programación II', 'POO avanzada, patrones de diseño, colecciones y excepciones en Java.', 6, 8, 30, 'ACTIVO'),
('BD-201', 'Bases de Datos I', 'Diseño de bases de datos relacionales, SQL, normalización.', 6, 8, 30, 'ACTIVO'),
('WEB-201', 'Desarrollo Web I', 'HTML5, CSS3, JavaScript y fundamentos de desarrollo frontend.', 5, 6, 30, 'ACTIVO'),
('RED-201', 'Redes y Comunicaciones', 'Protocolos de red, TCP/IP, arquitecturas cliente-servidor.', 5, 6, 28, 'ACTIVO'),

-- Tercer Año
('BD-202', 'Bases de Datos II', 'Optimización de consultas, procedimientos almacenados, transacciones.', 6, 8, 25, 'ACTIVO'),
('WEB-202', 'Desarrollo Web II', 'Frameworks backend (Servlets, JSP), APIs REST, seguridad web.', 6, 8, 25, 'ACTIVO'),
('IA-301', 'Inteligencia Artificial', 'Machine Learning, redes neuronales, procesamiento de lenguaje natural.', 6, 6, 25, 'ACTIVO'),
('SEG-301', 'Seguridad Informática', 'Criptografía, autenticación, seguridad en aplicaciones web.', 5, 6, 28, 'ACTIVO'),
('CLOUD-301', 'Cloud Computing', 'AWS, Azure, Docker, Kubernetes y arquitecturas en la nube.', 5, 6, 25, 'ACTIVO'),
('ARQ-301', 'Arquitectura de Software', 'Patrones arquitectónicos, microservicios, escalabilidad.', 5, 6, 25, 'ACTIVO');

SELECT '✅ Cursos insertados (14 registros)' AS Status;

-- =====================================================
-- PARTE 5: CORRELATIVIDADES
-- =====================================================

INSERT INTO correlatividades (curso_id, correlativa_id, tipo) VALUES
-- Programación II requiere Programación I (APROBADA)
((SELECT id FROM cursos WHERE codigo_curso = 'PROG-102'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101'), 
 'APROBADA'),

-- Algoritmos requiere Programación I (APROBADA)
((SELECT id FROM cursos WHERE codigo_curso = 'ALG-101'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101'), 
 'APROBADA'),

-- BD I requiere Programación I (REGULAR)
((SELECT id FROM cursos WHERE codigo_curso = 'BD-201'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101'), 
 'REGULAR'),

-- BD II requiere BD I (APROBADA)
((SELECT id FROM cursos WHERE codigo_curso = 'BD-202'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'BD-201'), 
 'APROBADA'),

-- Web II requiere Web I (APROBADA) y Programación II (REGULAR)
((SELECT id FROM cursos WHERE codigo_curso = 'WEB-202'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201'), 
 'APROBADA'),
((SELECT id FROM cursos WHERE codigo_curso = 'WEB-202'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102'), 
 'REGULAR'),

-- IA requiere Programación II (APROBADA) y Matemática (APROBADA)
((SELECT id FROM cursos WHERE codigo_curso = 'IA-301'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102'), 
 'APROBADA'),
((SELECT id FROM cursos WHERE codigo_curso = 'IA-301'), 
 (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101'), 
 'APROBADA');

SELECT '✅ Correlatividades insertadas (8 registros)' AS Status;

-- =====================================================
-- PARTE 6: AULAS
-- =====================================================

INSERT INTO aulas (codigo, nombre, capacidad, edificio, piso, tipo, equipamiento, estado) VALUES
('AULA-101', 'Aula 101', 35, 'Edificio A', 1, 'AULA', 'Proyector, Pizarra Digital, Aire Acondicionado, 35 sillas', 'DISPONIBLE'),
('AULA-102', 'Aula 102', 35, 'Edificio A', 1, 'AULA', 'Proyector, Pizarra, Aire Acondicionado, 35 sillas', 'DISPONIBLE'),
('AULA-103', 'Aula 103', 40, 'Edificio A', 1, 'AULA', 'Proyector, Pizarra, Ventiladores, 40 sillas', 'DISPONIBLE'),
('AULA-201', 'Aula 201', 30, 'Edificio A', 2, 'AULA', 'Proyector, Pizarra Digital, Aire Acondicionado, 30 sillas', 'DISPONIBLE'),
('AULA-202', 'Aula 202', 30, 'Edificio A', 2, 'AULA', 'Proyector, Pizarra, Aire Acondicionado, 30 sillas', 'DISPONIBLE'),
('LAB-301', 'Laboratorio de Programación 1', 30, 'Edificio B', 3, 'LABORATORIO', '30 PCs, Proyector, Aire Acondicionado, Software: JDK, Eclipse, MySQL', 'DISPONIBLE'),
('LAB-302', 'Laboratorio de Programación 2', 30, 'Edificio B', 3, 'LABORATORIO', '30 PCs, Proyector, Aire Acondicionado, Software: JDK, VS Code, Git', 'DISPONIBLE'),
('LAB-303', 'Laboratorio de Bases de Datos', 25, 'Edificio B', 3, 'LABORATORIO', '25 PCs, Proyector, MySQL Workbench, PostgreSQL, MongoDB', 'DISPONIBLE'),
('LAB-304', 'Laboratorio de Redes', 28, 'Edificio B', 3, 'LABORATORIO', '28 PCs, Switches, Routers, Cables UTP, Herramientas de red', 'DISPONIBLE'),
('AUD-401', 'Auditorio Principal', 150, 'Edificio C', 4, 'AUDITORIO', 'Sistema de Sonido Profesional, Proyector 4K, Aire Acondicionado, 150 butacas', 'DISPONIBLE');

SELECT '✅ Aulas insertadas (10 registros)' AS Status;

-- =====================================================
-- PARTE 7: PERÍODOS ACADÉMICOS
-- =====================================================

INSERT INTO periodos_academicos (nombre, anio, semestre, fecha_inicio, fecha_fin, fecha_inicio_inscripciones, fecha_fin_inscripciones, fecha_inicio_clases, fecha_fin_clases, descripcion, estado, activo) VALUES
-- Períodos Anteriores (finalizados)
('2024-1', 2024, '1', '2024-03-01', '2024-07-31', '2024-02-01', '2024-02-28', '2024-03-01', '2024-07-31', 'Primer Semestre 2024', 'FINALIZADO', FALSE),
('2024-2', 2024, '2', '2024-08-01', '2024-12-15', '2024-07-01', '2024-07-31', '2024-08-01', '2024-12-15', 'Segundo Semestre 2024', 'FINALIZADO', FALSE),

-- Período Actual (activo y en inscripción)
('2025-1', 2025, '1', '2025-03-01', '2025-07-31', '2025-02-01', '2025-02-28', '2025-03-01', '2025-07-31', 'Primer Semestre 2025', 'INSCRIPCION', TRUE),

-- Período Futuro (planificación)
('2025-2', 2025, '2', '2025-08-01', '2025-12-15', '2025-07-01', '2025-07-31', '2025-08-01', '2025-12-15', 'Segundo Semestre 2025', 'PLANIFICACION', FALSE);

SELECT '✅ Períodos académicos insertados (4 registros)' AS Status;

-- =====================================================
-- PARTE 8: CURSOS OFRECIDOS (Período 2025-1 ACTIVO)
-- =====================================================

-- Obtener el ID del período activo
SET @periodo_activo = (SELECT id FROM periodos_academicos WHERE nombre = '2025-1');

INSERT INTO cursos_ofrecidos (curso_id, periodo_id, profesor_id, aula, aula_id, horario, cupos_disponibles) VALUES
-- Primer Año
((SELECT id FROM cursos WHERE codigo_curso = 'PROG-101'), @periodo_activo, 1, 'LAB-301', (SELECT id FROM aulas WHERE codigo = 'LAB-301'), 'Lunes y Miércoles 08:00-12:00', 30),
((SELECT id FROM cursos WHERE codigo_curso = 'MAT-101'), @periodo_activo, 3, 'AULA-101', (SELECT id FROM aulas WHERE codigo = 'AULA-101'), 'Martes y Jueves 08:00-11:00', 32),
((SELECT id FROM cursos WHERE codigo_curso = 'ALG-101'), @periodo_activo, 1, 'LAB-302', (SELECT id FROM aulas WHERE codigo = 'LAB-302'), 'Martes y Jueves 14:00-18:00', 28),
((SELECT id FROM cursos WHERE codigo_curso = 'SIS-101'), @periodo_activo, 7, 'AULA-102', (SELECT id FROM aulas WHERE codigo = 'AULA-102'), 'Viernes 08:00-14:00', 33),

-- Segundo Año
((SELECT id FROM cursos WHERE codigo_curso = 'PROG-102'), @periodo_activo, 1, 'LAB-301', (SELECT id FROM aulas WHERE codigo = 'LAB-301'), 'Lunes y Miércoles 14:00-18:00', 28),
((SELECT id FROM cursos WHERE codigo_curso = 'BD-201'), @periodo_activo, 2, 'LAB-303', (SELECT id FROM aulas WHERE codigo = 'LAB-303'), 'Martes y Jueves 08:00-12:00', 25),
((SELECT id FROM cursos WHERE codigo_curso = 'WEB-201'), @periodo_activo, 5, 'LAB-302', (SELECT id FROM aulas WHERE codigo = 'LAB-302'), 'Lunes y Miércoles 14:00-17:00', 28),
((SELECT id FROM cursos WHERE codigo_curso = 'RED-201'), @periodo_activo, 4, 'LAB-304', (SELECT id FROM aulas WHERE codigo = 'LAB-304'), 'Martes y Jueves 14:00-17:00', 26),

-- Tercer Año
((SELECT id FROM cursos WHERE codigo_curso = 'BD-202'), @periodo_activo, 2, 'LAB-303', (SELECT id FROM aulas WHERE codigo = 'LAB-303'), 'Lunes y Miércoles 08:00-12:00', 23),
((SELECT id FROM cursos WHERE codigo_curso = 'WEB-202'), @periodo_activo, 5, 'LAB-302', (SELECT id FROM aulas WHERE codigo = 'LAB-302'), 'Viernes 08:00-14:00', 23),
((SELECT id FROM cursos WHERE codigo_curso = 'IA-301'), @periodo_activo, 6, 'LAB-301', (SELECT id FROM aulas WHERE codigo = 'LAB-301'), 'Martes y Jueves 18:00-21:00', 24),
((SELECT id FROM cursos WHERE codigo_curso = 'SEG-301'), @periodo_activo, 9, 'AULA-201', (SELECT id FROM aulas WHERE codigo = 'AULA-201'), 'Miércoles 14:00-20:00', 26),
((SELECT id FROM cursos WHERE codigo_curso = 'CLOUD-301'), @periodo_activo, 10, 'LAB-302', (SELECT id FROM aulas WHERE codigo = 'LAB-302'), 'Jueves 18:00-21:00', 24),
((SELECT id FROM cursos WHERE codigo_curso = 'ARQ-301'), @periodo_activo, 8, 'AULA-202', (SELECT id FROM aulas WHERE codigo = 'AULA-202'), 'Viernes 14:00-20:00', 24);

SELECT '✅ Cursos ofrecidos insertados (14 registros - Período 2025-1)' AS Status;

-- =====================================================
-- PARTE 9: HORARIOS DETALLADOS
-- =====================================================

-- PROG-101: Lunes y Miércoles 08:00-12:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), 'LUNES', '08:00:00', '12:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), 'MIERCOLES', '08:00:00', '12:00:00');

-- MAT-101: Martes y Jueves 08:00-11:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), 'MARTES', '08:00:00', '11:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), 'JUEVES', '08:00:00', '11:00:00');

-- ALG-101: Martes y Jueves 14:00-18:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ALG-101') AND periodo_id = @periodo_activo), 'MARTES', '14:00:00', '18:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ALG-101') AND periodo_id = @periodo_activo), 'JUEVES', '14:00:00', '18:00:00');

-- SIS-101: Viernes 08:00-14:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SIS-101') AND periodo_id = @periodo_activo), 'VIERNES', '08:00:00', '14:00:00');

-- PROG-102: Lunes y Miércoles 14:00-18:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), 'LUNES', '14:00:00', '18:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), 'MIERCOLES', '14:00:00', '18:00:00');

-- BD-201: Martes y Jueves 08:00-12:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), 'MARTES', '08:00:00', '12:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), 'JUEVES', '08:00:00', '12:00:00');

-- WEB-201: Lunes y Miércoles 14:00-17:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), 'LUNES', '14:00:00', '17:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), 'MIERCOLES', '14:00:00', '17:00:00');

-- RED-201: Martes y Jueves 14:00-17:00
INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'RED-201') AND periodo_id = @periodo_activo), 'MARTES', '14:00:00', '17:00:00'),
((SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'RED-201') AND periodo_id = @periodo_activo), 'JUEVES', '14:00:00', '17:00:00');

SELECT '✅ Horarios detallados insertados (16 registros)' AS Status;

-- =====================================================
-- PARTE 10: INSCRIPCIONES
-- =====================================================

-- Estudiantes de primer año (IDs 11-15) en cursos de primer año
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, fecha_inscripcion, estado, nota_final, fecha_aprobacion) VALUES
-- Estudiante 11 (4 cursos - dentro del límite de créditos: 6+5+6+4=21)
(11, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(11, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(11, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ALG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(11, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SIS-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 12 (3 cursos: 6+5+6=17)
(12, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(12, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(12, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ALG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 13 (4 cursos: 6+5+6+4=21)
(13, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(13, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(13, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ALG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(13, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SIS-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 14 (3 cursos: 6+5+4=15)
(14, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(14, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(14, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SIS-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 15 (2 cursos: 6+5=11)
(15, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(15, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'MAT-101') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL);

-- Estudiantes de segundo año (IDs 6-10) en cursos de segundo año
-- (Suponen que aprobaron PROG-101 en períodos anteriores)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, fecha_inscripcion, estado, nota_final, fecha_aprobacion) VALUES
-- Estudiante 6 (4 cursos: 6+6+5+5=22)
(6, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(6, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(6, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(6, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'RED-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 7 (3 cursos: 6+6+5=17)
(7, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(7, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(7, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 8 (3 cursos: 6+5+5=16)
(8, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(8, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(8, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'RED-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 9 (2 cursos: 6+6=12)
(9, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(9, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 10 (4 cursos: 6+6+5+5=22)
(10, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'PROG-102') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(10, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(10, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(10, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'RED-201') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL);

-- Estudiantes de tercer año (IDs 1-5) en cursos avanzados
-- (Suponen que aprobaron todas las correlativas necesarias)
INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, fecha_inscripcion, estado, nota_final, fecha_aprobacion) VALUES
-- Estudiante 1 (4 cursos avanzados: 6+6+6+5=23)
(1, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(1, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(1, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'IA-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(1, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ARQ-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 2 (3 cursos: 6+5+5=16)
(2, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(2, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SEG-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(2, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'CLOUD-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 3 (4 cursos: 6+6+5+5=22)
(3, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(3, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'IA-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(3, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'SEG-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(3, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ARQ-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 4 (3 cursos: 6+5+5=16)
(4, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'WEB-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(4, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'CLOUD-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(4, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'ARQ-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),

-- Estudiante 5 (2 cursos: 6+6=12)
(5, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'BD-202') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL),
(5, (SELECT id FROM cursos_ofrecidos WHERE curso_id = (SELECT id FROM cursos WHERE codigo_curso = 'IA-301') AND periodo_id = @periodo_activo), NOW(), 'CURSANDO', NULL, NULL);

SELECT '✅ Inscripciones insertadas (45 registros)' AS Status;

-- =====================================================
-- PARTE 11: ACTUALIZAR CUPOS DISPONIBLES
-- =====================================================

-- Actualizar cupos basándose en las inscripciones
UPDATE cursos_ofrecidos co
SET cupos_disponibles = cupos_disponibles - (
    SELECT COUNT(*) 
    FROM inscripciones i 
    WHERE i.curso_ofrecido_id = co.id
)
WHERE periodo_id = @periodo_activo;

SELECT '✅ Cupos actualizados' AS Status;

-- =====================================================
-- PARTE 12: VERIFICACIÓN FINAL
-- =====================================================

SELECT '' AS '';
SELECT '📊 RESUMEN DE DATOS CARGADOS:' AS Titulo;
SELECT '' AS '';

SELECT 'Personas:' AS Tabla, COUNT(*) AS Total FROM personas
UNION ALL
SELECT 'Estudiantes:', COUNT(*) FROM estudiantes
UNION ALL
SELECT 'Profesores:', COUNT(*) FROM profesores
UNION ALL
SELECT 'Cursos:', COUNT(*) FROM cursos
UNION ALL
SELECT 'Correlatividades:', COUNT(*) FROM correlatividades
UNION ALL
SELECT 'Aulas:', COUNT(*) FROM aulas
UNION ALL
SELECT 'Períodos Académicos:', COUNT(*) FROM periodos_academicos
UNION ALL
SELECT 'Cursos Ofrecidos:', COUNT(*) FROM cursos_ofrecidos
UNION ALL
SELECT 'Horarios:', COUNT(*) FROM horarios
UNION ALL
SELECT 'Inscripciones:', COUNT(*) FROM inscripciones;

SELECT '' AS '';
SELECT '🎓 ESTADÍSTICAS DEL PERÍODO ACTIVO (2025-1):' AS Titulo;
SELECT '' AS '';

SELECT 
    'Total de Cursos Ofrecidos' AS Descripcion,
    COUNT(*) AS Valor
FROM cursos_ofrecidos
WHERE periodo_id = @periodo_activo
UNION ALL
SELECT 
    'Total de Inscripciones',
    COUNT(*)
FROM inscripciones i
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
WHERE co.periodo_id = @periodo_activo
UNION ALL
SELECT 
    'Estudiantes Inscritos',
    COUNT(DISTINCT i.estudiante_id)
FROM inscripciones i
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
WHERE co.periodo_id = @periodo_activo
UNION ALL
SELECT 
    'Profesores Activos',
    COUNT(DISTINCT co.profesor_id)
FROM cursos_ofrecidos co
WHERE co.periodo_id = @periodo_activo;

-- Reactivar verificaciones
SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;

SELECT '' AS '';
SELECT '✅ CARGA DE DATOS COMPLETADA EXITOSAMENTE' AS Resultado;
SELECT '🎓 Sistema de Gestión Educativa v2.0' AS Sistema;
SELECT 'Base de datos lista para usar' AS Estado;

-- =====================================================
-- FIN DEL SCRIPT
-- =====================================================


