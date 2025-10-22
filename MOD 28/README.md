# 🌐 MOD 28 - Java EE Avanzado: JSP, Sesiones y RequestDispatcher

## 📖 Descripción

Este módulo profundiza en **Java Server Pages (JSP)** avanzadas y el manejo de **sesiones HTTP** con `HttpSession`. Aprenderás a mantener el estado del usuario, gestionar múltiples páginas dinámicas y aplicar técnicas avanzadas de navegación con RequestDispatcher.

---

## 📂 Contenido del Módulo

### 📚 Desafíos
- **Desafío 01**: Login con JSP puro (sin Servlets)
- **Desafío 02**: Login con Servlets, JSP, sesiones y control de usuarios activos

### 🔬 Laboratorio
- **Laboratorio**: Aplicación completa con login, sesiones, lista de empleados y Bootstrap

### 📄 Documentación (DOCS)
- JSP - RequestDispatcher
- Sesiones HTTP

### 🎯 Material Adicional
- Ejemplos de JSP básico y avanzado
- Ejemplos de sesiones HTTP
- Proyecto completo con Bootstrap

---

## 🎯 Conceptos Clave

### 1️⃣ **Java Server Pages (JSP) Avanzado**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
// Scriptlets: código Java embebido
String usuario = (String) session.getAttribute("usuario");
%>
<h1>Bienvenido <%= usuario %></h1> <!-- Expresión JSP -->
```

### 2️⃣ **HttpSession**
```java
// Crear o recuperar sesión
HttpSession sesion = request.getSession();

// Guardar datos en sesión
sesion.setAttribute("usuario", usuario);
sesion.setAttribute("administrativos", listaEmpleados);

// Recuperar datos de sesión
Usuario user = (Usuario) sesion.getAttribute("usuario");

// Invalidar sesión (logout)
sesion.invalidate();
```

### 3️⃣ **Ciclo de Vida de una Sesión**
```
1. Usuario hace login → session.setAttribute("usuario", user)
2. Usuario navega por páginas → session.getAttribute("usuario")
3. Usuario cierra sesión → session.invalidate()
4. Sesión expira automáticamente después de X minutos (timeout)
```

### 4️⃣ **RequestDispatcher Avanzado**
```java
// Forward (mantiene request y response)
RequestDispatcher rd = request.getRequestDispatcher("bienvenido.jsp");
rd.forward(request, response);

// Include (incluye contenido de otra página)
rd.include(request, response);

// Diferencia con sendRedirect
response.sendRedirect("bienvenido.jsp"); // Nueva petición, pierde atributos
```

### 5️⃣ **@WebServlet Annotation**
```java
@WebServlet(name = "validacion", urlPatterns = "/sesiones")
public class Validacion extends HttpServlet {
    // No necesita web.xml
}
```

### 6️⃣ **Control de Usuarios Activos**
```java
private static Map<String, Usuario> usuariosActivos = new HashMap<>();

// Al hacer login
if (!usuariosActivos.containsKey(correo)) {
    usuariosActivos.put(correo, usuario);
    sesion.setAttribute("usuario", usuario);
} else {
    // Usuario ya tiene sesión activa
    request.setAttribute("mensaje", "Sesión ya iniciada en otro equipo");
}

// Al hacer logout
usuariosActivos.remove(usuario.getCorreo());
sesion.invalidate();
```

### 7️⃣ **doGet() vs doPost()**
```java
// doPost: Para login (enviar credenciales)
protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String correo = request.getParameter("correo");
    // Procesar login
}

// doGet: Para logout (cerrar sesión)
protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    request.getSession().invalidate();
    // Redirigir a login
}
```

### 8️⃣ **Objetos Implícitos en JSP**
```jsp
<% 
// request - HttpServletRequest
String param = request.getParameter("nombre");

// response - HttpServletResponse
response.setContentType("text/html");

// session - HttpSession
session.setAttribute("usuario", "Juan");

// out - PrintWriter
out.println("Hola");

// application - ServletContext
String ruta = application.getRealPath("/");
%>
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Crear páginas JSP dinámicas** con scriptlets y expresiones  
✅ **Gestionar sesiones HTTP** con HttpSession  
✅ **Mantener estado del usuario** entre páginas  
✅ **Implementar login/logout** completo  
✅ **Controlar usuarios activos** con Map  
✅ **Usar @WebServlet** en lugar de web.xml  
✅ **Diferenciar forward vs sendRedirect**  
✅ **Integrar JSP + Servlets + DAO + JDBC**  
✅ **Aplicar Bootstrap** para UI moderna  
✅ **Manejar atributos de sesión y request**  

---

## 🚀 Estructura de Proyectos

```
src/
├── main/
│   ├── java/
│   │   └── com/educacionIT/javase/
│   │       ├── servlets/
│   │       │   └── Validacion.java      # Servlet con @WebServlet
│   │       ├── entidades/
│   │       │   ├── Usuario.java         # Usuario para login
│   │       │   └── Administrativo.java  # Empleado
│   │       └── implementaciones/
│   │           └── mariaDB/
│   │               └── AdministrativoImpl.java  # DAO
│   ├── resources/
│   │   └── database.properties          # Configuración BD
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.jsp                    # Login
│       └── bienvenido.jsp               # Dashboard con sesión
└── pom.xml
```

---

## 🛠️ Tecnologías Utilizadas

- **Java 8+**: Lenguaje de programación
- **JSP 2.3**: Java Server Pages
- **Servlet API 4.0**: Framework web
- **HttpSession**: Manejo de estado
- **Apache Tomcat 9.0**: Servidor de aplicaciones
- **Maven**: Gestión de dependencias
- **JDBC**: Conexión a base de datos
- **MySQL/MariaDB**: Base de datos
- **Bootstrap 5**: Framework CSS
- **Annotations**: @WebServlet

---

## 🔍 Diferencias entre Desafíos

| Característica | Desafío 01 | Desafío 02 | Laboratorio |
|----------------|------------|------------|-------------|
| **Tecnología** | JSP puro | Servlet + JSP | Servlet + JSP + DAO |
| **Sesiones** | ❌ | ✅ | ✅ |
| **Base de datos** | ❌ | ✅ | ✅ |
| **Control usuarios activos** | ❌ | ✅ | ❌ |
| **Lista dinámica** | ❌ | ❌ | ✅ (empleados) |
| **UI** | Básica | Básica | Bootstrap |
| **Complejidad** | ⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |

---

## 💡 Conceptos Importantes

### **¿Por qué usar sesiones?**
- ✅ HTTP es stateless (sin estado)
- ✅ Necesitamos mantener datos del usuario
- ✅ Evitar login en cada página
- ✅ Persistencia durante la navegación

### **¿Cuándo usar forward vs sendRedirect?**

**forward:**
- ✅ Mantiene request y response
- ✅ URL no cambia
- ✅ Más rápido (misma petición)
- ✅ Usa cuando: login exitoso, mostrar resultado

**sendRedirect:**
- ✅ Nueva petición HTTP
- ✅ URL cambia
- ✅ Pierde atributos del request
- ✅ Usa cuando: después de operación POST (patrón PRG)

### **Timeout de Sesión**
```xml
<!-- web.xml -->
<session-config>
    <session-timeout>30</session-timeout> <!-- minutos -->
</session-config>
```

### **Ventajas de @WebServlet**
```java
@WebServlet(name = "miServlet", urlPatterns = "/ruta")
public class MiServlet extends HttpServlet {
    // No necesita configuración en web.xml
}
```
- ✅ Menos configuración
- ✅ Más legible
- ✅ Fácil de mantener

---

## 📚 Recursos Adicionales

- [JSP Tutorial](https://docs.oracle.com/javaee/7/tutorial/jsf-page.htm)
- [HttpSession API](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html)
- [Bootstrap Documentation](https://getbootstrap.com/)

---

## 🎓 Flujo Completo de una Aplicación con Sesión

```
1. Usuario accede a index.jsp (login)
   ↓
2. Submit → POST a Servlet
   ↓
3. Servlet valida credenciales (BD)
   ↓
4. Si válido:
   - session.setAttribute("usuario", user)
   - forward a bienvenido.jsp
   ↓
5. Usuario navega:
   - bienvenido.jsp lee session.getAttribute("usuario")
   - Si session == null → redirige a login
   ↓
6. Usuario hace logout:
   - GET a Servlet
   - session.invalidate()
   - forward a index.jsp
```

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**




