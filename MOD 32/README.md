# 📚 MOD 32 - Documentación de APIs: Swagger y OpenAPI

## 📖 Descripción

Este módulo introduce los conceptos de **documentación de APIs** utilizando **Swagger** y **OpenAPI**. Aprenderás a documentar APIs REST, configurar Swagger UI y aplicar buenas prácticas de arquitectura para aplicaciones empresariales.

---

## 📂 Contenido del Módulo

### 📚 Documentación (DOCS)
- **Swagger**: Introducción a Swagger y su uso
- **Open API**: Estándar OpenAPI para documentación de APIs
- **Tipos de arquitectura**: Buenas prácticas de arquitectura
- **Cómo continuar**: Guía para seguir avanzando

### 🔬 Laboratorio
- **Laboratorio**: API REST con integración Swagger/OpenAPI completa
  - Configuración de `SwaggerConfig`
  - Documentación automática de endpoints
  - Interfaz Swagger UI interactiva
  - Seguridad integrada con Spring Security

### 🎯 Desafío
- **Desafío 10**: Ejercicios prácticos sobre documentación de APIs
- **Desafío 11**: Solución completa del desafío

### 🎓 Proyecto Integrador Etapa 3
- **Enunciado y resuelto**: Proyecto completo con documentación Swagger/OpenAPI

---

## 🎯 Conceptos Clave

### 1️⃣ **Swagger/OpenAPI**
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
            new Info()
                .title("API DOC")
                .version("1.0")
                .description("Documentación de la API REST")
        );
    }
}
```

### 2️⃣ **Swagger UI**
```
http://localhost:8080/swagger-ui/index.html
```
Interfaz gráfica interactiva para probar y documentar endpoints.

### 3️⃣ **Anotaciones Swagger**
```java
@RestController
@RequestMapping("/personas")
@Tag(name = "Personas", description = "API para gestión de personas")
public class PersonaController {
    
    @Operation(summary = "Obtener todas las personas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de personas"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }
}
```

### 4️⃣ **Configuración de Swagger**
```java
// Dependencia en pom.xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>

// Configuración en SwaggerConfig.java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info()...);
}
```

### 5️⃣ **Tipos de Arquitectura**
- **Monolítica**: Aplicación única con todas las funcionalidades
- **Microservicios**: Aplicación distribuida con servicios independientes
- **Arquitectura en Capas**: Separación de responsabilidades (Controller/Service/Repository)
- **Arquitectura Hexagonal**: Aislamiento de la lógica de negocio

---

## 🎯 Objetivo

Al finalizar este módulo podrás:  
✅ Documentar **APIs REST** con Swagger/OpenAPI  
✅ Configurar **Swagger UI** para documentación interactiva  
✅ Aplicar **anotaciones Swagger** en controladores  
✅ Generar **documentación automática** de endpoints  
✅ Integrar **Swagger con Spring Security**  
✅ Comprender **tipos de arquitectura** empresarial  
✅ Aplicar **buenas prácticas** de documentación de APIs  
✅ Implementar **interfaces interactivas** para probar APIs

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación (compatible con JDK 11)
- **Spring Boot 3.2.5**: Framework para aplicaciones Java
- **Spring Doc OpenAPI**: Integración de Swagger/OpenAPI con Spring Boot
- **Swagger UI**: Interfaz gráfica para documentación de APIs
- **Spring Security**: Framework de seguridad (integración con Swagger)
- **Maven**: Herramienta de gestión de proyectos y dependencias
- **Tomcat**: Servidor web embebido

---

## 📁 Estructura del Módulo

```
MOD 32/
├── 📄 README.md                    # Este archivo
├── 📁 DOCS/                        # Documentación teórica
│   ├── 📄 Swagger.pdf
│   ├── 📄 Open API.pdf
│   ├── 📄 Tipos de arquitectura.pdf
│   └── 📄 Cómo continuar.pdf
├── 📁 Desafio/                     # Retos prácticos
│   ├── 📄 README.md               # Documentación de desafíos
│   ├── 📄 10 - Desafío.pdf        # Material teórico
│   └── 📄 11 - Desafío resuelto.pdf # Solución del desafío
├── 📁 Laboratorio/                # Ejercicios prácticos
│   ├── 📄 README.md               # Documentación del laboratorio
│   ├── 📄 Laboratorio adicional.pdf
│   └── 📁 demo/                   # Proyecto Spring Boot con Swagger
│       ├── 📄 README.md           # Documentación del demo
│       ├── 📄 pom.xml             # Configuración Maven
│       └── 📁 src/                # Código fuente
└── 📁 Proyecto Integrador Etapa 3/ # Proyecto integrador
    ├── 📄 README.md               # Documentación del proyecto
    ├── 📄 Etapa 3.pdf             # Enunciado
    └── 📁 demo/                   # Proyecto Spring Boot resuelto
        └── 📁 src/                # Código fuente
```

---

## 🚀 Cómo Empezar

### Prerrequisitos
- Completar MOD 31 (Seguridad en APIs REST)
- Conocimientos sólidos de Spring Boot
- Comprensión de REST APIs y Spring Security
- IDE instalado (IntelliJ IDEA, Eclipse, VS Code)

### Pasos Recomendados
1. **Lee la documentación teórica** en la carpeta `DOCS/`
2. **Comienza con el Laboratorio** para entender Swagger básico
3. **Practica con el Desafío** para consolidar conceptos
4. **Completa el Proyecto Integrador** para aplicar conocimientos
5. **Accede a Swagger UI** en `http://localhost:8080/swagger-ui/index.html`

### Comandos Útiles
```bash
# Compilar el proyecto
mvn clean package

# Ejecutar la aplicación Spring Boot
mvn spring-boot:run

# Ejecutar desde JAR compilado
java -jar target/demo-*.jar

# Acceder a Swagger UI
# Abrir en el navegador: http://localhost:8080/swagger-ui/index.html
```

---

## 🎓 Conceptos Clave

### 🔹 **Swagger/OpenAPI**
- **OpenAPI**: Estándar abierto para documentación de APIs REST
- **Swagger**: Herramientas para trabajar con OpenAPI
- **Swagger UI**: Interfaz gráfica interactiva para documentación
- **Swagger Editor**: Editor para crear y editar especificaciones OpenAPI

### 🔹 **Documentación de APIs**
- **Documentación Automática**: Generada a partir del código
- **Especificación OpenAPI**: Archivo YAML/JSON que describe la API
- **Anotaciones**: Marcadores en el código para documentar endpoints
- **Interfaz Interactiva**: Prueba de endpoints directamente desde el navegador

### 🔹 **Arquitectura**
- **Monolítica**: Aplicación única con todas las funcionalidades
- **Microservicios**: Aplicación distribuida con servicios independientes
- **Arquitectura en Capas**: Separación de responsabilidades
- **Arquitectura Hexagonal**: Aislamiento de la lógica de negocio

### 🔹 **Integración con Spring Security**
- **Configuración de Seguridad**: Protección de endpoints documentados
- **Autenticación en Swagger UI**: Prueba de endpoints protegidos
- **API Key en Swagger**: Configuración de headers de autenticación

---

## 📚 Recursos Adicionales

- [Swagger Documentation](https://swagger.io/docs/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Spring Doc OpenAPI](https://springdoc.org/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)
- [REST API Documentation Best Practices](https://restfulapi.net/documentation/)

---

## ⚠️ Notas Importantes

### 📚 Documentación
- ✅ La documentación se genera automáticamente desde el código
- ✅ Swagger UI permite probar endpoints directamente desde el navegador
- ✅ La documentación está siempre sincronizada con el código

### 💡 Próximas Mejoras
En módulos futuros se implementará:
- Integración con bases de datos
- Documentación más detallada con ejemplos
- Versionado de APIs
- Generación de clientes a partir de la documentación

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---
