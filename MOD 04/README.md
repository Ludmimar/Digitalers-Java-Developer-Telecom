# 🔄 MOD 04 - Estructuras de Bucle: For, While y Do-While

## 📖 Descripción

Este módulo cubre las **estructuras de repetición o bucles** en Java, permitiéndote ejecutar bloques de código múltiples veces. Aprenderás a usar `for`, `while`, `do-while`, bucles anidados, y las sentencias `break` y `continue` para control de flujo avanzado.

---

## 📂 Contenido del Módulo

### 📚 Material Teórico
- Bucle for (contador)
- Bucle while (condición previa)
- Bucle do-while (condición posterior)
- Bucles anidados
- Sentencias break y continue
- Enhanced for (for-each)

### 🎯 Conceptos Fundamentales
- Iteración y repetición
- Condiciones de terminación
- Contadores y acumuladores
- Bucles infinitos y cómo evitarlos
- Optimización de bucles
- Recorrido de estructuras

### 📄 Ejercicios Prácticos
- Tablas de multiplicar
- Cálculo de factorial
- Números primos
- Series numéricas
- Menús repetitivos

---

## 🎯 Conceptos Clave

### 1️⃣ **Bucle For - Estructura Básica**
```java
// Sintaxis: for (inicialización; condición; actualización)
for (int i = 0; i < 10; i++) {
    System.out.println("Iteración: " + i);
}

// De 1 a 10
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}

// Decremental
for (int i = 10; i >= 1; i--) {
    System.out.println(i);
}

// Con saltos de 2
for (int i = 0; i <= 20; i += 2) {
    System.out.println(i);  // Números pares
}
```

### 2️⃣ **Bucle While - Condición Previa**
```java
int contador = 0;

while (contador < 5) {
    System.out.println("Contador: " + contador);
    contador++;
}

// Lectura de datos hasta centinela
Scanner scanner = new Scanner(System.in);
int numero = 0;

while (numero != -1) {
    System.out.print("Ingresa un número (-1 para salir): ");
    numero = scanner.nextInt();
    
    if (numero != -1) {
        System.out.println("Número ingresado: " + numero);
    }
}

scanner.close();
```

### 3️⃣ **Bucle Do-While - Condición Posterior**
```java
// Se ejecuta AL MENOS UNA VEZ
int numero;
Scanner scanner = new Scanner(System.in);

do {
    System.out.print("Ingresa un número positivo: ");
    numero = scanner.nextInt();
    
    if (numero <= 0) {
        System.out.println("Error: El número debe ser positivo");
    }
} while (numero <= 0);

System.out.println("Número válido: " + numero);
scanner.close();
```

### 4️⃣ **Break - Salir del Bucle**
```java
// Buscar un número en un rango
int numeroBuscado = 7;
boolean encontrado = false;

for (int i = 1; i <= 10; i++) {
    if (i == numeroBuscado) {
        System.out.println("Número encontrado en posición: " + i);
        encontrado = true;
        break;  // Sale inmediatamente del bucle
    }
}

if (!encontrado) {
    System.out.println("Número no encontrado");
}
```

### 5️⃣ **Continue - Saltar Iteración**
```java
// Imprimir solo números impares
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue;  // Salta a la siguiente iteración
    }
    System.out.println(i);  // Solo impares
}

// Saltar múltiplos de 3
for (int i = 1; i <= 20; i++) {
    if (i % 3 == 0) {
        continue;
    }
    System.out.print(i + " ");  // Imprime: 1 2 4 5 7 8 10 11...
}
```

### 6️⃣ **Bucles Anidados**
```java
// Tabla de multiplicar del 1 al 10
for (int i = 1; i <= 10; i++) {
    for (int j = 1; j <= 10; j++) {
        System.out.printf("%4d", i * j);
    }
    System.out.println();  // Nueva línea
}

// Patrón de asteriscos
for (int fila = 1; fila <= 5; fila++) {
    for (int col = 1; col <= fila; col++) {
        System.out.print("* ");
    }
    System.out.println();
}
// Resultado:
// * 
// * * 
// * * * 
// * * * * 
// * * * * *
```

### 7️⃣ **Enhanced For (For-Each)**
```java
// Para recorrer arrays y colecciones
int[] numeros = {1, 2, 3, 4, 5};

for (int numero : numeros) {
    System.out.println(numero);
}

String[] nombres = {"Ana", "Juan", "Pedro"};

for (String nombre : nombres) {
    System.out.println("Hola, " + nombre);
}
```

### 8️⃣ **Acumuladores y Contadores**
```java
// Suma de números del 1 al 100
int suma = 0;
for (int i = 1; i <= 100; i++) {
    suma += i;
}
System.out.println("Suma total: " + suma);  // 5050

// Contar números pares
int contador = 0;
for (int i = 1; i <= 50; i++) {
    if (i % 2 == 0) {
        contador++;
    }
}
System.out.println("Cantidad de pares: " + contador);  // 25
```

### 9️⃣ **Bucles Infinitos** (¡Evitar!)
```java
// ❌ Bucle infinito - nunca termina
while (true) {
    System.out.println("Esto se ejecuta infinitamente");
    // Sin break, nunca sale
}

// ❌ Condición siempre true
int i = 0;
while (i < 10) {
    System.out.println(i);
    // Falta i++, i siempre es 0
}

// ✅ Bucle infinito controlado (útil para menús)
while (true) {
    int opcion = mostrarMenu();
    if (opcion == 0) {
        break;  // Salida controlada
    }
    procesarOpcion(opcion);
}
```

### 🔟 **Comparación For vs While**
```java
// For - cuando sabes cuántas iteraciones
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// While - cuando depende de una condición
Scanner scanner = new Scanner(System.in);
String respuesta = "";
while (!respuesta.equals("salir")) {
    System.out.print("Comando: ");
    respuesta = scanner.nextLine();
}

// Do-While - al menos una ejecución garantizada
int intentos = 0;
do {
    System.out.println("Intento " + (++intentos));
} while (intentos < 3);
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Usar for** para iteraciones con contador  
✅ **Usar while** para bucles con condición previa  
✅ **Usar do-while** para al menos una ejecución  
✅ **Implementar bucles anidados** para estructuras complejas  
✅ **Aplicar break** para salir de bucles  
✅ **Aplicar continue** para saltar iteraciones  
✅ **Usar for-each** para recorrer arrays  
✅ **Implementar acumuladores** y contadores  
✅ **Evitar bucles infinitos** no deseados  
✅ **Elegir el bucle** apropiado según el caso  

---

## 🚀 Estructura de Proyectos

```
MOD 04/
├── DOCS/                  # Documentación teórica
│   └── Estructuras-Bucle.pdf
├── Ejemplos/              # Ejemplos de código
│   ├── BucleFor.java
│   ├── BucleWhile.java
│   ├── BucleDoWhile.java
│   ├── BreakContinue.java
│   └── BuclesAnidados.java
├── Ejercicios/            # Ejercicios prácticos
│   ├── TablaMultiplicar.java
│   ├── Factorial.java
│   ├── NumerosPrimos.java
│   ├── SerieFibonacci.java
│   └── MenuInteractivo.java
└── README.md             # Este archivo
```

---

## 🛠️ Herramientas y Tecnologías

- **Java JDK 8+**: Kit de desarrollo de Java
- **Bucles**: for, while, do-while, for-each
- **Control de flujo**: break, continue
- **Scanner**: Para entrada interactiva
- **Arrays**: Para for-each

---

## 📊 Comparación de Bucles

| Bucle | Cuándo Usar | Ejemplo de Uso |
|-------|-------------|----------------|
| **for** | Número conocido de iteraciones | Tablas de multiplicar, rangos numéricos |
| **while** | Condición previa, iteraciones desconocidas | Validación de entrada, lectura de archivo |
| **do-while** | Al menos una ejecución garantizada | Menús, validación con reintentos |
| **for-each** | Recorrer colecciones completas | Arrays, listas |

---

## 🎓 Ejemplos Prácticos

### Ejemplo 1: Tabla de Multiplicar
```java
import java.util.Scanner;

public class TablaMultiplicar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("¿De qué número quieres la tabla? ");
        int numero = scanner.nextInt();
        
        System.out.println("\nTabla del " + numero + ":");
        System.out.println("====================");
        
        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.printf("%d x %2d = %3d%n", numero, i, resultado);
        }
        
        scanner.close();
    }
}
```

### Ejemplo 2: Calcular Factorial
```java
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa un número: ");
        int n = scanner.nextInt();
        
        long factorial = 1;
        
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        
        System.out.printf("El factorial de %d es: %d%n", n, factorial);
        
        // Alternativa con while
        int j = 1;
        long fact2 = 1;
        while (j <= n) {
            fact2 *= j;
            j++;
        }
        
        scanner.close();
    }
}
```

### Ejemplo 3: Números Primos
```java
import java.util.Scanner;

public class NumerosPrimos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("¿Hasta qué número buscar primos? ");
        int limite = scanner.nextInt();
        
        System.out.println("\nNúmeros primos hasta " + limite + ":");
        
        for (int numero = 2; numero <= limite; numero++) {
            boolean esPrimo = true;
            
            // Verificar si es primo
            for (int divisor = 2; divisor <= Math.sqrt(numero); divisor++) {
                if (numero % divisor == 0) {
                    esPrimo = false;
                    break;  // No es primo, salir
                }
            }
            
            if (esPrimo) {
                System.out.print(numero + " ");
            }
        }
        
        scanner.close();
    }
}
```

### Ejemplo 4: Serie de Fibonacci
```java
import java.util.Scanner;

public class SerieFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("¿Cuántos términos de Fibonacci? ");
        int n = scanner.nextInt();
        
        long primero = 0, segundo = 1;
        
        System.out.println("\nSerie de Fibonacci:");
        for (int i = 1; i <= n; i++) {
            System.out.print(primero + " ");
            
            long siguiente = primero + segundo;
            primero = segundo;
            segundo = siguiente;
        }
        
        scanner.close();
    }
}
```

### Ejemplo 5: Menú Interactivo
```java
import java.util.Scanner;

public class MenuInteractivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Saludar");
            System.out.println("2. Calcular suma");
            System.out.println("3. Mostrar fecha");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n¡Hola! Bienvenido");
                    break;
                case 2:
                    System.out.print("Número 1: ");
                    int a = scanner.nextInt();
                    System.out.print("Número 2: ");
                    int b = scanner.nextInt();
                    System.out.printf("Suma: %d%n", a + b);
                    break;
                case 3:
                    System.out.println("\nFecha: " + 
                        java.time.LocalDate.now());
                    break;
                case 0:
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida");
            }
            
        } while (opcion != 0);
        
        scanner.close();
    }
}
```

### Ejemplo 6: Patrón de Asteriscos
```java
public class PatronAsteriscos {
    public static void main(String[] args) {
        int altura = 5;
        
        // Triángulo
        System.out.println("Triángulo:");
        for (int i = 1; i <= altura; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // Pirámide
        System.out.println("\nPirámide:");
        for (int i = 1; i <= altura; i++) {
            // Espacios
            for (int j = 1; j <= altura - i; j++) {
                System.out.print(" ");
            }
            // Asteriscos
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        // Resultado:
        // * 
        // * * 
        // * * * 
        // * * * * 
        // * * * * * 
        //
        //     *
        //    ***
        //   *****
        //  *******
        // *********
    }
}
```

---

## 🐛 Errores Comunes

### Error 1: Bucle infinito accidental
```java
// ❌ MAL
int i = 0;
while (i < 10) {
    System.out.println(i);
    // Olvidó i++, bucle infinito
}

// ✅ BIEN
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;  // Incremento esencial
}
```

### Error 2: Off-by-one error
```java
// ❌ MAL - Imprime 9 números en lugar de 10
for (int i = 1; i < 10; i++) {
    System.out.println(i);  // 1 al 9
}

// ✅ BIEN - Imprime 10 números
for (int i = 1; i <= 10; i++) {
    System.out.println(i);  // 1 al 10
}
```

### Error 3: Break en switch dentro de bucle
```java
// ❌ MAL - break solo sale del switch, no del for
for (int i = 0; i < 10; i++) {
    switch (i) {
        case 5:
            break;  // Solo sale del switch
    }
}

// ✅ BIEN - usar etiqueta para salir del for
bucleExterno:
for (int i = 0; i < 10; i++) {
    switch (i) {
        case 5:
            break bucleExterno;  // Sale del for
    }
}
```

---

## 💡 Buenas Prácticas

### ✅ HACER
```java
// Usar nombres descriptivos
for (int i = 0; i < estudiantes.length; i++) {
    System.out.println(estudiantes[i]);
}

// For-each cuando no necesitas índice
for (String estudiante : estudiantes) {
    System.out.println(estudiante);
}

// Límites claros y constantes
final int MAX_INTENTOS = 3;
for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
    // ...
}
```

### ❌ NO HACER
```java
// Evitar modificar contador dentro del bucle
for (int i = 0; i < 10; i++) {
    i += 2;  // Confuso y propenso a errores
}

// No usar for cuando while es más claro
for (; condicion; ) {  // Confuso
    // ...
}
// Mejor:
while (condicion) {
    // ...
}
```

---

## 📚 Recursos Adicionales

- [Java Loops Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html)
- [Control Flow Statements](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Break and Continue](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos  

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**
