# Proyecto Integrador Etapa 2 (MOD 31)

## 📖 Resumen
API REST completa con arquitectura en capas (controller/service/repository) y seguridad básica implementada con Spring Security.

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

## 🔒 Seguridad

### Autenticación HTTP Basic
- Username: `user`
- Password: `password`

### Autenticación por API Key (opcional)
- Header: `X-API-KEY: <tu_api_key>`

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

## 📝 Ejemplo de Uso

### Crear una Persona con Autenticación
```bash
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
El proyecto sigue una **arquitectura en capas**:
- **Controller**: Maneja peticiones HTTP y respuestas
- **Service**: Contiene lógica de negocio
- **Repository**: Acceso a datos y persistencia
- **SecurityConfig**: Configuración de seguridad

## ⚠️ Notas Importantes
- ⚠️ Las contraseñas son solo para desarrollo
- ⚠️ En producción, usar contraseñas encriptadas
- Para más información, consulta `MOD 31/README.md`




