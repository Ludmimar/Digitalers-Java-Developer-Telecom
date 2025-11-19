# Desafíos - MOD 29: Programación Funcional y Streams

Este directorio contiene los desafíos prácticos del Módulo 29, enfocados en la aplicación de conceptos de **Programación Funcional** y **Java Streams** para procesamiento de datos de forma declarativa.

---

## 📋 Contenido

### Desafío 01: Filtrado y Transformación con Streams
**Objetivo:** Implementar filtrado y transformación de datos usando Java Streams para procesar una lista de usuarios.

#### Características:
- **Filtrado con Streams:** Uso de `filter()` para seleccionar usuarios según criterios
- **Transformación de Datos:** Uso de `map()` para transformar correos a mayúsculas
- **Reducción de Resultados:** Uso de `reduce()` para concatenar resultados en un String
- **Fechas con LocalDate:** Manejo de fechas y comparación con `isBefore()`
- **Programación Funcional:** Código declarativo vs imperativo

#### Estructura del Proyecto:
```
Desafio01/
├── src/main/java/com/educacionit/
│   ├── entidades/
│   │   └── Usuario.java            # Entidad Usuario con atributos
│   └── principal/
│       └── Principal.java          # Clase principal con lógica de Streams
├── pom.xml                         # Configuración Maven
└── target/                        # Archivos compilados
```

#### Conceptos Aplicados:
- ✅ **Streams API:** `filter()`, `map()`, `reduce()`
- ✅ **Expresiones Lambda:** Funciones anónimas para operaciones
- ✅ **LocalDate:** Manejo de fechas en Java 8+
- ✅ **Programación Declarativa:** Código más legible y funcional
- ✅ **Composición de Operaciones:** Encadenamiento de operaciones Stream

#### Ejemplo de Código:
```java
String correos = usuarios.stream()
    .filter(u -> fechaMinima.isBefore(u.getFechaCreacion()) && u.getActivo())
    .map(u -> u.getCorreo().toUpperCase())
    .reduce("Correos activos:", (c1, c2) -> c1.concat("\n\t ").concat(c2));
```

---

### Desafío 02: Validación de Patrones con Interfaces Funcionales
**Objetivo:** Implementar validación de correos electrónicos y contraseñas usando interfaces funcionales y Streams.

#### Características:
- **Interfaz Funcional:** `ValidarPatron` con métodos estáticos para validación
- **Validación de Correos:** Verificación de formato de email con dominio específico
- **Validación de Contraseñas:** 
  - Entre 8 y 16 caracteres
  - Al menos un dígito
  - Al menos una minúscula
  - Al menos una mayúscula
  - NO puede tener otros símbolos
- **Filtrado Avanzado:** Uso de `filter()` con métodos de validación
- **Collectors:** Uso de `Collectors.toList()` para convertir Stream a Lista
- **Operaciones de Lista:** `removeAll()` para filtrar elementos

#### Estructura del Proyecto:
```
Desafio02/
├── src/main/java/com/educacionit/
│   ├── entidades/
│   │   └── Usuario.java            # Entidad Usuario
│   ├── interfaces/
│   │   └── ValidarPatron.java     # Interfaz con métodos estáticos de validación
│   └── principal/
│       └── Principal.java          # Clase principal con validaciones
├── pom.xml                         # Configuración Maven
└── target/                        # Archivos compilados
```

#### Conceptos Aplicados:
- ✅ **Interfaces Funcionales:** Métodos estáticos en interfaces
- ✅ **Validación de Patrones:** Expresiones regulares y validaciones complejas
- ✅ **Streams Avanzados:** `filter()`, `collect()`, `forEach()`
- ✅ **Collectors:** Conversión de Streams a colecciones
- ✅ **Manejo de Listas:** Operaciones como `addAll()`, `removeAll()`

#### Ejemplo de Código:
```java
List<Usuario> usuariosEducacionIT = usuarios.stream()
    .filter(u -> ValidarPatron.esCorreo(u.getCorreo()))
    .collect(Collectors.toList());

usuariosEducacionIT.stream()
    .filter(u -> !ValidarPatron.esClave(u.getClave()))
    .forEach(u -> System.out.println("\t" + u.getCorreo()));
```

---

## 🎯 Objetivos de Aprendizaje

Al completar estos desafíos, el estudiante habrá desarrollado competencias en:

1. **Programación Funcional:** Aplicación de paradigma funcional en Java
2. **Java Streams API:** Uso de operaciones `filter()`, `map()`, `reduce()`, `collect()`
3. **Expresiones Lambda:** Creación y uso de funciones anónimas
4. **Interfaces Funcionales:** Diseño de interfaces con métodos estáticos
5. **Validación de Datos:** Implementación de validaciones complejas con patrones
6. **Código Declarativo:** Escritura de código más legible y mantenible
7. **Manejo de Fechas:** Uso de `LocalDate` para operaciones con fechas

---

## 🚀 Cómo Ejecutar

### Prerrequisitos:
- Java JDK 11 o superior (recomendado JDK 17)
- Maven 3.6 o superior

### Pasos:
1. Navegar al directorio del desafío deseado
2. Compilar el proyecto: `mvn clean package`
3. Ejecutar la aplicación: `mvn exec:java -Dexec.mainClass="com.educacionit.principal.Principal"`

### Ejemplo:
```bash
cd "Desafio01"
mvn clean package
mvn exec:java -Dexec.mainClass="com.educacionit.principal.Principal"
```

### Alternativa (compilado):
```bash
cd "Desafio01"
mvn clean package
java -cp target/classes com.educacionit.principal.Principal
```

---

## 📚 Conceptos Teóricos Relacionados

- **Streams API:** Flujo de datos para procesamiento funcional
- **Expresiones Lambda:** Funciones anónimas `(param) -> expresión`
- **Programación Funcional:** Paradigma basado en funciones puras
- **Interfaces Funcionales:** Interfaces con un solo método abstracto
- **LocalDate:** Clase para manejo de fechas sin hora
- **Collectors:** Utilidades para convertir Streams a colecciones
- **Operaciones Terminales:** `forEach()`, `collect()`, `reduce()`
- **Operaciones Intermedias:** `filter()`, `map()`, `sorted()`

---

## 🔗 Enlaces Útiles

- [Java Streams API - Documentación Oficial](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
- [Java Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Java LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)
- [Maven - Guía de Inicio](https://maven.apache.org/guides/getting-started/)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

