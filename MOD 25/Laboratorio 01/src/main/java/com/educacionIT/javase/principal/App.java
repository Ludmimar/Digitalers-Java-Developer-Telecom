package com.educacionIT.javase.principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import com.educacionIT.javase.entidades.Administrativo;
import com.educacionIT.javase.entidades.Documento;
import com.educacionIT.javase.entidades.Empleado;
import com.educacionIT.javase.enumerados.TiposDocumento;
import com.educacionIT.javase.interfaces.UtilidadesFecha;

/**
 * Clase principal de la aplicación del sistema de gestión de personal educativo.
 * 
 * Esta clase contiene el método main y la lógica principal de la aplicación.
 * Permite el ingreso de datos de empleados administrativos, validación de
 * entrada de datos, creación de objetos y persistencia en base de datos MariaDB.
 * 
 * Funcionalidades principales:
 * - Ingreso interactivo de datos de empleados
 * - Validación de tipos de documento
 * - Formateo y validación de fechas
 * - Creación de objetos Administrativo
 * - Persistencia en base de datos MariaDB
 * - Manejo de conexiones y recursos
 * 
 * Flujo de la aplicación:
 * 1. Solicita datos del empleado administrativo
 * 2. Valida los datos ingresados
 * 3. Crea el objeto Administrativo
 * 4. Persiste los datos en la base de datos
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public class App {
	
	// Scanner estático para entrada de datos desde consola
	private static Scanner teclado = new Scanner(System.in);

	/**
	 * Método principal de la aplicación.
	 * 
	 * Este método ejecuta el flujo completo de la aplicación:
	 * - Solicita datos del empleado administrativo
	 * - Valida los datos ingresados
	 * - Crea el objeto Administrativo
	 * - Persiste los datos en la base de datos
	 * 
	 * @param args Argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		// Muestra el título de la aplicación
		System.out.println("Ingreso de Datos de Administrador Laboratorio01");
		// Muestra un salto de línea para separar el título del contenido
		System.out.print("\n");
		
		// Declaración de variables para almacenar los datos del empleado
		String nombre;           // Nombre del empleado
		String apellido;         // Apellido del empleado
		TiposDocumento tipo;     // Tipo de documento (enum)
		Integer numero;          // Número de documento
		Date fechaNacimiento;   // Fecha de nacimiento
		Date fechaCargo;        // Fecha de inicio del cargo
		Float sueldo;           // Sueldo del empleado

		// Objeto auxiliar para almacenar el empleado creado
		Empleado empleado = null;

		// Solicita los datos comunes a todas las personas
		System.out.print("Ingrese el Nombre de la Persona : ");
		nombre = teclado.next();
		
		System.out.print("Ingrese el Apellido de la Persona : ");
		apellido = teclado.next();

		// Bucle para validar el tipo de documento
		while (true) {
			System.out.print("Ingrese el Tipo de Documento de la Persona : ");

			try {
				// Intenta convertir el string ingresado a un valor del enum
				// Convierte a mayúsculas para hacer la comparación case-insensitive
				tipo = TiposDocumento.valueOf(teclado.next().toUpperCase());
				break;  // Si la conversión es exitosa, sale del bucle
			} catch (Exception e) {
				// Si hay error en la conversión, muestra el mensaje y continúa el bucle
				System.out.println(e.getMessage());
			}
		}
		
		System.out.print("Ingrese el Numero de Documento de la Persona : ");
		numero = teclado.nextInt();
		
		System.out.println("Ingrese la Fecha de Nacimiento de la Persona : ");
		fechaNacimiento = obtenerFecha();

		System.out.println("Ingrese la Fecha de inicio del Cargo de la Persona : ");
		fechaCargo = obtenerFecha();

		System.out.print("Ingrese el Sueldo de la Persona : ");
		sueldo = teclado.nextFloat();

		// Crea el objeto Administrativo con todos los datos ingresados
		// Utiliza composición para crear el objeto Documento
		empleado = new Administrativo(nombre, apellido, new Documento(tipo, numero), fechaNacimiento, fechaCargo,
				sueldo);

		// Persiste el empleado en la base de datos
		insertar(empleado);

		// Cierra el scanner para liberar recursos
		teclado.close();
	}

	/**
	 * Método auxiliar para obtener y validar fechas ingresadas por el usuario.
	 * 
	 * Este método solicita al usuario que ingrese una fecha en formato dd/mm/aaaa,
	 * valida el formato y convierte el string a un objeto Date. Si el formato
	 * no es válido, solicita nuevamente hasta que se ingrese una fecha válida.
	 * 
	 * @return Date objeto Date con la fecha ingresada y validada
	 */
	private static Date obtenerFecha() {

		String fechaUsuario = "";  // Variable para almacenar la fecha como string
		Date fecha = null;         // Variable para almacenar la fecha como Date

		// Bucle para validar la fecha hasta que sea correcta
		while (true) {
			try {
				System.out.print("Formato Fecha[dd/mm/aaaa]: ");
				fechaUsuario = teclado.next();
				
				// Utiliza la interfaz UtilidadesFecha para convertir string a Date
				// El método parse convierte un string en formato dd/MM/yyyy a Date
				fecha = UtilidadesFecha.getStringAFecha(fechaUsuario);
				break;  // Si la conversión es exitosa, sale del bucle
			} catch (ParseException e) {
				// Si hay error en el parsing, muestra el mensaje y continúa el bucle
				System.err.println("debe ingresar un dato valido: " + e.getMessage());
			}
		}

		return fecha;  // Retorna la fecha validada
	}

	/**
	 * Método para establecer conexión con la base de datos MariaDB.
	 * 
	 * Este método configura y establece la conexión con la base de datos MariaDB
	 * utilizando JDBC. Registra el driver, configura los parámetros de conexión
	 * y establece la conexión con la base de datos.
	 * 
	 * Configuración de conexión:
	 * - Driver: org.mariadb.jdbc.Driver
	 * - URL: jdbc:mariadb://localhost:3306/sistemaEducacionIT
	 * - Usuario: root
	 * - Contraseña: (vacía)
	 * 
	 * @return Connection objeto de conexión a la base de datos
	 * @throws SQLException si hay error en la conexión
	 */
	private static Connection conectarBaseDeDatos() throws SQLException {
		Connection conexion = null;
		try {

			// Configuración del driver de MariaDB
			String driver = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mariadb://localhost:3306/sistemaEducacionIT";
			String usuario = "root";
			String clave = "";

			// Registra el driver de MariaDB
			Class.forName(driver);

			// Crea la conexión con la base de datos
			conexion = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e) {
			// Si no se encuentra el driver, imprime el stack trace
			e.printStackTrace();
		}
		return conexion;  // Retorna la conexión establecida
	}

	/**
	 * Método para insertar un empleado en la base de datos.
	 * 
	 * Este método toma un objeto Empleado y lo inserta en la tabla Empleados
	 * de la base de datos MariaDB. Utiliza try-with-resources para manejar
	 * automáticamente el cierre de la conexión y el statement.
	 * 
	 * La consulta SQL inserta los siguientes campos:
	 * - TipoDocumento: Tipo del documento (DNI, PAS, etc.)
	 * - NumeroDocumento: Número del documento
	 * - Nombre: Nombre del empleado
	 * - Apellido: Apellido del empleado
	 * - FechaNacimiento: Fecha de nacimiento (formato SQL)
	 * - FechaCargo: Fecha de inicio del cargo (formato SQL)
	 * - sueldo: Sueldo del empleado
	 * - Tipo: Tipo de empleado (4 para administrativo)
	 * 
	 * @param empleado Objeto Empleado a insertar en la base de datos
	 */
	private static void insertar(Empleado empleado) {

		// Utiliza try-with-resources para manejo automático de recursos
		try (Connection conexion = conectarBaseDeDatos()) {
			
			// Construye la consulta SQL de inserción
			String sql = "INSERT INTO Empleados (TipoDocumento,NumeroDocumento,Nombre,Apellido,FechaNacimiento,FechaCargo,sueldo,Tipo) "
					+ "VALUES  ('" + empleado.getDocumento().getTipo() + "'," + empleado.getDocumento().getNumero()
					+ ",'" + empleado.getNombre() + "','" + empleado.getApellido() + "','"
					+ UtilidadesFecha.getFechaAStringsSQL(empleado.getFechaNacimiento()) + "','"
					+ UtilidadesFecha.getFechaAStringsSQL(empleado.getFechaCargo()) + "'," + empleado.getSueldo()
					+ ",4);";

			// Crea el objeto Statement para ejecutar la consulta
			Statement declaracionSQL = conexion.createStatement();

			// Ejecuta la consulta SQL de inserción
			declaracionSQL.execute(sql);

		} catch (SQLException e) {
			// Si hay error en la ejecución SQL, imprime el stack trace
			e.printStackTrace();
		}

	}

}
