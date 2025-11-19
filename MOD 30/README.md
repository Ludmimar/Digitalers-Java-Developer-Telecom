# 🚀 MOD 30 - Introducción a Java Backend: Spring Boot y REST APIs

## 📖 Descripción

Este módulo introduce los conceptos fundamentales de **Java Backend** utilizando **Spring Boot** y **REST APIs**. Aprenderás a crear aplicaciones web modernas con arquitectura en capas, implementar operaciones CRUD completas y consumir APIs externas.

---

## 📂 Contenido del Módulo

### 📚 Documentación (DOCS)
- **Glosario**: Terminología a utilizar
- **Introducción a Java Backend**: Conceptos fundamentales
- **JAVA y REST**: Principios RESTful y métodos HTTP
- **JSON y XML**: Formatos de intercambio de datos
- **Organización del curso**: Estructura del programa
- **Probar APIs**: Herramientas y técnicas para probar APIs REST

### 🎯 Desafío
- **Demo Spring Boot**: API REST básica con gestión de Personas y Productos
  - Arquitectura en capas (Controller/Service/Repository)
  - Operaciones CRUD completas
  - Almacenamiento en memoria
  - Endpoints REST

### 🔬 Laboratorio
- **Laboratorio adicional**: Ejercicios prácticos complementarios

### 🎓 Proyecto Integrador Etapa 1
- **Enunciado**: Descripción del proyecto integrador
- **Resuelto**: Solución completa con consumo de API externa (RandomUser)

---

## 🎯 Conceptos Clave

### 1️⃣ **Spring Boot**
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### 2️⃣ **REST Controller**
```java
@RestController
@RequestMapping("/personas")
public class PersonaController {
    
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona savedPersona = personaService.save(persona);
        return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
    }
}
```

### 3️⃣ **Arquitectura en Capas**
```
┌─────────────────────────────────┐
│   CONTROLLER (Capa de Presentación)  │
│   - Maneja peticiones HTTP           │
│   - Validaciones básicas             │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────┐
│   SERVICE (Capa de Negocio)          │
│   - Lógica de negocio                │
│   - Reglas de aplicación             │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────┐
│   REPOSITORY (Capa de Datos)         │
│   - Acceso a datos                  │
│   - Almacenamiento (ArrayList)       │
└─────────────────────────────────┘
```

### 4️⃣ **Modelos de Datos**
```java
public class Persona {
    private Long id;
    private String nombre;
    private int edad;
    private Direccion direccion;
    private String telefono;
    private String email;
    
    // Getters y Setters
}
```

### 5️⃣ **Métodos HTTP y REST**
| Método | Endpoint | Acción | Código HTTP |
|--------|----------|--------|-------------|
| `GET` | `/personas` | Obtener todas | 200 OK |
| `GET` | `/personas/{id}` | Obtener por ID | 200 OK |
| `POST` | `/personas` | Crear nueva | 201 CREATED |
| `PUT` | `/personas/{id}` | Actualizar | 200 OK |
| `DELETE` | `/personas/{id}` | Eliminar | 204 NO CONTENT |

---

## 🎯 Objetivo

Al finalizar este módulo podrás:  
✅ Crear aplicaciones **Spring Boot** desde cero  
✅ Implementar **REST APIs** con operaciones CRUD  
✅ Aplicar **arquitectura en capas** en aplicaciones web  
✅ Utilizar **anotaciones Spring** (`@RestController`, `@GetMapping`, etc.)  
✅ Manejar **códigos de estado HTTP** apropiados  
✅ Trabajar con **JSON** para intercambio de datos  
✅ Consumir **APIs externas** con RestTemplate  
✅ Probar APIs con herramientas como **Postman** o **cURL**

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación (compatible con JDK 11)
- **Spring Boot 3.2.5**: Framework para aplicaciones Java
- **Spring Web**: Para crear endpoints REST
- **Maven**: Herramienta de gestión de proyectos y dependencias
- **Tomcat**: Servidor web embebido
- **Jackson**: Serialización/deserialización JSON
- **RestTemplate**: Consumo de APIs externas

---

## 📁 Estructura del Módulo

```
MOD 30/
├── 📄 README.md                    # Este archivo
├── 📁 DOCS/                        # Documentación teórica
│   ├── 📄 Glosario - Terminología a utilizar.pdf
│   ├── 📄 Introducción a Java Backend.pdf
│   ├── 📄 JAVA y REST.pdf
│   ├── 📄 JSON y XML.pdf
│   ├── 📄 Organización del curso.pdf
│   └── 📄 Probar APIs.pdf
├── 📁 Desafio/                     # Retos prácticos
│   ├── 📄 README.md               # Documentación de desafíos
│   ├── 📄 Desafío.pdf             # Material teórico
│   ├── 📄 Desafío resuelto.pdf    # Solución del desafío
│   └── 📁 demo/                    # Proyecto Spring Boot demo
│       ├── 📄 README.md           # Documentación del demo
│       ├── 📄 pom.xml             # Configuración Maven
│       └── 📁 src/                # Código fuente
├── 📁 Laboratorio/                # Ejercicios prácticos
│   ├── 📄 Laboratorio adicional.pdf
│   └── 📄 Laboratorio adicional resuelto.pdf
└── 📁 Proyecto Integrador Etapa 1/ # Proyecto integrador
    ├── 📄 README.md               # Documentación del proyecto
    ├── 📄 Etapa 1.pdf             # Enunciado
    ├── 📄 Analisis de las capas del proyecto.md
    └── 📁 resuelto/               # Solución completa
        └── 📁 demo/               # Proyecto Spring Boot resuelto
            └── 📄 README.md       # Documentación detallada
```

---

## 🚀 Cómo Empezar

### Prerrequisitos
- Completar MOD 29 (Programación Funcional y Streams)
- Conocimientos sólidos de Java y POO
- Comprensión de Maven
- IDE instalado (IntelliJ IDEA, Eclipse, VS Code)

### Pasos Recomendados
1. **Lee la documentación teórica** en la carpeta `DOCS/`
2. **Comienza con el Desafío demo** para entender Spring Boot básico
3. **Practica con el Laboratorio** para consolidar conceptos
4. **Completa el Proyecto Integrador** para aplicar conocimientos
5. **Prueba los endpoints** con Postman o cURL

### Comandos Útiles
```bash
# Compilar el proyecto
mvn clean package

# Ejecutar la aplicación Spring Boot
mvn spring-boot:run

# Ejecutar desde JAR compilado
java -jar target/demo-*.jar

# Verificar que esté funcionando
curl http://localhost:8080/personas
```

---

## 🎓 Conceptos Clave

### 🔹 **Spring Boot**
- **Auto-configuración**: Configuración automática basada en dependencias
- **Starters**: Dependencias preconfiguradas (`spring-boot-starter-web`)
- **Embedded Server**: Servidor embebido (Tomcat) para ejecutar aplicaciones
- **Application Properties**: Configuración en `application.properties`

### 🔹 **REST APIs**
- **RESTful**: Arquitectura de software para APIs web
- **Métodos HTTP**: GET, POST, PUT, DELETE para operaciones CRUD
- **JSON**: Formato de intercambio de datos (JavaScript Object Notation)
- **Códigos de Estado HTTP**: 200 OK, 201 CREATED, 204 NO CONTENT, 404 NOT FOUND

### 🔹 **Arquitectura en Capas**
- **Controller**: Maneja peticiones HTTP y respuestas
- **Service**: Contiene lógica de negocio
- **Repository**: Acceso a datos y persistencia
- **Model**: Entidades del dominio

### 🔹 **Anotaciones Spring**
- `@RestController`: Define un controlador REST
- `@RequestMapping`: Mapea URLs base
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Mapea métodos HTTP
- `@PathVariable`: Extrae parámetros de la URL
- `@RequestBody`: Deserializa JSON a objetos Java
- `@ResponseEntity`: Maneja códigos de estado HTTP

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [REST API Tutorial](https://restfulapi.net/)
- [JSON Introduction](https://www.json.org/json-es.html)
- [Postman Documentation](https://learning.postman.com/)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---
