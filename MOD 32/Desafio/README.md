# Desafío - MOD 32: Documentación de APIs con Swagger/OpenAPI

Este directorio contiene los desafíos prácticos del Módulo 32, enfocados en la **documentación de APIs REST** utilizando **Swagger** y **OpenAPI**.

---

## 📋 Contenido

### Desafío 10: Documentación de APIs
**Objetivo:** Implementar documentación de APIs REST usando Swagger/OpenAPI y configurar Swagger UI.

#### Características:
- **Swagger/OpenAPI:** Configuración de `SwaggerConfig` para documentación
- **Swagger UI:** Interfaz gráfica interactiva para probar endpoints
- **Documentación Automática:** Generación automática de documentación desde el código
- **Anotaciones Swagger:** Uso de anotaciones para documentar endpoints
- **Integración con Spring Security:** Documentación de endpoints protegidos
- **Arquitectura en Capas:** Mantiene la separación Controller/Service/Repository

#### Conceptos Aplicados:
- ✅ **Swagger/OpenAPI:** Estándar para documentación de APIs REST
- ✅ **Swagger UI:** Interfaz gráfica interactiva
- ✅ **Spring Doc OpenAPI:** Integración con Spring Boot
- ✅ **Anotaciones Swagger:** Documentación de endpoints
- ✅ **Integración con Seguridad:** Documentación de endpoints protegidos
- ✅ **Buenas Prácticas:** Aplicación de estándares de documentación

---

### Desafío 11: Solución Completa
**Objetivo:** Revisar la solución completa del desafío con documentación Swagger/OpenAPI implementada.

#### Características:
- Solución completa del desafío
- Configuración de Swagger/OpenAPI
- Documentación de todos los endpoints
- Integración con Spring Security
- Ejemplos funcionales de documentación

---

## 🎯 Objetivos de Aprendizaje

Al completar estos desafíos, el estudiante habrá desarrollado competencias en:

1. **Swagger/OpenAPI:** Configuración y uso del estándar
2. **Documentación de APIs:** Generación automática de documentación
3. **Swagger UI:** Uso de la interfaz gráfica interactiva
4. **Anotaciones Swagger:** Documentación de endpoints con anotaciones
5. **Integración con Spring Security:** Documentación de endpoints protegidos
6. **Buenas Prácticas:** Aplicación de estándares de documentación
7. **Tipos de Arquitectura:** Comprensión de arquitecturas empresariales

---

## 🚀 Cómo Ejecutar

### Prerrequisitos:
- Java JDK 17 o superior (compatible con JDK 11)
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos:
1. Compilar el proyecto:
   ```bash
   mvn clean package
   ```

2. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
   
   O ejecutar desde tu IDE la clase `DemoApplication.java`

3. Acceder a Swagger UI:
   - Abrir en el navegador: `http://localhost:8080/swagger-ui/index.html`
   - Explorar la documentación de los endpoints
   - Probar los endpoints directamente desde la interfaz

---

## 📝 Ejemplos de Uso

### Acceder a Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

### Configuración de Swagger
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

### Documentar un Endpoint
```java
@Operation(summary = "Obtener todas las personas")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de personas"),
    @ApiResponse(responseCode = "401", description = "No autorizado")
})
@GetMapping
public List<Persona> getAllPersonas() {
    return personaService.findAll();
}
```

---

## 📚 Conceptos Teóricos Relacionados

- **Swagger/OpenAPI:** Estándar abierto para documentación de APIs REST
- **Swagger UI:** Interfaz gráfica interactiva para documentación
- **OpenAPI Specification:** Especificación estándar para APIs REST
- **Spring Doc OpenAPI:** Integración de Swagger/OpenAPI con Spring Boot
- **Documentación Automática:** Generación automática de documentación desde el código
- **Anotaciones Swagger:** Marcadores en el código para documentar endpoints
- **Tipos de Arquitectura:** Monolítica, Microservicios, Capas, Hexagonal

---

## 🔗 Enlaces Útiles

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
- ✅ Los endpoints protegidos se pueden probar con autenticación en Swagger UI

### 💡 Próximas Mejoras
En módulos futuros se implementará:
- Documentación más detallada con ejemplos
- Versionado de APIs
- Generación de clientes a partir de la documentación
- Integración con bases de datos

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

