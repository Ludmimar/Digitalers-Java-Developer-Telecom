# Desafío 01 - Búsqueda y Reemplazo en Archivos de Texto

## Descripción

Este desafío implementa un sistema de búsqueda y reemplazo de palabras en archivos de texto. El programa lee un archivo, busca una palabra específica ingresada por el usuario, reporta todas las ocurrencias con su posición (fila y columna), y reemplaza las palabras encontradas con marcadores especiales `<< >>`.

## Funcionalidades

### 🔍 Búsqueda de Palabras
- Búsqueda de palabra específica en archivo de texto
- Detección de todas las ocurrencias en el archivo
- Reporte de posición exacta (fila y columna inicial)
- Mensaje informativo si la palabra no existe

### ✏️ Reemplazo Automático
- Reemplazo de palabra con formato `<<palabra>>`
- Preservación del resto del contenido
- Actualización automática del archivo
- Confirmación de operaciones realizadas

### 📄 Manejo de Archivos
- Lectura de archivo línea por línea
- Almacenamiento temporal en memoria (List)
- Escritura completa del archivo modificado
- Gestión automática de recursos

## Conceptos Técnicos Aplicados

### Java I/O
- **BufferedReader**: Lectura eficiente de archivos
- **BufferedWriter**: Escritura eficiente de archivos
- **FileReader/FileWriter**: Streams de caracteres
- **Try-with-resources**: Gestión automática de recursos

### Interfaces con Métodos Estáticos
- **Métodos estáticos en interfaces**: Utilidades reutilizables
- **Encapsulación de operaciones I/O**: Código organizado
- **Abstracción**: Simplificación de operaciones complejas

### Manipulación de Strings
- **contains()**: Verificación de presencia de substring
- **indexOf()**: Obtención de posición de substring
- **replace()**: Reemplazo de texto
- **List<String>**: Manejo de líneas en memoria

### Manejo de Excepciones
- **FileNotFoundException**: Archivo no encontrado
- **IOException**: Errores de entrada/salida
- **printStackTrace()**: Trazabilidad de errores

## Estructura del Proyecto

```
Desafio01/
├── src/main/java/com/educacionit/desafio01/
│   ├── App.java                    # Aplicación principal
│   └── interfaces/
│       └── Archivo.java            # Interface con operaciones I/O
├── Java.txt                        # Archivo de texto de prueba
├── pom.xml                         # Configuración Maven
└── README.md                       # Este archivo
```

## Cómo Ejecutar

### Usando Maven
```bash
# Navegar al directorio
cd Desafio01

# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="com.educacionit.desafio01.App"
```

### Compilación Manual
```bash
# Compilar
javac -d target/classes src/main/java/com/educacionit/desafio01/interfaces/Archivo.java
javac -cp target/classes -d target/classes src/main/java/com/educacionit/desafio01/App.java

# Ejecutar
java -cp target/classes com.educacionit.desafio01.App
```

## Ejemplo de Uso

### Entrada
```
Indique la palabra a buscar: Java
```

### Salida
```
La palabra <<Java>> se encuentra en la fila 1 y comienza en la columna 0
La palabra <<Java>> se encuentra en la fila 3 y comienza en la columna 15
La palabra <<Java>> se encuentra en la fila 7 y comienza en la columna 8
```

### Archivo Original (Java.txt)
```
Java es un lenguaje de programación.
Es multiplataforma.
Se usa en desarrollo web, móvil y empresarial.
Java fue creado por Sun Microsystems.
Hoy es mantenido por Oracle.
```

### Archivo Modificado
```
<<Java>> es un lenguaje de programación.
Es multiplataforma.
Se usa en desarrollo web, móvil y empresarial.
<<Java>> fue creado por Sun Microsystems.
Hoy es mantenido por Oracle.
```

## Análisis del Código

### Interface Archivo
```java
public interface Archivo {
    
    // Método estático para leer archivo
    static List<String> leer(String directorio) {
        List<String> texto = new ArrayList<>();
        File archivoLectura = new File(directorio);
        
        try (BufferedReader leerFichero = new BufferedReader(new FileReader(archivoLectura))) {
            String mensaje = null;
            
            if (leerFichero.ready()) {
                while ((mensaje = leerFichero.readLine()) != null) {
                    texto.add(mensaje);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }
    
    // Método estático para escribir archivo
    static boolean escribir(String directorio, List<String> texto) {
        File archivoEscritura = new File(directorio);
        
        try (BufferedWriter escribirFichero = new BufferedWriter(new FileWriter(archivoEscritura, false))) {
            for (String linea : texto) {
                escribirFichero.write(linea);
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
}
```

### Aplicación Principal
```java
public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String archivo = "Java.txt";
        String palabra;
        boolean existe = false;

        System.out.print("Indique la palabra a buscar: ");
        palabra = teclado.next();

        // Leer archivo completo
        List<String> texto = Archivo.leer(archivo);

        // Buscar y reemplazar
        for (int i = 0; i < texto.size(); i++) {
            String linea = texto.get(i);
            
            if (linea.contains(palabra)) {
                String nuevaPalabra = "<<" + palabra + ">>";
                System.out.println("La palabra " + nuevaPalabra + " se encuentra en la fila " + (i + 1)
                        + " y comienza en la columna " + linea.indexOf(palabra));
                
                texto.set(i, linea.replace(palabra, nuevaPalabra));
                existe = true;
            }
        }

        if (!existe) {
            System.out.println("La palabra indicada no existe en el archivo");
        }

        // Escribir archivo modificado
        Archivo.escribir(archivo, texto);
        
        teclado.close();
    }
}
```

## Puntos de Aprendizaje

### 1. Try-with-Resources
- **Gestión automática**: Los recursos se cierran automáticamente
- **Sintaxis limpia**: Código más legible
- **Prevención de fugas**: No hay riesgo de olvidar close()
- **Múltiples recursos**: Se pueden declarar varios en un try

### 2. BufferedReader/Writer
- **Eficiencia**: Uso de buffer interno reduce I/O
- **Método readLine()**: Lee línea completa automáticamente
- **Método newLine()**: Multiplataforma (\\n, \\r\\n)
- **ready()**: Verifica si hay datos disponibles

### 3. Métodos Estáticos en Interfaces
- **Utilidades**: Métodos que no requieren instancia
- **Organización**: Agrupación de funciones relacionadas
- **Java 8+**: Característica introducida en Java 8
- **Sin estado**: No mantienen información entre llamadas

### 4. Manipulación de Strings
- **Inmutabilidad**: Strings no se modifican, se crean nuevos
- **contains()**: O(n) para búsqueda
- **indexOf()**: Retorna -1 si no encuentra
- **replace()**: Reemplaza todas las ocurrencias

## Posibles Mejoras

1. **Búsqueda case-insensitive**: Usar `equalsIgnoreCase()` o regex
2. **Múltiples palabras**: Permitir buscar varias palabras
3. **Expresiones regulares**: Patrones más complejos
4. **Respaldo del archivo**: Guardar copia antes de modificar
5. **Contador de reemplazos**: Mostrar total de cambios
6. **Validación de archivo**: Verificar existencia antes de leer
7. **Formato configurable**: Permitir elegir marcadores
8. **Búsqueda en múltiples archivos**: Procesar directorio completo

## Ejercicios Sugeridos

1. **Búsqueda insensible a mayúsculas**: Modificar para ignorar case
2. **Contador de palabras**: Implementar función que cuente palabras
3. **Búsqueda con regex**: Usar expresiones regulares
4. **Menú de opciones**: Agregar más operaciones (eliminar, insertar)
5. **Historial de cambios**: Registrar modificaciones en log
6. **Deshacer cambios**: Restaurar versión anterior
7. **Búsqueda en directorio**: Procesar múltiples archivos

## Evaluación

Este desafío será evaluado considerando:
- ✅ Correcta lectura de archivo línea por línea
- ✅ Búsqueda efectiva de palabra en cada línea
- ✅ Cálculo correcto de posición (fila y columna)
- ✅ Reemplazo preciso con marcadores
- ✅ Escritura correcta del archivo modificado
- ✅ Uso apropiado de try-with-resources
- ✅ Manejo de excepciones I/O
- ✅ Calidad del código y organización

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

