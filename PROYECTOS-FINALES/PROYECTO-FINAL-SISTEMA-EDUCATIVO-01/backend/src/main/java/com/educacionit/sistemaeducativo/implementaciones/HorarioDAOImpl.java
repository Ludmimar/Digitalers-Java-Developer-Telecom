package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.entidades.Horario;
import com.educacionit.sistemaeducativo.entidades.Horario.DiaSemana;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar horarios de cursos ofrecidos.
 * 
 * @author Ludmila Martos
 */
public class HorarioDAOImpl {
    
    /**
     * Obtiene todos los horarios de un curso ofrecido.
     */
    public List<Horario> obtenerPorCursoOfrecido(Integer cursoOfrecidoId) throws SQLException {
        List<Horario> horarios = new ArrayList<Horario>();
        String sql = "SELECT * FROM horarios WHERE curso_ofrecido_id = ? ORDER BY dia_semana, hora_inicio";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoOfrecidoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    horarios.add(mapearHorario(rs));
                }
            }
        }
        return horarios;
    }
    
    /**
     * Inserta un nuevo horario.
     */
    public boolean insertar(Horario horario) throws SQLException {
        String sql = "INSERT INTO horarios (curso_ofrecido_id, dia_semana, hora_inicio, hora_fin) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, horario.getCursoOfrecidoId());
            ps.setString(2, horario.getDiaSemana().name());
            ps.setTime(3, horario.getHoraInicio());
            ps.setTime(4, horario.getHoraFin());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        horario.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si hay conflicto de horario para un profesor en un período.
     */
    public boolean tieneConflictoProfesor(Integer profesorId, Integer periodoId, DiaSemana dia, 
                                           Time horaInicio, Time horaFin, Integer cursoOfrecidoExcluir) throws SQLException {
        String sql = "SELECT h.* FROM horarios h " +
                     "INNER JOIN cursos_ofrecidos co ON h.curso_ofrecido_id = co.id " +
                     "WHERE co.profesor_id = ? AND co.periodo_id = ? " +
                     "AND h.dia_semana = ? " +
                     "AND co.id != ? " +
                     "AND ((? < h.hora_fin AND ? > h.hora_inicio))";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, profesorId);
            ps.setInt(2, periodoId);
            ps.setString(3, dia.name());
            ps.setInt(4, cursoOfrecidoExcluir != null ? cursoOfrecidoExcluir : 0);
            ps.setTime(5, horaInicio);
            ps.setTime(6, horaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultado, hay conflicto
            }
        }
    }
    
    /**
     * Verifica si hay conflicto de horario para un aula en un período.
     */
    public boolean tieneConflictoAula(Integer aulaId, Integer periodoId, DiaSemana dia,
                                       Time horaInicio, Time horaFin, Integer cursoOfrecidoExcluir) throws SQLException {
        String sql = "SELECT h.* FROM horarios h " +
                     "INNER JOIN cursos_ofrecidos co ON h.curso_ofrecido_id = co.id " +
                     "WHERE co.aula_id = ? AND co.periodo_id = ? " +
                     "AND h.dia_semana = ? " +
                     "AND co.id != ? " +
                     "AND ((? < h.hora_fin AND ? > h.hora_inicio))";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, aulaId);
            ps.setInt(2, periodoId);
            ps.setString(3, dia.name());
            ps.setInt(4, cursoOfrecidoExcluir != null ? cursoOfrecidoExcluir : 0);
            ps.setTime(5, horaInicio);
            ps.setTime(6, horaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultado, hay conflicto
            }
        }
    }
    
    /**
     * Obtiene los horarios con conflicto del profesor (para mostrar detalles).
     */
    public List<ConflictoHorario> obtenerConflictosProfesor(Integer profesorId, Integer periodoId, 
                                                             DiaSemana dia, Time horaInicio, Time horaFin) throws SQLException {
        List<ConflictoHorario> conflictos = new ArrayList<ConflictoHorario>();
        String sql = "SELECT h.*, c.nombre as curso_nombre, co.horario as horario_texto " +
                     "FROM horarios h " +
                     "INNER JOIN cursos_ofrecidos co ON h.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "WHERE co.profesor_id = ? AND co.periodo_id = ? " +
                     "AND h.dia_semana = ? " +
                     "AND ((? < h.hora_fin AND ? > h.hora_inicio))";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, profesorId);
            ps.setInt(2, periodoId);
            ps.setString(3, dia.name());
            ps.setTime(4, horaInicio);
            ps.setTime(5, horaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ConflictoHorario conflicto = new ConflictoHorario();
                    conflicto.setDia(DiaSemana.valueOf(rs.getString("dia_semana")));
                    conflicto.setHoraInicio(rs.getTime("hora_inicio"));
                    conflicto.setHoraFin(rs.getTime("hora_fin"));
                    conflicto.setCursoNombre(rs.getString("curso_nombre"));
                    conflicto.setTipo("PROFESOR");
                    conflictos.add(conflicto);
                }
            }
        }
        return conflictos;
    }
    
    /**
     * Obtiene los horarios con conflicto del aula (para mostrar detalles).
     */
    public List<ConflictoHorario> obtenerConflictosAula(Integer aulaId, Integer periodoId,
                                                         DiaSemana dia, Time horaInicio, Time horaFin) throws SQLException {
        List<ConflictoHorario> conflictos = new ArrayList<ConflictoHorario>();
        String sql = "SELECT h.*, c.nombre as curso_nombre, co.horario as horario_texto " +
                     "FROM horarios h " +
                     "INNER JOIN cursos_ofrecidos co ON h.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "WHERE co.aula_id = ? AND co.periodo_id = ? " +
                     "AND h.dia_semana = ? " +
                     "AND ((? < h.hora_fin AND ? > h.hora_inicio))";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, aulaId);
            ps.setInt(2, periodoId);
            ps.setString(3, dia.name());
            ps.setTime(4, horaInicio);
            ps.setTime(5, horaFin);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ConflictoHorario conflicto = new ConflictoHorario();
                    conflicto.setDia(DiaSemana.valueOf(rs.getString("dia_semana")));
                    conflicto.setHoraInicio(rs.getTime("hora_inicio"));
                    conflicto.setHoraFin(rs.getTime("hora_fin"));
                    conflicto.setCursoNombre(rs.getString("curso_nombre"));
                    conflicto.setTipo("AULA");
                    conflictos.add(conflicto);
                }
            }
        }
        return conflictos;
    }
    
    /**
     * Elimina todos los horarios de un curso ofrecido.
     */
    public boolean eliminarPorCursoOfrecido(Integer cursoOfrecidoId) throws SQLException {
        String sql = "DELETE FROM horarios WHERE curso_ofrecido_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoOfrecidoId);
            return ps.executeUpdate() >= 0; // Puede ser 0 si no había horarios
        }
    }
    
    /**
     * Mapea un ResultSet a un objeto Horario.
     */
    private Horario mapearHorario(ResultSet rs) throws SQLException {
        Horario horario = new Horario();
        horario.setId(rs.getInt("id"));
        horario.setCursoOfrecidoId(rs.getInt("curso_ofrecido_id"));
        horario.setDiaSemana(DiaSemana.valueOf(rs.getString("dia_semana")));
        horario.setHoraInicio(rs.getTime("hora_inicio"));
        horario.setHoraFin(rs.getTime("hora_fin"));
        return horario;
    }
    
    /**
     * Clase interna para representar un conflicto de horario con información adicional.
     */
    public static class ConflictoHorario {
        private DiaSemana dia;
        private Time horaInicio;
        private Time horaFin;
        private String cursoNombre;
        private String tipo; // "PROFESOR" o "AULA"
        
        public DiaSemana getDia() { return dia; }
        public void setDia(DiaSemana dia) { this.dia = dia; }
        
        public Time getHoraInicio() { return horaInicio; }
        public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }
        
        public Time getHoraFin() { return horaFin; }
        public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }
        
        public String getCursoNombre() { return cursoNombre; }
        public void setCursoNombre(String cursoNombre) { this.cursoNombre = cursoNombre; }
        
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
    }
}


