# MOD 26 - Java I/O: Manejo de Archivos y Flujos de Datos

## Descripción del Módulo

Este módulo se enfoca en el manejo de archivos y flujos de datos en Java, cubriendo tanto la comunicación orientada a bytes como a caracteres. Los estudiantes aprenderán a leer, escribir y manipular archivos de texto, implementar sistemas de logs, y trabajar con propiedades y configuraciones.

## Contenidos del Módulo

### 📚 Documentación
- **DOCS/**: Contiene la documentación teórica del módulo
  - Java IO Comunicación Orientada a Bytes.pdf
  - Java IO Comunicación Orientada a Caracteres.pdf
  - Propiedades Conceptos y usos.pdf

### 🎯 Desafíos
- **Desafío01**: Búsqueda y reemplazo de palabras en archivos de texto
- **Desafío02**: Sistema bancario con manejo de logs y archivos de error

### 🧪 Laboratorio
- **Laboratorio**: Sistema de lectura y escritura de archivos con BufferedReader/Writer

### 📖 Material Adicional
- **JavaIO**: Ejemplos completos de manejo de archivos (bytes, caracteres, buffers)
- **Propiedades**: Manejo de archivos de configuración (.properties)

## Conceptos Clave

### Java I/O Streams
- **FileReader/FileWriter**: Lectura y escritura orientada a caracteres
- **BufferedReader/BufferedWriter**: Buffers para operaciones eficientes
- **FileInputStream/FileOutputStream**: Lectura y escritura orientada a bytes
- **Try-with-resources**: Gestión automática de recursos

### Manejo de Archivos
- **File**: Clase para manipulación de archivos y directorios
- **Lectura de archivos**: Métodos readLine(), read()
- **Escritura de archivos**: Métodos write(), newLine()
- **Operaciones**: crear, leer, escribir, eliminar, listar archivos

### Archivos de Propiedades
- **Properties**: Manejo de configuraciones
- **Carga y guardado**: load(), store()
- **Acceso a propiedades**: getProperty(), setProperty()

### Sistema de Logs
- **Registro de eventos**: Escritura de logs de aplicación
- **Manejo de errores**: Archivos de error separados
- **Trazabilidad**: Registro de operaciones del sistema

## Estructura del Proyecto

```
MOD 26/
├── Desafio/            # Desafíos prácticos
│   ├── Desafio01/      # Búsqueda y reemplazo en archivos
│   └── Desafio02/      # Sistema bancario con logs
├── Laboratorio/        # Ejercicio de laboratorio
├── Material extra/     # Ejemplos adicionales
│   ├── JavaIO/         # Ejemplos completos de I/O
│   └── Propiedades/    # Manejo de archivos .properties
├── DOCS/              # Documentación teórica
└── README.md          # Este archivo
```

## Requisitos Previos

- Conocimiento de Java SE básico
- Comprensión de excepciones y manejo de errores
- Familiaridad con colecciones (List, Map)
- Conceptos básicos de POO

## Tecnologías Utilizadas

- Java SE 8+
- Maven (gestión de dependencias)
- Java I/O API
- Properties API

## Cómo Ejecutar los Proyectos

1. Asegúrate de tener Java JDK 8+ instalado
2. Navega al directorio del proyecto deseado
3. Compila el proyecto: `mvn compile`
4. Ejecuta la aplicación principal

### Ejemplo de ejecución:
```bash
cd "MOD 26/Desafio/Desafio01"
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"
```

## Objetivos de Aprendizaje

Al finalizar este módulo, los estudiantes serán capaces de:

1. **Manejar archivos de texto** utilizando streams de caracteres
2. **Implementar sistemas de logs** para trazabilidad de aplicaciones
3. **Trabajar con buffers** para operaciones eficientes de I/O
4. **Gestionar archivos de configuración** usando Properties
5. **Manipular archivos y directorios** programáticamente
6. **Aplicar try-with-resources** para gestión segura de recursos
7. **Implementar búsqueda y reemplazo** en archivos de texto
8. **Manejar errores** con archivos de log dedicados

## Casos de Uso Prácticos

### 🔍 Búsqueda en Archivos
- Buscar palabras o patrones en archivos de texto
- Reemplazar contenido de forma automática
- Procesar grandes archivos línea por línea

### 📝 Sistemas de Logging
- Registrar eventos de aplicaciones
- Separar logs de errores y eventos normales
- Análisis de logs para debugging

### ⚙️ Archivos de Configuración
- Almacenar configuraciones de aplicación
- Cargar parámetros de base de datos
- Gestionar propiedades del sistema

### 💾 Persistencia de Datos
- Guardar y cargar datos de aplicaciones
- Exportar información a archivos de texto
- Importar datos desde archivos

## Patrones y Mejores Prácticas

### Try-with-Resources
```java
try (BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"))) {
    String linea;
    while ((linea = reader.readLine()) != null) {
        // Procesar línea
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### Uso de Buffers
```java
// Más eficiente que FileReader directo
BufferedReader reader = new BufferedReader(new FileReader(archivo));
BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
```

### Manejo de Excepciones
```java
try {
    // Operaciones de archivo
} catch (FileNotFoundException e) {
    // Archivo no encontrado
} catch (IOException e) {
    // Otros errores de I/O
}
```

## Progreso del Módulo

| Componente | Descripción | Estado |
|------------|-------------|--------|
| Desafío 01 | Búsqueda y reemplazo en archivos | ✅ Implementado |
| Desafío 02 | Sistema bancario con logs | ✅ Implementado |
| Laboratorio | Lectura/escritura con buffers | ✅ Implementado |
| Material Extra | Ejemplos de I/O y Properties | ✅ Implementado |

## Recursos Adicionales

### Documentación Oficial
- [Java I/O Tutorial](https://docs.oracle.com/javase/tutorial/essential/io/)
- [BufferedReader JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)
- [Properties JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)

### Conceptos Relacionados
- NIO (New I/O) para operaciones más avanzadas
- Serialización de objetos
- Compresión de archivos
- Procesamiento de archivos XML y JSON

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

*Desarrollado con ❤️ para la comunidad educativa*

