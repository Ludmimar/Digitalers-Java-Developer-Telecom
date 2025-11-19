# 🔒 MOD 31 - Seguridad en APIs REST: Autenticación y Autorización

## 📖 Descripción

Este módulo introduce los conceptos fundamentales de **seguridad en APIs REST** utilizando **Spring Security** y **autenticación básica**. Aprenderás a proteger endpoints, implementar autenticación por API Key y configurar Spring Security para aplicaciones web.

---

## 📂 Contenido del Módulo

### 📚 Documentación (DOCS)
- **Aplicar seguridad básica en Rest**: Conceptos de seguridad en APIs REST
- **Autenticación y autorización**: Diferencias y aplicaciones prácticas
- **Introducción a serializadores JSON y Gson**: Trabajo con formatos JSON

### 🎯 Desafío
- **Desafío 2 - Módulo 2 - Seguridad**: API REST con autenticación por API Key y Spring Security
  - Configuración de `SecurityConfig`
  - Filtro de API Key (`ApiKeyFilter`)
  - Autenticación básica HTTP
  - Protección de endpoints

### ✅ Resolución Desafío
- **Resolución Desafío Módulo 2**: Solución completa del desafío con todas las capas

### 🔬 Laboratorio
- **Laboratorio adicional**: Ejercicios prácticos complementarios sobre seguridad

### 🎓 Proyecto Integrador Etapa 2
- **Enunciado y resuelto**: Proyecto completo con implementación de seguridad

---

## 🎯 Conceptos Clave

### 1️⃣ **Spring Security**
```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
}
```

### 2️⃣ **Autenticación por API Key**
```java
@Component
public class ApiKeyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) {
        String apiKey = ((HttpServletRequest) request).getHeader("X-API-KEY");
        if ("valid-api-key".equals(apiKey)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
```

### 3️⃣ **Autenticación HTTP Basic**
```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
}
```

### 4️⃣ **Protección de Endpoints**
```java
@RestController
@RequestMapping("/personas")
public class PersonaController {
    
    @GetMapping
    public List<Persona> getAllPersonas() {
        // Requiere autenticación
        return personaService.findAll();
    }
}
```

### 5️⃣ **Configuración de Seguridad**
```java
// Configuración en SecurityConfig
- Endpoints públicos: `/public/**` → permitAll()
- Endpoints protegidos: `/**` → authenticated()
- Método de autenticación: HTTP Basic
```

---

## 🎯 Objetivo

Al finalizar este módulo podrás:  
✅ Configurar **Spring Security** en aplicaciones Spring Boot  
✅ Implementar **autenticación por API Key** con filtros personalizados  
✅ Aplicar **autenticación HTTP Basic** con usuarios en memoria  
✅ Proteger **endpoints REST** según roles y permisos  
✅ Configurar **filtros de seguridad** personalizados  
✅ Diferenciar entre **autenticación y autorización**  
✅ Trabajar con **Gson** para serialización JSON  
✅ Aplicar **buenas prácticas** de seguridad en APIs REST

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación (compatible con JDK 11)
- **Spring Boot 3.2.5**: Framework para aplicaciones Java
- **Spring Security**: Framework de seguridad para aplicaciones Java
- **Maven**: Herramienta de gestión de proyectos y dependencias
- **Gson**: Librería para serialización/deserialización JSON
- **Tomcat**: Servidor web embebido

---

## 📁 Estructura del Módulo

```
MOD 31/
├── 📄 README.md                    # Este archivo
├── 📁 DOCS/                        # Documentación teórica
│   ├── 📄 Aplicar seguridad básica en Rest.pdf
│   ├── 📄 Autenticación y autorización.pdf
│   └── 📄 Introducción a serializadores JSON y Gson.pdf
├── 📁 Desafio/                     # Retos prácticos
│   ├── 📄 README.md               # Documentación de desafíos
│   ├── 📄 Desafío.pdf             # Material teórico
│   ├── 📁 Desafio 2 - modulo 2 - Seguridad/  # Desafío con seguridad
│   │   ├── 📄 README.md           # Documentación del desafío
│   │   └── 📁 src/                # Código fuente
│   └── 📁 Resolucion Desafio Modulo 2/  # Solución del desafío
│       ├── 📄 README.md           # Documentación de la solución
│       └── 📁 src/                # Código fuente
├── 📁 Laboratorio/                # Ejercicios prácticos
│   ├── 📄 Laboratorio adicional.pdf
│   └── 📄 Laboratorio adicional resuelto.pdf
└── 📁 Proyecto Integrador Etapa 2/ # Proyecto integrador
    ├── 📄 README.md               # Documentación del proyecto
    ├── 📄 Etapa 2.pdf             # Enunciado
    └── 📁 demo/                   # Proyecto Spring Boot resuelto
        └── 📁 src/                # Código fuente
```

---

## 🚀 Cómo Empezar

### Prerrequisitos
- Completar MOD 30 (Spring Boot y REST APIs)
- Conocimientos sólidos de Spring Boot
- Comprensión de REST APIs y arquitectura en capas
- IDE instalado (IntelliJ IDEA, Eclipse, VS Code)

### Pasos Recomendados
1. **Lee la documentación teórica** en la carpeta `DOCS/`
2. **Comienza con el Desafío** para entender autenticación básica
3. **Revisa la Resolución** para ver la solución implementada
4. **Practica con el Laboratorio** para consolidar conceptos
5. **Completa el Proyecto Integrador** para aplicar conocimientos
6. **Prueba los endpoints** con Postman usando autenticación

### Comandos Útiles
```bash
# Compilar el proyecto
mvn clean package

# Ejecutar la aplicación Spring Boot
mvn spring-boot:run

# Ejecutar desde JAR compilado
java -jar target/demo-*.jar

# Probar endpoint con autenticación HTTP Basic
curl -u user:password http://localhost:8080/personas

# Probar endpoint con API Key
curl -H "X-API-KEY: tu-api-key" http://localhost:8080/personas
```

---

## 🎓 Conceptos Clave

### 🔹 **Autenticación vs Autorización**
- **Autenticación**: Verificar quién es el usuario (¿Quién eres?)
- **Autorización**: Verificar qué puede hacer el usuario (¿Qué puedes hacer?)

### 🔹 **Spring Security**
- **SecurityFilterChain**: Cadena de filtros de seguridad configurable
- **UserDetailsService**: Servicio para cargar información de usuarios
- **InMemoryUserDetailsManager**: Gestor de usuarios en memoria
- **HttpSecurity**: Configuración de seguridad HTTP

### 🔹 **Métodos de Autenticación**
- **HTTP Basic**: Autenticación básica con usuario/contraseña
- **API Key**: Autenticación mediante clave en header
- **JWT**: Tokens JSON (temas avanzados en módulos futuros)
- **OAuth2**: Autenticación federada (temas avanzados)

### 🔹 **Filtros de Seguridad**
- **Filter Chain**: Cadena de filtros que procesan peticiones
- **ApiKeyFilter**: Filtro personalizado para validar API Key
- **SecurityContext**: Contexto de seguridad con información del usuario autenticado

---

## 📚 Recursos Adicionales

- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/servlet/index.html)
- [OAuth2 and OpenID Connect](https://oauth.net/2/)
- [Gson Documentation](https://github.com/google/gson)
- [REST API Security Best Practices](https://restfulapi.net/security-essentials/)

---

## ⚠️ Notas Importantes

### 🔒 Seguridad en Desarrollo
- ⚠️ Las contraseñas en `SecurityConfig` son solo para desarrollo
- ⚠️ En producción, usar contraseñas encriptadas y almacenamiento seguro
- ⚠️ API Keys deben ser generadas de forma segura y almacenadas de forma segura

### 💡 Próximas Mejoras
En módulos futuros se implementará:
- Autenticación con JWT (JSON Web Tokens)
- OAuth2 y OpenID Connect
- Integración con bases de datos para usuarios
- Encriptación de contraseñas con BCrypt
- Autorización basada en roles (RBAC)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---
