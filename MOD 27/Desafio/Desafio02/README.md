# 🎯 Desafío 02 - Login Avanzado con JSP, DAO y Base de Datos

## 📖 Descripción

Sistema de autenticación profesional que integra **Servlets, JSP, JDBC y el patrón DAO** para validar usuarios contra una base de datos MySQL, con separación de responsabilidades y navegación mediante RequestDispatcher.

---

## ✨ Funcionalidades

✅ **Formulario de login** (index.jsp)  
✅ **Validación contra base de datos** MySQL  
✅ **Patrón DAO** para acceso a datos  
✅ **RequestDispatcher** para navegación  
✅ **JSP para vistas** (separación de presentación)  
✅ **Manejo de atributos** en request  
✅ **Arquitectura MVC + DAO**  

---

## 🛠️ Tecnologías

- **Java 8+**
- **Servlet API 4.0**
- **JSP 2.3**
- **JDBC**
- **MySQL/MariaDB**
- **Apache Tomcat 9.0**
- **Maven**

---

## 📦 Estructura del Proyecto

```
Desafio02/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java              # Servlet controlador
│   │       ├── entidades/
│   │       │   └── Usuario.java                # Modelo
│   │       ├── interfaces/
│   │       │   ├── DAO.java                    # Interface genérica
│   │       │   └── ConexionMariaDB.java        # Conexión BD
│   │       └── implementaciones/
│   │           └── mariadb/
│   │               └── UsuarioImplementacion.java  # DAO concreto
│   ├── resources/
│   │   └── database.properties                 # Configuración BD
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                         # Configuración
│       ├── index.jsp                           # Login
│       └── Bienvenida.jsp                      # Vista de éxito
├── Script.sql                                  # Script de BD
└── pom.xml                                     # Dependencias
```

---

## 🚀 Ejecución

### 1. Crear Base de Datos
```sql
-- Ejecutar en MySQL Workbench
CREATE DATABASE IF NOT EXISTS sistema_login;
USE sistema_login;

CREATE TABLE usuarios (
    correo VARCHAR(100) PRIMARY KEY,
    clave VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (correo, clave) VALUES 
('admin@educacionit.com', 'admin123'),
('user@educacionit.com', 'user123'),
('ludmila@educacionit.com', 'ludmila123');
```

### 2. Configurar Conexión
```properties
# src/main/resources/database.properties
db.url=jdbc:mysql://localhost:3306/sistema_login
db.user=root
db.password=tu_password_aqui
```

### 3. Compilar e Importar
```bash
cd MOD\ 27/Desafio/Desafio02
mvn clean package
```

### 4. Desplegar en Tomcat
```
Run As → Run on Server → Tomcat 9.0
```

### 5. Acceder
```
http://localhost:8080/Desafio02/
```

### 6. Probar
```
admin@educacionit.com / admin123
user@educacionit.com / user123
ludmila@educacionit.com / ludmila123
```

---

## 💻 Código Principal

### Usuario.java
```java
package com.educacionit.entidades;

public class Usuario {
    private String correo;
    private String clave;
    
    // Constructor, getters y setters
}
```

### DAO.java (Interface Genérica)
```java
public interface DAO<K, V> {
    boolean insertar(V v);
    boolean actualizar(V v);
    boolean eliminar(V v);
    V buscarPorID(K k);
    List<V> listar();
}
```

### UsuarioImplementacion.java
```java
public class UsuarioImplementacion implements DAO<String, Usuario> {
    
    @Override
    public Usuario buscarPorID(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection con = ConexionMariaDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    rs.getString("correo"),
                    rs.getString("clave")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Implementar otros métodos...
}
```

### Principal.java (Servlet)
```java
public class Principal extends HttpServlet {
    private UsuarioImplementacion usuarioImplementacion = 
        new UsuarioImplementacion();
    
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Usuario usuario = usuarioImplementacion.buscarPorID(correo);
        String redireccion = "index.jsp";
        
        if (usuario != null && 
            usuario.getClave().equalsIgnoreCase(clave)) {
            redireccion = "Bienvenida.jsp";
            request.setAttribute("usuario", usuario);
        } else {
            request.setAttribute("credencialesValidas", false);
        }
        
        RequestDispatcher rd = 
            request.getRequestDispatcher(redireccion);
        rd.forward(request, response);
    }
}
```

### index.jsp
```jsp
<%
Boolean credencialesValidas = 
    (Boolean) request.getAttribute("credencialesValidas");
%>
<form action="Principal" method="post">
    <% if (credencialesValidas != null && !credencialesValidas) { %>
        <p style="color: red;">Credenciales inválidas</p>
    <% } %>
    
    <input type="email" name="correo" required />
    <input type="password" name="clave" required />
    <button type="submit">Iniciar Sesión</button>
</form>
```

### Bienvenida.jsp
```jsp
<%@ page import="com.educacionit.entidades.Usuario" %>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Bienvenida</title>
</head>
<body>
    <h1>¡Bienvenido <%= usuario.getCorreo() %>!</h1>
    <a href="index.jsp">Cerrar sesión</a>
</body>
</html>
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
4. Servlet obtiene parámetros
   ↓
5. DAO consulta base de datos
   ↓
6. Servlet valida y setea atributos
   ↓
7. RequestDispatcher forward a JSP
   ↓
8. JSP muestra resultado (Bienvenida.jsp o index.jsp)
```

---

## 📊 Ejemplo de Ejecución

### ✅ Login Exitoso

**Request:**
```
POST /Principal
correo=admin@educacionit.com
clave=admin123
```

**Servlet:**
```java
Usuario usuario = usuarioDAO.buscarPorID("admin@educacionit.com");
// usuario != null && clave coincide
request.setAttribute("usuario", usuario);
forward("Bienvenida.jsp");
```

**Response:** Vista Bienvenida.jsp
```html
<h1>¡Bienvenido admin@educacionit.com!</h1>
```

---

### ❌ Login Fallido

**Request:**
```
POST /Principal
correo=fake@example.com
clave=wrong
```

**Servlet:**
```java
Usuario usuario = usuarioDAO.buscarPorID("fake@example.com");
// usuario == null
request.setAttribute("credencialesValidas", false);
forward("index.jsp");
```

**Response:** index.jsp con mensaje de error
```html
<p style="color: red;">Credenciales inválidas</p>
```

---

## 🔍 Análisis del Código

### 1. Patrón DAO
**Ventajas:**
- ✅ Separa lógica de negocio de acceso a datos
- ✅ Reutilizable
- ✅ Fácil de testear
- ✅ Cambiar BD sin afectar el Servlet

```java
// Servlet no conoce detalles de BD
Usuario usuario = usuarioDAO.buscarPorID(correo);
```

### 2. RequestDispatcher vs PrintWriter
**RequestDispatcher:**
- ✅ Mantiene request y response
- ✅ URL no cambia
- ✅ Permite pasar atributos
- ✅ JSP maneja la presentación

**PrintWriter (Desafío 01):**
- ❌ HTML mezclado con Java
- ❌ Difícil de mantener
- ❌ No reutilizable

### 3. JSP para Vistas
**Ventajas:**
- ✅ Separación de presentación
- ✅ HTML limpio
- ✅ Fácil de modificar
- ✅ Reutilización de componentes

### 4. database.properties
**Ventajas:**
- ✅ Configuración externa
- ✅ Fácil de cambiar entre entornos
- ✅ No hardcodear credenciales

---

## 🎓 Conceptos Aprendidos

### 1. RequestDispatcher
```java
RequestDispatcher rd = request.getRequestDispatcher("Bienvenida.jsp");
rd.forward(request, response);
```

### 2. Atributos en Request
```java
// En Servlet
request.setAttribute("usuario", usuario);

// En JSP
Usuario usuario = (Usuario) request.getAttribute("usuario");
```

### 3. Integración JDBC
```java
Connection con = ConexionMariaDB.getConexion();
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, correo);
ResultSet rs = ps.executeQuery();
```

### 4. Arquitectura MVC + DAO
```
View (JSP) ←→ Controller (Servlet) ←→ DAO ←→ Database
```

---

## 🎯 Diferencias con Desafío 01

| Aspecto | Desafío 01 | Desafío 02 |
|---------|------------|------------|
| **Validación** | En memoria | Base de datos |
| **Respuesta** | PrintWriter | JSP + forward |
| **Arquitectura** | Básica | MVC + DAO |
| **Persistencia** | ❌ | ✅ |
| **Mantenibilidad** | ⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Escalabilidad** | ❌ | ✅ |
| **Separación** | ❌ | ✅ |

---

## 🎓 Evaluación

**¿Qué deberías dominar después de este desafío?**

- [x] Implementar patrón DAO
- [x] Conectar Servlet con JDBC
- [x] Usar RequestDispatcher
- [x] Pasar datos entre Servlet y JSP
- [x] Configurar database.properties
- [x] Crear vistas JSP dinámicas
- [x] Separar responsabilidades (MVC)

---

## 💡 Tips

1. **Siempre cierra conexiones:**
   ```java
   try (Connection con = ...) {
       // Uso
   } // Se cierra automáticamente
   ```

2. **Usa PreparedStatement:**
   ```java
   String sql = "SELECT * FROM usuarios WHERE correo = ?";
   ps.setString(1, correo); // Previene SQL Injection
   ```

3. **Valida en Servlet, no en JSP:**
   ```java
   // Servlet hace la lógica
   if (usuario != null && clave.equals(...)) {
       // JSP solo muestra
   }
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



