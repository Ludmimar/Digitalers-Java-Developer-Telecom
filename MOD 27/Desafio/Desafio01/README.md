# 🎯 Desafío 01 - Login Básico con Servlets

## 📖 Descripción

Sistema de autenticación simple que utiliza **Servlets** para validar usuarios contra una lista en memoria y generar respuestas HTML dinámicas usando `PrintWriter`.

---

## ✨ Funcionalidades

✅ **Formulario de login** (index.jsp)  
✅ **Validación de credenciales** en memoria (List<Usuario>)  
✅ **Generación dinámica de HTML** con PrintWriter  
✅ **Respuestas condicionales** (éxito/error)  
✅ **Arquitectura básica** cliente-servidor  

---

## 🛠️ Tecnologías

- **Java 8+**
- **Servlet API 4.0**
- **Apache Tomcat 9.0**
- **Maven**
- **JSP** (solo para el formulario)

---

## 📦 Estructura del Proyecto

```
Desafio01/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java          # Servlet controlador
│   │       └── entidades/
│   │           └── Usuario.java            # Modelo de usuario
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                     # Configuración del Servlet
│       └── index.jsp                       # Formulario de login
└── pom.xml                                 # Dependencias Maven
```

---

## 🚀 Ejecución

### 1. Importar en Eclipse/IntelliJ
```bash
File → Import → Maven → Existing Maven Projects
Seleccionar: MOD 27/Desafio/Desafio01
```

### 2. Configurar Tomcat
```
Run As → Run on Server → Tomcat 9.0
```

### 3. Acceder a la aplicación
```
http://localhost:8080/Desafio01/
```

### 4. Probar con usuarios válidos
```
user1@deducacionit.com / user1.1234
user2@deducacionit.com / user2.1235
user3@deducacionit.com / user3.1236
```

---

## 💻 Código Principal

### Usuario.java
```java
package com.educacionit.entidades;

public class Usuario {
    private String correo;
    private String clave;
    
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    // Getters y setters
}
```

### Principal.java (Servlet)
```java
public class Principal extends HttpServlet {
    private static final List<Usuario> usuarios = Arrays.asList(
        new Usuario("user1@deducacionit.com", "user1.1234"),
        new Usuario("user2@deducacionit.com", "user2.1235"),
        new Usuario("user3@deducacionit.com", "user3.1236")
    );
    
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        String plantilla = usuarioValido(correo, clave) 
            ? plantillaUsuarioValido 
            : plantillaUsuarioNoValido;
        
        PrintWriter out = response.getWriter();
        out.println(plantilla);
        out.close();
    }
    
    private boolean usuarioValido(String correo, String clave) {
        return usuarios.stream()
            .anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo) 
                        && u.getClave().equals(clave));
    }
}
```

### web.xml
```xml
<servlet>
    <servlet-name>Principal</servlet-name>
    <servlet-class>com.educacionit.servlets.Principal</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>Principal</servlet-name>
    <url-pattern>/Principal</url-pattern>
</servlet-mapping>
```

### index.jsp
```jsp
<form action="Principal" method="post">
    <input type="email" name="correo" required />
    <input type="password" name="clave" required />
    <button type="submit">Iniciar Sesión</button>
</form>
```

---

## 🎯 Flujo de Ejecución

```
1. Usuario accede a index.jsp
   ↓
2. Llena formulario (correo, clave)
   ↓
3. Submit → POST a /Principal
   ↓
4. Servlet recibe parámetros
   ↓
5. Valida contra List<Usuario>
   ↓
6. Genera HTML con PrintWriter
   ↓
7. Usuario ve resultado (éxito/error)
```

---

## 📊 Salida de Ejemplo

### ✅ Login Exitoso
```html
<h1 style="color: yellowgreen;">
    Bienvenido Credenciales Validas
</h1>
<a href="index.jsp">volver</a>
```

### ❌ Login Fallido
```html
<h1 style="color: red;">
    Credenciales No Validas, vuelva a intentarlo
</h1>
<a href="index.jsp">volver</a>
```

---

## 🔍 Análisis del Código

### ¿Por qué usar List<Usuario>?
- ✅ Simple para demostración
- ✅ No requiere base de datos
- ✅ Fácil de entender
- ❌ No escalable (datos en memoria)
- ❌ Se pierden al reiniciar

### ¿Por qué PrintWriter?
```java
PrintWriter out = response.getWriter();
out.println("<h1>Hola</h1>");
```
- ✅ Control total del HTML
- ✅ Fácil para respuestas simples
- ❌ Mezcla lógica y presentación
- ❌ Difícil de mantener para HTML complejo
- ❌ No reutilizable

**Mejor alternativa:** JSP con RequestDispatcher (ver Desafío 02)

---

## 🎓 Conceptos Aprendidos

### 1. Servlet Básico
```java
public class MiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) {
        // Procesar petición
    }
}
```

### 2. Obtener Parámetros
```java
String valor = request.getParameter("nombre");
```

### 3. Generar Respuesta
```java
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
out.println("<html>...</html>");
```

### 4. Validación Simple
```java
private boolean usuarioValido(String correo, String clave) {
    for (Usuario u : usuarios) {
        if (u.getCorreo().equals(correo) && 
            u.getClave().equals(clave)) {
            return true;
        }
    }
    return false;
}
```

---

## 🎯 Puntos Clave

### ✅ Ventajas del Enfoque
- Simple y directo
- No requiere configuración adicional
- Bueno para aprender fundamentos

### ❌ Limitaciones
- HTML mezclado con Java (no separation of concerns)
- No hay persistencia de datos
- Difícil de mantener
- No reutilizable

### 💡 Mejoras Sugeridas
1. Usar JSP en lugar de PrintWriter
2. Implementar DAO para acceso a datos
3. Usar RequestDispatcher para navegación
4. Agregar validación de entrada
5. Implementar manejo de sesiones

---

## 🔗 Siguiente Paso

Ver **Desafío 02** para una implementación mejorada con:
- JSP para vistas
- DAO para base de datos
- RequestDispatcher para navegación
- Separación de responsabilidades

---

## 🎓 Evaluación

**¿Qué deberías poder hacer después de este desafío?**

- [x] Crear un Servlet básico que extienda HttpServlet
- [x] Sobrescribir doPost() para procesar formularios
- [x] Obtener parámetros con request.getParameter()
- [x] Generar HTML con PrintWriter
- [x] Configurar web.xml
- [x] Validar datos contra una lista
- [x] Desplegar en Tomcat

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**



