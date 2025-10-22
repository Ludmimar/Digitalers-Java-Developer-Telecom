-- ========================================
-- CONSULTAS ÚTILES PARA EL SISTEMA
-- Autor: Ludmila Martos
-- ========================================

USE sistema_educativo;

-- ========================================
-- CONSULTAS DE ESTUDIANTES
-- ========================================

-- Listar todos los estudiantes activos
SELECT * FROM vista_estudiantes WHERE estado_academico = 'ACTIVO';

-- Estudiantes con mejor promedio
SELECT matricula, nombre, apellido, promedio_general
FROM vista_estudiantes
WHERE promedio_general > 0
ORDER BY promedio_general DESC
LIMIT 10;

-- Estudiantes por rango de edad
SELECT 
    CASE 
        WHEN edad BETWEEN 18 AND 20 THEN '18-20 años'
        WHEN edad BETWEEN 21 AND 25 THEN '21-25 años'
        ELSE '25+ años'
    END AS rango_edad,
    COUNT(*) AS cantidad
FROM vista_estudiantes
GROUP BY rango_edad;

-- ========================================
-- CONSULTAS DE CURSOS
-- ========================================

-- Cursos más populares (más inscripciones)
SELECT 
    c.codigo_curso,
    c.nombre,
    COUNT(i.id) AS total_inscripciones
FROM cursos c
INNER JOIN cursos_ofrecidos co ON c.id = co.curso_id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
GROUP BY c.id, c.codigo_curso, c.nombre
ORDER BY total_inscripciones DESC;

-- Cursos con cupos disponibles
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
WHERE co.cupos_disponibles > 0
AND co.periodo_id = (SELECT id FROM periodos_academicos WHERE activo = TRUE);

-- Estadísticas de aprobación por curso
SELECT * FROM vista_estadisticas_cursos
WHERE total_inscripciones > 0
ORDER BY tasa_aprobacion DESC;

-- ========================================
-- CONSULTAS DE PROFESORES
-- ========================================

-- Profesores con más cursos asignados
SELECT * FROM vista_profesores
ORDER BY cursos_asignados DESC;

-- Profesores por especialidad
SELECT 
    especialidad,
    COUNT(*) AS cantidad,
    AVG(sueldo) AS sueldo_promedio
FROM profesores
GROUP BY especialidad;

-- ========================================
-- CONSULTAS DE INSCRIPCIONES
-- ========================================

-- Inscripciones del periodo actual
SELECT * FROM vista_inscripciones_detalle
WHERE periodo LIKE '%2024%'
ORDER BY estudiante, curso;

-- Estudiantes inscritos en más cursos
SELECT 
    e.matricula,
    CONCAT(p.nombre, ' ', p.apellido) AS estudiante,
    COUNT(i.id) AS total_cursos,
    SUM(c.creditos) AS total_creditos
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas p ON e.persona_id = p.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
GROUP BY e.id, e.matricula, p.nombre, p.apellido
ORDER BY total_cursos DESC;

-- ========================================
-- CONSULTAS DE CALIFICACIONES
-- ========================================

-- Promedio de notas por estudiante
SELECT 
    e.matricula,
    CONCAT(p.nombre, ' ', p.apellido) AS estudiante,
    AVG(i.nota_final) AS promedio,
    COUNT(i.id) AS cursos_con_nota
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas p ON e.persona_id = p.id
WHERE i.nota_final IS NOT NULL
GROUP BY e.id, e.matricula, p.nombre, p.apellido
ORDER BY promedio DESC;

-- Distribución de calificaciones
SELECT 
    CASE 
        WHEN nota_final >= 9 THEN 'Excelente (9-10)'
        WHEN nota_final >= 7 THEN 'Bueno (7-8.9)'
        WHEN nota_final >= 6 THEN 'Aprobado (6-6.9)'
        ELSE 'Reprobado (0-5.9)'
    END AS categoria,
    COUNT(*) AS cantidad,
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM inscripciones WHERE nota_final IS NOT NULL), 2) AS porcentaje
FROM inscripciones
WHERE nota_final IS NOT NULL
GROUP BY categoria
ORDER BY MIN(nota_final) DESC;

-- ========================================
-- CONSULTAS DE ASISTENCIAS
-- ========================================

-- Asistencia por estudiante
SELECT 
    e.matricula,
    CONCAT(p.nombre, ' ', p.apellido) AS estudiante,
    i.asistencia_porcentaje,
    CASE 
        WHEN i.asistencia_porcentaje >= 80 THEN 'Regular'
        WHEN i.asistencia_porcentaje >= 60 THEN 'En Riesgo'
        ELSE 'Irregular'
    END AS estado_asistencia
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas p ON e.persona_id = p.id
WHERE i.asistencia_porcentaje IS NOT NULL
ORDER BY i.asistencia_porcentaje DESC;

-- ========================================
-- REPORTES PARA EXPORTAR
-- ========================================

-- Reporte completo de estudiante (para certificado)
SELECT 
    e.matricula,
    CONCAT(p.nombre, ' ', p.apellido) AS nombre_completo,
    CONCAT(p.tipo_documento, ' ', p.numero_documento) AS documento,
    c.nombre AS curso,
    i.nota_final,
    i.estado,
    pa.nombre AS periodo
FROM inscripciones i
INNER JOIN estudiantes e ON i.estudiante_id = e.id
INNER JOIN personas p ON e.persona_id = p.id
INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN periodos_academicos pa ON co.periodo_id = pa.id
WHERE e.matricula = 'EST-2024-001'  -- Cambiar por matrícula deseada
ORDER BY pa.anio DESC, pa.semestre DESC;

-- Reporte de cursos para un periodo
SELECT 
    c.codigo_curso,
    c.nombre,
    c.creditos,
    CONCAT(p.nombre, ' ', p.apellido) AS profesor,
    co.horario,
    co.aula,
    COUNT(i.id) AS inscriptos,
    co.cupos_disponibles
FROM cursos_ofrecidos co
INNER JOIN cursos c ON co.curso_id = c.id
INNER JOIN profesores pr ON co.profesor_id = pr.id
INNER JOIN personas p ON pr.persona_id = p.id
LEFT JOIN inscripciones i ON co.id = i.curso_ofrecido_id
WHERE co.periodo_id = 1  -- Cambiar por periodo deseado
GROUP BY c.id, c.codigo_curso, c.nombre, c.creditos, p.nombre, p.apellido, co.horario, co.aula, co.cupos_disponibles
ORDER BY c.codigo_curso;

-- ========================================
-- MANTENIMIENTO
-- ========================================

-- Ver tamaño de las tablas
SELECT 
    table_name AS tabla,
    table_rows AS filas,
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS tamaño_mb
FROM information_schema.TABLES
WHERE table_schema = 'sistema_educativo'
ORDER BY tamaño_mb DESC;

-- Ver logs recientes
SELECT * FROM logs
ORDER BY fecha_hora DESC
LIMIT 20;

-- Limpiar logs antiguos (más de 30 días)
DELETE FROM logs
WHERE fecha_hora < DATE_SUB(NOW(), INTERVAL 30 DAY);

-- ========================================
-- UTILIDADES PARA DESARROLLO
-- ========================================

-- Resetear auto_increment de una tabla
-- ALTER TABLE estudiantes AUTO_INCREMENT = 1;

-- Ver estructura de una tabla
-- DESCRIBE estudiantes;

-- Ver todas las FK de una tabla
-- SHOW CREATE TABLE inscripciones;


