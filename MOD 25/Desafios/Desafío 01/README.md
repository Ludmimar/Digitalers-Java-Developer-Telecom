# Desafío 01 - Consultas JDBC con Relaciones (Países y Ciudades)

## Descripción

Este desafío implementa un sistema de consulta de países y ciudades utilizando JDBC para conectarse a una base de datos MariaDB. El sistema maneja relaciones uno-a-muchos entre países y ciudades, agrupando resultados con colecciones Java (HashMap y List).

## Funcionalidades

### 🌍 Consulta de Datos Geográficos
- Búsqueda de ciudades por nombre (LIKE SQL)
- Consulta de países relacionados con Foreign Keys
- Agrupación automática de ciudades por país
- Visualización estructurada de resultados

### 🔗 Manejo de Relaciones
- Relación uno-a-muchos (Pais → Ciudades)
- Uso de Foreign Keys en base de datos
- Agrupación con HashSet para IDs únicos
- ListIterator para manipulación eficiente

## Conceptos Técnicos Aplicados

### JDBC Básico
- **DriverManager**: Gestión del driver de MariaDB
- **Connection**: Establecimiento de conexión
- **Statement**: Ejecución de consultas SQL
- **ResultSet**: Procesamiento de resultados
- **Try-with-resources**: Cierre automático de conexión

### Relaciones en Base de Datos
- **Foreign Keys**: Relación entre tablas
- **JOIN implícito**: Múltiples consultas relacionadas
- **Uno-a-muchos**: Un país tiene muchas ciudades

### Colecciones Java
- **HashMap**: Almacenamiento de países por ID
- **HashSet**: IDs únicos de países
- **ArrayList**: Ciudades por país
- **ListIterator**: Iteración con remove()

## Estructura del Proyecto

```
Desafío 01/
├── src/main/java/com/educacionit/desafio01/
│   ├── App.java                    # Aplicación principal
│   └── entidades/
│       ├── Pais.java               # Entidad País
│       └── Ciudad.java             # Entidad Ciudad
├── Script.sql                      # Creación de BD y datos
├── pom.xml
└── README.md
```

## Base de Datos

### Esquema
```sql
CREATE DATABASE Desafio01JavaSE;

CREATE TABLE paises (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE ciudades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pais INT NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    FOREIGN KEY (id_pais) REFERENCES paises(id)
);
```

### Datos de Ejemplo
- **País**: Argentina
- **Ciudades**: 24 provincias argentinas
  - Buenos Aires (Provincia)
  - Córdoba (Provincia)
  - Ciudad Autónoma de Buenos Aires (Ciudad Autónoma)
  - etc.

## Cómo Ejecutar

### 1. Configurar Base de Datos
```bash
# Ejecutar script SQL
mysql -u root -p < Script.sql
```

### 2. Compilar y Ejecutar
```bash
cd "Desafío 01"
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"
```

## Ejemplo de Salida

```
Pais: Argentina
Ciudades:
	Buenos Aires
	Catamarca
	Chaco
	Chubut
	Ciudad Autónoma de Buenos Aires
	Córdoba
	Corrientes
	Entre Ríos
	Formosa
	Jujuy
	La Pampa
	La Rioja
	Mendoza
	Misiones
	Neuquén
	Rio Negro
	Salta
	San Juan
	San Luis
	Santa Cruz
	Santa Fe
	Santiago del Estero
	Tierra del Fuego
	Tucumán
```

## Análisis del Código

### Método conectar()
```java
private static Connection conectar() throws SQLException {
    Connection conexion = null;
    try {
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3306/Desafio01JavaSE";
        String usuario = "root";
        String clave = "";
        
        Class.forName(driver);
        conexion = DriverManager.getConnection(url, usuario, clave);
        
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return conexion;
}
```

### Búsqueda de Ciudades
```java
private static void buscarCiudad(String cadena) {
    List<Ciudad> ciudades = new ArrayList<>();
    Map<Integer, Pais> paises = new HashMap<>();

    try (Connection conexion = conectar()) {
        String sql = "SELECT id, id_pais, descripcion, categoria " +
                     "FROM ciudades WHERE descripcion LIKE '%" + cadena + "%'";
        
        Statement declaracionSQL = conexion.createStatement();
        ResultSet resultado = declaracionSQL.executeQuery(sql);

        while (resultado.next()) {
            Ciudad ciudad = new Ciudad(
                resultado.getInt("id"),
                resultado.getInt("id_pais"),
                resultado.getString("descripcion"),
                resultado.getString("categoria")
            );
            ciudades.add(ciudad);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Agrupar ciudades por país...
}
```

### Agrupación de Ciudades por País
```java
// Obtener IDs únicos de países
Set<Integer> idPaises = new HashSet<>();
for (Ciudad ciudad : ciudades) {
    idPaises.add(ciudad.getId_pais());
}

// Para cada país, buscar y agrupar sus ciudades
for (Integer id : idPaises) {
    Pais pais = buscarPais(id);
    pais.setCiudades(new ArrayList<>());
    
    ListIterator<Ciudad> iterador = ciudades.listIterator();
    while (iterador.hasNext()) {
        Ciudad ciudad = iterador.next();
        
        if (ciudad.getId_pais() == pais.getId()) {
            pais.getCiudades().add(ciudad);
            iterador.remove();  // Evita procesamiento duplicado
        }
    }
    
    paises.put(id, pais);
}
```

## Puntos de Aprendizaje

### 1. JDBC Básico
- Conexión con DriverManager
- Ejecución de SELECT con Statement
- Procesamiento de ResultSet
- Try-with-resources para conexiones

### 2. Relaciones en BD
- Foreign Keys entre tablas
- Consultas con filtros (LIKE)
- Mapeo de relaciones en objetos Java

### 3. Agrupación de Datos
- HashSet para IDs únicos
- HashMap para búsqueda eficiente
- ListIterator para remove() seguro

### 4. Patrón de Mapeo
- Entidades Java ↔ Tablas SQL
- Constructores con datos de ResultSet
- Composición (Pais contiene List<Ciudad>)

## ⚠️ Notas de Seguridad

Este desafío usa concatenación de SQL para fines educativos:
```java
// SOLO PARA APRENDIZAJE - Vulnerable a SQL Injection
String sql = "SELECT ... WHERE descripcion LIKE '%" + cadena + "%'";
```

**En producción usar PreparedStatement**:
```java
String sql = "SELECT ... WHERE descripcion LIKE ?";
PreparedStatement ps = conexion.prepareStatement(sql);
ps.setString(1, "%" + cadena + "%");
```

## Evaluación

Este desafío será evaluado considerando:
- ✅ Correcta conexión con MariaDB usando JDBC
- ✅ Ejecución apropiada de consultas SELECT
- ✅ Procesamiento correcto de ResultSet
- ✅ Manejo de relaciones uno-a-muchos
- ✅ Agrupación efectiva con colecciones
- ✅ Uso de try-with-resources
- ✅ Calidad del código y organización

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---


