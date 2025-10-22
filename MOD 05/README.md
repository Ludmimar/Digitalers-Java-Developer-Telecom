# 📦 MOD 05 - Métodos y Arreglos: Modularización y Estructuras de Datos

## 📖 Descripción

Este módulo introduce los **métodos (funciones)** y los **arreglos (arrays)** en Java. Aprenderás a modularizar tu código creando métodos reutilizables y a trabajar con estructuras de datos para almacenar múltiples valores. Estos conceptos son fundamentales para escribir código organizado, mantenible y eficiente.

---

## 📂 Contenido del Módulo

### 📚 Material Teórico
- Definición y llamada de métodos
- Parámetros y argumentos
- Retorno de valores
- Sobrecarga de métodos (Overloading)
- Ámbito de variables (scope)
- Arrays unidimensionales
- Arrays multidimensionales
- Recorrido de arrays

### 🎯 Conceptos Fundamentales
- Modularización del código
- Reutilización de código
- Paso por valor vs paso por referencia
- Declaración e inicialización de arrays
- Índices y longitud de arrays
- Arrays y bucles
- Enhanced for con arrays

### 📄 Ejercicios Prácticos
- Calculadora con métodos
- Búsqueda en arrays
- Ordenamiento de arrays
- Matriz de números
- Estadísticas de arrays

---

## 🎯 Conceptos Clave

### 1️⃣ **Definición de Métodos**
```java
// Sintaxis básica
// modificador tipoRetorno nombreMetodo(parámetros) {
//     // cuerpo del método
//     return valor;
// }

public class EjemploMetodos {
    
    // Método sin parámetros ni retorno
    public static void saludar() {
        System.out.println("¡Hola!");
    }
    
    // Método con parámetros sin retorno
    public static void saludarPersona(String nombre) {
        System.out.println("¡Hola, " + nombre + "!");
    }
    
    // Método con parámetros y retorno
    public static int sumar(int a, int b) {
        return a + b;
    }
    
    // Método con múltiples parámetros y retorno
    public static double calcularPromedio(double[] numeros) {
        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        return suma / numeros.length;
    }
}
```

### 2️⃣ **Llamada de Métodos**
```java
public class Main {
    public static void main(String[] args) {
        // Llamada a método sin parámetros
        saludar();  // ¡Hola!
        
        // Llamada a método con parámetros
        saludarPersona("Ana");  // ¡Hola, Ana!
        
        // Llamada a método con retorno
        int resultado = sumar(5, 3);
        System.out.println("Suma: " + resultado);  // Suma: 8
        
        // Uso directo del valor retornado
        System.out.println("Suma: " + sumar(10, 20));  // Suma: 30
    }
    
    // Métodos definidos aquí...
}
```

### 3️⃣ **Sobrecarga de Métodos (Overloading)**
```java
public class Calculadora {
    
    // Sumar dos enteros
    public static int sumar(int a, int b) {
        return a + b;
    }
    
    // Sumar tres enteros (sobrecarga)
    public static int sumar(int a, int b, int c) {
        return a + b + c;
    }
    
    // Sumar dos doubles (sobrecarga)
    public static double sumar(double a, double b) {
        return a + b;
    }
    
    // Sumar array de enteros (sobrecarga)
    public static int sumar(int[] numeros) {
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        return suma;
    }
}

// Uso:
int r1 = sumar(5, 3);           // Llama a sumar(int, int)
int r2 = sumar(5, 3, 2);        // Llama a sumar(int, int, int)
double r3 = sumar(5.5, 3.2);    // Llama a sumar(double, double)
int[] nums = {1, 2, 3, 4, 5};
int r4 = sumar(nums);           // Llama a sumar(int[])
```

### 4️⃣ **Arrays Unidimensionales - Declaración e Inicialización**
```java
// Declaración
int[] numeros;
String[] nombres;
double[] precios;

// Declaración e inicialización con tamaño
int[] edades = new int[5];  // Array de 5 enteros (inicializados en 0)

// Declaración e inicialización con valores
int[] dias = {1, 2, 3, 4, 5, 6, 7};
String[] meses = {"Enero", "Febrero", "Marzo"};
double[] temperaturas = {23.5, 25.0, 22.8, 24.1};

// Acceso a elementos (índices desde 0)
int primerDia = dias[0];        // 1
String primerMes = meses[0];    // "Enero"

// Modificación de elementos
dias[0] = 31;                   // Cambia el primer elemento
meses[1] = "Feb";              // Cambia "Febrero" a "Feb"

// Longitud del array
int cantidad = dias.length;     // 7
```

### 5️⃣ **Recorrido de Arrays**
```java
int[] numeros = {10, 20, 30, 40, 50};

// Con for tradicional
for (int i = 0; i < numeros.length; i++) {
    System.out.println("Elemento " + i + ": " + numeros[i]);
}

// Con enhanced for (for-each)
for (int numero : numeros) {
    System.out.println(numero);
}

// Con while
int i = 0;
while (i < numeros.length) {
    System.out.println(numeros[i]);
    i++;
}
```

### 6️⃣ **Arrays Multidimensionales (Matrices)**
```java
// Declaración e inicialización
int[][] matriz = new int[3][4];  // 3 filas, 4 columnas

// Inicialización con valores
int[][] numeros = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// Acceso a elementos
int valor = numeros[0][0];  // 1 (primera fila, primera columna)
int otro = numeros[2][3];   // 12 (tercera fila, cuarta columna)

// Modificación
numeros[1][2] = 100;  // Cambia 7 por 100

// Recorrido con for anidado
for (int fila = 0; fila < numeros.length; fila++) {
    for (int col = 0; col < numeros[fila].length; col++) {
        System.out.printf("%4d", numeros[fila][col]);
    }
    System.out.println();
}

// Recorrido con enhanced for
for (int[] fila : numeros) {
    for (int valor : fila) {
        System.out.print(valor + " ");
    }
    System.out.println();
}
```

### 7️⃣ **Métodos con Arrays**
```java
public class ArrayUtils {
    
    // Imprimir array
    public static void imprimirArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // Encontrar el máximo
    public static int encontrarMaximo(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    // Calcular promedio
    public static double calcularPromedio(int[] array) {
        int suma = 0;
        for (int num : array) {
            suma += num;
        }
        return (double) suma / array.length;
    }
    
    // Buscar elemento
    public static int buscar(int[] array, int elemento) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemento) {
                return i;  // Retorna el índice
            }
        }
        return -1;  // No encontrado
    }
    
    // Invertir array
    public static void invertir(int[] array) {
        int inicio = 0;
        int fin = array.length - 1;
        
        while (inicio < fin) {
            // Intercambiar elementos
            int temp = array[inicio];
            array[inicio] = array[fin];
            array[fin] = temp;
            
            inicio++;
            fin--;
        }
    }
}
```

### 8️⃣ **Paso de Parámetros**
```java
public class PasoParametros {
    
    // Tipos primitivos: paso por valor (copia)
    public static void modificarPrimitivo(int numero) {
        numero = 100;  // Solo modifica la copia
    }
    
    // Arrays: paso por referencia
    public static void modificarArray(int[] array) {
        array[0] = 999;  // Modifica el array original
    }
    
    public static void main(String[] args) {
        // Primitivo
        int x = 10;
        modificarPrimitivo(x);
        System.out.println(x);  // 10 (no cambió)
        
        // Array
        int[] numeros = {1, 2, 3};
        modificarArray(numeros);
        System.out.println(numeros[0]);  // 999 (sí cambió)
    }
}
```

### 9️⃣ **Ordenamiento de Arrays**
```java
import java.util.Arrays;

public class OrdenamientoArrays {
    
    // Ordenamiento burbuja (manual)
    public static void ordenarBurbuja(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Intercambiar
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    
    // Usando Arrays.sort() (más eficiente)
    public static void ordenarConSort(int[] array) {
        Arrays.sort(array);
    }
    
    public static void main(String[] args) {
        int[] numeros = {5, 2, 8, 1, 9, 3};
        
        ordenarBurbuja(numeros);
        // o
        Arrays.sort(numeros);
        
        System.out.println(Arrays.toString(numeros));
        // [1, 2, 3, 5, 8, 9]
    }
}
```

### 🔟 **Clase Arrays - Métodos Útiles**
```java
import java.util.Arrays;

public class MetodosArrays {
    public static void main(String[] args) {
        int[] numeros = {3, 1, 4, 1, 5, 9, 2, 6};
        
        // Ordenar
        Arrays.sort(numeros);
        System.out.println(Arrays.toString(numeros));
        // [1, 1, 2, 3, 4, 5, 6, 9]
        
        // Búsqueda binaria (array debe estar ordenado)
        int indice = Arrays.binarySearch(numeros, 5);
        System.out.println("Índice de 5: " + indice);  // 5
        
        // Llenar array con un valor
        int[] array = new int[5];
        Arrays.fill(array, 10);
        System.out.println(Arrays.toString(array));
        // [10, 10, 10, 10, 10]
        
        // Comparar arrays
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        boolean iguales = Arrays.equals(array1, array2);
        System.out.println("¿Iguales? " + iguales);  // true
        
        // Copiar array
        int[] copia = Arrays.copyOf(numeros, numeros.length);
        int[] subArray = Arrays.copyOfRange(numeros, 2, 5);
        // Copia elementos desde índice 2 hasta 4 (5 exclusivo)
    }
}
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Crear y llamar métodos** con y sin parámetros  
✅ **Retornar valores** desde métodos  
✅ **Sobrecargar métodos** para diferentes tipos  
✅ **Declarar e inicializar arrays** unidimensionales  
✅ **Recorrer arrays** con diferentes tipos de bucles  
✅ **Trabajar con arrays multidimensionales** (matrices)  
✅ **Pasar arrays** como parámetros a métodos  
✅ **Implementar algoritmos** de búsqueda y ordenamiento  
✅ **Usar la clase Arrays** para operaciones comunes  
✅ **Modularizar código** creando métodos reutilizables  

---

## 🚀 Estructura de Proyectos

```
MOD 05/
├── DOCS/                  # Documentación teórica
│   ├── Metodos.pdf
│   └── Arrays.pdf
├── Ejemplos/              # Ejemplos de código
│   ├── MetodosBasicos.java
│   ├── SobrecargaMetodos.java
│   ├── ArraysUnidimensionales.java
│   ├── ArraysMultidimensionales.java
│   └── MetodosConArrays.java
├── Ejercicios/            # Ejercicios prácticos
│   ├── Calculadora.java
│   ├── BusquedaArray.java
│   ├── OrdenamientoArray.java
│   ├── MatrizNumeros.java
│   └── EstadisticasArray.java
└── README.md             # Este archivo
```

---

## 🛠️ Herramientas y Tecnologías

- **Java JDK 8+**: Kit de desarrollo de Java
- **Métodos**: Modularización del código
- **Arrays**: Estructuras de datos
- **java.util.Arrays**: Utilidades para arrays
- **Algoritmos**: Búsqueda y ordenamiento

---

## 📊 Tabla de Comparación

| Concepto | Descripción | Ejemplo |
|----------|-------------|---------|
| **Método sin retorno** | void, solo ejecuta código | `void saludar()` |
| **Método con retorno** | Devuelve un valor | `int sumar(int a, int b)` |
| **Sobrecarga** | Mismo nombre, diferentes parámetros | `sumar(int, int)` y `sumar(double, double)` |
| **Array 1D** | Lista lineal de elementos | `int[] numeros = {1, 2, 3}` |
| **Array 2D** | Matriz de filas y columnas | `int[][] matriz = new int[3][4]` |

---

## 🎓 Ejemplos Prácticos

### Ejemplo 1: Calculadora Modular
```java
import java.util.Scanner;

public class CalculadoraModular {
    
    public static double sumar(double a, double b) {
        return a + b;
    }
    
    public static double restar(double a, double b) {
        return a - b;
    }
    
    public static double multiplicar(double a, double b) {
        return a * b;
    }
    
    public static double dividir(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            System.out.println("Error: División por cero");
            return 0;
        }
    }
    
    public static void mostrarMenu() {
        System.out.println("\n=== CALCULADORA ===");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            
            if (opcion >= 1 && opcion <= 4) {
                System.out.print("Primer número: ");
                double a = scanner.nextDouble();
                System.out.print("Segundo número: ");
                double b = scanner.nextDouble();
                
                double resultado = 0;
                
                switch (opcion) {
                    case 1:
                        resultado = sumar(a, b);
                        System.out.printf("%.2f + %.2f = %.2f%n", a, b, resultado);
                        break;
                    case 2:
                        resultado = restar(a, b);
                        System.out.printf("%.2f - %.2f = %.2f%n", a, b, resultado);
                        break;
                    case 3:
                        resultado = multiplicar(a, b);
                        System.out.printf("%.2f × %.2f = %.2f%n", a, b, resultado);
                        break;
                    case 4:
                        resultado = dividir(a, b);
                        if (b != 0) {
                            System.out.printf("%.2f ÷ %.2f = %.2f%n", a, b, resultado);
                        }
                        break;
                }
            } else if (opcion != 0) {
                System.out.println("Opción no válida");
            }
        } while (opcion != 0);
        
        System.out.println("¡Hasta luego!");
        scanner.close();
    }
}
```

### Ejemplo 2: Estadísticas de un Array
```java
import java.util.Arrays;

public class EstadisticasArray {
    
    public static int encontrarMaximo(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    public static int encontrarMinimo(int[] array) {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
    
    public static double calcularPromedio(int[] array) {
        int suma = 0;
        for (int num : array) {
            suma += num;
        }
        return (double) suma / array.length;
    }
    
    public static void mostrarEstadisticas(int[] array) {
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Máximo: " + encontrarMaximo(array));
        System.out.println("Mínimo: " + encontrarMinimo(array));
        System.out.printf("Promedio: %.2f%n", calcularPromedio(array));
        System.out.println("Cantidad de elementos: " + array.length);
    }
    
    public static void main(String[] args) {
        int[] calificaciones = {85, 92, 78, 90, 88, 76, 95, 89};
        
        mostrarEstadisticas(calificaciones);
        
        // Ordenar y mostrar
        Arrays.sort(calificaciones);
        System.out.println("\nOrdenado: " + Arrays.toString(calificaciones));
    }
}
```

### Ejemplo 3: Matriz de Multiplicación
```java
public class MatrizMultiplicacion {
    
    public static int[][] generarTabla(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (i + 1) * (j + 1);
            }
        }
        
        return matriz;
    }
    
    public static void imprimirMatriz(int[][] matriz) {
        System.out.println("\nTabla de Multiplicar:");
        System.out.println("=====================");
        
        // Encabezado
        System.out.print("    ");
        for (int j = 1; j <= matriz[0].length; j++) {
            System.out.printf("%4d", j);
        }
        System.out.println("\n" + "=".repeat(5 + matriz[0].length * 4));
        
        // Filas
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("%2d |", i + 1);
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%4d", matriz[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] tabla = generarTabla(10, 10);
        imprimirMatriz(tabla);
    }
}
```

---

## 🐛 Errores Comunes

### Error 1: IndexOutOfBoundsException
```java
// ❌ MAL
int[] numeros = {1, 2, 3, 4, 5};
System.out.println(numeros[5]);  // Error: índice fuera de rango

// ✅ BIEN
if (indice >= 0 && indice < numeros.length) {
    System.out.println(numeros[indice]);
}
```

### Error 2: NullPointerException
```java
// ❌ MAL
int[] numeros = null;
System.out.println(numeros.length);  // Error: array es null

// ✅ BIEN
if (numeros != null) {
    System.out.println(numeros.length);
}
```

### Error 3: No retornar valor en todos los caminos
```java
// ❌ MAL
public static int obtenerValor(boolean condicion) {
    if (condicion) {
        return 10;
    }
    // Falta return para cuando condicion es false
}

// ✅ BIEN
public static int obtenerValor(boolean condicion) {
    if (condicion) {
        return 10;
    } else {
        return 0;
    }
}
// o simplemente:
public static int obtenerValor(boolean condicion) {
    return condicion ? 10 : 0;
}
```

---

## 💡 Buenas Prácticas

### ✅ HACER
```java
// Métodos pequeños y específicos
public static boolean esPar(int numero) {
    return numero % 2 == 0;
}

// Nombres descriptivos
public static double calcularPrecioConImpuesto(double precio, double impuesto) {
    return precio * (1 + impuesto);
}

// Validar parámetros
public static int dividir(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("División por cero");
    }
    return a / b;
}
```

### ❌ NO HACER
```java
// Métodos muy largos (hacer una sola cosa)
public static void procesarTodo() {
    // 200 líneas de código...
}

// Nombres no descriptivos
public static int m1(int x, int y) {
    return x + y;
}

// Modificar arrays sin documentar
public static void procesar(int[] array) {
    array[0] = 999;  // Efecto secundario no documentado
}
```

---

## 📚 Recursos Adicionales

- [Methods Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Arrays Class JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**
