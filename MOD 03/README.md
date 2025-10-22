# 🔀 MOD 03 - Estructuras Condicionales: If, Else if, Else y Switch

## 📖 Descripción

Este módulo cubre las **estructuras de control condicionales** en Java, permitiéndote crear programas que toman decisiones basadas en condiciones. Aprenderás a usar `if`, `else if`, `else`, `switch-case`, operadores ternarios y a combinar condiciones con operadores lógicos.

---

## 📂 Contenido del Módulo

### 📚 Material Teórico
- Estructuras condicionales simples (if)
- Estructuras condicionales compuestas (if-else)
- Estructuras condicionales anidadas (if-else if-else)
- Switch-case statement
- Operador ternario (? :)
- Operadores lógicos (&&, ||, !)

### 🎯 Conceptos Fundamentales
- Toma de decisiones en programación
- Evaluación de expresiones booleanas
- Casos de uso de cada estructura
- Cortocircuito de operadores lógicos
- Switch con String (Java 7+)
- Switch expressions (Java 12+)

### 📄 Ejercicios Prácticos
- Validación de edad
- Calculadora de notas
- Sistema de descuentos
- Menú de opciones
- Clasificación de números

---

## 🎯 Conceptos Clave

### 1️⃣ **If Simple**
```java
int edad = 18;

if (edad >= 18) {
    System.out.println("Eres mayor de edad");
}

// Sintaxis corta (sin llaves) - Solo para una instrucción
if (edad >= 18)
    System.out.println("Eres mayor de edad");
```

### 2️⃣ **If-Else**
```java
int edad = 16;

if (edad >= 18) {
    System.out.println("Eres mayor de edad");
} else {
    System.out.println("Eres menor de edad");
}

// Operador ternario equivalente
String mensaje = (edad >= 18) ? "Mayor de edad" : "Menor de edad";
System.out.println(mensaje);
```

### 3️⃣ **If-Else If-Else**
```java
double nota = 8.5;

if (nota >= 9.0) {
    System.out.println("Sobresaliente");
} else if (nota >= 7.0) {
    System.out.println("Notable");
} else if (nota >= 5.0) {
    System.out.println("Aprobado");
} else {
    System.out.println("Suspenso");
}
```

### 4️⃣ **If Anidados**
```java
int edad = 25;
boolean tieneLicencia = true;

if (edad >= 18) {
    if (tieneLicencia) {
        System.out.println("Puedes conducir");
    } else {
        System.out.println("Necesitas obtener la licencia");
    }
} else {
    System.out.println("No tienes edad para conducir");
}
```

### 5️⃣ **Switch-Case Tradicional**
```java
int dia = 3;
String nombreDia;

switch (dia) {
    case 1:
        nombreDia = "Lunes";
        break;
    case 2:
        nombreDia = "Martes";
        break;
    case 3:
        nombreDia = "Miércoles";
        break;
    case 4:
        nombreDia = "Jueves";
        break;
    case 5:
        nombreDia = "Viernes";
        break;
    case 6:
        nombreDia = "Sábado";
        break;
    case 7:
        nombreDia = "Domingo";
        break;
    default:
        nombreDia = "Día inválido";
        break;
}

System.out.println(nombreDia);
```

### 6️⃣ **Switch con String** (Java 7+)
```java
String mes = "enero";

switch (mes.toLowerCase()) {
    case "enero":
    case "febrero":
    case "marzo":
        System.out.println("Primer trimestre");
        break;
    case "abril":
    case "mayo":
    case "junio":
        System.out.println("Segundo trimestre");
        break;
    case "julio":
    case "agosto":
    case "septiembre":
        System.out.println("Tercer trimestre");
        break;
    case "octubre":
    case "noviembre":
    case "diciembre":
        System.out.println("Cuarto trimestre");
        break;
    default:
        System.out.println("Mes inválido");
}
```

### 7️⃣ **Operadores Lógicos**
```java
int edad = 25;
boolean tieneLicencia = true;
double saldo = 5000;

// AND (&&) - Ambas condiciones deben ser true
if (edad >= 18 && tieneLicencia) {
    System.out.println("Puede alquilar un coche");
}

// OR (||) - Al menos una condición debe ser true
if (edad < 18 || edad > 65) {
    System.out.println("Tarifa con descuento");
}

// NOT (!) - Invierte el valor booleano
if (!tieneLicencia) {
    System.out.println("No puede conducir");
}

// Combinación compleja
if ((edad >= 18 && tieneLicencia) || saldo > 10000) {
    System.out.println("Préstamo aprobado");
}
```

### 8️⃣ **Cortocircuito de Operadores**
```java
int x = 0;
int y = 10;

// Con && si x == 0 es false, no evalúa y/x (evita división por cero)
if (x != 0 && y/x > 5) {
    System.out.println("Resultado mayor a 5");
}

// Con || si x == 0 es true, no evalúa y/x
if (x == 0 || y/x > 5) {
    System.out.println("x es cero o resultado mayor a 5");
}
```

### 9️⃣ **Operador Ternario**
```java
// Sintaxis: condición ? valorSiTrue : valorSiFalse

int edad = 20;
String categoria = (edad >= 18) ? "Adulto" : "Menor";

// Anidado
int nota = 8;
String resultado = (nota >= 9) ? "Excelente" :
                   (nota >= 7) ? "Bueno" :
                   (nota >= 5) ? "Regular" : "Insuficiente";

// Con operaciones
int a = 10, b = 20;
int mayor = (a > b) ? a : b;
System.out.println("El mayor es: " + mayor);
```

### 🔟 **Switch Expressions** (Java 12+)
```java
String dia = "Lunes";

// Nueva sintaxis con -> (sin break necesario)
String tipo = switch (dia) {
    case "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" -> "Día laboral";
    case "Sábado", "Domingo" -> "Fin de semana";
    default -> "Día inválido";
};

System.out.println(tipo);

// Con yield (para bloques complejos)
int mes = 2;
int dias = switch (mes) {
    case 1, 3, 5, 7, 8, 10, 12 -> 31;
    case 4, 6, 9, 11 -> 30;
    case 2 -> {
        boolean esBisiesto = true;  // Lógica compleja
        yield esBisiesto ? 29 : 28;
    }
    default -> throw new IllegalArgumentException("Mes inválido");
};
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Usar if-else** para toma de decisiones simples  
✅ **Implementar if-else if-else** para múltiples condiciones  
✅ **Aplicar switch-case** para selección múltiple  
✅ **Combinar condiciones** con operadores lógicos  
✅ **Usar operador ternario** para asignaciones condicionales  
✅ **Validar datos** de entrada del usuario  
✅ **Anidar estructuras** condicionales correctamente  
✅ **Elegir la estructura** más apropiada según el caso  
✅ **Evitar errores comunes** con switch (break)  
✅ **Optimizar condiciones** con cortocircuito  

---

## 🚀 Estructura de Proyectos

```
MOD 03/
├── DOCS/                  # Documentación teórica
│   └── Condicionales.pdf
├── Ejemplos/              # Ejemplos de código
│   ├── IfElse.java
│   ├── IfElseIfElse.java
│   ├── SwitchCase.java
│   ├── OperadoresLogicos.java
│   └── OperadorTernario.java
├── Ejercicios/            # Ejercicios prácticos
│   ├── Calculadora.java
│   ├── SistemaNotas.java
│   ├── MenuOpciones.java
│   ├── Descuentos.java
│   └── ValidacionEdad.java
└── README.md             # Este archivo
```

---

## 🛠️ Herramientas y Tecnologías

- **Java JDK 8+**: Kit de desarrollo de Java
- **Operadores relacionales**: ==, !=, >, <, >=, <=
- **Operadores lógicos**: &&, ||, !
- **Estructuras**: if, else, else if, switch, ternario
- **Scanner**: Para entrada de datos interactiva

---

## 📊 Comparación de Estructuras

| Estructura | Uso Recomendado | Ejemplo |
|-----------|-----------------|---------|
| **if** | Condición simple | `if (edad >= 18)` |
| **if-else** | Dos alternativas | `if (pago) {...} else {...}` |
| **if-else if-else** | Múltiples rangos | Clasificación de notas |
| **switch** | Valores discretos | Días de la semana, menús |
| **ternario** | Asignación condicional simple | `max = (a > b) ? a : b` |

---

## 🎓 Ejemplos Prácticos

### Ejemplo 1: Calculadora de Notas
```java
import java.util.Scanner;

public class CalculadoraNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa tu nota (0-10): ");
        double nota = scanner.nextDouble();
        
        if (nota < 0 || nota > 10) {
            System.out.println("Nota inválida");
        } else if (nota >= 9.0) {
            System.out.println("Sobresaliente - Excelente trabajo!");
        } else if (nota >= 7.0) {
            System.out.println("Notable - Muy bien!");
        } else if (nota >= 5.0) {
            System.out.println("Aprobado - Buen esfuerzo!");
        } else {
            System.out.println("Suspenso - Debes mejorar");
        }
        
        scanner.close();
    }
}
```

### Ejemplo 2: Sistema de Descuentos
```java
import java.util.Scanner;

public class SistemaDescuentos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa el precio del producto: $");
        double precio = scanner.nextDouble();
        
        System.out.print("¿Eres miembro del club? (true/false): ");
        boolean esMiembro = scanner.nextBoolean();
        
        System.out.print("Ingresa tu edad: ");
        int edad = scanner.nextInt();
        
        double descuento = 0;
        
        // Descuento por membresía
        if (esMiembro) {
            descuento += 0.10;  // 10% descuento
        }
        
        // Descuento por edad
        if (edad < 18 || edad >= 65) {
            descuento += 0.05;  // 5% descuento adicional
        }
        
        // Descuento por compra mayor
        if (precio >= 1000) {
            descuento += 0.15;  // 15% descuento adicional
        }
        
        double precioFinal = precio * (1 - descuento);
        double ahorrado = precio - precioFinal;
        
        System.out.printf("\nPrecio original: $%.2f%n", precio);
        System.out.printf("Descuento total: %.0f%%%n", descuento * 100);
        System.out.printf("Ahorrado: $%.2f%n", ahorrado);
        System.out.printf("Precio final: $%.2f%n", precioFinal);
        
        scanner.close();
    }
}
```

### Ejemplo 3: Menú de Restaurante
```java
import java.util.Scanner;

public class MenuRestaurante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== MENÚ DEL RESTAURANTE ===");
        System.out.println("1. Pizza - $12.99");
        System.out.println("2. Hamburguesa - $8.99");
        System.out.println("3. Ensalada - $6.99");
        System.out.println("4. Pasta - $10.99");
        System.out.println("5. Sushi - $15.99");
        System.out.print("\nSelecciona una opción (1-5): ");
        
        int opcion = scanner.nextInt();
        String plato;
        double precio;
        
        switch (opcion) {
            case 1:
                plato = "Pizza";
                precio = 12.99;
                break;
            case 2:
                plato = "Hamburguesa";
                precio = 8.99;
                break;
            case 3:
                plato = "Ensalada";
                precio = 6.99;
                break;
            case 4:
                plato = "Pasta";
                precio = 10.99;
                break;
            case 5:
                plato = "Sushi";
                precio = 15.99;
                break;
            default:
                plato = "Opción inválida";
                precio = 0;
        }
        
        if (precio > 0) {
            System.out.printf("\nHas pedido: %s%n", plato);
            System.out.printf("Total a pagar: $%.2f%n", precio);
        } else {
            System.out.println("\n" + plato);
        }
        
        scanner.close();
    }
}
```

### Ejemplo 4: Calculadora con Switch
```java
import java.util.Scanner;

public class CalculadoraSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa el primer número: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Ingresa el segundo número: ");
        double num2 = scanner.nextDouble();
        
        System.out.print("Operación (+, -, *, /): ");
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
        
        scanner.close();
    }
}
```

---

## 🐛 Errores Comunes

### Error 1: Olvidar break en switch
```java
// ❌ MAL - Fall-through no deseado
switch (dia) {
    case 1:
        System.out.println("Lunes");
        // Sin break! Ejecuta el siguiente caso también
    case 2:
        System.out.println("Martes");
        break;
}

// ✅ BIEN
switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;  // Termina el switch
    case 2:
        System.out.println("Martes");
        break;
}
```

### Error 2: Comparar Strings con ==
```java
String color = "rojo";

// ❌ MAL
if (color == "rojo") {  // Compara referencias, no contenido
    System.out.println("Es rojo");
}

// ✅ BIEN
if (color.equals("rojo")) {  // Compara contenido
    System.out.println("Es rojo");
}

// ✅ MEJOR (ignora mayúsculas/minúsculas)
if (color.equalsIgnoreCase("rojo")) {
    System.out.println("Es rojo");
}
```

### Error 3: Lógica de cortocircuito incorrecta
```java
int x = 0;

// ❌ MAL - División por cero
if (x != 0 & 10/x > 5) {  // & evalúa ambos lados
    System.out.println("OK");
}

// ✅ BIEN - Cortocircuito
if (x != 0 && 10/x > 5) {  // && no evalúa el segundo si el primero es false
    System.out.println("OK");
}
```

---

## 💡 Buenas Prácticas

### ✅ HACER
```java
// Usar llaves incluso con una sola instrucción
if (condicion) {
    instruccion();
}

// Validar rangos primero
if (nota < 0 || nota > 10) {
    System.out.println("Nota inválida");
} else {
    // Procesar nota válida
}

// Switch con default
switch (opcion) {
    case 1:
        // ...
        break;
    default:
        System.out.println("Opción no válida");
}
```

### ❌ NO HACER
```java
// Evitar ifs sin llaves (confuso)
if (condicion)
    instruccion1();
    instruccion2();  // ¡No está dentro del if!

// No comparar con true/false explícitamente
if (esValido == true)  // Redundante
if (esValido)  // Mejor

// No anidar demasiado (max 3 niveles)
if (...) {
    if (...) {
        if (...) {
            if (...) {  // Muy anidado, difícil de leer
```

---

## 📚 Recursos Adicionales

- [Java Control Flow](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Switch Statement](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)
- [Operators](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos  

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**
