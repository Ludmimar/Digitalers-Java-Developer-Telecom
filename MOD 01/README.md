# ☕ MOD 01 - Introducción a Java: Fundamentos y Sintaxis Básica

## 📖 Descripción

Este módulo introduce los **fundamentos de Java**, cubriendo desde la instalación del entorno de desarrollo hasta los conceptos básicos de programación. Aprenderás sobre variables, tipos de datos, operadores y la estructura básica de un programa Java.

---

## 📂 Contenido del Módulo

### 📚 Material Teórico
- Introducción a Java y su ecosistema
- Instalación de JDK (Java Development Kit)
- Configuración de IDE (Eclipse, IntelliJ IDEA, VS Code)
- Estructura de un programa Java
- Variables y tipos de datos primitivos

### 🎯 Conceptos Fundamentales
- Sintaxis básica de Java
- Compilación y ejecución de programas
- Comentarios y documentación
- Convenciones de nomenclatura
- Operadores aritméticos, lógicos y relacionales

### 📄 Documentación (DOCS)
- Guía de instalación JDK
- Configuración de variables de entorno
- Primeros pasos con Java

---

## 🎯 Conceptos Clave

### 1️⃣ **Estructura de un Programa Java**
```java
public class HolaMundo {
    // Método principal - punto de entrada del programa
    public static void main(String[] args) {
        System.out.println("¡Hola Mundo!");
    }
}
```

### 2️⃣ **Variables y Tipos de Datos Primitivos**
```java
// Tipos numéricos enteros
byte edad = 25;           // -128 a 127
short año = 2025;         // -32,768 a 32,767
int poblacion = 1000000;  // -2^31 a 2^31-1
long distancia = 9876543210L;  // -2^63 a 2^63-1

// Tipos numéricos decimales
float precio = 19.99f;    // Precisión simple
double pi = 3.14159265359;  // Precisión doble

// Tipo carácter
char letra = 'A';         // Un solo carácter Unicode

// Tipo booleano
boolean esActivo = true;  // true o false
```

### 3️⃣ **Operadores Aritméticos**
```java
int a = 10, b = 3;

int suma = a + b;         // 13
int resta = a - b;        // 7
int multiplicacion = a * b; // 30
int division = a / b;     // 3 (división entera)
int modulo = a % b;       // 1 (resto)

// Operadores de asignación compuesta
a += 5;  // a = a + 5
a -= 2;  // a = a - 2
a *= 3;  // a = a * 3
a /= 2;  // a = a / 2

// Operadores de incremento/decremento
a++;  // Post-incremento
++a;  // Pre-incremento
b--;  // Post-decremento
--b;  // Pre-decremento
```

### 4️⃣ **Operadores Relacionales**
```java
int x = 5, y = 10;

boolean igual = (x == y);        // false
boolean diferente = (x != y);    // true
boolean mayor = (x > y);         // false
boolean menor = (x < y);         // true
boolean mayorIgual = (x >= y);   // false
boolean menorIgual = (x <= y);   // true
```

### 5️⃣ **Operadores Lógicos**
```java
boolean a = true, b = false;

boolean and = a && b;    // false (AND lógico)
boolean or = a || b;     // true (OR lógico)
boolean not = !a;        // false (NOT lógico)

// Evaluación de cortocircuito
if (a && metodo()) {
    // metodo() solo se ejecuta si a es true
}
```

### 6️⃣ **Entrada y Salida Básica**
```java
import java.util.Scanner;

public class EntradaSalida {
    public static void main(String[] args) {
        // Salida de datos
        System.out.println("Texto con salto de línea");
        System.out.print("Texto sin salto de línea");
        System.out.printf("Formato: %d años, %.2f euros%n", 25, 19.99);
        
        // Entrada de datos
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingresa tu edad: ");
        int edad = scanner.nextInt();
        
        System.out.println("Hola " + nombre + ", tienes " + edad + " años");
        
        scanner.close();
    }
}
```

### 7️⃣ **Conversión de Tipos (Casting)**
```java
// Conversión implícita (widening)
int entero = 100;
double decimal = entero;  // 100.0 (automático)

// Conversión explícita (narrowing)
double precio = 19.99;
int precioEntero = (int) precio;  // 19 (se pierde la parte decimal)

// Conversión de String a tipos primitivos
String texto = "123";
int numero = Integer.parseInt(texto);
double decimal = Double.parseDouble("45.67");
boolean valor = Boolean.parseBoolean("true");
```

### 8️⃣ **Convenciones de Nomenclatura**
```java
// Clases: PascalCase (primera letra mayúscula)
public class MiPrimeraClase { }

// Variables y métodos: camelCase (primera letra minúscula)
int miVariable = 10;
void calcularTotal() { }

// Constantes: MAYÚSCULAS_CON_GUION_BAJO
final double PI = 3.14159;
final int MAX_VALOR = 100;

// Paquetes: todo minúsculas
package com.educacionit.proyecto;
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Instalar y configurar** el entorno de desarrollo Java  
✅ **Escribir programas básicos** con sintaxis correcta  
✅ **Declarar y usar variables** de diferentes tipos  
✅ **Aplicar operadores** aritméticos, lógicos y relacionales  
✅ **Realizar entrada/salida** de datos por consola  
✅ **Convertir tipos de datos** (casting)  
✅ **Seguir convenciones** de nomenclatura de Java  
✅ **Compilar y ejecutar** programas Java  
✅ **Depurar errores** básicos de sintaxis  
✅ **Documentar código** con comentarios  

---

## 🚀 Estructura de Proyectos

```
MOD 01/
├── DOCS/                  # Documentación teórica
│   └── Introduccion-Java.pdf
├── Ejemplos/              # Ejemplos de código
│   ├── HolaMundo.java
│   ├── Variables.java
│   ├── Operadores.java
│   └── EntradaSalida.java
├── Ejercicios/            # Ejercicios prácticos
│   ├── Ejercicio01.java
│   ├── Ejercicio02.java
│   └── Ejercicio03.java
└── README.md             # Este archivo
```

---

## 🛠️ Herramientas y Tecnologías

- **Java JDK 8+**: Kit de desarrollo de Java
- **IDE**: Eclipse, IntelliJ IDEA, VS Code, NetBeans
- **Compilador**: javac (incluido en JDK)
- **JVM**: Java Virtual Machine (ejecuta bytecode)
- **Scanner**: Clase para entrada de datos

---

## 💻 Instalación y Configuración

### 1. Instalar JDK
```bash
# Descargar JDK desde:
https://www.oracle.com/java/technologies/downloads/

# Verificar instalación
java -version
javac -version
```

### 2. Configurar Variables de Entorno
```bash
# Windows
JAVA_HOME = C:\Program Files\Java\jdk-11.0.x
PATH = %JAVA_HOME%\bin

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
export PATH=$JAVA_HOME/bin:$PATH
```

### 3. Compilar y Ejecutar
```bash
# Compilar
javac HolaMundo.java

# Ejecutar
java HolaMundo
```

---

## 📚 Ciclo de Vida de un Programa Java

```
1. Escribir código fuente → HolaMundo.java
   ↓
2. Compilar con javac → HolaMundo.class (bytecode)
   ↓
3. Ejecutar con JVM → Resultado en consola
```

---

## 🔍 Tipos de Datos Primitivos

| Tipo | Tamaño | Rango | Valor por Defecto | Ejemplo |
|------|--------|-------|-------------------|---------|
| `byte` | 8 bits | -128 a 127 | 0 | `byte edad = 25;` |
| `short` | 16 bits | -32,768 a 32,767 | 0 | `short año = 2025;` |
| `int` | 32 bits | -2³¹ a 2³¹-1 | 0 | `int poblacion = 1000000;` |
| `long` | 64 bits | -2⁶³ a 2⁶³-1 | 0L | `long distancia = 9876543210L;` |
| `float` | 32 bits | ~±3.4e±38 | 0.0f | `float precio = 19.99f;` |
| `double` | 64 bits | ~±1.7e±308 | 0.0d | `double pi = 3.14159265359;` |
| `char` | 16 bits | 0 a 65,535 (Unicode) | '\u0000' | `char letra = 'A';` |
| `boolean` | 1 bit | true/false | false | `boolean activo = true;` |

---

## 💡 Buenas Prácticas

### ✅ HACER
```java
// Nombres descriptivos
int edadEstudiante = 20;
double precioProducto = 99.99;

// Usar constantes para valores fijos
final double IVA = 0.21;

// Comentarios útiles
// Calcula el precio con IVA incluido
double precioFinal = precioProducto * (1 + IVA);

// Cerrar recursos
Scanner scanner = new Scanner(System.in);
// ... usar scanner ...
scanner.close();
```

### ❌ NO HACER
```java
// Nombres no descriptivos
int x = 20;
double y = 99.99;

// Números mágicos sin explicación
double z = y * 1.21;  // ¿Qué es 1.21?

// No cerrar recursos
Scanner sc = new Scanner(System.in);
// ... programa termina sin cerrar sc
```

---

## 🎓 Ejemplos Prácticos

### Ejemplo 1: Calculadora Simple
```java
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa el primer número: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Ingresa el segundo número: ");
        double num2 = scanner.nextDouble();
        
        double suma = num1 + num2;
        double resta = num1 - num2;
        double multiplicacion = num1 * num2;
        double division = num1 / num2;
        
        System.out.println("\nResultados:");
        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicación: " + multiplicacion);
        System.out.println("División: " + division);
        
        scanner.close();
    }
}
```

### Ejemplo 2: Conversor de Temperatura
```java
import java.util.Scanner;

public class ConversorTemperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa temperatura en Celsius: ");
        double celsius = scanner.nextDouble();
        
        // Conversión a Fahrenheit: (C × 9/5) + 32
        double fahrenheit = (celsius * 9.0 / 5.0) + 32;
        
        // Conversión a Kelvin: C + 273.15
        double kelvin = celsius + 273.15;
        
        System.out.printf("%.2f°C = %.2f°F = %.2fK%n", 
                         celsius, fahrenheit, kelvin);
        
        scanner.close();
    }
}
```

---

## 🐛 Errores Comunes

### Error 1: No cerrar Scanner
```java
// ❌ MAL
Scanner scanner = new Scanner(System.in);
// ... código ...
// No se cierra el scanner
```

### Error 2: División entera
```java
// ❌ MAL
int a = 5, b = 2;
double resultado = a / b;  // 2.0 (división entera)

// ✅ BIEN
double resultado = (double) a / b;  // 2.5
```

### Error 3: Comparar Strings con ==
```java
// ❌ MAL
String s1 = "Hola";
String s2 = "Hola";
if (s1 == s2) { }  // Compara referencias

// ✅ BIEN
if (s1.equals(s2)) { }  // Compara contenido
```

---

## 📚 Recursos Adicionales

- [Documentación oficial de Java](https://docs.oracle.com/javase/tutorial/)
- [Java SE API Documentation](https://docs.oracle.com/javase/8/docs/api/)
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/java/index.html)

---

## 🗂️ Organización del Curso

- **Módulo 1:** Introducción a Java ⬅️ Estás aquí
- **Módulo 2:** Consola y Scanner
- **Módulo 3:** Condicionales (if, switch)
- **Módulo 4:** Estructuras de bucle (for, while)
- **Módulo 5:** Métodos y arreglos

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**
