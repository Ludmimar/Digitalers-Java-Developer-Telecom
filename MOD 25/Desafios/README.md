# Desafíos - MOD 25: JDBC y Bases de Datos

## Descripción General

Esta carpeta contiene desafíos prácticos diseñados para aplicar y reforzar los conceptos de JDBC y acceso a bases de datos en Java. Cada desafío presenta problemas incrementales que requieren el uso de conexiones, consultas SQL, patrón DAO y manejo de transacciones.

## Lista de Desafíos

### 🌍 Desafío 01 - Consultas JDBC con Relaciones (Países y Ciudades)
**Ubicación**: `Desafío 01/`

**Descripción**: Implementación de un sistema que consulta países y ciudades desde una base de datos MariaDB, manejando relaciones uno-a-muchos y agrupando resultados con colecciones Java.

**Conceptos aplicados**:
- Conexión básica con JDBC
- Statement para consultas SQL
- ResultSet para procesar resultados
- Relaciones entre tablas (Foreign Keys)
- Agrupación con HashMap y List
- Try-with-resources

**Funcionalidades**:
- Búsqueda de ciudades por nombre
- Consulta de países relacionados
- Agrupación de ciudades por país
- Visualización estructurada de datos

**Archivos principales**:
- `App.java`: Aplicación principal con lógica JDBC
- `Pais.java`: Entidad país
- `Ciudad.java`: Entidad ciudad
- `Script.sql`: Script de creación de BD y datos

**Base de datos**: `Desafio01JavaSE`

### 🏦 Desafío 02 - Sistema Bancario con JDBC
**Ubicación**: `Desafío 02/`

**Descripción**: Sistema bancario que persiste clientes y productos financieros en base de datos utilizando JDBC. Evoluciona el desafío del MOD 24 agregando persistencia.

**Conceptos aplicados**:
- Conexión con MariaDB
- Statement para INSERT, SELECT
- Manejo de múltiples entidades
- Relaciones entre clientes y productos
- Enumerados en base de datos
- Excepciones personalizadas

**Funcionalidades**:
- Búsqueda de clientes por documento
- Persistencia de productos financieros
- Gestión de cuentas y tarjetas
- Validación de datos
- Manejo de errores con excepciones

**Archivos principales**:
- `App.java`: Aplicación principal
- `AppError.java`: Demo de manejo de errores
- `ConexionMariaDB.java`: Gestión de conexión
- `Cliente.java`, `Producto.java`, `Cuenta.java`, `TarjetaCredito.java`
- `Script.sql`: Esquema de base de datos

**Base de datos**: `desafio02javase`

### 📊 Desafío 03 - Sistema Bancario con Patrón DAO y Logs
**Ubicación**: `Desafío 03/`

**Descripción**: Versión completa del sistema bancario implementando el patrón DAO para acceso a datos y sistema de logging persistente en base de datos. Integra conceptos de los MOD 24, 25 y 26.

**Conceptos aplicados**:
- Patrón DAO (Data Access Object)
- PreparedStatement (prevención SQL Injection)
- Interface genérica DAO<K, V>
- Persistencia de logs en BD
- Auto-increment con getGeneratedKeys()
- Transacciones y manejo de errores
- Integración JDBC + I/O

**Funcionalidades**:
- CRUD completo de logs
- Búsqueda por ID con PreparedStatement
- Inserción con recuperación de ID auto-incremental
- Actualización y eliminación de logs
- Listado completo de logs
- Doble persistencia (BD + archivo)

**Archivos principales**:
- `App.java`: Aplicación principal
- `AppError.java`: Demo de errores y logging
- `LogImplementacion.java`: DAO de logs
- `DAO.java`: Interface genérica
- `Log.java`: Entidad log
- `Script.sql`: Esquema con tabla logs

**Base de datos**: `desafio02javase`

## Cómo Ejecutar los Desafíos

### Requisitos Previos
- Java JDK 8 o superior
- Maven 3.6 o superior
- MariaDB o MySQL instalado
- Driver JDBC de MariaDB

### Configuración General

1. **Instalar MariaDB/MySQL**
2. **Crear las bases de datos** ejecutando los scripts SQL
3. **Configurar credenciales** en el código fuente
4. **Compilar con Maven**
5. **Ejecutar la aplicación**

### Ejemplo: Desafío 01

```bash
# 1. Crear base de datos
mysql -u root -p < "Desafío 01/Script.sql"

# 2. Navegar al directorio
cd "Desafío 01"

# 3. Compilar
mvn compile

# 4. Ejecutar
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"
```

### Ejemplo: Desafío 02

```bash
# 1. Crear base de datos
mysql -u root -p < "Desafío 02/Script.sql"

# 2. Navegar y ejecutar
cd "Desafío 02"
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"
```

### Ejemplo: Desafío 03

```bash
# 1. Crear base de datos (incluye tabla logs)
mysql -u root -p < "Desafío 03/Script.sql"

# 2. Ejecutar aplicación principal
cd "Desafío 03"
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"

# 3. Ejecutar demo de errores
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.AppError"
```

## Estructura Común

Todos los desafíos siguen una estructura Maven estándar:

```
Desafío XX/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── educacionit/
│   │               └── desafioXX/
│   │                   ├── App.java
│   │                   ├── entidades/
│   │                   ├── implementaciones/  (Desafío 03)
│   │                   ├── interfaces/
│   │                   └── utilidades/
│   └── test/
│       └── java/
├── Script.sql              # Script de base de datos
├── pom.xml
└── README.md
```

## Comparación de Desafíos

| Aspecto | Desafío 01 | Desafío 02 | Desafío 03 |
|---------|------------|------------|------------|
| **Complejidad** | Básica | Intermedia | Avanzada |
| **JDBC** | Statement básico | Statement múltiple | PreparedStatement |
| **Patrón** | Directo | Clases de conexión | DAO completo |
| **Seguridad** | No aplica | Vulnerable | SQL Injection safe |
| **Logging** | No | Excepciones | BD + archivo |
| **Integración** | JDBC básico | JDBC + Colecciones | JDBC + DAO + I/O |

## Objetivos de Aprendizaje

Al completar estos desafíos, los estudiantes desarrollarán:

1. **Conexión JDBC**: Establecer y gestionar conexiones con MariaDB
2. **Consultas SQL**: Ejecutar SELECT, INSERT, UPDATE, DELETE
3. **ResultSet**: Procesar resultados de consultas
4. **Relaciones**: Manejar Foreign Keys y relaciones entre tablas
5. **Patrón DAO**: Implementar separación de responsabilidades
6. **PreparedStatement**: Prevenir inyección SQL
7. **Transacciones**: Manejar operaciones atómicas
8. **Integración**: Combinar JDBC con otros conceptos

## Configuración de Conexión

### Parámetros Comunes
```java
String driver = "org.mariadb.jdbc.Driver";
String url = "jdbc:mariadb://localhost:3306/nombreBD";
String usuario = "root";
String clave = "";
```

### Desafío 01
```java
String url = "jdbc:mariadb://localhost:3306/Desafio01JavaSE";
```

### Desafíos 02 y 03
```java
String url = "jdbc:mariadb://localhost:3306/desafio02javase";
```

## Evolución del Sistema Bancario

### Desafío 02 → Desafío 03

**Mejoras implementadas**:
1. **Statement** → **PreparedStatement**
2. **Código directo** → **Patrón DAO**
3. **Sin logging** → **Logging en BD y archivo**
4. **Vulnerable** → **Seguro contra SQL Injection**
5. **Código duplicado** → **Reutilizable con interfaces**

## Patrón DAO Implementado

### Interface Genérica
```java
public interface DAO<K, V> {
    V buscarPorID(K id);
    boolean insertar(V entidad) throws SQLException;
    boolean actualizar(V entidad) throws SQLException;
    boolean eliminar(V entidad) throws SQLException;
    List<V> listar();
}
```

### Implementación
```java
public class LogImplementacion implements DAO<Integer, Log> {
    private PreparedStatement psInsertar;
    private PreparedStatement psBuscar;
    // ... otros PreparedStatements
    
    @Override
    public boolean insertar(Log log) throws SQLException {
        String sql = "INSERT INTO logs (fecha, clase, objeto, error) VALUES(?, ?, ?, ?)";
        // Implementación con PreparedStatement
    }
}
```

## Prevención de SQL Injection

### ❌ MAL (Vulnerable)
```java
// Desafío 01 y 02 - Solo para aprendizaje
String sql = "SELECT * FROM ciudades WHERE descripcion LIKE '%" + cadena + "%'";
```

### ✅ BIEN (Seguro)
```java
// Desafío 03 - Producción
String sql = "SELECT * FROM logs WHERE id = ?";
PreparedStatement ps = conexion.prepareStatement(sql);
ps.setInt(1, id);
```

## Ejemplos de Salida

### Desafío 01
```
Pais: Argentina
Ciudades:
    Buenos Aires
    Córdoba
    Santa Fe
    Mendoza
    ...
```

### Desafío 02
```
Sistema de busqueda Bancario:
Ingrese el tipo de documento: CI
Ingrese el numero de documento: 01

Cliente 01
Documento [tipoDocumento=CI, numeroDocumento=01]
Productos:
    Cuenta - Número: 15, Saldo: 2.0, Tipo: CC
    Tarjeta de Crédito - Número: 2, Límite: 15.0
```

### Desafío 03
```
[Registro en BD]
Log insertado con ID: 1

[Archivo log.error]
2024-12-15 10:30:00 TipoProductoExcepcion: ERROR_DESCONOCIDO - Registrado en la tabla logs id[1]
```

## Consejos y Mejores Prácticas

1. **Always use try-with-resources**: Cierre automático de Connection, Statement, ResultSet
2. **PreparedStatement para queries dinámicas**: Previene SQL Injection
3. **Manejo de excepciones específicas**: SQLException, ClassNotFoundException
4. **Validación de datos**: Antes de insertar en BD
5. **Connection pooling**: Para aplicaciones reales
6. **Transacciones**: Para operaciones múltiples
7. **Índices en BD**: Para mejorar rendimiento de búsquedas

## Evaluación

Cada desafío será evaluado considerando:
- ✅ Correcta conexión con base de datos
- ✅ Ejecución apropiada de consultas SQL
- ✅ Procesamiento correcto de ResultSet
- ✅ Implementación del patrón DAO (Desafío 03)
- ✅ Uso de PreparedStatement (Desafío 03)
- ✅ Manejo de excepciones SQL
- ✅ Cierre correcto de recursos
- ✅ Calidad del código y arquitectura

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---


