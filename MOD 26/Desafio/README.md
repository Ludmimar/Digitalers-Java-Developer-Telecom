# Desafíos - MOD 26: Java I/O

## Descripción General

Esta carpeta contiene desafíos prácticos diseñados para aplicar y reforzar los conceptos de manejo de archivos y flujos de datos en Java. Cada desafío presenta problemas reales que requieren el uso de streams, buffers y operaciones de I/O.

## Lista de Desafíos

### 🔍 Desafío 01 - Búsqueda y Reemplazo en Archivos de Texto
**Ubicación**: `Desafio01/`

**Descripción**: Implementación de un sistema que busca palabras específicas en archivos de texto y las reemplaza con un formato especial (marcadores `<< >>`). El sistema reporta la posición de cada ocurrencia encontrada.

**Conceptos aplicados**:
- BufferedReader para lectura eficiente
- BufferedWriter para escritura de archivos
- Interfaces con métodos estáticos
- Manipulación de Strings (contains, indexOf, replace)
- Try-with-resources para gestión de recursos

**Funcionalidades**:
- Búsqueda de palabras en archivos de texto
- Reporte de posición (fila y columna)
- Reemplazo automático con marcadores
- Guardado del archivo modificado

**Archivos principales**:
- `App.java`: Aplicación principal con lógica de búsqueda
- `Archivo.java`: Interface con métodos estáticos para I/O
- `Java.txt`: Archivo de texto de prueba

### 📊 Desafío 02 - Sistema Bancario con Logs y Manejo de Errores
**Ubicación**: `Desafio02/`

**Descripción**: Sistema bancario completo con búsqueda de clientes que incorpora un sistema de logs para registrar errores y eventos. Integra conceptos previos de colecciones con manejo avanzado de archivos.

**Conceptos aplicados**:
- Sistema de logging personalizado
- Manejo de archivos de error (log.error)
- Integración de I/O con sistema bancario
- HashMap para búsqueda de clientes
- Excepciones personalizadas con logging
- Patrón DAO para acceso a datos

**Funcionalidades**:
- Búsqueda de clientes por documento
- Gestión de productos financieros (cuentas y tarjetas)
- Registro de errores en archivo log
- Manejo de excepciones con trazabilidad
- Validación de entrada de datos

**Archivos principales**:
- `App.java`: Aplicación principal del sistema bancario
- `AppError.java`: Demostración de manejo de errores
- `Log.java`: Clase entidad para logs
- `LogImplementacion.java`: Implementación de sistema de logs
- `Archivo.java`: Interface para operaciones de I/O
- `log.error`: Archivo de registro de errores

**Estructura del sistema**:
```
Desafio02/
├── entidades/           # Clases de modelo
│   ├── Cliente.java
│   ├── Producto.java
│   ├── Cuenta.java
│   ├── TarjetaCredito.java
│   ├── Documento.java
│   └── Log.java
├── implementaciones/    # Lógica de negocio
│   └── LogImplementacion.java
├── interfaces/          # Contratos
│   ├── Archivo.java
│   ├── DAO.java
│   └── NumeroProducto.java
├── enumerados/         # Tipos enumerados
├── excepciones/        # Excepciones personalizadas
└── utilidades/         # Helpers
```

## Cómo Ejecutar los Desafíos

### Requisitos
- Java JDK 8 o superior
- Maven 3.6 o superior
- Editor de texto o IDE (Eclipse, IntelliJ, VS Code)

### Pasos para ejecutar

#### Desafío 01
```bash
# Navegar al directorio
cd Desafio01

# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"

# Interactuar
# Se solicitará ingresar una palabra a buscar
# El programa buscará en Java.txt y modificará el archivo
```

#### Desafío 02
```bash
# Navegar al directorio
cd Desafio02

# Compilar
mvn compile

# Ejecutar aplicación principal
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"

# Ejecutar demo de errores
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.AppError"

# Revisar archivo log.error generado
```

## Estructura Común

Ambos desafíos siguen una estructura Maven estándar:

```
DesafioXX/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── educacionit/
│   │               └── desafioXX/
│   │                   ├── App.java
│   │                   ├── entidades/
│   │                   ├── interfaces/
│   │                   ├── implementaciones/
│   │                   └── utilidades/
│   └── test/
│       └── java/
├── target/
├── pom.xml
└── archivos de datos (.txt, .error)
```

## Objetivos de Aprendizaje

Al completar estos desafíos, los estudiantes desarrollarán:

1. **Manejo de archivos**: Lectura y escritura eficiente con buffers
2. **Try-with-resources**: Gestión automática de recursos
3. **Búsqueda de texto**: Algoritmos de búsqueda en archivos
4. **Sistema de logging**: Implementación de logs para trazabilidad
5. **Integración**: Combinación de I/O con otros conceptos (colecciones, excepciones)
6. **Interfaces con métodos estáticos**: Utilidades reutilizables
7. **Manejo de excepciones**: Captura y registro de errores
8. **Persistencia simple**: Almacenamiento de datos en archivos

## Ejemplos de Uso

### Desafío 01 - Búsqueda de Palabra
```
Indique la palabra a buscar: Java

La palabra <<Java>> se encuentra en la fila 1 y comienza en la columna 0
La palabra <<Java>> se encuentra en la fila 3 y comienza en la columna 15
La palabra <<Java>> se encuentra en la fila 7 y comienza en la columna 8

[El archivo Java.txt se modifica automáticamente con los marcadores]
```

### Desafío 02 - Búsqueda de Cliente
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

## Comparación de Desafíos

| Aspecto | Desafío 01 | Desafío 02 |
|---------|------------|------------|
| **Complejidad** | Básica | Avanzada |
| **Enfoque** | Manipulación de archivos | Sistema completo con logs |
| **I/O** | Lectura/escritura básica | Logging y manejo de errores |
| **Estructura** | Simple (2 clases) | Completa (múltiples paquetes) |
| **Entrada** | Interactiva | Datos predefinidos e interactiva |

## Patrones y Técnicas Aplicadas

### Interface con Métodos Estáticos (Desafío 01)
```java
public interface Archivo {
    static List<String> leer(String directorio) {
        // Implementación
    }
    
    static boolean escribir(String directorio, List<String> texto) {
        // Implementación
    }
}
```

### Sistema de Logging (Desafío 02)
```java
public class LogImplementacion implements Archivo {
    public void registrarError(Log log) {
        // Escribe en log.error
    }
}
```

### Try-with-Resources
```java
try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
    // Operaciones de lectura
} catch (IOException e) {
    e.printStackTrace();
}
```

## Consejos para Resolver los Desafíos

1. **Entiende el flujo**: Lee cómo se abren, procesan y cierran los archivos
2. **Usa buffers**: Siempre prefiere BufferedReader/Writer para eficiencia
3. **Gestiona recursos**: Utiliza try-with-resources para evitar fugas
4. **Maneja excepciones**: Captura específicamente FileNotFoundException e IOException
5. **Prueba con datos reales**: Crea archivos de prueba para validar

## Evaluación

Cada desafío será evaluado considerando:
- ✅ Correcta implementación de lectura y escritura de archivos
- ✅ Uso apropiado de try-with-resources
- ✅ Manejo adecuado de excepciones
- ✅ Eficiencia en operaciones de I/O (uso de buffers)
- ✅ Funcionalidad completa según especificaciones
- ✅ Calidad del código y documentación
- ✅ Integración con conceptos previos (para Desafío 02)

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

