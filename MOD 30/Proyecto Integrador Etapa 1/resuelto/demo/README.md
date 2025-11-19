# 📚 API REST - Gestión de Personas y Productos

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue)

## 📋 Descripción

Este proyecto es una **API REST** desarrollada con **Spring Boot** que permite gestionar información de **Personas** y **Productos**. Implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) utilizando una arquitectura en capas y almacenamiento en memoria.

Es un proyecto educativo ideal para aprender los fundamentos de Spring Boot, REST APIs y arquitectura de software.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Spring Boot 3.2.5** - Framework para aplicaciones Java
- **Spring Web** - Para crear endpoints REST
- **Maven** - Gestor de dependencias y construcción
- **Tomcat** - Servidor web embebido

---

## 📁 Estructura del Proyecto

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── controller/          # Controladores REST
│   │   │       │   ├── PersonaController.java
│   │   │       │   ├── ProductoController.java
│   │   │       │   └── RandomUserController.java  # Nuevo! Consume API externa
│   │   │       ├── service/             # Lógica de negocio
│   │   │       │   ├── PersonaService.java
│   │   │       │   ├── ProductoService.java
│   │   │       │   └── RandomUserService.java     # Nuevo! Servicio API externa
│   │   │       ├── repository/          # Acceso a datos
│   │   │       │   ├── PersonaRepository.java
│   │   │       │   └── ProductoRepository.java
│   │   │       ├── model/               # Entidades del dominio
│   │   │       │   ├── Persona.java
│   │   │       │   ├── Producto.java
│   │   │       │   ├── Direccion.java
│   │   │       │   └── RandomUserPhone.java       # Nuevo! DTO para API
│   │   │       └── DemoApplication.java # Clase principal
│   │   └── resources/
│   │       └── application.properties   # Configuración
│   └── test/                            # Pruebas unitarias
├── pom.xml                              # Configuración Maven
└── README.md                            # Este archivo
```

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura en capas (Layered Architecture)**:

```
┌─────────────────────────────────────────┐
│         CLIENTE (Postman, Browser)      │
└────────────────┬────────────────────────┘
                 │ HTTP Request
                 ▼
┌─────────────────────────────────────────┐
│     CONTROLLER (Capa de Presentación)   │
│  - PersonaController                    │
│  - ProductoController                   │
│  (Maneja peticiones HTTP, validaciones) │
└────────────────┬────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────┐
│      SERVICE (Capa de Negocio)          │
│  - PersonaService (Singleton)           │
│  - ProductoService (Singleton)          │
│  (Lógica de negocio, reglas)            │
└────────────────┬────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────┐
│    REPOSITORY (Capa de Datos)           │
│  - PersonaRepository                    │
│  - ProductoRepository                   │
│  (Almacenamiento en memoria - ArrayList)│
└────────────────┬────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────┐
│         MODEL (Entidades)               │
│  - Persona, Producto, Direccion         │
└─────────────────────────────────────────┘
```

---

## 🚀 Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior
- IDE (Eclipse, IntelliJ IDEA, VS Code, etc.)

### Pasos de Instalación

1. **Clonar o descargar el proyecto**
   ```bash
   cd demo
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```
   
   O ejecutar desde tu IDE la clase `DemoApplication.java`

4. **Verificar que esté funcionando**
   
   La aplicación se iniciará en: `http://localhost:8080`

---

## 📡 Endpoints de la API

### 🧑 **Personas**

| Método | Endpoint | Descripción | Body |
|--------|----------|-------------|------|
| `GET` | `/personas` | Obtiene todas las personas | - |
| `GET` | `/personas/{id}` | Obtiene una persona por ID | - |
| `POST` | `/personas` | Crea una nueva persona | JSON |
| `PUT` | `/personas/{id}` | Actualiza una persona existente | JSON |
| `DELETE` | `/personas/{id}` | Elimina una persona | - |

### 🛒 **Productos**

| Método | Endpoint | Descripción | Body |
|--------|----------|-------------|------|
| `GET` | `/productos` | Obtiene todos los productos | - |
| `GET` | `/productos/{id}` | Obtiene un producto por ID | - |
| `POST` | `/productos` | Crea un nuevo producto | JSON |
| `PUT` | `/productos/{id}` | Actualiza un producto existente | JSON |
| `DELETE` | `/productos/{id}` | Elimina un producto | - |

### 🎲 **Usuario Aleatorio (RandomUser API)**

| Método | Endpoint | Descripción | Body |
|--------|----------|-------------|------|
| `GET` | `/random-user/phone` | Obtiene teléfonos de un usuario aleatorio | - |

---

## 📝 Ejemplos de Uso

### **Crear una Persona** (POST)

**Request:**
```http
POST http://localhost:8080/personas
Content-Type: application/json

{
  "nombre": "Juan Pérez",
  "edad": 30,
  "telefono": "+54 11 1234-5678",
  "email": "juan.perez@example.com",
  "direccion": {
    "calle": "Av. Corrientes 1234",
    "ciudad": "Buenos Aires",
    "codigoPostal": 1043
  }
}
```

> ⚠️ **Nota:** No es necesario enviar el campo `id`, se genera automáticamente.

**Response:** `201 CREATED`
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "edad": 30,
  "telefono": "+54 11 1234-5678",
  "email": "juan.perez@example.com",
  "direccion": {
    "calle": "Av. Corrientes 1234",
    "ciudad": "Buenos Aires",
    "codigoPostal": 1043
  }
}
```

---

### **Obtener todas las Personas** (GET)

**Request:**
```http
GET http://localhost:8080/personas
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "edad": 30,
    "telefono": "+54 11 1234-5678",
    "email": "juan.perez@example.com",
    "direccion": {
      "calle": "Av. Corrientes 1234",
      "ciudad": "Buenos Aires",
      "codigoPostal": 1043
    }
  }
]
```

---

### **Crear un Producto** (POST)

**Request:**
```http
POST http://localhost:8080/productos
Content-Type: application/json

{
  "nombre": "Laptop HP",
  "precio": 899.99,
  "descripcion": "Laptop HP 15.6 pulgadas, 8GB RAM, 256GB SSD",
  "urlFoto": "https://example.com/images/laptop-hp.jpg"
}
```

> ⚠️ **Nota:** No es necesario enviar el campo `id`, se genera automáticamente.

**Response:** `201 CREATED`
```json
{
  "id": 1,
  "nombre": "Laptop HP",
  "precio": 899.99,
  "descripcion": "Laptop HP 15.6 pulgadas, 8GB RAM, 256GB SSD",
  "urlFoto": "https://example.com/images/laptop-hp.jpg"
}
```

---

### **Actualizar un Producto** (PUT)

**Request:**
```http
PUT http://localhost:8080/productos/1
Content-Type: application/json

{
  "id": 1,
  "nombre": "Laptop HP ProBook",
  "precio": 799.99,
  "descripcion": "Laptop HP ProBook 15.6 pulgadas, 16GB RAM, 512GB SSD",
  "urlFoto": "https://example.com/images/laptop-hp-pro.jpg"
}
```

**Response:** `200 OK`

---

### **Eliminar una Persona** (DELETE)

**Request:**
```http
DELETE http://localhost:8080/personas/1
```

**Response:** `204 NO CONTENT`

---

### **Obtener Usuario Aleatorio (RandomUser)** (GET)

Este endpoint consume la API externa de [RandomUser.me](https://randomuser.me/api/) y retorna únicamente los números de teléfono.

**Request:**
```http
GET http://localhost:8080/random-user/phone
```

**Response:** `200 OK`
```json
{
  "phone": "0374-4225067",
  "cell": "0176-1246970"
}
```

---

## 🔧 Códigos de Estado HTTP

| Código | Significado | Cuándo se usa |
|--------|-------------|---------------|
| `200 OK` | Solicitud exitosa | GET, PUT exitosos |
| `201 CREATED` | Recurso creado | POST exitoso |
| `204 NO CONTENT` | Sin contenido | DELETE exitoso |
| `404 NOT FOUND` | Recurso no encontrado | GET, PUT, DELETE con ID inexistente |

---

## 📦 Modelos de Datos

### **Persona**
```java
{
  "id": Long,           // Identificador único
  "nombre": String,     // Nombre completo
  "edad": int,          // Edad en años
  "telefono": String,   // Número de teléfono
  "email": String,      // Correo electrónico
  "direccion": {        // Objeto Direccion
    "calle": String,
    "ciudad": String,
    "codigoPostal": int
  }
}
```

### **Producto**
```java
{
  "id": Long,           // Identificador único
  "nombre": String,     // Nombre del producto
  "precio": Double,     // Precio (permite decimales)
  "descripcion": String,// Descripción detallada
  "urlFoto": String     // URL de la imagen
}
```

### **Direccion**
```java
{
  "calle": String,      // Nombre de la calle
  "ciudad": String,     // Nombre de la ciudad
  "codigoPostal": int   // Código postal
}
```

### **RandomUserPhone**
```java
{
  "phone": String,      // Teléfono fijo
  "cell": String        // Teléfono celular
}
```

---

## ⚠️ Consideraciones Importantes

### **Almacenamiento en Memoria**
- ⚠️ Los datos se almacenan en **memoria (ArrayList)**, no en una base de datos
- ⚠️ **Los datos se pierden al reiniciar la aplicación**
- ⚠️ No es persistente - solo para desarrollo y pruebas

### **Limitaciones Actuales**
- ❌ No hay validaciones de entrada (email, teléfono, etc.)
- ❌ No hay manejo robusto de excepciones
- ❌ No hay autenticación ni autorización
- ❌ No hay paginación en los listados
- ✅ ~~Los IDs deben ser asignados manualmente por el cliente~~ **¡Ahora son autoincrementales!**

### **Mejoras Sugeridas**
- ✅ Agregar JPA con H2 o PostgreSQL para persistencia
- ✅ Implementar validaciones con `@Valid` y Bean Validation
- ✅ Agregar manejo global de excepciones con `@ControllerAdvice`
- ✅ Implementar Spring Security para autenticación
- ✅ Agregar paginación con `Pageable`
- ✅ ~~Generar IDs automáticamente con `@GeneratedValue`~~ **¡Ya implementado!**
- ✅ Agregar logs con SLF4J
- ✅ Documentar API con Swagger/OpenAPI

---

## 🧪 Probando la API

### **Con Postman**
1. Descargar e instalar [Postman](https://www.postman.com/downloads/)
2. Importar la colección de endpoints
3. Configurar la URL base: `http://localhost:8080`
4. Ejecutar las peticiones

### **Con cURL**

```bash
# Obtener todas las personas
curl http://localhost:8080/personas

# Crear una persona (el ID se genera automáticamente)
curl -X POST http://localhost:8080/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María García",
    "edad": 25,
    "telefono": "+54 11 9876-5432",
    "email": "maria@example.com",
    "direccion": {
      "calle": "Calle Florida 456",
      "ciudad": "Córdoba",
      "codigoPostal": 5000
    }
  }'

# Obtener una persona por ID
curl http://localhost:8080/personas/1

# Eliminar una persona
curl -X DELETE http://localhost:8080/personas/1

# Obtener teléfonos de usuario aleatorio
curl http://localhost:8080/random-user/phone
```

---

## 🎓 Conceptos Aprendidos

Este proyecto te permite aprender:

- ✅ **Spring Boot** - Framework y configuración automática
- ✅ **REST API** - Principios RESTful y métodos HTTP
- ✅ **Arquitectura en Capas** - Separación de responsabilidades
- ✅ **Patrón Singleton** - En la capa de servicios
- ✅ **Anotaciones Spring** - `@RestController`, `@GetMapping`, etc.
- ✅ **JSON** - Serialización y deserialización automática
- ✅ **ResponseEntity** - Manejo de códigos de estado HTTP
- ✅ **CRUD Operations** - Operaciones básicas de datos
- ✅ **RestTemplate** - Consumo de APIs externas
- ✅ **Jackson** - Parseo de JSON con ObjectMapper

---

## 👥 Autor

**Proyecto Integrador Etapa 1**  
Digitalers - Java Developer Telecom  
Módulo 30

---

## 📄 Licencia

Este proyecto es de uso educativo.

---

## 🤝 Contribuciones

Si deseas mejorar este proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Agregar nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

---

## 📞 Soporte

Para preguntas o problemas, contacta al instructor del curso o revisa la documentación de Spring Boot:
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)

---

**¡Feliz Coding! 🚀**

