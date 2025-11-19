# Desafío Seguridad (MOD 31)

## 📖 Resumen
API REST con autenticación básica por API Key, Spring Security y configuración de seguridad (`SecurityConfig`, `ApiKeyFilter`).

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
Enviar credenciales básicas:
- Username: `user`
- Password: `password`

### Autenticación por API Key
Enviar header: `X-API-KEY: <tu_api_key>`

### Endpoints Públicos
- `GET /public/**` - Endpoints públicos sin autenticación

### Endpoints Protegidos
Requieren autenticación (HTTP Basic o API Key):
- `GET /personas` - Obtener todas las personas
- `GET /personas/{id}` - Obtener persona por ID
- `POST /personas` - Crear nueva persona
- `PUT /personas/{id}` - Actualizar persona existente
- `DELETE /personas/{id}` - Eliminar persona

**Productos:**
- `GET /productos` - Obtener todos los productos
- `GET /productos/{id}` - Obtener producto por ID
- `POST /productos` - Crear nuevo producto
- `PUT /productos/{id}` - Actualizar producto existente
- `DELETE /productos/{id}` - Eliminar producto

## 📝 Ejemplos de Uso

### Crear una Persona con HTTP Basic
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

### Obtener Personas con API Key
```bash
curl -H "X-API-KEY: tu-api-key" http://localhost:8080/personas
```

### Obtener Personas con HTTP Basic
```bash
curl -u user:password http://localhost:8080/personas
```

## ⚙️ Configuración
Archivo de configuración: `src/main/resources/application.properties`

## ⚠️ Notas Importantes
- ⚠️ Las contraseñas en `SecurityConfig` son solo para desarrollo
- ⚠️ En producción, usar contraseñas encriptadas y almacenamiento seguro
- ⚠️ API Keys deben ser generadas de forma segura
- Para más información, consulta `MOD 31/Desafio/README.md`




