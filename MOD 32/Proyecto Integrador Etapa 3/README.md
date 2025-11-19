# Proyecto Integrador Etapa 3 (MOD 32)

## 📖 Resumen
API REST completa con seguridad básica (Spring Security), documentación Swagger/OpenAPI y arquitectura en capas.

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

## 📝 Ejemplo de Uso

### Acceder a Swagger UI
1. Ejecutar la aplicación
2. Abrir en el navegador: `http://localhost:8080/swagger-ui/index.html`
3. Explorar la documentación de los endpoints
4. Probar los endpoints directamente desde la interfaz

### Probar Endpoint con Autenticación en Swagger UI
1. Seleccionar un endpoint protegido
2. Hacer clic en el botón "Authorize" (🔒)
3. Ingresar credenciales (usuario/contraseña)
4. Hacer clic en "Authorize"
5. Probar el endpoint normalmente

## 🏗️ Arquitectura
El proyecto sigue una **arquitectura en capas**:
- **Controller**: Maneja peticiones HTTP y respuestas
- **Service**: Contiene lógica de negocio
- **Repository**: Acceso a datos y persistencia
- **SecurityConfig**: Configuración de seguridad
- **SwaggerConfig**: Configuración de documentación

## 📋 Entregables
1. **Código fuente** del proyecto Spring Boot
2. **README.md** con documentación completa
3. **Swagger UI** con documentación interactiva
4. **Configuración de seguridad** implementada
5. **Explicación de la arquitectura** en capas

## ⚠️ Notas Importantes
- ✅ La documentación se genera automáticamente desde el código
- ✅ Swagger UI permite probar endpoints protegidos con autenticación
- ✅ La documentación está siempre sincronizada con el código
- Para más información, consulta `MOD 32/README.md`




