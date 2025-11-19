# Demo Spring Boot con Swagger (MOD 32)

## 📖 Resumen
API REST completa con integración Swagger/OpenAPI, seguridad con Spring Security y arquitectura en capas.

## 🛠️ Requisitos
- JDK 17 (o 11)
- Maven 3.8+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Ejecución
```bash
# Opción 1: Ejecutar con Maven
mvn spring-boot:run

# Opción 2: Compilar y ejecutar JAR
mvn clean package
java -jar target/demo-*.jar
```

La aplicación se iniciará en: `http://localhost:8080`

## 📚 Swagger / OpenAPI

### Acceder a Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

### Características
- ✅ Documentación automática de todos los endpoints
- ✅ Interfaz gráfica interactiva para probar endpoints
- ✅ Integración con Spring Security para endpoints protegidos
- ✅ Especificación OpenAPI disponible en `/v3/api-docs`

### Configuración
- **SwaggerConfig.java**: Configuración de Swagger/OpenAPI
- **pom.xml**: Dependencia `springdoc-openapi-starter-webmvc-ui`
- **SecurityConfig.java**: Configuración de seguridad integrada con Swagger

## 📡 Endpoints Disponibles

### Personas
- `GET /personas` - Obtener todas las personas
- `GET /personas/{id}` - Obtener persona por ID
- `POST /personas` - Crear nueva persona
- `PUT /personas/{id}` - Actualizar persona existente
- `DELETE /personas/{id}` - Eliminar persona

### Productos
- `GET /productos` - Obtener todos los productos
- `GET /productos/{id}` - Obtener producto por ID
- `POST /productos` - Crear nuevo producto
- `PUT /productos/{id}` - Actualizar producto existente
- `DELETE /productos/{id}` - Eliminar producto

## 🔒 Seguridad

### Autenticación HTTP Basic
- Username: `user`
- Password: `password`

### Autenticación por API Key (opcional)
- Header: `X-API-KEY: <tu_api_key>`

## 📝 Ejemplos de Uso

### Acceder a Swagger UI
1. Ejecutar la aplicación
2. Abrir en el navegador: `http://localhost:8080/swagger-ui/index.html`
3. Explorar la documentación de los endpoints
4. Probar los endpoints directamente desde la interfaz

### Probar Endpoint desde Swagger UI
1. Seleccionar un endpoint en Swagger UI
2. Hacer clic en "Try it out"
3. Ingresar parámetros si es necesario
4. Hacer clic en "Execute"
5. Ver la respuesta del servidor

### Probar Endpoint Protegido en Swagger UI
1. Hacer clic en el botón "Authorize" (🔒) en la parte superior
2. Ingresar credenciales:
   - Username: `user`
   - Password: `password`
3. Hacer clic en "Authorize"
4. Probar endpoints protegidos normalmente

### Probar Endpoint con cURL
```bash
# Crear una Persona con Autenticación HTTP Basic
curl -X POST http://localhost:8080/personas \
  -u user:password \
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

## 🏗️ Arquitectura

### Estructura del Proyecto
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
│   │   ├── Direccion.java
│   │   └── Usuario.java
│   ├── SecurityConfig.java  # Configuración de seguridad
│   ├── ApiKeyFilter.java    # Filtro personalizado para API Key
│   ├── SwaggerConfig.java   # Configuración de Swagger/OpenAPI
│   └── DemoApplication.java # Clase principal
├── src/main/resources/
│   └── application.properties # Configuración
├── pom.xml                  # Configuración Maven
└── README.md                # Este archivo
```

### Capas de la Aplicación
- **Controller**: Maneja peticiones HTTP y respuestas
- **Service**: Contiene lógica de negocio
- **Repository**: Acceso a datos y persistencia
- **SecurityConfig**: Configuración de seguridad
- **SwaggerConfig**: Configuración de documentación

## ⚙️ Configuración

### Dependencias en pom.xml
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>
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

## ⚠️ Notas Importantes
- ✅ La documentación se genera automáticamente desde el código
- ✅ Swagger UI permite probar endpoints protegidos con autenticación
- ✅ La documentación está siempre sincronizada con el código
- ⚠️ Las contraseñas son solo para desarrollo
- Para más información, consulta `MOD 32/Laboratorio/README.md`

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

