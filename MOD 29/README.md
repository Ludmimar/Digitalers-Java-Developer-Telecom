# 🔄 MOD 29 - Programación Funcional y Java Streams

## 📖 Descripción

Este módulo introduce los conceptos de **Programación Funcional** en Java y el uso de la **API Streams** para procesamiento de datos de forma declarativa. Aprenderás a transformar código imperativo en código funcional, aplicar operaciones sobre colecciones y trabajar con expresiones lambda.

---

## 📂 Contenido del Módulo

### 📚 Material Adicional
- **Programación Funcional**: Ejemplos comparativos entre código imperativo y declarativo
- **Streams**: Ejemplos prácticos de `map()`, `filter()` y `reduce()`

### 🔬 Laboratorio
- **Laboratorio**: Modelo de dominio completo (Personas, Documentos, Empleados) con:
  - Comparadores personalizados
  - Manejo de excepciones
  - DAO con conexión a MariaDB
  - Interfaces y enumerados

### 🎯 Desafíos
- **Desafío 01**: Filtrado y transformación con Streams (fechas y usuarios activos)
- **Desafío 02**: Validación de patrones con interfaces funcionales (correos y contraseñas)

### 📄 Documentación (DOCS)
- Programación Funcional - conceptos y usos
- Stream - conceptos básicos

---

## 🎯 Conceptos Clave

### 1️⃣ **Programación Funcional**
```java
// Código Imperativo (tradicional)
List<String> correos = new ArrayList<>();
for (Usuario u : usuarios) {
    if (u.getActivo() && fechaMinima.isBefore(u.getFechaCreacion())) {
        correos.add(u.getCorreo().toUpperCase());
    }
}

// Código Declarativo (funcional)
String correos = usuarios.stream()
    .filter(u -> fechaMinima.isBefore(u.getFechaCreacion()) && u.getActivo())
    .map(u -> u.getCorreo().toUpperCase())
    .reduce("", (c1, c2) -> c1.concat("\n\t ").concat(c2));
```

### 2️⃣ **Java Streams API**
```java
// Operaciones intermedias (lazy)
Stream<T> filter(Predicate<T> predicate)  // Filtra elementos
Stream<R> map(Function<T, R> mapper)        // Transforma elementos
Stream<T> sorted(Comparator<T> comparator) // Ordena elementos

// Operaciones terminales (eager)
void forEach(Consumer<T> action)           // Ejecuta acción por elemento
List<T> collect(Collectors.toList())       // Convierte a Lista
Optional<T> reduce(BinaryOperator<T> op)  // Reduce a un valor
```

### 3️⃣ **Expresiones Lambda**
```java
// Sintaxis básica
(parámetros) -> expresión
(parámetros) -> { bloque de código }

// Ejemplos
u -> u.getActivo()                                    // Un parámetro
(u, fecha) -> fecha.isBefore(u.getFechaCreacion())   // Múltiples parámetros
() -> System.out.println("Hola")                    // Sin parámetros
```

### 4️⃣ **Interfaces Funcionales**
```java
// Interfaz con métodos estáticos
public interface ValidarPatron {
    static boolean esCorreo(String correo) {
        return correo.matches("^[a-zA-Z0-9._%+-]+@educacionit\\.com$");
    }
    
    static boolean esClave(String clave) {
        return clave.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
    }
}

// Uso en Streams
usuarios.stream()
    .filter(u -> ValidarPatron.esCorreo(u.getCorreo()))
    .collect(Collectors.toList());
```

### 5️⃣ **Operaciones con Streams**
```java
// Filtrado
usuarios.stream()
    .filter(u -> u.getActivo())
    .forEach(System.out::println);

// Transformación
List<String> correos = usuarios.stream()
    .map(Usuario::getCorreo)
    .collect(Collectors.toList());

// Reducción
int total = numeros.stream()
    .reduce(0, (a, b) -> a + b);
```

---

## 🎯 Objetivo

Al finalizar este módulo podrás:  
✅ Aplicar **programación funcional** en Java  
✅ Utilizar **Java Streams API** para procesamiento de datos  
✅ Crear y usar **expresiones lambda**  
✅ Diseñar **interfaces funcionales** con métodos estáticos  
✅ Transformar código **imperativo a declarativo**  
✅ Aplicar operaciones **filter, map, reduce** sobre colecciones  
✅ Validar datos usando **patrones y expresiones regulares**

---

## 🛠️ Tecnologías Utilizadas

- **Java 11+**: Lenguaje de programación (Streams API desde Java 8)
- **Maven**: Herramienta de gestión de proyectos y dependencias
- **MariaDB**: Base de datos relacional (en Laboratorio)
- **JDBC**: Conexión a base de datos (en Laboratorio)
- **Programación Funcional**: Paradigma de programación
- **Streams API**: API para procesamiento funcional de colecciones

---

## 📁 Estructura del Módulo

```
MOD 29/
├── 📄 README.md                    # Este archivo
├── 📁 Material Adicional/          # Ejemplos adicionales
│   ├── 📁 ProgramacionFuncional/  # Imperativo vs Declarativo
│   │   ├── 📄 README.md           # Documentación del ejemplo
│   │   └── 📁 src/                # Código fuente
│   └── 📁 Stream/                 # Ejemplos de Streams
│       ├── 📄 README.md           # Documentación del ejemplo
│       └── 📁 src/                # Código fuente
├── 📁 Laboratorio/                # Proyecto completo
│   ├── 📁 Laboratorio/            # Modelo de dominio con DAO
│   │   ├── 📄 README.md           # Documentación del laboratorio
│   │   ├── 📄 Laboratorio.pdf     # Material teórico
│   │   └── 📁 src/                # Código fuente
│   └── 📄 Laboratorio.pdf         # Material teórico
├── 📁 Desafio/                     # Retos prácticos
│   ├── 📄 README.md               # Documentación de desafíos
│   ├── 📄 Desafío.pdf             # Material teórico
│   ├── 📁 Desafio01/              # Filtrado y transformación
│   │   ├── 📄 README.md           # Documentación del desafío
│   │   └── 📁 src/                # Código fuente
│   └── 📁 Desafio02/              # Validación de patrones
│       ├── 📄 README.md           # Documentación del desafío
│       └── 📁 src/                # Código fuente
└── 📁 DOCS/                       # Documentación teórica
    ├── 📄 Programación Funcional conceptos y usos.pdf
    └── 📄 Stream conceptos básicos.pdf
```

---

## 🚀 Cómo Empezar

### Prerrequisitos
- Completar MOD 28 (Java EE Avanzado)
- Conocimientos sólidos de Java y POO
- Comprensión de colecciones (List, Set, Map)
- Conocimientos básicos de Maven

### Pasos Recomendados
1. **Lee la documentación teórica** en la carpeta `DOCS/`
2. **Explora Material Adicional** para ver ejemplos básicos
3. **Comienza con Desafío 01** para entender Streams básicos
4. **Avanza a Desafío 02** para validaciones con interfaces
5. **Completa el Laboratorio** para aplicación completa
6. **Practica transformando código** imperativo a funcional

### Comandos Útiles
```bash
# Compilar un proyecto Maven
mvn clean package

# Ejecutar la aplicación principal
mvn exec:java -Dexec.mainClass="com.educacionit.principal.Principal"

# Ejecutar con clase específica
mvn exec:java -Dexec.mainClass="com.educacionIT.javase.principal.App"

# Limpiar archivos compilados
mvn clean

# Compilar sin ejecutar tests
mvn clean package -DskipTests
```

---

## 🎓 Conceptos Clave

### 🔹 **Programación Funcional**
- **Inmutabilidad**: Los datos no se modifican, se crean nuevos
- **Funciones Puras**: Sin efectos secundarios, mismo input = mismo output
- **Composición**: Combinar funciones pequeñas para crear funciones complejas
- **Expresiones Lambda**: Funciones anónimas que se pueden pasar como parámetros

### 🔹 **Java Streams**
- **Stream**: Flujo de datos que permite operaciones funcionales
- **Lazy Evaluation**: Las operaciones intermedias no se ejecutan hasta una terminal
- **Pipeline**: Encadenamiento de operaciones sobre un Stream
- **Operaciones Intermedias**: `filter()`, `map()`, `sorted()`, `distinct()`
- **Operaciones Terminales**: `forEach()`, `collect()`, `reduce()`, `count()`

### 🔹 **Expresiones Lambda**
- **Sintaxis**: `(parámetros) -> expresión` o `(parámetros) -> { bloque }`
- **Method References**: `System.out::println`, `String::toUpperCase`
- **Predicate**: `(T) -> boolean` para filtros
- **Function**: `(T) -> R` para transformaciones
- **Consumer**: `(T) -> void` para acciones

---

## 📚 Recursos Adicionales

- [Java Streams API - Documentación Oficial](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
- [Java Lambda Expressions - Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Java Functional Interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
- [Maven - Guía de Inicio](https://maven.apache.org/guides/getting-started/)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---
