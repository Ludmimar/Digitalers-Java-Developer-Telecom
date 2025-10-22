# 🎯 MOD 28 - Desafíos: JSP Avanzado y Sesiones HTTP

## 📖 Descripción General

Esta carpeta contiene **2 desafíos** que demuestran el uso avanzado de JSP y sesiones HTTP, desde validación básica en JSP hasta un sistema completo con control de usuarios activos.

---

## 📂 Contenido

### 🏆 **Desafío 01**: Login con JSP Puro
Login simple usando **solo JSP** (sin Servlets):
- Validación en scriptlets JSP
- Lista de usuarios en memoria
- Redirección entre páginas JSP
- Sin uso de sesiones

**Características:**
- ✅ JSP con scriptlets
- ✅ Declaraciones JSP (<%! %>)
- ✅ Validación directa en JSP
- ✅ Redirección con response.sendRedirect()
- ✅ Sin persistencia

---

### 🏆 **Desafío 02**: Login con Sesiones y Control de Usuarios Activos
Sistema avanzado de autenticación con:
- Sesiones HTTP (HttpSession)
- Control de usuarios activos (Map)
- DAO para validación en base de datos
- Prevención de sesiones múltiples
- Servlet maneja doGet (logout) y doPost (login)

**Características:**
- ✅ HttpSession
- ✅ session.setAttribute() / getAttribute()
- ✅ session.invalidate()
- ✅ Map<String, Usuario> para usuarios activos
- ✅ Validación contra BD
- ✅ DAO + JDBC
- ✅ RequestDispatcher

---

## 🔄 Comparación de Desafíos

| Aspecto | Desafío 01 | Desafío 02 |
|---------|------------|------------|
| **Validación** | JSP scriptlet | Servlet + DAO |
| **Sesiones** | ❌ | ✅ (HttpSession) |
| **Base de datos** | ❌ | ✅ |
| **Control usuarios activos** | ❌ | ✅ (Map) |
| **Logout** | ❌ | ✅ (doGet) |
| **Arquitectura** | JSP puro | MVC completo |
| **Escalabilidad** | Baja | Alta |
| **Complejidad** | ⭐⭐ | ⭐⭐⭐⭐⭐ |

---

## 🚀 Cómo Ejecutar

### Prerequisitos Comunes
```
✅ JDK 8 o superior
✅ Apache Tomcat 9.0
✅ Maven
✅ IDE (Eclipse/IntelliJ)
✅ MySQL (solo Desafío 02)
```

### **Desafío 01**

1. **Importar proyecto**
   ```bash
   cd MOD\ 28/Desafio/Desafio01
   mvn clean package
   ```

2. **Desplegar en Tomcat**
   - Run As → Run on Server

3. **Acceder**
   ```
   http://localhost:8080/Desafio01/
   ```

4. **Usuarios codificados en JSP**
   - Ver `principal.jsp` para lista de usuarios

---

### **Desafío 02**

1. **Crear base de datos**
   ```sql
   CREATE DATABASE IF NOT EXISTS sistema_login;
   USE sistema_login;
   
   CREATE TABLE usuarios (
       correo VARCHAR(100) PRIMARY KEY,
       clave VARCHAR(50) NOT NULL
   );
   
   INSERT INTO usuarios VALUES 
   ('admin@educacionit.com', 'admin123'),
   ('user@educacionit.com', 'user123');
   ```

2. **Configurar database.properties**
   ```properties
   db.url=jdbc:mysql://localhost:3306/sistema_login
   db.user=root
   db.password=tu_password
   ```

3. **Compilar y desplegar**
   ```bash
   cd MOD\ 28/Desafio/Desafio02
   mvn clean package
   ```

4. **Acceder**
   ```
   http://localhost:8080/Desafio02/
   ```

5. **Pruebas:**
   - Login: admin@educacionit.com / admin123
   - Intentar login doble: mensaje de error
   - Logout: click en cerrar sesión

---

## 🎯 Conceptos Aprendidos

### Desafío 01
```jsp
<%@ page import="java.util.*, com.educacionit.entidades.Usuario" %>
<%!
// Declaración: se ejecuta una vez
List<Usuario> usuarios = Arrays.asList(
    new Usuario("user1@test.com", "1234"),
    new Usuario("user2@test.com", "5678")
);
%>
<%
// Scriptlet: se ejecuta en cada petición
String correo = request.getParameter("correo");
String clave = request.getParameter("clave");

boolean encontrado = false;
for (Usuario u : usuarios) {
    if (u.getCorreo().equals(correo) && u.getClave().equals(clave)) {
        encontrado = true;
        break;
    }
}

if (encontrado) {
    response.sendRedirect("Bienvenida.jsp");
} else {
    response.sendRedirect("index.jsp?error=1");
}
%>
```

**Aprendizajes:**
- ✅ Scriptlets JSP (<%...%>)
- ✅ Declaraciones JSP (<%!...%>)
- ✅ response.sendRedirect()
- ✅ Lógica de validación en JSP

**Limitaciones:**
- ❌ Mezcla lógica y presentación
- ❌ No usa sesiones
- ❌ Difícil de mantener

---

### Desafío 02
```java
// En Servlet
@Override
protected void doPost(HttpServletRequest request, 
                     HttpServletResponse response) {
    String correo = request.getParameter("correo");
    String clave = request.getParameter("clave");
    
    Usuario usuario = usuarioDAO.buscarPorID(correo);
    HttpSession sesion = null;
    String redireccion = "index.jsp";
    
    // Control de usuarios activos
    Boolean sesionIniciada = usuariosActivos.containsKey(correo);
    
    if (usuario != null && 
        usuario.getClave().equals(clave) && 
        !sesionIniciada) {
        
        // Registrar usuario activo
        usuariosActivos.put(correo, usuario);
        
        // Crear sesión
        sesion = request.getSession();
        sesion.setAttribute("usuario", usuario);
        
        redireccion = "Bienvenida.jsp";
    } else if (sesionIniciada) {
        request.setAttribute("mensaje", 
            "Sesión ya iniciada en otro equipo");
    }
    
    RequestDispatcher rd = request.getRequestDispatcher(redireccion);
    rd.forward(request, response);
}

@Override
protected void doGet(HttpServletRequest request, 
                    HttpServletResponse response) {
    Boolean cerrarSesion = Boolean.valueOf(
        request.getParameter("cerrarSesion")
    );
    
    if (cerrarSesion) {
        // Remover de usuarios activos
        Usuario usuario = (Usuario) request.getSession()
            .getAttribute("usuario");
        usuariosActivos.remove(usuario.getCorreo());
        
        // Invalidar sesión
        request.getSession().invalidate();
        
        request.setAttribute("mensaje", "Sesión cerrada correctamente");
    }
    
    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
    rd.forward(request, response);
}
```

**Aprendizajes:**
- ✅ HttpSession completo
- ✅ session.setAttribute() / getAttribute()
- ✅ session.invalidate()
- ✅ Control de usuarios activos con Map
- ✅ doGet() para logout
- ✅ doPost() para login
- ✅ Arquitectura MVC

---

## 📁 Estructura de Proyectos

### Desafío 01
```
Desafio01/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/entidades/
│   │       └── Usuario.java
│   └── webapp/
│       ├── index.jsp              # Formulario
│       ├── principal.jsp          # Validación
│       └── WEB-INF/web.xml
└── pom.xml
```

### Desafío 02
```
Desafio02/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java         # Servlet con sesiones
│   │       ├── entidades/
│   │       │   └── Usuario.java
│   │       ├── interfaces/
│   │       │   ├── DAO.java
│   │       │   └── ConexionMariaDB.java
│   │       └── implementaciones/
│   │           └── mariadb/
│   │               └── UsuarioImplementacion.java
│   ├── resources/
│   │   └── database.properties
│   └── webapp/
│       ├── index.jsp
│       ├── Bienvenida.jsp
│       └── WEB-INF/web.xml
├── Script.sql
└── pom.xml
```

---

## 🎓 Evaluación

**¿Qué deberías dominar después de estos desafíos?**

**Desafío 01:**
- [x] Escribir lógica en JSP con scriptlets
- [x] Usar declaraciones JSP (<%! %>)
- [x] Redirigir entre páginas JSP
- [x] Entender limitaciones de JSP puro

**Desafío 02:**
- [x] Crear y gestionar sesiones HTTP
- [x] Usar session.setAttribute() / getAttribute()
- [x] Invalidar sesiones (logout)
- [x] Controlar usuarios activos con Map
- [x] Implementar doGet() y doPost()
- [x] Prevenir sesiones múltiples

---

## 💡 Tips

1. **Desafío 01:**
   - Observa cómo la lógica está mezclada con HTML
   - Nota la falta de sesiones (usuario no persiste)
   - Aprecia la simplicidad pero también las limitaciones

2. **Desafío 02:**
   - Usa `Map<String, Usuario>` para rastrear sesiones activas
   - El logout debe remover del Map Y invalidar sesión
   - doGet() típicamente para logout
   - doPost() típicamente para login

3. **General:**
   - En producción, usa timeout de sesión
   - Almacena sesiones en BD para alta disponibilidad
   - Usa HTTPS para proteger credenciales

---

## 🐛 Solución de Problemas

### Sesión no persiste
**Causa**: Cookies deshabilitadas  
**Solución**: Habilitar cookies en navegador

### Usuario puede iniciar sesión múltiple
**Causa**: No se implementó control con Map  
**Solución**: Ver Desafío 02 para implementación

### Sesión no se invalida
**Causa**: session.invalidate() no llamado  
**Solución**: Verificar método doGet() del logout

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**




