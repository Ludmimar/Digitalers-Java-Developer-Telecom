# Desafío - MOD 30: Spring Boot y REST APIs

Este directorio contiene el desafío práctico del Módulo 30, enfocado en la creación de una **API REST** con **Spring Boot** para gestionar Personas y Productos.

---

## 📋 Contenido

### Demo Spring Boot: API REST Básica
**Objetivo:** Implementar una API REST completa con Spring Boot para gestionar Personas y Productos, aplicando arquitectura en capas y operaciones CRUD.

#### Características:
- **Arquitectura en Capas:** Separación en Controller/Service/Repository
- **Operaciones CRUD Completas:** Create, Read, Update, Delete para Personas y Productos
- **Almacenamiento en Memoria:** Uso de ArrayList para persistencia temporal
- **Endpoints REST:** Implementación de todos los métodos HTTP
- **ResponseEntity:** Manejo de códigos de estado HTTP apropiados
- **JSON Automático:** Serialización/deserialización con Jackson

#### Estructura del Proyecto:
```
demo/
├── src/main/java/com/example/demo/
│   ├── controller/          # Controladores REST
│   │   ├── PersonaController.java
│   │   └── ProductoController.java
│   ├── service/             # Lógica de negocio
│   │   ├── PersonaService.java
│   │   └── ProductoService.java
│   ├── repository/          # Acceso a datos
│   │   ├── PersonaRepository.java
│   │   └── ProductoRepository.java
│   ├── model/               # Entidades del dominio
│   │   ├── Persona.java
│   │   ├── Producto.java
│   │   └── Direccion.java
│   └── DemoApplication.java # Clase principal
├── src/main/resources/
│   └── application.properties # Configuración
├── pom.xml                  # Configuración Maven
└── README.md                # Documentación del proyecto
```

#### Conceptos Aplicados:
- ✅ **Spring Boot:** Framework y auto-configuración
- ✅ **REST APIs:** Principios RESTful y métodos HTTP
- ✅ **Arquitectura en Capas:** Separación de responsabilidades
- ✅ **Anotaciones Spring:** `@RestController`, `@GetMapping`, `@PostMapping`, etc.
- ✅ **JSON:** Serialización/deserialización automática
- ✅ **ResponseEntity:** Manejo de códigos de estado HTTP
- ✅ **CRUD Operations:** Operaciones básicas de datos
- ✅ **Patrón Singleton:** En la capa de servicios

#### Endpoints Disponibles:

**Personas:**
- `GET /personas` - Obtener todas las personas
- `GET /personas/{id}` - Obtener persona por ID
- `POST /personas` - Crear nueva persona
- `PUT /personas/{id}` - Actualizar persona existente
- `DELETE /personas/{id}` - Eliminar persona

**Productos:**
- `GET /productos` - Obtener todos los productos
- `GET /productos/{id}` - Obtener producto por ID
- `GET /productos/nombre/{nombre}` - Obtener producto por nombre
- `POST /productos` - Crear nuevo producto
- `PUT /productos/{id}` - Actualizar producto existente
- `DELETE /productos/{id}` - Eliminar producto

---

## 🎯 Objetivos de Aprendizaje

Al completar este desafío, el estudiante habrá desarrollado competencias en:

1. **Spring Boot:** Configuración y uso del framework
2. **REST APIs:** Diseño e implementación de APIs RESTful
3. **Arquitectura en Capas:** Separación de responsabilidades
4. **Anotaciones Spring:** Uso de anotaciones para mapeo de endpoints
5. **Manejo de HTTP:** Códigos de estado y métodos HTTP
6. **JSON:** Trabajo con formatos de intercambio de datos
7. **CRUD Operations:** Implementación de operaciones básicas
8. **Patrones de Diseño:** Aplicación del patrón Singleton

---

## 🚀 Cómo Ejecutar

### Prerrequisitos:
- Java JDK 17 o superior (compatible con JDK 11)
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos:
1. Navegar al directorio del proyecto:
   ```bash
   cd demo
   ```

2. Compilar el proyecto:
   ```bash
   mvn clean package
   ```

3. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
   
   O ejecutar desde tu IDE la clase `DemoApplication.java`

4. Verificar que esté funcionando:
   ```bash
   curl http://localhost:8080/personas
   ```

### Ejecutar desde JAR compilado:
```bash
mvn clean package
java -jar target/demo-*.jar
```

---

## 📝 Ejemplos de Uso

### Crear una Persona (POST)
```bash
curl -X POST http://localhost:8080/personas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "edad": 30,
    "telefono": "+54 11 1234-5678",
    "email": "juan.perez@example.com",
    "direccion": {
      "calle": "Av. Corrientes 1234",
      "ciudad": "Buenos Aires",
      "codigoPostal": 1043
    }
  }'
```

### Obtener todas las Personas (GET)
```bash
curl http://localhost:8080/personas
```

### Crear un Producto (POST)
```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop HP",
    "precio": 899.99,
    "descripcion": "Laptop HP 15.6 pulgadas",
    "urlFoto": "https://example.com/images/laptop.jpg"
  }'
```

### Obtener Producto por Nombre (GET)
```bash
curl http://localhost:8080/productos/nombre/Laptop%20HP
```

---

## 🧪 Probando la API

### Con Postman
1. Descargar e instalar [Postman](https://www.postman.com/downloads/)
2. Crear una nueva colección
3. Configurar la URL base: `http://localhost:8080`
4. Agregar las peticiones según los endpoints disponibles

### Con cURL
Usa los ejemplos proporcionados arriba para probar cada endpoint.

---

## 📚 Conceptos Teóricos Relacionados

- **Spring Boot:** Framework para aplicaciones Java empresariales
- **REST API:** Arquitectura de software para APIs web
- **Arquitectura en Capas:** Separación de responsabilidades en capas
- **JSON:** Formato de intercambio de datos (JavaScript Object Notation)
- **HTTP Methods:** GET, POST, PUT, DELETE para operaciones CRUD
- **ResponseEntity:** Manejo de códigos de estado HTTP en Spring
- **Anotaciones Spring:** Simplificación de configuración con anotaciones

---

## 🔗 Enlaces Útiles

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [REST API Tutorial](https://restfulapi.net/)
- [JSON Introduction](https://www.json.org/json-es.html)
- [Maven - Guía de Inicio](https://maven.apache.org/guides/getting-started/)
- [Postman Documentation](https://learning.postman.com/)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

