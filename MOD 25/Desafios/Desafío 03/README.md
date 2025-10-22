# Desafío 03 - Sistema Bancario con Patrón DAO y Logging

## Descripción

Versión completa del sistema bancario implementando el patrón DAO para acceso a datos y sistema de logging persistente en base de datos. Integra JDBC, I/O y colecciones.

## Funcionalidades

- CRUD completo de logs con patrón DAO
- PreparedStatement para prevenir SQL Injection
- Logging dual (base de datos + archivo)
- Recuperación de IDs auto-incrementales
- Sistema de trazabilidad completo

## Conceptos Técnicos

- **Patrón DAO**: Interface genérica DAO<K, V>
- **PreparedStatement**: Consultas parametrizadas seguras
- **getGeneratedKeys()**: Recuperación de IDs auto-increment
- **Dual persistencia**: BD + archivo log.error
- **Interface Archivo**: Métodos estáticos para I/O

## Estructura

```
Desafío 03/
├── src/main/java/com/educacionit/desafio02/
│   ├── App.java
│   ├── AppError.java
│   ├── entidades/Log.java
│   ├── implementaciones/LogImplementacion.java
│   ├── interfaces/DAO.java
│   └── interfaces/Archivo.java
├── Script.sql
├── log.error
└── README.md
```

## Ejecución

```bash
mysql -u root -p < Script.sql
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.AppError"
```

## Análisis del Patrón DAO

```java
public interface DAO<K, V> {
    V buscarPorID(K id);
    boolean insertar(V entidad) throws SQLException;
    boolean actualizar(V entidad) throws SQLException;
    boolean eliminar(V entidad) throws SQLException;
    List<V> listar();
}

public class LogImplementacion implements DAO<Integer, Log> {
    private PreparedStatement psInsertar;
    
    @Override
    public boolean insertar(Log log) throws SQLException {
        String sql = "INSERT INTO logs (fecha, clase, objeto, error) VALUES(?, ?, ?, ?)";
        psInsertar = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        // ... seteo de parámetros ...
        psInsertar.executeUpdate();
        
        // Recuperar ID generado
        ResultSet rs = psInsertar.getGeneratedKeys();
        if (rs.next()) {
            log.setId(rs.getInt(1));
        }
        
        // Escribir también en archivo
        Archivo.escribir("log.error", Arrays.asList(log.toLog()));
        return true;
    }
}
```

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---


