# Demo Spring Boot (MOD 30)

## 📖 Resumen
API REST básica con entidades `Persona` y `Producto`, implementando arquitectura en capas (controller/service/repository) y operaciones CRUD completas.

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
- `GET /productos/nombre/{nombre}` - Obtener producto por nombre
- `POST /productos` - Crear nuevo producto
- `PUT /productos/{id}` - Actualizar producto existente
- `DELETE /productos/{id}` - Eliminar producto

## 📝 Ejemplo de Uso

### Crear una Persona
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

### Obtener todas las Personas
```bash
curl http://localhost:8080/personas
```

## ⚙️ Configuración
Archivo de configuración: `src/main/resources/application.properties`

## ⚠️ Notas Importantes
- Los datos se almacenan en **memoria (ArrayList)**
- **Los datos se pierden al reiniciar la aplicación**
- Para más información, consulta `MOD 30/Desafio/README.md`




