# MOD 25 - JDBC y Bases de Datos: Conexión Java con MariaDB/MySQL

## Descripción del Módulo

Este módulo se enfoca en la integración de aplicaciones Java con bases de datos relacionales utilizando JDBC (Java Database Connectivity). Los estudiantes aprenderán a conectarse a bases de datos MariaDB/MySQL, realizar operaciones CRUD (Create, Read, Update, Delete), implementar el patrón DAO (Data Access Object), y gestionar conexiones y transacciones de manera eficiente.

## Contenidos del Módulo

### 📚 Documentación
- **DOC/**: Contiene la documentación teórica del módulo
  - JDBC Conceptos y Conexión.pdf
  - Patrón DAO.pdf
  - SQL y JDBC.pdf
  - Transacciones y Manejo de Errores.pdf

### 🎯 Desafíos
- **Desafío 01**: Consultas JDBC con relaciones (Países y Ciudades)
- **Desafío 02**: Sistema bancario con persistencia en base de datos
- **Desafío 03**: Sistema bancario completo con patrón DAO y logging

### 🧪 Laboratorios
- **Laboratorio 01**: Inserción de empleados con JDBC básico
- **Laboratorio 02**: CRUD completo con Statement y PreparedStatement

### 📖 Material Adicional
- Ejemplos de conexión y configuración
- Implementaciones de patrón DAO
- Manejo de transacciones
- Prevención de inyección SQL

## Conceptos Clave

### JDBC (Java Database Connectivity)
- **DriverManager**: Gestión de drivers de base de datos
- **Connection**: Representación de conexión a la BD
- **Statement**: Ejecución de consultas SQL estáticas
- **PreparedStatement**: Consultas parametrizadas (prevención SQL Injection)
- **ResultSet**: Manejo de resultados de consultas

### Patrón DAO (Data Access Object)
- **Separación de responsabilidades**: Lógica de negocio vs acceso a datos
- **Interface DAO**: Contrato para operaciones CRUD
- **Implementaciones concretas**: Por cada entidad del sistema
- **Genericidad**: DAOs genéricos reutilizables

### Operaciones CRUD
- **Create**: INSERT INTO - Inserción de registros
- **Read**: SELECT - Consulta de datos
- **Update**: UPDATE - Actualización de registros
- **Delete**: DELETE - Eliminación de registros

### Gestión de Conexiones
- **Try-with-resources**: Cierre automático de recursos
- **Connection pooling**: Reutilización de conexiones
- **Transacciones**: COMMIT y ROLLBACK
- **Manejo de excepciones**: SQLException

### Prevención de Inyección SQL
- **PreparedStatement**: Consultas parametrizadas
- **Validación de entrada**: Sanitización de datos
- **Buenas prácticas**: No concatenar SQL

## Estructura del Proyecto

```
MOD 25/
├── Desafios/          # Desafíos prácticos
│   ├── Desafío 01/    # Países y ciudades con JDBC
│   ├── Desafío 02/    # Sistema bancario con JDBC
│   └── Desafío 03/    # Sistema bancario con DAO y logs
├── Laboratorio 01/    # Inserción básica con JDBC
├── Laboratorio 2/     # CRUD completo
├── MATERIAL ADICIONAL/# Ejemplos y recursos
├── DOC/              # Documentación teórica
└── README.md         # Este archivo
```

## Requisitos Previos

- Conocimiento de Java SE básico e intermedio
- Comprensión de SQL y bases de datos relacionales
- Familiaridad con POO y colecciones
- MariaDB o MySQL instalado

## Tecnologías Utilizadas

- Java SE 8+
- JDBC API
- MariaDB/MySQL
- Maven (gestión de dependencias)
- Driver MariaDB JDBC

## Configuración Inicial

### 1. Instalación de MariaDB/MySQL
```bash
# Descargar e instalar MariaDB
# https://mariadb.org/download/

# O MySQL
# https://dev.mysql.com/downloads/mysql/
```

### 2. Dependencia Maven
```xml
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>3.0.8</version>
</dependency>
```

### 3. Configuración de Conexión
```java
String driver = "org.mariadb.jdbc.Driver";
String url = "jdbc:mariadb://localhost:3306/nombreBD";
String usuario = "root";
String clave = "";

Class.forName(driver);
Connection conexion = DriverManager.getConnection(url, usuario, clave);
```

## Cómo Ejecutar los Proyectos

### Preparación
1. Crear la base de datos ejecutando el script SQL proporcionado
2. Configurar credenciales de conexión en el código
3. Compilar el proyecto con Maven
4. Ejecutar la aplicación principal

### Ejemplo de ejecución:
```bash
cd "MOD 25/Desafios/Desafío 01"

# Ejecutar script SQL en MariaDB
mysql -u root -p < Script.sql

# Compilar y ejecutar
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"
```

## Objetivos de Aprendizaje

Al finalizar este módulo, los estudiantes serán capaces de:

1. **Establecer conexiones** con bases de datos MariaDB/MySQL
2. **Ejecutar operaciones CRUD** utilizando JDBC
3. **Implementar el patrón DAO** para acceso a datos
4. **Utilizar PreparedStatement** para prevenir inyección SQL
5. **Manejar ResultSet** para procesar resultados
6. **Gestionar transacciones** con commit y rollback
7. **Cerrar recursos** correctamente con try-with-resources
8. **Integrar JDBC** con conceptos previos (colecciones, excepciones, I/O)

## Casos de Uso Prácticos

### 🌍 Consultas con Relaciones (Desafío 01)
- Búsqueda de ciudades por país
- Manejo de relaciones uno-a-muchos
- Agrupación de resultados con colecciones

### 🏦 Sistema Bancario (Desafíos 02 y 03)
- CRUD de clientes y productos
- Persistencia de transacciones
- Sistema de logging en base de datos

### 👥 Gestión de Empleados (Laboratorios)
- Inserción de empleados
- Consultas y actualizaciones
- Eliminación con validaciones

## Patrones y Mejores Prácticas

### Try-with-Resources
```java
try (Connection conexion = DriverManager.getConnection(url, usuario, clave);
     Statement stmt = conexion.createStatement();
     ResultSet rs = stmt.executeQuery(sql)) {
    
    // Procesar resultados
    
} catch (SQLException e) {
    e.printStackTrace();
}
```

### PreparedStatement (Prevención SQL Injection)
```java
String sql = "SELECT * FROM empleados WHERE id = ?";
try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();
    // Procesar resultados
}
```

### Patrón DAO
```java
public interface DAO<K, V> {
    V buscarPorID(K id);
    boolean insertar(V entidad) throws SQLException;
    boolean actualizar(V entidad) throws SQLException;
    boolean eliminar(V entidad) throws SQLException;
    List<V> listar();
}
```

## Comparación de Técnicas

| Aspecto | Statement | PreparedStatement |
|---------|-----------|-------------------|
| **Seguridad** | Vulnerable a SQL Injection | Previene SQL Injection |
| **Rendimiento** | Compilación cada vez | Compilación una vez |
| **Parámetros** | Concatenación manual | Parámetros automáticos |
| **Uso** | Consultas estáticas | Consultas dinámicas |

## Progreso del Módulo

| Componente | Descripción | Estado |
|------------|-------------|--------|
| Desafío 01 | Países y ciudades con JDBC | ✅ Implementado |
| Desafío 02 | Sistema bancario con JDBC | ✅ Implementado |
| Desafío 03 | Sistema bancario con DAO y logs | ✅ Implementado |
| Laboratorio 01 | Inserción de empleados | ✅ Implementado |
| Laboratorio 02 | CRUD completo | ✅ Implementado |

## Recursos Adicionales

### Documentación Oficial
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MariaDB Connector/J](https://mariadb.com/kb/en/about-mariadb-connector-j/)
- [PreparedStatement JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html)

### Conceptos Relacionados
- Connection pooling (HikariCP, Apache DBCP)
- ORM (Object-Relational Mapping) - JPA, Hibernate
- NoSQL y JDBC alternativo
- Transacciones distribuidas

## Advertencias de Seguridad

### ⚠️ NO HACER
```java
// MAL - Vulnerable a SQL Injection
String sql = "SELECT * FROM usuarios WHERE nombre = '" + nombre + "'";
```

### ✅ HACER
```java
// BIEN - Seguro con PreparedStatement
String sql = "SELECT * FROM usuarios WHERE nombre = ?";
PreparedStatement pstmt = conexion.prepareStatement(sql);
pstmt.setString(1, nombre);
```

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

*Desarrollado con ❤️ para la comunidad educativa*

