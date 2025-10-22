# 💻 MOD 02 - Consola e Interacción con el Usuario

## 📖 Descripción

Este módulo profundiza en la **interacción con el usuario a través de la consola**, utilizando la clase `Scanner` para entrada de datos y diferentes métodos de salida. Aprenderás a crear programas interactivos, validar entrada de datos y formatear salidas de manera profesional.

---

## 📂 Contenido del Módulo

### 📚 Material Teórico
- Clase Scanner y sus métodos
- System.out: print, println, printf
- Lectura de diferentes tipos de datos
- Manejo de buffers y saltos de línea
- Formateo de salida de datos

### 🎯 Conceptos Fundamentales
- Entrada de datos por consola
- Validación básica de entrada
- Formato de números y texto
- Limpieza de buffer
- Manejo de excepciones InputMismatchException

### 📄 Ejercicios Prácticos
- Calculadoras interactivas
- Conversores de unidades
- Formularios por consola
- Menús de opciones

---

## 🎯 Conceptos Clave

### 1️⃣ **Clase Scanner - Métodos Principales**
```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

// Lectura de diferentes tipos
String texto = scanner.nextLine();      // Lee línea completa
String palabra = scanner.next();        // Lee hasta el primer espacio
int entero = scanner.nextInt();         // Lee un entero
double decimal = scanner.nextDouble();  // Lee un decimal
boolean booleano = scanner.nextBoolean(); // Lee true/false
char caracter = scanner.next().charAt(0); // Lee un carácter

scanner.close();  // ¡Siempre cerrar!
```

### 2️⃣ **Problema del Buffer y Solución**
```java
Scanner scanner = new Scanner(System.in);

System.out.print("Ingresa tu edad: ");
int edad = scanner.nextInt();

// Problema: nextInt() deja el \n en el buffer
scanner.nextLine();  // Limpia el buffer

System.out.print("Ingresa tu nombre: ");
String nombre = scanner.nextLine();  // Ahora funciona correctamente

scanner.close();
```

### 3️⃣ **Formateo con printf()**
```java
String nombre = "Ana";
int edad = 25;
double salario = 45000.75;

// %s = String, %d = int, %f = float/double, %n = salto de línea
System.out.printf("Nombre: %s%n", nombre);
System.out.printf("Edad: %d años%n", edad);
System.out.printf("Salario: $%.2f%n", salario);  // 2 decimales

// Alineación y ancho
System.out.printf("%-20s %10d%n", nombre, edad);  // Izq | Der

// Resultado:
// Nombre: Ana
// Edad: 25 años
// Salario: $45000.75
// Ana                         25
```

### 4️⃣ **Diferentes Métodos de Salida**
```java
// println - imprime y salta de línea
System.out.println("Hola Mundo");

// print - imprime sin saltar de línea
System.out.print("Hola ");
System.out.print("Mundo");  // HolaMundo

// printf - imprime con formato
System.out.printf("Precio: $%.2f%n", 19.99);

// Concatenación
String nombre = "Juan";
System.out.println("Hola " + nombre);

// Usando format()
String mensaje = String.format("Hola %s, tienes %d años", nombre, 25);
System.out.println(mensaje);
```

### 5️⃣ **Validación de Entrada**
```java
Scanner scanner = new Scanner(System.in);
int numero = 0;
boolean entradaValida = false;

while (!entradaValida) {
    try {
        System.out.print("Ingresa un número: ");
        numero = scanner.nextInt();
        entradaValida = true;  // Entrada válida, salir del bucle
    } catch (InputMismatchException e) {
        System.out.println("Error: Debes ingresar un número entero");
        scanner.nextLine();  // Limpiar buffer
    }
}

System.out.println("Número ingresado: " + numero);
scanner.close();
```

### 6️⃣ **Menú Interactivo**
```java
Scanner scanner = new Scanner(System.in);
int opcion = 0;

do {
    System.out.println("\n=== MENÚ PRINCIPAL ===");
    System.out.println("1. Sumar");
    System.out.println("2. Restar");
    System.out.println("3. Multiplicar");
    System.out.println("4. Dividir");
    System.out.println("0. Salir");
    System.out.print("Selecciona una opción: ");
    
    opcion = scanner.nextInt();
    
    switch (opcion) {
        case 1:
            System.out.println("Seleccionaste SUMAR");
            // ... código de suma
            break;
        case 2:
            System.out.println("Seleccionaste RESTAR");
            // ... código de resta
            break;
        case 3:
            System.out.println("Seleccionaste MULTIPLICAR");
            // ... código de multiplicación
            break;
        case 4:
            System.out.println("Seleccionaste DIVIDIR");
            // ... código de división
            break;
        case 0:
            System.out.println("¡Hasta luego!");
            break;
        default:
            System.out.println("Opción no válida");
    }
} while (opcion != 0);

scanner.close();
```

### 7️⃣ **Lectura Segura con hasNext()**
```java
Scanner scanner = new Scanner(System.in);

System.out.print("Ingresa un número: ");
if (scanner.hasNextInt()) {
    int numero = scanner.nextInt();
    System.out.println("Número válido: " + numero);
} else {
    System.out.println("Entrada no válida");
    scanner.next();  // Descartar entrada incorrecta
}

scanner.close();
```

### 8️⃣ **Formateo de Números**
```java
double numero = 1234567.89;

// Con comas separadoras de miles
System.out.printf("%,.2f%n", numero);  // 1,234,567.89

// Con signo
System.out.printf("%+.2f%n", numero);  // +1234567.89

// Rellenado con ceros
System.out.printf("%010.2f%n", numero);  // 01234567.89

// Notación científica
System.out.printf("%e%n", numero);  // 1.234568e+06

// Porcentaje
double porcentaje = 0.85;
System.out.printf("%.0f%%%n", porcentaje * 100);  // 85%
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Usar Scanner** para entrada de datos interactiva  
✅ **Leer diferentes tipos** de datos (int, double, String, etc.)  
✅ **Formatear salidas** con printf()  
✅ **Manejar el buffer** correctamente  
✅ **Validar entrada** del usuario  
✅ **Crear menús** interactivos  
✅ **Aplicar try-catch** para InputMismatchException  
✅ **Usar hasNext()** para validación preventiva  
✅ **Formatear números** con separadores y decimales  
✅ **Crear interfaces** de consola amigables  

---

## 🚀 Estructura de Proyectos

```
MOD 02/
├── DOCS/                  # Documentación teórica
│   └── Scanner-Consola.pdf
├── Ejemplos/              # Ejemplos de código
│   ├── EntradaDatos.java
│   ├── FormateoSalida.java
│   ├── ValidacionEntrada.java
│   └── MenuInteractivo.java
├── Ejercicios/            # Ejercicios prácticos
│   ├── Calculadora.java
│   ├── ConversorUnidades.java
│   ├── Formulario.java
│   └── MenuOpciones.java
└── README.md             # Este archivo
```

---

## 🛠️ Herramientas y Tecnologías

- **Java JDK 8+**: Kit de desarrollo de Java
- **Scanner**: Clase para entrada de datos
- **System.out**: Flujo de salida estándar
- **PrintStream**: Clase para formateo avanzado
- **Exceptions**: Manejo de errores de entrada

---

## 📋 Especificadores de Formato

| Especificador | Tipo | Ejemplo | Resultado |
|--------------|------|---------|-----------|
| `%s` | String | `printf("%s", "Hola")` | Hola |
| `%d` | int | `printf("%d", 42)` | 42 |
| `%f` | float/double | `printf("%.2f", 3.14159)` | 3.14 |
| `%e` | Notación científica | `printf("%e", 1000.0)` | 1.000000e+03 |
| `%b` | boolean | `printf("%b", true)` | true |
| `%c` | char | `printf("%c", 'A')` | A |
| `%n` | Salto de línea | `printf("Hola%n")` | Hola\n |
| `%%` | Símbolo % | `printf("50%%")` | 50% |

---

## 💡 Modificadores de Formato

```java
int numero = 42;
double decimal = 3.14159;
String texto = "Java";

// Ancho mínimo
System.out.printf("%10d%n", numero);        //         42
System.out.printf("%10s%n", texto);         //       Java

// Alineación a la izquierda
System.out.printf("%-10d|%n", numero);      // 42        |
System.out.printf("%-10s|%n", texto);       // Java      |

// Rellenado con ceros
System.out.printf("%010d%n", numero);       // 0000000042

// Precisión decimal
System.out.printf("%.2f%n", decimal);       // 3.14
System.out.printf("%.4f%n", decimal);       // 3.1416

// Signo
System.out.printf("%+d%n", numero);         // +42
System.out.printf("%+.2f%n", decimal);      // +3.14

// Separador de miles
System.out.printf("%,d%n", 1000000);        // 1,000,000
System.out.printf("%,.2f%n", 1234567.89);   // 1,234,567.89
```

---

## 🎓 Ejemplos Prácticos

### Ejemplo 1: Calculadora con Validación
```java
import java.util.Scanner;
import java.util.InputMismatchException;

public class CalculadoraSegura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingresa el primer número: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Ingresa el segundo número: ");
            double num2 = scanner.nextDouble();
            
            System.out.print("Ingresa la operación (+, -, *, /): ");
            char operacion = scanner.next().charAt(0);
            
            double resultado = 0;
            boolean operacionValida = true;
            
            switch (operacion) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        System.out.println("Error: División por cero");
                        operacionValida = false;
                    }
                    break;
                default:
                    System.out.println("Operación no válida");
                    operacionValida = false;
            }
            
            if (operacionValida) {
                System.out.printf("%.2f %c %.2f = %.2f%n", 
                                 num1, operacion, num2, resultado);
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar números válidos");
        } finally {
            scanner.close();
        }
    }
}
```

### Ejemplo 2: Formulario de Registro
```java
import java.util.Scanner;

public class FormularioRegistro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== FORMULARIO DE REGISTRO ===\n");
        
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        
        System.out.print("Altura (m): ");
        double altura = scanner.nextDouble();
        
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();
        
        scanner.nextLine();  // Limpiar buffer
        
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        
        System.out.print("¿Es estudiante? (true/false): ");
        boolean esEstudiante = scanner.nextBoolean();
        
        // Calcular IMC
        double imc = peso / (altura * altura);
        
        // Mostrar resumen
        System.out.println("\n=== RESUMEN DEL REGISTRO ===");
        System.out.printf("Nombre: %s%n", nombre);
        System.out.printf("Edad: %d años%n", edad);
        System.out.printf("Altura: %.2f m%n", altura);
        System.out.printf("Peso: %.2f kg%n", peso);
        System.out.printf("IMC: %.2f%n", imc);
        System.out.printf("Ciudad: %s%n", ciudad);
        System.out.printf("Estudiante: %s%n", esEstudiante ? "Sí" : "No");
        
        scanner.close();
    }
}
```

### Ejemplo 3: Conversor de Monedas
```java
import java.util.Scanner;

public class ConversorMonedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        final double USD_A_EUR = 0.85;
        final double USD_A_GBP = 0.73;
        final double USD_A_JPY = 110.0;
        
        System.out.println("=== CONVERSOR DE MONEDAS ===\n");
        System.out.print("Ingresa cantidad en USD: $");
        double cantidadUSD = scanner.nextDouble();
        
        System.out.println("\nConversiones:");
        System.out.printf("$%.2f USD = €%.2f EUR%n", 
                         cantidadUSD, cantidadUSD * USD_A_EUR);
        System.out.printf("$%.2f USD = £%.2f GBP%n", 
                         cantidadUSD, cantidadUSD * USD_A_GBP);
        System.out.printf("$%.2f USD = ¥%.2f JPY%n", 
                         cantidadUSD, cantidadUSD * USD_A_JPY);
        
        scanner.close();
    }
}
```

---

## 🐛 Errores Comunes

### Error 1: No limpiar el buffer
```java
// ❌ MAL
int edad = scanner.nextInt();
String nombre = scanner.nextLine();  // Lee línea vacía

// ✅ BIEN
int edad = scanner.nextInt();
scanner.nextLine();  // Limpiar buffer
String nombre = scanner.nextLine();  // Ahora funciona
```

### Error 2: No validar entrada
```java
// ❌ MAL
int numero = scanner.nextInt();  // Crash si no es número

// ✅ BIEN
try {
    int numero = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Entrada no válida");
    scanner.next();  // Limpiar entrada incorrecta
}
```

### Error 3: No cerrar Scanner
```java
// ❌ MAL
Scanner scanner = new Scanner(System.in);
// ... usar scanner ...
// No se cierra

// ✅ BIEN
Scanner scanner = new Scanner(System.in);
try {
    // ... usar scanner ...
} finally {
    scanner.close();
}
```

---

## 📚 Recursos Adicionales

- [Scanner JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)
- [PrintStream JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html)
- [Formatter JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos  

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**
