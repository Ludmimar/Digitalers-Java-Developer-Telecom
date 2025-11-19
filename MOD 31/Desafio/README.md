# Desafío - MOD 31: Seguridad en APIs REST

Este directorio contiene los desafíos prácticos del Módulo 31, enfocados en la implementación de **seguridad básica** en APIs REST utilizando **Spring Security** y **autenticación por API Key**.

---

## 📋 Contenido

### Desafío 2 - Módulo 2 - Seguridad: API REST con Autenticación
**Objetivo:** Implementar autenticación básica en una API REST usando Spring Security y filtros personalizados para API Key.

#### Características:
- **Spring Security:** Configuración de `SecurityConfig` con filtros de seguridad
- **Autenticación por API Key:** Filtro personalizado `ApiKeyFilter` para validar headers
- **Autenticación HTTP Basic:** Configuración de usuarios en memoria
- **Protección de Endpoints:** Configuración de rutas públicas y protegidas
- **Arquitectura en Capas:** Mantiene la separación Controller/Service/Repository
- **Operaciones CRUD:** API REST completa para Personas y Productos

#### Estructura del Proyecto:
```
Desafio 2 - modulo 2 - Seguridad/
├── src/main/java/com/example/demo/
│   ├── controller/          # Controladores REST protegidos
│   │   ├── PersonaController.java
│   │   └── ProductoController.java
│   ├── service/             # Lógica de negocio
│   │   ├── PersonaService.java
│   │   ├── ProductoService.java
│   │   └── UsuarioService.java
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
│   └── DemoApplication.java # Clase principal
├── src/main/resources/
│   └── application.properties # Configuración
├── pom.xml                  # Configuración Maven
└── README.md                # Documentación del proyecto
```

#### Conceptos Aplicados:
- ✅ **Spring Security:** Framework de seguridad para aplicaciones Java
- ✅ **SecurityFilterChain:** Configuración de filtros de seguridad
- ✅ **Filtros Personalizados:** Implementación de `Filter` para API Key
- ✅ **Autenticación HTTP Basic:** Autenticación con usuario/contraseña
- ✅ **UserDetailsService:** Gestión de usuarios con `InMemoryUserDetailsManager`
- ✅ **Protección de Endpoints:** Configuración de rutas públicas y protegidas
- ✅ **CSRF Protection:** Deshabilitación para APIs REST

#### Endpoints Disponibles:

**Endpoints Públicos:**
- `GET /public/**` - Endpoints públicos sin autenticación

**Endpoints Protegidos (requieren autenticación):**
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

---

### Resolución Desafío Módulo 2: Solución Completa
**Objetivo:** Revisar la solución completa del desafío con todas las capas implementadas.

#### Características:
- Solución completa del desafío
- Todas las capas implementadas (Controller/Service/Repository)
- Configuración de seguridad completa
- Ejemplos funcionales de autenticación

---

## 🎯 Objetivos de Aprendizaje

Al completar estos desafíos, el estudiante habrá desarrollado competencias en:

1. **Spring Security:** Configuración y uso del framework de seguridad
2. **Autenticación:** Implementación de métodos de autenticación básicos
3. **Filtros Personalizados:** Creación de filtros para validación de API Key
4. **Protección de Endpoints:** Configuración de rutas públicas y protegidas
5. **UserDetailsService:** Gestión de usuarios con usuarios en memoria
6. **HTTP Basic:** Autenticación básica con usuario/contraseña
7. **Seguridad en APIs REST:** Aplicación de buenas prácticas de seguridad

---

## 🚀 Cómo Ejecutar

### Prerrequisitos:
- Java JDK 17 o superior (compatible con JDK 11)
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos:
1. Navegar al directorio del desafío:
   ```bash
   cd "Desafio 2 - modulo 2 - Seguridad"
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
   # Probar endpoint público (sin autenticación)
   curl http://localhost:8080/public/test
   
   # Probar endpoint protegido con HTTP Basic
   curl -u user:password http://localhost:8080/personas
   
   # Probar endpoint protegido con API Key
   curl -H "X-API-KEY: tu-api-key" http://localhost:8080/personas
   ```

---

## 📝 Ejemplos de Uso

### Crear una Persona con Autenticación HTTP Basic
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

### Obtener Personas con Autenticación HTTP Basic
```bash
curl -u user:password http://localhost:8080/personas
```

---

## 🔒 Configuración de Seguridad

### Usuarios en Memoria
```java
// Configurado en SecurityConfig.java
Username: user
Password: password
Role: USER

Username: admin
Password: admin
Role: USER, ADMIN
```

### API Key
- Header requerido: `X-API-KEY`
- Valor: Configurado en `ApiKeyFilter`
- Validación: Se realiza en el filtro personalizado

---

## ⚠️ Notas Importantes

### 🔒 Seguridad en Desarrollo
- ⚠️ Las contraseñas en `SecurityConfig` son solo para desarrollo
- ⚠️ En producción, usar contraseñas encriptadas y almacenamiento seguro
- ⚠️ API Keys deben ser generadas de forma segura

### 💡 Próximas Mejoras
En módulos futuros se implementará:
- Autenticación con JWT (JSON Web Tokens)
- OAuth2 y OpenID Connect
- Integración con bases de datos para usuarios
- Encriptación de contraseñas con BCrypt
- Autorización basada en roles (RBAC)

---

## 📚 Conceptos Teóricos Relacionados

- **Spring Security:** Framework de seguridad para aplicaciones Java
- **Autenticación:** Proceso de verificar la identidad de un usuario
- **Autorización:** Proceso de verificar qué puede hacer un usuario
- **HTTP Basic:** Método de autenticación básico con usuario/contraseña
- **API Key:** Clave de autenticación para APIs
- **Filtros de Seguridad:** Procesamiento de peticiones antes de llegar al controlador
- **SecurityFilterChain:** Cadena de filtros de seguridad configurable

---

## 🔗 Enlaces Útiles

- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/servlet/index.html)
- [REST API Security Best Practices](https://restfulapi.net/security-essentials/)
- [HTTP Basic Authentication](https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication)
- [Maven - Guía de Inicio](https://maven.apache.org/guides/getting-started/)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

