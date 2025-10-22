package com.educacionit.sistemaeducativo.principal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.educacionit.sistemaeducativo.entidades.Estudiante;
import com.educacionit.sistemaeducativo.enumerados.EstadoAcademico;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;
import com.educacionit.sistemaeducativo.implementaciones.EstudianteDAOImpl;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

/**
 * Clase principal del Sistema de Gestión Educativa.
 * Proporciona un menú interactivo para gestionar estudiantes.
 * 
 * @author Ludmila Martos
 */
public class App {
    
    private static Scanner scanner = new Scanner(System.in);
    private static EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
    private static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        mostrarBanner();
        
        try {
            // Verificar conexión
            ConexionDB.getConexion();
            
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                int opcion = leerOpcion();
                
                switch (opcion) {
                    case 1:
                        registrarEstudiante();
                        break;
                    case 2:
                        listarEstudiantes();
                        break;
                    case 3:
                        buscarEstudiante();
                        break;
                    case 4:
                        actualizarEstudiante();
                        break;
                    case 5:
                        eliminarEstudiante();
                        break;
                    case 6:
                        buscarPorMatricula();
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("\n👋 ¡Hasta luego! Cerrando sistema...");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
                
                if (continuar) {
                    System.out.println("\nPresione ENTER para continuar...");
                    scanner.nextLine();
                }
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión a la base de datos:");
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
            scanner.close();
        }
    }

    private static void mostrarBanner() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║       SISTEMA DE GESTIÓN EDUCATIVA                     ║");
        System.out.println("║       Proyecto Final - Java Fullstack                  ║");
        System.out.println("║                                                        ║");
        System.out.println("║       Desarrollado por: Ludmila Martos                 ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void mostrarMenu() {
        System.out.println("\n╔═══════════════════ MENÚ PRINCIPAL ═══════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║  1. 📝 Registrar nuevo estudiante                    ║");
        System.out.println("║  2. 📋 Listar todos los estudiantes                  ║");
        System.out.println("║  3. 🔍 Buscar estudiante por ID                      ║");
        System.out.println("║  4. ✏️  Actualizar datos de estudiante                ║");
        System.out.println("║  5. 🗑️  Eliminar estudiante                           ║");
        System.out.println("║  6. 🎓 Buscar por matrícula                          ║");
        System.out.println("║  0. 🚪 Salir                                         ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("\nSeleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();  // Limpiar buffer
            return -1;
        }
    }

    /**
     * Método auxiliar para imprimir líneas repetidas (compatible con Java 8).
     * Reemplaza el método String.repeat() de Java 11+
     */
    private static void imprimirLinea(int longitud) {
        for (int i = 0; i < longitud; i++) {
            System.out.print("─");
        }
        System.out.println();
    }

    private static void registrarEstudiante() {
        System.out.println("\n═══ REGISTRO DE NUEVO ESTUDIANTE ═══\n");
        
        try {
            Estudiante estudiante = new Estudiante();
            
            // Tipo de documento
            System.out.println("Tipos de documento disponibles:");
            for (TipoDocumento tipo : TipoDocumento.values()) {
                System.out.println("  - " + tipo.name() + ": " + tipo.getDescripcion());
            }
            System.out.print("Tipo de documento: ");
            String tipoDoc = scanner.nextLine().toUpperCase();
            estudiante.setTipoDocumento(TipoDocumento.valueOf(tipoDoc));
            
            // Número de documento
            System.out.print("Número de documento: ");
            estudiante.setNumeroDocumento(scanner.nextLine());
            
            // Nombre
            System.out.print("Nombre: ");
            estudiante.setNombre(scanner.nextLine());
            
            // Apellido
            System.out.print("Apellido: ");
            estudiante.setApellido(scanner.nextLine());
            
            // Fecha de nacimiento
            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            String fechaNac = scanner.nextLine();
            estudiante.setFechaNacimiento(LocalDate.parse(fechaNac, formatoFecha));
            
            // Email
            System.out.print("Email: ");
            estudiante.setEmail(scanner.nextLine());
            
            // Teléfono
            System.out.print("Teléfono: ");
            estudiante.setTelefono(scanner.nextLine());
            
            // Dirección
            System.out.print("Dirección: ");
            estudiante.setDireccion(scanner.nextLine());
            
            // Matrícula
            System.out.print("Matrícula (ej: EST-2024-001): ");
            estudiante.setMatricula(scanner.nextLine());
            
            // Fecha de ingreso
            System.out.print("Fecha de ingreso (dd/MM/yyyy): ");
            String fechaIng = scanner.nextLine();
            estudiante.setFechaIngreso(LocalDate.parse(fechaIng, formatoFecha));
            
            // Insertar en BD
            if (estudianteDAO.insertar(estudiante)) {
                System.out.println("\n✅ ¡Estudiante registrado exitosamente!");
                System.out.println("📌 ID asignado: " + estudiante.getId());
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: Tipo de documento inválido");
        } catch (DateTimeParseException e) {
            System.err.println("❌ Error: Formato de fecha inválido. Use dd/MM/yyyy");
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar estudiante:");
            e.printStackTrace();
        }
    }

    private static void listarEstudiantes() {
        System.out.println("\n═══ LISTADO DE ESTUDIANTES ═══\n");
        
        try {
            List<Estudiante> estudiantes = estudianteDAO.listar();
            
            if (estudiantes.isEmpty()) {
                System.out.println("ℹ️  No hay estudiantes registrados.");
            } else {
                System.out.println("Total de estudiantes: " + estudiantes.size());
                imprimirLinea(100);
                System.out.printf("%-5s %-15s %-25s %-25s %-10s %-10s%n", 
                    "ID", "MATRÍCULA", "NOMBRE", "EMAIL", "PROMEDIO", "ESTADO");
                imprimirLinea(100);
                
                for (Estudiante est : estudiantes) {
                    System.out.printf("%-5d %-15s %-25s %-25s %-10.2f %-10s%n",
                        est.getId(),
                        est.getMatricula(),
                        est.getNombreCompleto(),
                        est.getEmail(),
                        est.getPromedioGeneral(),
                        est.getEstadoAcademico());
                }
                imprimirLinea(100);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar estudiantes:");
            e.printStackTrace();
        }
    }

    private static void buscarEstudiante() {
        System.out.println("\n═══ BUSCAR ESTUDIANTE POR ID ═══\n");
        System.out.print("Ingrese el ID del estudiante: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Estudiante estudiante = estudianteDAO.buscarPorID(id);
            
            if (estudiante != null) {
                mostrarDetalleEstudiante(estudiante);
            } else {
                System.out.println("❌ No se encontró estudiante con ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar estudiante:");
            e.printStackTrace();
        }
    }

    private static void buscarPorMatricula() {
        System.out.println("\n═══ BUSCAR POR MATRÍCULA ═══\n");
        System.out.print("Ingrese la matrícula: ");
        String matricula = scanner.nextLine();
        
        try {
            Estudiante estudiante = estudianteDAO.buscarPorMatricula(matricula);
            
            if (estudiante != null) {
                mostrarDetalleEstudiante(estudiante);
            } else {
                System.out.println("❌ No se encontró estudiante con matrícula: " + matricula);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar estudiante:");
            e.printStackTrace();
        }
    }

    private static void actualizarEstudiante() {
        System.out.println("\n═══ ACTUALIZAR ESTUDIANTE ═══\n");
        System.out.print("Ingrese el ID del estudiante a actualizar: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Estudiante estudiante = estudianteDAO.buscarPorID(id);
            
            if (estudiante == null) {
                System.out.println("❌ No se encontró estudiante con ID: " + id);
                return;
            }
            
            mostrarDetalleEstudiante(estudiante);
            System.out.println("\nIngrese los nuevos datos (ENTER para mantener actual):\n");
            
            // Actualizar nombre
            System.out.print("Nuevo nombre [" + estudiante.getNombre() + "]: ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                estudiante.setNombre(nuevoNombre);
            }
            
            // Actualizar apellido
            System.out.print("Nuevo apellido [" + estudiante.getApellido() + "]: ");
            String nuevoApellido = scanner.nextLine();
            if (!nuevoApellido.isEmpty()) {
                estudiante.setApellido(nuevoApellido);
            }
            
            // Actualizar email
            System.out.print("Nuevo email [" + estudiante.getEmail() + "]: ");
            String nuevoEmail = scanner.nextLine();
            if (!nuevoEmail.isEmpty()) {
                estudiante.setEmail(nuevoEmail);
            }
            
            if (estudianteDAO.actualizar(estudiante)) {
                System.out.println("\n✅ ¡Estudiante actualizado exitosamente!");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estudiante:");
            e.printStackTrace();
        }
    }

    private static void eliminarEstudiante() {
        System.out.println("\n═══ ELIMINAR ESTUDIANTE ═══\n");
        System.out.print("Ingrese el ID del estudiante a eliminar: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            Estudiante estudiante = estudianteDAO.buscarPorID(id);
            
            if (estudiante == null) {
                System.out.println("❌ No se encontró estudiante con ID: " + id);
                return;
            }
            
            mostrarDetalleEstudiante(estudiante);
            System.out.print("\n⚠️  ¿Está seguro que desea eliminar este estudiante? (S/N): ");
            String confirmacion = scanner.nextLine();
            
            if (confirmacion.equalsIgnoreCase("S")) {
                if (estudianteDAO.eliminar(estudiante)) {
                    System.out.println("\n✅ ¡Estudiante eliminado exitosamente!");
                }
            } else {
                System.out.println("ℹ️  Operación cancelada.");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar estudiante:");
            e.printStackTrace();
        }
    }

    private static void mostrarDetalleEstudiante(Estudiante est) {
        System.out.println("\n┌─────────────────────────────────────────────────────┐");
        System.out.println("│           INFORMACIÓN DEL ESTUDIANTE                │");
        System.out.println("├─────────────────────────────────────────────────────┤");
        System.out.printf("│ ID:              %-35d│%n", est.getId());
        System.out.printf("│ Matrícula:       %-35s│%n", est.getMatricula());
        System.out.printf("│ Nombre:          %-35s│%n", est.getNombreCompleto());
        System.out.printf("│ Documento:       %-35s│%n", 
            est.getTipoDocumento() + " " + est.getNumeroDocumento());
        System.out.printf("│ Edad:            %-35d│%n", est.calcularEdad());
        System.out.printf("│ Email:           %-35s│%n", est.getEmail());
        System.out.printf("│ Teléfono:        %-35s│%n", est.getTelefono());
        System.out.printf("│ Fecha Ingreso:   %-35s│%n", 
            est.getFechaIngreso().format(formatoFecha));
        System.out.printf("│ Promedio:        %-35.2f│%n", est.getPromedioGeneral());
        System.out.printf("│ Créditos:        %-35d│%n", est.getCreditosCursados());
        System.out.printf("│ Estado:          %-35s│%n", est.getEstadoAcademico());
        System.out.println("└─────────────────────────────────────────────────────┘");
    }

    /**
     * Método main simplificado para demostración rápida.
     * Muestra el uso básico del sistema sin menú interactivo.
     */
    public static void demo() throws SQLException {
        System.out.println("═══ DEMO RÁPIDA DEL SISTEMA ═══\n");
        
        // Crear estudiante de prueba
        Estudiante estudiante = new Estudiante(
            TipoDocumento.DNI,
            "99999999",
            "Demo",
            "Prueba",
            LocalDate.of(2000, 1, 1),
            "EST-DEMO-001",
            LocalDate.now()
        );
        estudiante.setEmail("demo@email.com");
        estudiante.setTelefono("1199999999");
        estudiante.setDireccion("Calle Demo 123");
        
        // Insertar
        System.out.println("1. Insertando estudiante...");
        estudianteDAO.insertar(estudiante);
        
        // Listar
        System.out.println("\n2. Listando estudiantes...");
        List<Estudiante> lista = estudianteDAO.listar();
        lista.forEach(System.out::println);
        
        // Buscar
        System.out.println("\n3. Buscando por matrícula...");
        Estudiante encontrado = estudianteDAO.buscarPorMatricula("EST-DEMO-001");
        System.out.println("Encontrado: " + encontrado);
        
        System.out.println("\n✅ Demo completada exitosamente!");
    }
}


