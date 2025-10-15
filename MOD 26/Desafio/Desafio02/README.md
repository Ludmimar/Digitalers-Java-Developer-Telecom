# Desafío 02 - Sistema Bancario con Logs y Manejo de Errores

## Descripción

Este desafío implementa un sistema bancario completo que integra conceptos de colecciones, excepciones y Java I/O para crear un sistema de logging robusto. El sistema permite buscar clientes por documento, visualizar sus productos financieros, y registra todos los errores en un archivo de log (`log.error`) y en base de datos.

## Funcionalidades

### 🏦 Sistema Bancario
- Búsqueda de clientes por tipo y número de documento
- Gestión de múltiples productos por cliente
- Cuentas bancarias (Corriente y Ahorro)
- Tarjetas de crédito con límite y tasa
- Validación de datos de entrada

### 📝 Sistema de Logging
- Registro de errores en archivo `log.error`
- Persistencia de logs en base de datos MariaDB
- Formato estructurado de logs con timestamp
- Trazabilidad completa de errores
- Patrón DAO para operaciones de base de datos

### 🔍 Búsqueda y Validación
- HashMap para búsqueda eficiente O(1)
- Validación de tipo de documento
- Manejo de clientes no encontrados
- Enumerados para tipos seguros

### ⚠️ Manejo de Errores
- Excepciones personalizadas (TipoProductoExcepcion)
- Captura y registro automático de errores
- Demostración de errores (AppError.java)
- Stack trace completo en logs

## Conceptos Técnicos Aplicados

### Java I/O Avanzado
- **BufferedWriter**: Escritura eficiente en archivos de log
- **Append mode**: Agregar logs sin sobrescribir
- **Try-with-resources**: Gestión automática de recursos
- **Interface Archivo**: Métodos estáticos para I/O

### Integración de Conceptos
- **Colecciones**: HashMap, List para datos
- **Enumerados**: TipoDocumento, TipoCuenta, ErrorBanco
- **Excepciones personalizadas**: Con logging integrado
- **Patrón DAO**: Acceso a datos de logs
- **JDBC**: Conexión y operaciones con MariaDB

### Modelo de Datos
- **Cliente**: Con documento, productos y fecha nacimiento
- **Producto**: Clase base abstracta (polimorfismo)
- **Cuenta**: Herencia de Producto
- **TarjetaCredito**: Herencia de Producto
- **Log**: Entidad para registro de errores

### Patrones de Diseño
- **DAO (Data Access Object)**: Abstracción de persistencia
- **Factory**: Construcción de objetos Log
- **Repository**: Gestión de colecciones de clientes
- **Exception Handling**: Manejo centralizado de errores

## Estructura del Proyecto

```
Desafio02/
├── src/main/java/com/educacionit/desafio02/
│   ├── App.java                          # Aplicación principal
│   ├── AppError.java                     # Demo de manejo de errores
│   ├── comparadores/
│   │   └── OrdenProductoNumeroDesc.java  # Comparador de productos
│   ├── entidades/
│   │   ├── Cliente.java                  # Cliente bancario
│   │   ├── Producto.java                 # Clase base abstracta
│   │   ├── Cuenta.java                   # Cuenta bancaria
│   │   ├── TarjetaCredito.java           # Tarjeta de crédito
│   │   ├── Documento.java                # Documento de identidad
│   │   ├── Log.java                      # Entidad de log
│   │   └── ConexionMariaDB.java          # Gestión de conexión BD
│   ├── enumerados/
│   │   ├── TipoDocumento.java            # CI, DNI, PASAPORTE
│   │   ├── TipoCuenta.java               # CC, CA
│   │   └── ErrorBanco.java               # Códigos de error
│   ├── excepciones/
│   │   └── TipoProductoExcepcion.java    # Excepción personalizada
│   ├── implementaciones/
│   │   └── LogImplementacion.java        # DAO de logs
│   ├── interfaces/
│   │   ├── Archivo.java                  # Operaciones I/O
│   │   ├── DAO.java                      # Interface genérica DAO
│   │   └── NumeroProducto.java           # Interface de productos
│   └── utilidades/
│       ├── Fechas.java                   # Utilidades de fecha
│       └── Formatos.java                 # Utilidades de formato
├── log.error                             # Archivo de logs generado
├── pom.xml
└── README.md
```

## Cómo Ejecutar

### Requisitos Previos
- Java JDK 8+
- Maven 3.6+
- MariaDB/MySQL (opcional para persistencia de logs)

### Configuración de Base de Datos (Opcional)
```sql
CREATE DATABASE desafio02javase;

CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha VARCHAR(50),
    clase VARCHAR(100),
    objeto TEXT,
    error TEXT
);
```

### Ejecución

#### Aplicación Principal
```bash
cd Desafio02
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"
```

#### Demostración de Errores
```bash
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.AppError"
```

## Ejemplo de Uso

### Búsqueda de Cliente
```
Sistema de busqueda Bancario:
Ingrese el tipo de documento: CI
Ingrese el numero de documento: 01

Cliente 01
Documento [tipoDocumento=CI, numeroDocumento=01]
1983-03-15
Productos:
	Cuenta - Número: 15, Saldo: 2.0, Tipo: CC
	Cuenta - Número: 16, Saldo: 4.0, Tipo: CC
	Cuenta - Número: 125, Saldo: 9.0, Tipo: CA
	Tarjeta de Crédito - Número: 2, Límite: 15.0, Tasa: 8.0%
	Tarjeta de Crédito - Número: 87, Límite: 74.0, Tasa: 89.0%
```

### Cliente No Encontrado
```
Ingrese el tipo de documento: DNI
Ingrese el numero de documento: 99
Cliente no existe en nuestro sistema
```

### Archivo log.error
```
2024-12-15 14:30:15 TipoProductoExcepcion: ERROR_DESCONOCIDO - Registrado en la tabla logs id[1]
2024-12-15 14:32:20 TipoProductoExcepcion: ERROR_PRODUCTO_INEXISTENTE - Registrado en la tabla logs id[2]
```

## Análisis del Código

### Clase Log
```java
public class Log {
    private Integer id;
    private LocalDateTime fecha;
    private String clase;
    private String objeto;
    private String error;
    
    public String toLog() {
        return Fechas.getStringLocalDateTime(fecha) + " " + 
               clase + ": " + error + 
               " - Registrado en la tabla logs id[" + id + "]";
    }
}
```

### LogImplementacion con Archivo
```java
public class LogImplementacion implements DAO<Integer, Log> {
    
    @Override
    public boolean insertar(Log log) throws SQLException {
        // ... Inserción en base de datos ...
        
        // Escritura adicional en archivo log.error
        Archivo.escribir("log.error", Arrays.asList(log.toLog()));
        return inserto;
    }
}
```

### Interface Archivo (Modo Append)
```java
public interface Archivo {
    static List<String> leer(String directorio) {
        // ... lectura ...
    }
    
    static boolean escribir(String directorio, List<String> texto) {
        File archivoEscritura = new File(directorio);
        
        // true = modo append (agregar al final)
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(archivoEscritura, true))) {
            
            for (String linea : texto) {
                escritor.write(linea);
                escritor.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
```

### Aplicación Principal
```java
public class App {
    public static void main(String[] args) throws TipoProductoExcepcion {
        Scanner teclado = new Scanner(System.in);
        Map<Documento, Cliente> clientes = new HashMap<>();
        
        // Configurar clientes de prueba
        cliente1.setDocumento(new Documento(TipoDocumento.CI, "01"));
        cliente1.setProductos(productosCliente1);
        clientes.put(cliente1.getDocumento(), cliente1);
        
        // Búsqueda interactiva
        System.out.print("Ingrese el tipo de documento:");
        tipoDocumento = TipoDocumento.valueOf(teclado.next().toUpperCase());
        
        System.out.print("Ingrese el numero de documento:");
        numeroDocumento = teclado.next();
        
        documento = new Documento(tipoDocumento, numeroDocumento);
        Cliente cliente = clientes.get(documento);
        
        // Mostrar resultados
        if (cliente != null) {
            // Mostrar información del cliente
        } else {
            System.out.println("Cliente no existe en nuestro sistema");
        }
    }
}
```

## Puntos de Aprendizaje

### 1. Sistema de Logging
- **Dual persistencia**: Archivo + Base de datos
- **Formato estructurado**: Fecha, clase, objeto, error
- **Modo append**: No sobrescribir logs anteriores
- **Trazabilidad**: ID de registro para referencia

### 2. Integración I/O con Otros Conceptos
- **HashMap**: Búsqueda eficiente de clientes
- **Herencia**: Productos polimórficos
- **Excepciones**: Manejo centralizado
- **DAO**: Abstracción de persistencia

### 3. Append vs Sobrescritura
- **false**: Sobrescribe archivo (Desafío 01)
- **true**: Agrega al final (Desafío 02 logs)
- **Uso apropiado**: Logs siempre en modo append

### 4. Patrón DAO
- **Abstracción**: Operaciones CRUD genéricas
- **Separación de responsabilidades**: Lógica vs persistencia
- **Reutilización**: Interface genérica DAO<K, V>

## Comparación con Desafío 01

| Aspecto | Desafío 01 | Desafío 02 |
|---------|------------|------------|
| **Complejidad** | Básica | Avanzada |
| **Modo escritura** | Sobrescritura | Append (logs) |
| **Estructura** | 2 clases | Sistema completo |
| **Persistencia** | Solo archivo | Archivo + BD |
| **Integración** | Solo I/O | I/O + Colecciones + JDBC |

## Mejoras Posibles

1. **Rotación de logs**: Crear nuevo archivo cuando alcance tamaño límite
2. **Niveles de log**: INFO, WARN, ERROR, DEBUG
3. **Formato de fecha configurable**: Permitir diferentes formatos
4. **Compresión de logs**: Comprimir logs antiguos
5. **Búsqueda en logs**: Herramienta para buscar en archivos log
6. **Notificaciones**: Email cuando ocurra error crítico
7. **Dashboard**: Visualización de logs en interfaz web
8. **Limpieza automática**: Eliminar logs antiguos

## Evaluación

Este desafío será evaluado considerando:
- ✅ Correcta implementación del sistema de logging
- ✅ Uso apropiado de modo append para logs
- ✅ Integración efectiva de I/O con colecciones y JDBC
- ✅ Implementación del patrón DAO
- ✅ Manejo robusto de excepciones
- ✅ Búsqueda eficiente con HashMap
- ✅ Formato estructurado y legible de logs
- ✅ Calidad del código y arquitectura

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

