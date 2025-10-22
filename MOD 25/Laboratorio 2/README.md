# Laboratorio 02 - CRUD Completo con Patrón DAO

## Descripción

Este laboratorio implementa un sistema completo de gestión de empleados administrativos utilizando el patrón DAO para todas las operaciones CRUD. Evoluciona del Laboratorio 01 agregando capacidades de consulta, actualización y eliminación.

## Funcionalidades

### 📝 Operaciones CRUD Completas
- **CREATE**: Inserción de empleados con PreparedStatement
- **READ**: Búsqueda por ID y listado completo
- **UPDATE**: Actualización de datos de empleados
- **DELETE**: Eliminación con validaciones

### 🏗️ Patrón DAO
- Interface DAO genérica
- AdministrativoImpl implementa DAO
- Separación de responsabilidades
- Reutilización de PreparedStatements

## Conceptos Técnicos

- **Patrón DAO**: Abstracción de acceso a datos
- **PreparedStatement**: Prevención de SQL Injection
- **Interface genérica**: DAO<Integer, Administrativo>
- **Try-with-resources**: Gestión de conexiones
- **CRUD**: Operaciones completas sobre empleados

## Estructura

```
Laboratorio 2/
├── src/main/java/com/educacionIT/javase/
│   ├── principal/App.java
│   ├── entidades/
│   │   ├── Persona.java
│   │   ├── Empleado.java
│   │   ├── Administrativo.java
│   │   └── Documento.java
│   ├── implementaciones/mariaDB/
│   │   └── AdministrativoImpl.java
│   ├── interfaces/
│   │   ├── DAO.java
│   │   ├── ConexionMariaDB.java
│   │   └── UtilidadesFecha.java
│   └── enumerados/TiposDocumento.java
├── pom.xml
└── README.md
```

## Cómo Ejecutar

```bash
cd "Laboratorio 2"
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionIT.javase.principal.App"
```

## Patrón DAO Implementado

```java
public class AdministrativoImpl implements DAO<Integer, Administrativo> {
    private PreparedStatement psInsertar;
    private PreparedStatement psBuscar;
    private PreparedStatement psActualizar;
    private PreparedStatement psEliminar;
    
    @Override
    public boolean insertar(Administrativo admin) {
        String sql = "INSERT INTO Empleados (TipoDocumento, NumeroDocumento, " +
                     "Nombre, Apellido, FechaNacimiento, FechaCargo, sueldo, Tipo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // Implementación con PreparedStatement
    }
    
    @Override
    public Administrativo buscarPorID(Integer id) {
        String sql = "SELECT * FROM Empleados WHERE id = ?";
        // Implementación
    }
    
    @Override
    public boolean actualizar(Administrativo admin) {
        String sql = "UPDATE Empleados SET Nombre=?, Apellido=?, ... WHERE id=?";
        // Implementación
    }
    
    @Override
    public boolean eliminar(Administrativo admin) {
        String sql = "DELETE FROM Empleados WHERE id = ?";
        // Implementación
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


