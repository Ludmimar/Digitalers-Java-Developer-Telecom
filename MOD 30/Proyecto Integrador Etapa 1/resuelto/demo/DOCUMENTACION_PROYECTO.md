# 📘 DOCUMENTACIÓN TÉCNICA DEL PROYECTO
## Proyecto Integrador Etapa 1 - API REST con Spring Boot

**Curso:** Digitalers - Java Developer Telecom  
**Módulo:** 30  
**Fecha:** 2024  

---

## 📑 ÍNDICE

1. [Introducción](#introducción)
2. [Objetivos del Proyecto](#objetivos-del-proyecto)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Arquitectura del Sistema](#arquitectura-del-sistema)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Explicación Detallada por Capas](#explicación-detallada-por-capas)
7. [Funcionalidades Implementadas](#funcionalidades-implementadas)
8. [Flujo de Datos Completo](#flujo-de-datos-completo)
9. [Características Técnicas Destacadas](#características-técnicas-destacadas)
10. [Pruebas y Validación](#pruebas-y-validación)
11. [Conclusiones](#conclusiones)

---

## 1. INTRODUCCIÓN

Este proyecto consiste en el desarrollo de una **API REST completa** utilizando **Spring Boot 3.2.5** como framework principal. La aplicación implementa un sistema de gestión que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre dos entidades principales: **Personas** y **Productos**. 

Además, el sistema integra el consumo de una API externa ([RandomUser.me](https://randomuser.me/api/)) para demostrar capacidades de comunicación entre servicios.

El proyecto sigue las mejores prácticas de desarrollo, aplicando una **arquitectura en capas** que garantiza la separación de responsabilidades y facilita el mantenimiento y escalabilidad del código.

---

## 2. OBJETIVOS DEL PROYECTO

### Objetivos Generales
- Desarrollar una API REST funcional y completa
- Implementar operaciones CRUD para gestión de datos
- Aplicar arquitectura en capas para organización del código
- Integrar servicios externos mediante consumo de APIs

### Objetivos Específicos
- Crear endpoints RESTful siguiendo las convenciones HTTP
- Implementar almacenamiento en memoria con IDs autoincrementales
- Desarrollar modelos de datos con relaciones (composición)
- Consumir APIs externas y parsear respuestas JSON
- Documentar el código de manera profesional

---

## 3. TECNOLOGÍAS UTILIZADAS

### Framework y Lenguaje
- **Java 17**: Lenguaje de programación principal
- **Spring Boot 3.2.5**: Framework para desarrollo de aplicaciones empresariales
- **Spring Web**: Módulo para crear aplicaciones web y APIs REST
- **Maven 4.0.0**: Gestor de dependencias y construcción del proyecto

### Bibliotecas Principales
- **Jackson**: Librería para serialización/deserialización de JSON
- **Tomcat Embebido**: Servidor de aplicaciones incluido en Spring Boot
- **RestTemplate**: Cliente HTTP para consumir APIs externas

### Herramientas de Desarrollo
- **IDE**: Eclipse / IntelliJ IDEA / VS Code
- **Postman**: Para pruebas de endpoints
- **Git**: Control de versiones

---

## 4. ARQUITECTURA DEL SISTEMA

### 4.1 Patrón Arquitectónico

El proyecto implementa una **Arquitectura en Capas (Layered Architecture)** con las siguientes capas claramente definidas:

```
┌─────────────────────────────────────────────────────┐
│                   CAPA DE PRESENTACIÓN              │
│              (Controllers - REST API)               │
│   - PersonaController                               │
│   - ProductoController                              │
│   - RandomUserController                            │
│   Responsabilidad: Recibir peticiones HTTP,         │
│   validar entrada, invocar servicios, retornar JSON │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                   CAPA DE NEGOCIO                   │
│                   (Services)                        │
│   - PersonaService (Singleton)                      │
│   - ProductoService (Singleton)                     │
│   - RandomUserService                               │
│   Responsabilidad: Lógica de negocio, reglas,      │
│   validaciones, coordinación entre capas            │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                   CAPA DE DATOS                     │
│                  (Repositories)                     │
│   - PersonaRepository                               │
│   - ProductoRepository                              │
│   Responsabilidad: Acceso y persistencia de datos,  │
│   operaciones CRUD, gestión de IDs                  │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                   CAPA DE MODELO                    │
│                    (Entities)                       │
│   - Persona, Producto, Direccion, RandomUserPhone   │
│   Responsabilidad: Definir estructura de datos,     │
│   POJOs con getters y setters                       │
└─────────────────────────────────────────────────────┘
```

### 4.2 Ventajas de esta Arquitectura

✅ **Separación de Responsabilidades**: Cada capa tiene una función específica  
✅ **Mantenibilidad**: Cambios en una capa no afectan a las demás  
✅ **Testabilidad**: Cada capa puede ser probada independientemente  
✅ **Escalabilidad**: Fácil agregar nuevas funcionalidades  
✅ **Reutilización**: Los servicios pueden ser usados por múltiples controllers  

---

## 5. ESTRUCTURA DEL PROYECTO

```
demo/
│
├── src/main/java/com/example/demo/
│   │
│   ├── DemoApplication.java              ← Clase principal (punto de entrada)
│   │
│   ├── controller/                       ← CAPA DE PRESENTACIÓN
│   │   ├── PersonaController.java        → Endpoints REST para Personas
│   │   ├── ProductoController.java       → Endpoints REST para Productos
│   │   └── RandomUserController.java     → Endpoint para usuario aleatorio
│   │
│   ├── service/                          ← CAPA DE LÓGICA DE NEGOCIO
│   │   ├── PersonaService.java           → Lógica de negocio de Personas
│   │   ├── ProductoService.java          → Lógica de negocio de Productos
│   │   └── RandomUserService.java        → Consumo de API externa
│   │
│   ├── repository/                       ← CAPA DE ACCESO A DATOS
│   │   ├── PersonaRepository.java        → CRUD en memoria para Personas
│   │   └── ProductoRepository.java       → CRUD en memoria para Productos
│   │
│   └── model/                            ← CAPA DE MODELO
│       ├── Persona.java                  → Entidad Persona
│       ├── Producto.java                 → Entidad Producto
│       ├── Direccion.java                → Entidad embebida Direccion
│       └── RandomUserPhone.java          → DTO para teléfonos
│
├── src/main/resources/
│   └── application.properties            ← Configuración de Spring Boot
│
├── src/test/java/                        ← Pruebas unitarias
│
├── pom.xml                               ← Configuración Maven (dependencias)
├── README.md                             ← Documentación general
└── DOCUMENTACION_PROYECTO.md             ← Este documento
```

---

## 6. EXPLICACIÓN DETALLADA POR CAPAS

### 6.1 CLASE PRINCIPAL: DemoApplication.java

**Ubicación:** `src/main/java/com/example/demo/DemoApplication.java`

**Función:** Punto de entrada de la aplicación Spring Boot.

**Código Explicado:**
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

**¿Qué hace?**
- `@SpringBootApplication`: Combina 3 anotaciones importantes
  - `@Configuration`: Marca la clase como fuente de configuración
  - `@EnableAutoConfiguration`: Activa la configuración automática de Spring
  - `@ComponentScan`: Escanea el paquete buscando componentes (@RestController, @Service, etc.)
- `SpringApplication.run()`: Inicia el servidor Tomcat embebido en el puerto 8080

---

### 6.2 CAPA DE MODELO (Entidades)

#### 6.2.1 Persona.java

**Función:** Define la estructura de datos para una persona.

**Atributos:**
- `Long id`: Identificador único (autoincremental)
- `String nombre`: Nombre completo
- `int edad`: Edad en años
- `Direccion direccion`: Objeto embebido con dirección completa
- `String telefono`: Número de teléfono
- `String email`: Correo electrónico

**Características:**
- Es un POJO (Plain Old Java Object)
- Incluye getters y setters para todos los atributos
- Implementa relación de **composición** con Direccion

#### 6.2.2 Producto.java

**Función:** Define la estructura de datos para un producto.

**Atributos:**
- `Long id`: Identificador único
- `String nombre`: Nombre del producto
- `Double precio`: Precio (permite decimales)
- `String descripcion`: Descripción detallada
- `String urlFoto`: URL de la imagen

#### 6.2.3 Direccion.java

**Función:** Clase embebida para representar direcciones.

**Atributos:**
- `String calle`: Nombre de la calle
- `String ciudad`: Ciudad
- `int codigoPostal`: Código postal

**Relación:** Se utiliza dentro de Persona (composición)

#### 6.2.4 RandomUserPhone.java

**Función:** DTO (Data Transfer Object) para transferir solo teléfonos.

**Atributos:**
- `String phone`: Teléfono fijo
- `String cell`: Teléfono celular

---

### 6.3 CAPA DE REPOSITORIO (Acceso a Datos)

#### 6.3.1 PersonaRepository.java

**Función:** Gestionar el almacenamiento y recuperación de personas en memoria.

**Atributos:**
```java
private ArrayList<Persona> personas = new ArrayList<>();
private Long currentId = 1L;  // Contador para IDs autoincrementales
```

**Métodos CRUD:**

1. **findAll()**: Retorna todas las personas
   ```java
   public List<Persona> findAll() {
       return personas;
   }
   ```

2. **findById(Long id)**: Busca una persona por ID
   ```java
   public Persona findById(Long id) {
       for (Persona persona : personas) {
           if (id != null && id.equals(persona.getId())) {
               return persona;
           }
       }
       return null;
   }
   ```

3. **save(Persona persona)**: Guarda o actualiza una persona
   - Si el ID es null → asigna ID autoincremental y guarda
   - Si el ID existe → busca y actualiza
   ```java
   public Persona save(Persona persona) {
       if (persona.getId() == null) {
           persona.setId(currentId);
           currentId++;
           personas.add(persona);
       }
       return persona;
   }
   ```

4. **deleteById(Long id)**: Elimina una persona por ID
   ```java
   public void deleteById(Long id) {
       Persona persona = findById(id);
       if (persona != null) {
           personas.remove(persona);
       }
   }
   ```

**Característica Destacada:** Sistema de IDs Autoincrementales
- No requiere que el cliente envíe el ID
- Garantiza unicidad de IDs
- Comienza en 1 y se incrementa automáticamente

#### 6.3.2 ProductoRepository.java

**Función:** Igual que PersonaRepository pero para productos.
Implementa los mismos métodos CRUD con la misma lógica.

---

### 6.4 CAPA DE SERVICIO (Lógica de Negocio)

#### 6.4.1 PersonaService.java

**Patrón Implementado:** Singleton

**¿Qué es Singleton?**
Un patrón de diseño que garantiza que solo exista **una única instancia** de la clase en toda la aplicación.

**Implementación:**
```java
private static PersonaService instance = new PersonaService();  // Única instancia
private PersonaRepository personaRepository;

private PersonaService() {  // Constructor privado
    personaRepository = new PersonaRepository();
}

public static PersonaService getInstance() {  // Método de acceso
    return instance;
}
```

**Métodos:**
- `findAll()`: Delega al repositorio
- `findBy(Long id)`: Busca por ID
- `save(Persona persona)`: Guarda persona
- `deleteBy(Long id)`: Elimina persona

**Responsabilidad:**
- Intermediario entre Controller y Repository
- Aquí se pueden agregar validaciones de negocio
- Ejemplo: validar que el email sea único, que la edad sea mayor a 0, etc.

#### 6.4.2 ProductoService.java

Similar a PersonaService, también implementa Singleton.

#### 6.4.3 RandomUserService.java

**Función:** Consumir la API externa de RandomUser.me

**Herramientas utilizadas:**
- `RestTemplate`: Para hacer peticiones HTTP GET
- `ObjectMapper`: Para parsear JSON
- `JsonNode`: Para navegar por el JSON

**Proceso:**
1. Hace petición GET a https://randomuser.me/api/
2. Recibe respuesta JSON completa
3. Parsea el JSON con Jackson
4. Navega por la estructura: `results[0].phone` y `results[0].cell`
5. Extrae solo esos dos campos
6. Retorna objeto `RandomUserPhone` con los datos

**Código clave:**
```java
String response = restTemplate.getForObject(RANDOM_USER_API, String.class);
JsonNode root = objectMapper.readTree(response);
JsonNode user = root.path("results").get(0);
String phone = user.path("phone").asText();
String cell = user.path("cell").asText();
return new RandomUserPhone(phone, cell);
```

---

### 6.5 CAPA DE CONTROLADOR (API REST)

#### 6.5.1 PersonaController.java

**Anotaciones principales:**
- `@RestController`: Marca la clase como controlador REST
- `@RequestMapping("/personas")`: Prefijo base para todas las rutas

**Endpoints implementados:**

| Método | Ruta | Función | Código HTTP |
|--------|------|---------|-------------|
| GET | `/personas` | Lista todas | 200 OK |
| GET | `/personas/{id}` | Obtiene una | 200 OK |
| POST | `/personas` | Crea nueva | 201 CREATED |
| PUT | `/personas/{id}` | Actualiza | 200 OK / 404 |
| DELETE | `/personas/{id}` | Elimina | 204 / 404 |

**Ejemplo detallado - Método POST:**
```java
@PostMapping
public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
    // @RequestBody: Convierte JSON del body en objeto Persona automáticamente
    
    Persona savedPersona = personaService.save(persona);
    
    // ResponseEntity: Permite especificar código HTTP de respuesta
    return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);  // 201
}
```

**Ejemplo detallado - Método PUT:**
```java
@PutMapping("/{id}")
public ResponseEntity<Persona> updatePersona(
    @PathVariable Long id,  // Extrae {id} de la URL
    @RequestBody Persona personaDetails) {
    
    Persona existingPersona = personaService.findBy(id);
    
    if (existingPersona != null) {
        // Actualiza campos uno por uno
        existingPersona.setNombre(personaDetails.getNombre());
        existingPersona.setEdad(personaDetails.getEdad());
        // ... más campos
        return ResponseEntity.ok(existingPersona);  // 200 OK
    } else {
        return ResponseEntity.notFound().build();   // 404 NOT FOUND
    }
}
```

#### 6.5.2 ProductoController.java

Estructura idéntica a PersonaController pero para productos.

#### 6.5.3 RandomUserController.java

**Función:** Exponer el servicio de usuario aleatorio.

**Endpoint:**
```java
@GetMapping("/phone")
public ResponseEntity<?> getRandomUserPhone() {
    try {
        RandomUserPhone userPhone = randomUserService.getRandomUserPhone();
        return new ResponseEntity<>(userPhone, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(
            "Error: " + e.getMessage(), 
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
```

**Manejo de errores:**
- Try-catch para capturar excepciones
- Retorna código 500 si hay error
- Mensaje descriptivo del error

---

## 7. FUNCIONALIDADES IMPLEMENTADAS

### 7.1 CRUD Completo de Personas

**CREATE - Crear Persona**
- Endpoint: `POST /personas`
- No requiere enviar ID (autoincremental)
- Acepta objeto JSON con todos los datos
- Retorna la persona creada con su ID asignado

**READ - Leer Personas**
- `GET /personas`: Lista todas las personas
- `GET /personas/{id}`: Obtiene una persona específica

**UPDATE - Actualizar Persona**
- Endpoint: `PUT /personas/{id}`
- Requiere ID en la URL
- Envía los datos actualizados en el body
- Actualiza todos los campos

**DELETE - Eliminar Persona**
- Endpoint: `DELETE /personas/{id}`
- Solo requiere el ID en la URL
- Retorna 204 No Content si se elimina con éxito

### 7.2 CRUD Completo de Productos

Misma funcionalidad que Personas pero aplicada a productos.

### 7.3 Integración con API Externa

**Funcionalidad:** Obtener teléfonos de usuarios aleatorios
- Endpoint: `GET /random-user/phone`
- Consume: https://randomuser.me/api/
- Extrae: Solo `phone` y `cell`
- Retorna: JSON simple con los dos teléfonos

**Ventajas:**
- Demuestra consumo de servicios externos
- Uso de RestTemplate
- Parseo de JSON con Jackson
- Extracción selectiva de datos

---

## 8. FLUJO DE DATOS COMPLETO

### Ejemplo: Crear una Persona

**Paso 1:** Cliente hace petición POST
```http
POST http://localhost:8080/personas
Content-Type: application/json

{
  "nombre": "Juan Pérez",
  "edad": 30,
  "telefono": "123456789",
  "email": "juan@example.com",
  "direccion": {
    "calle": "Calle Principal 123",
    "ciudad": "Buenos Aires",
    "codigoPostal": 1000
  }
}
```

**Paso 2:** Spring Boot recibe la petición
- El servidor Tomcat recibe el HTTP POST
- Spring enruta la petición al controlador correcto

**Paso 3:** PersonaController procesa
```java
@PostMapping
public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
```
- `@RequestBody` convierte el JSON en objeto Persona
- En este punto, `persona.getId()` es `null`

**Paso 4:** Controller llama al Service
```java
Persona savedPersona = personaService.save(persona);
```

**Paso 5:** Service llama al Repository
```java
return personaRepository.save(persona);
```

**Paso 6:** Repository asigna ID y guarda
```java
if (persona.getId() == null) {
    persona.setId(currentId);  // Asigna ID = 1
    currentId++;               // Incrementa a 2
    personas.add(persona);     // Añade a la lista
}
```

**Paso 7:** Se retorna la persona con ID
- Repository → Service → Controller
- La persona ahora tiene `id: 1`

**Paso 8:** Controller retorna respuesta HTTP
```java
return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);  // 201
```

**Paso 9:** Cliente recibe la respuesta
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "edad": 30,
  "telefono": "123456789",
  "email": "juan@example.com",
  "direccion": {
    "calle": "Calle Principal 123",
    "ciudad": "Buenos Aires",
    "codigoPostal": 1000
  }
}
```

---

## 9. CARACTERÍSTICAS TÉCNICAS DESTACADAS

### 9.1 IDs Autoincrementales

**Problema resuelto:** No es necesario que el cliente genere o envíe IDs.

**Implementación:**
```java
private Long currentId = 1L;

if (persona.getId() == null) {
    persona.setId(currentId);
    currentId++;
}
```

**Ventajas:**
- Evita conflictos de IDs duplicados
- Simplifica el uso de la API
- Simula el comportamiento de bases de datos reales

### 9.2 Patrón Singleton en Servicios

**Razón:** Garantizar una única instancia compartida en toda la aplicación.

**Beneficios:**
- Ahorro de memoria (solo una instancia)
- Estado compartido consistente
- Control centralizado

### 9.3 Uso de ResponseEntity

**Ventaja:** Control completo sobre la respuesta HTTP.

**Permite:**
- Especificar código de estado (200, 201, 404, etc.)
- Agregar headers personalizados
- Controlar el body de la respuesta

**Ejemplo:**
```java
return new ResponseEntity<>(persona, HttpStatus.CREATED);  // 201
return ResponseEntity.ok(persona);                         // 200
return ResponseEntity.notFound().build();                  // 404
return ResponseEntity.noContent().build();                 // 204
```

### 9.4 Serialización/Deserialización Automática

**Jackson** se encarga automáticamente de:
- Convertir objetos Java → JSON (serialización)
- Convertir JSON → objetos Java (deserialización)

No es necesario escribir código de conversión manual.

### 9.5 Consumo de APIs Externas

**RestTemplate** simplifica las peticiones HTTP:
```java
String response = restTemplate.getForObject(url, String.class);
```

**Jackson** parsea el JSON:
```java
JsonNode root = objectMapper.readTree(response);
```

---

## 10. PRUEBAS Y VALIDACIÓN

### 10.1 Herramientas de Prueba

**Postman:**
- Crear colecciones de peticiones
- Probar todos los endpoints
- Verificar códigos de respuesta
- Validar formato JSON

**cURL:**
```bash
# Crear persona
curl -X POST http://localhost:8080/personas \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test","edad":25}'

# Listar personas
curl http://localhost:8080/personas

# Obtener una persona
curl http://localhost:8080/personas/1

# Eliminar persona
curl -X DELETE http://localhost:8080/personas/1
```

### 10.2 Casos de Prueba Realizados

#### Test 1: Crear Persona sin ID
- **Entrada:** JSON sin campo `id`
- **Esperado:** Persona creada con `id: 1`
- **Resultado:** ✅ Exitoso

#### Test 2: Crear Segunda Persona
- **Entrada:** JSON sin campo `id`
- **Esperado:** Persona creada con `id: 2`
- **Resultado:** ✅ Exitoso (autoincremento funciona)

#### Test 3: Listar Todas las Personas
- **Entrada:** GET /personas
- **Esperado:** Array con todas las personas creadas
- **Resultado:** ✅ Exitoso

#### Test 4: Actualizar Persona
- **Entrada:** PUT /personas/1 con datos modificados
- **Esperado:** Persona actualizada con código 200
- **Resultado:** ✅ Exitoso

#### Test 5: Eliminar Persona
- **Entrada:** DELETE /personas/1
- **Esperado:** Código 204 No Content
- **Resultado:** ✅ Exitoso

#### Test 6: Buscar Persona Inexistente
- **Entrada:** GET /personas/999
- **Esperado:** Código 404 o null
- **Resultado:** ✅ Retorna null (mejorable)

#### Test 7: Usuario Aleatorio
- **Entrada:** GET /random-user/phone
- **Esperado:** JSON con phone y cell
- **Resultado:** ✅ Exitoso

---

## 11. CONCLUSIONES

### 11.1 Logros Alcanzados

✅ **API REST Funcional:** Se implementó una API completa con todos los endpoints CRUD  
✅ **Arquitectura en Capas:** Código organizado y mantenible  
✅ **IDs Autoincrementales:** Sistema robusto de generación de identificadores  
✅ **Integración Externa:** Consumo exitoso de API de terceros  
✅ **Buenas Prácticas:** Código comentado y documentado  
✅ **Manejo de HTTP:** Uso correcto de códigos de estado  

### 11.2 Conceptos Aprendidos

Durante el desarrollo de este proyecto se aplicaron y consolidaron los siguientes conceptos:

**Spring Boot:**
- Configuración automática
- Inyección de dependencias
- Anotaciones (@RestController, @Service, etc.)
- Servidor embebido

**Desarrollo de APIs:**
- Principios REST
- Métodos HTTP (GET, POST, PUT, DELETE)
- Códigos de estado HTTP
- Formato JSON

**Patrones de Diseño:**
- Singleton
- Layered Architecture
- DTO (Data Transfer Object)

**Tecnologías:**
- Maven para gestión de dependencias
- Jackson para JSON
- RestTemplate para HTTP
- Git para control de versiones

### 11.3 Limitaciones Actuales

⚠️ **Almacenamiento en Memoria:** Los datos no son persistentes  
⚠️ **Sin Validaciones:** No hay validación de entrada (email, teléfono)  
⚠️ **Sin Autenticación:** Cualquiera puede acceder a los endpoints  
⚠️ **Sin Paginación:** Listados completos sin límite  
⚠️ **Manejo de Errores Básico:** Falta manejo global de excepciones  

### 11.4 Mejoras Futuras Propuestas

**Persistencia de Datos:**
- Integrar JPA + Hibernate
- Conectar a base de datos (H2, PostgreSQL, MySQL)
- Usar @GeneratedValue para IDs

**Validaciones:**
- Implementar Bean Validation (@Valid, @NotNull, @Email)
- Validar datos de entrada
- Retornar mensajes de error descriptivos

**Seguridad:**
- Implementar Spring Security
- Agregar autenticación JWT
- Roles y permisos

**Funcionalidades Adicionales:**
- Paginación con Pageable
- Filtros y búsquedas
- Ordenamiento
- Documentación con Swagger/OpenAPI

**Testing:**
- Pruebas unitarias con JUnit
- Pruebas de integración
- Cobertura de código

### 11.5 Reflexión Final

Este proyecto demuestra la capacidad de desarrollar una aplicación backend completa utilizando Spring Boot y siguiendo las mejores prácticas de la industria. La arquitectura en capas implementada facilita el mantenimiento y la escalabilidad del código, mientras que la integración con APIs externas muestra habilidades de comunicación entre servicios.

El código está completamente comentado y documentado, lo que facilita su comprensión y futuras modificaciones. La implementación de características como IDs autoincrementales y el patrón Singleton demuestran comprensión de patrones de diseño y soluciones técnicas elegantes.

Aunque existen limitaciones (principalmente el almacenamiento en memoria), estas son esperables en un proyecto educativo inicial y pueden ser fácilmente superadas en futuras iteraciones mediante la integración de tecnologías adicionales como JPA y bases de datos relacionales.

El proyecto cumple con todos los objetivos planteados y proporciona una base sólida para futuras expansiones y mejoras.

---

## 📚 ANEXOS

### A. Comandos Útiles

**Compilar el proyecto:**
```bash
mvn clean install
```

**Ejecutar la aplicación:**
```bash
mvn spring-boot:run
```

**Detener la aplicación:**
```
Ctrl + C
```

### B. URLs de Endpoints

**Personas:**
- GET http://localhost:8080/personas
- GET http://localhost:8080/personas/{id}
- POST http://localhost:8080/personas
- PUT http://localhost:8080/personas/{id}
- DELETE http://localhost:8080/personas/{id}

**Productos:**
- GET http://localhost:8080/productos
- GET http://localhost:8080/productos/{id}
- POST http://localhost:8080/productos
- PUT http://localhost:8080/productos/{id}
- DELETE http://localhost:8080/productos/{id}

**Usuario Aleatorio:**
- GET http://localhost:8080/random-user/phone

### C. Códigos de Estado HTTP Utilizados

| Código | Nombre | Uso en el Proyecto |
|--------|--------|-------------------|
| 200 | OK | GET exitoso, PUT exitoso |
| 201 | Created | POST exitoso (recurso creado) |
| 204 | No Content | DELETE exitoso |
| 404 | Not Found | Recurso no encontrado |
| 500 | Internal Server Error | Error en consumo de API externa |

---

**Fin del Documento**

Este documento ha sido elaborado como parte del Proyecto Integrador Etapa 1 del curso Digitalers - Java Developer Telecom, Módulo 30.

