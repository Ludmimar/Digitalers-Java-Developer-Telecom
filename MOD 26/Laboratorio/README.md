# Laboratorio - Manejo de Archivos con BufferedReader/Writer

## Descripción

Este laboratorio implementa un sistema básico pero completo de lectura y escritura de archivos de texto utilizando BufferedReader y BufferedWriter. El programa permite al usuario ingresar texto línea por línea desde consola, lo guarda en un archivo, y luego lee y muestra el contenido almacenado.

## Funcionalidades

### ✍️ Escritura Interactiva
- Captura de texto desde consola línea por línea
- Palabra clave "fin" para terminar captura
- Almacenamiento en archivo `MiArchivo.txt`
- Modo append (agregar al final del archivo)
- Uso de newLine() para compatibilidad multiplataforma

### 📖 Lectura y Visualización
- Lectura completa del archivo guardado
- Verificación de disponibilidad con ready()
- Lectura línea por línea con readLine()
- Muestra del contenido en consola
- Manejo seguro de archivos con try-with-resources

### 🔄 Flujo Completo
- Entrada → Almacenamiento → Lectura → Salida
- Demostración de ciclo completo de I/O
- Gestión automática de recursos
- Manejo de excepciones I/O

## Conceptos Técnicos Aplicados

### BufferedReader/Writer
- **BufferedWriter**: Escritura eficiente con buffer interno
- **BufferedReader**: Lectura eficiente con buffer
- **newLine()**: Salto de línea multiplataforma
- **readLine()**: Lectura de línea completa
- **ready()**: Verificación de disponibilidad de datos

### Try-with-Resources
- **Gestión automática**: Cierre automático de recursos
- **Sintaxis limpia**: Declaración de recursos en try
- **Múltiples recursos**: Varios streams en un try
- **Exception handling**: Captura de excepciones I/O

### FileReader/FileWriter
- **FileReader**: Stream de entrada de caracteres
- **FileWriter**: Stream de salida de caracteres
- **Modo append**: Constructor con parámetro true
- **File**: Manejo de referencias a archivos

### Manejo de Excepciones
- **FileNotFoundException**: Archivo no encontrado
- **IOException**: Errores de entrada/salida
- **printStackTrace()**: Trazabilidad de errores
- **Excepciones específicas**: Captura diferenciada

## Estructura del Proyecto

```
Laboratorio/
├── Laboratorio/
│   ├── src/main/java/com/educacionIT/javase/principal/
│   │   └── App.java                # Aplicación principal
│   ├── src/test/java/              # Tests unitarios
│   ├── MiArchivo.txt               # Archivo generado
│   ├── pom.xml                     # Configuración Maven
│   └── README.md                   # Este archivo
├── Laboratorio.pdf                 # Enunciado del laboratorio
└── README.md                       # Documentación general
```

## Cómo Ejecutar

### Usando Maven
```bash
# Navegar al directorio
cd "MOD 26/Laboratorio/Laboratorio"

# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="com.educacionIT.javase.principal.App"
```

### Compilación Manual
```bash
# Compilar
javac -d target/classes src/main/java/com/educacionIT/javase/principal/App.java

# Ejecutar
java -cp target/classes com.educacionIT.javase.principal.App
```

## Ejemplo de Uso

### Ejecución del Programa
```
Inicio del Programa
Escriba el texto que desea guardar y al finalizar escriba una linea con la palabra 'fin':
Este es un ejemplo de texto
que se guardará en un archivo
usando BufferedWriter
fin

[El archivo MiArchivo.txt se crea/actualiza]

Este es un ejemplo de texto
que se guardará en un archivo
usando BufferedWriter

Fin del Programa
```

### Contenido de MiArchivo.txt
```
Este es un ejemplo de texto
que se guardará en un archivo
usando BufferedWriter
```

## Análisis del Código

### Método escribir()
```java
static boolean escribir(String directorio, List<String> texto) {
    // Crear referencia al archivo
    File archivoEscritura = new File(directorio);
    
    // Try-with-resources para gestión automática
    // true = modo append (agregar al final)
    try (BufferedWriter escribirFichero = new BufferedWriter(
            new FileWriter(archivoEscritura, true))) {
        
        // Escribir cada línea
        for (String linea : texto) {
            escribirFichero.write(linea);
            
            // newLine() es multiplataforma (\n en Unix, \r\n en Windows)
            escribirFichero.newLine();
        }
        return true;
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
```

### Método leer()
```java
static void leer(String directorio) {
    // Crear referencia al archivo
    File archivoLectura = new File(directorio);
    
    // Try-with-resources con FileReader
    try (BufferedReader leerFichero = new BufferedReader(
            new FileReader(archivoLectura))) {
        
        String mensaje = null;
        
        // Verificar si el archivo está listo para lectura
        if (leerFichero.ready()) {
            
            // Leer línea por línea hasta el final (null)
            while ((mensaje = leerFichero.readLine()) != null) {
                System.out.println(mensaje);
            }
        }
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

### Aplicación Principal
```java
public class App {
    public static void main(String[] args) {
        System.out.println("Inicio del Programa");
        System.out.println("Escriba el texto que desea guardar y al finalizar escriba una linea con la palabra 'fin':");

        Scanner teclado = new Scanner(System.in);
        List<String> texto = new ArrayList<>();
        
        // Captura de texto línea por línea
        do {
            String linea = teclado.nextLine();
            if (linea.equalsIgnoreCase("fin")) {
                break;
            }
            texto.add(linea);
        } while (true);
        
        // Escribir y leer el archivo
        escribir("MiArchivo.txt", texto);
        leer("MiArchivo.txt");
        
        teclado.close();
        System.out.println("Fin del Programa");
    }
}
```

## Puntos de Aprendizaje

### 1. BufferedReader/Writer
- **Buffer interno**: Reduce operaciones de I/O físicas
- **Eficiencia**: Más rápido que FileReader/Writer directo
- **readLine()**: Lee hasta fin de línea automáticamente
- **newLine()**: Independiente de plataforma

### 2. Try-with-Resources
- **AutoCloseable**: Implementado por streams
- **Orden de cierre**: Inverso al de declaración
- **Excepciones suprimidas**: Manejo automático
- **Código limpio**: Sin necesidad de finally

### 3. Modo Append vs Sobrescritura
- **new FileWriter(file, false)**: Sobrescribe (por defecto)
- **new FileWriter(file, true)**: Agrega al final
- **Uso apropiado**: Append para logs, sobrescritura para backups

### 4. ready() vs hasNext()
- **ready()**: Verifica si hay datos sin bloquear
- **Non-blocking**: No espera entrada
- **Uso recomendado**: Antes de leer archivos

## Flujo de Datos

```
Usuario (Consola) 
    ↓
Scanner.nextLine()
    ↓
List<String> (Memoria)
    ↓
BufferedWriter + FileWriter
    ↓
MiArchivo.txt (Disco)
    ↓
BufferedReader + FileReader
    ↓
System.out.println()
    ↓
Usuario (Consola)
```

## Comparación de Técnicas

| Técnica | Ventaja | Desventaja | Uso |
|---------|---------|------------|-----|
| FileReader/Writer | Simple | Lento | Archivos pequeños |
| BufferedReader/Writer | Rápido | Más complejo | Archivos grandes |
| Scanner | Parseo fácil | Lento | Entrada de usuario |
| DataInputStream | Tipos primitivos | Solo binario | Datos binarios |

## Ejercicios Sugeridos

### Básicos
1. **Modificar modo**: Cambiar entre append y sobrescritura
2. **Validación**: Verificar que el archivo exista antes de leer
3. **Contador**: Contar líneas y caracteres escritos
4. **Formato**: Agregar timestamp a cada línea

### Intermedios
5. **Múltiples archivos**: Escribir en varios archivos simultáneamente
6. **Búsqueda**: Implementar búsqueda de palabras en el archivo
7. **Numeración**: Agregar números de línea al escribir
8. **Respaldo**: Crear copia de seguridad antes de modificar

### Avanzados
9. **Cifrado**: Cifrar el contenido al escribir
10. **Compresión**: Comprimir archivo grande
11. **Streaming**: Procesar archivo línea por línea sin cargar todo
12. **Monitor**: Observar cambios en archivo en tiempo real

## Mejoras Posibles

1. **Menú interactivo**: Opciones de leer, escribir, append
2. **Validación de entrada**: Verificar texto antes de guardar
3. **Rutas relativas/absolutas**: Permitir elegir ubicación
4. **Manejo de errores mejorado**: Mensajes más descriptivos
5. **Configuración**: Usar Properties para configurar archivo
6. **Log de operaciones**: Registrar todas las operaciones
7. **Límite de tamaño**: Prevenir archivos muy grandes
8. **Formato personalizado**: CSV, JSON, XML

## Evaluación

Este laboratorio será evaluado considerando:
- ✅ Correcta captura de entrada desde consola
- ✅ Uso apropiado de BufferedReader/Writer
- ✅ Implementación de try-with-resources
- ✅ Manejo correcto de newLine() para multiplataforma
- ✅ Lectura completa del archivo con ready()
- ✅ Captura de excepciones FileNotFoundException e IOException
- ✅ Flujo completo: entrada → almacenamiento → lectura → salida
- ✅ Calidad del código y organización

## Recursos Relacionados

### Documentación Oficial
- [BufferedReader JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)
- [BufferedWriter JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html)
- [Try-with-Resources Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)

### Conceptos Relacionados
- **NIO.2**: API moderna de Java 7+
- **Files y Paths**: Manejo simplificado de archivos
- **CharacterEncoding**: UTF-8, UTF-16, etc.
- **PrintWriter**: Alternativa con métodos convenientes

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

