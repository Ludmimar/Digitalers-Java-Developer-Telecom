# 🎯 MOD 27 - Desafíos: Servlets y Java EE

## 📖 Descripción General

Esta carpeta contiene **2 desafíos** progresivos que demuestran el desarrollo de aplicaciones web con Servlets, desde validación en memoria hasta integración completa con base de datos.

---

## 📂 Contenido

### 🏆 **Desafío 01**: Login con Servlets Básico
Sistema de autenticación simple con:
- Validación en memoria (Lista de usuarios predefinidos)
- Servlet que procesa formulario
- Respuesta HTML dinámica con PrintWriter
- Plantillas HTML embebidas en Java

**Características:**
- ✅ HttpServlet y doPost()
- ✅ request.getParameter()
- ✅ PrintWriter para generar HTML
- ✅ Validación con List<Usuario>
- ✅ Entidad Usuario simple

---

### 🏆 **Desafío 02**: Login con JSP, DAO y Base de Datos
Sistema de autenticación avanzado con:
- Validación contra base de datos MySQL
- Patrón DAO para acceso a datos
- RequestDispatcher para navegación
- JSP para vistas (index.jsp, Bienvenida.jsp)
- Manejo de atributos en request

**Características:**
- ✅ Integración JDBC
- ✅ Patrón DAO
- ✅ RequestDispatcher.forward()
- ✅ JSP para vistas
- ✅ request.setAttribute()
- ✅ Base de datos MySQL
- ✅ Script SQL incluido

---

## 🔄 Comparación de Desafíos

| Aspecto | Desafío 01 | Desafío 02 |
|---------|------------|------------|
| **Autenticación** | En memoria | Base de datos |
| **Tecnologías** | Servlet + PrintWriter | Servlet + JSP + JDBC |
| **Patrones** | MVC básico | MVC + DAO |
| **Respuesta** | HTML generado en Java | Forward a JSP |
| **Persistencia** | ❌ | ✅ |
| **Escalabilidad** | Baja | Alta |
| **Complejidad** | ⭐⭐ | ⭐⭐⭐⭐ |

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

1. **Importar proyecto en IDE**
   ```bash
   cd MOD\ 27/Desafio/Desafio01
   mvn clean package
   ```

2. **Desplegar en Tomcat**
   - Run As → Run on Server → Tomcat

3. **Acceder**
   ```
   http://localhost:8080/Desafio01/
   ```

4. **Usuarios de prueba**
   ```
   user1@deducacionit.com / user1.1234
   user2@deducacionit.com / user2.1235
   user3@deducacionit.com / user3.1236
   ```

---

### **Desafío 02**

1. **Crear base de datos**
   ```sql
   -- Ejecutar Script.sql en MySQL Workbench
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

2. **Configurar conexión**
   ```properties
   # src/main/resources/database.properties
   db.url=jdbc:mysql://localhost:3306/sistema_login
   db.user=root
   db.password=tu_password
   ```

3. **Compilar y desplegar**
   ```bash
   cd MOD\ 27/Desafio/Desafio02
   mvn clean package
   # Desplegar en Tomcat desde IDE
   ```

4. **Acceder**
   ```
   http://localhost:8080/Desafio02/
   ```

5. **Usuarios de prueba**
   ```
   admin@educacionit.com / admin123
   user@educacionit.com / user123
   ```

---

## 🎯 Conceptos Aprendidos

### Desafío 01
```java
// Servlet básico con PrintWriter
protected void doPost(HttpServletRequest request, 
                     HttpServletResponse response) {
    String correo = request.getParameter("correo");
    String clave = request.getParameter("clave");
    
    if (usuarioValido(correo, clave)) {
        out.println(plantillaUsuarioValido);
    } else {
        out.println(plantillaUsuarioNoValido);
    }
}
```

**Aprendizajes:**
- ✅ Ciclo de vida de Servlets
- ✅ Procesamiento de formularios
- ✅ Generación dinámica de HTML
- ✅ Validación simple

---

### Desafío 02
```java
// Servlet avanzado con DAO y RequestDispatcher
protected void doPost(HttpServletRequest request, 
                     HttpServletResponse response) {
    String correo = request.getParameter("correo");
    String clave = request.getParameter("clave");
    
    Usuario usuario = usuarioDAO.buscarPorID(correo);
    String redireccion = "index.jsp";
    
    if (usuario != null && usuario.getClave().equals(clave)) {
        redireccion = "Bienvenida.jsp";
        request.setAttribute("usuario", usuario);
    } else {
        request.setAttribute("credencialesValidas", false);
    }
    
    RequestDispatcher rd = request.getRequestDispatcher(redireccion);
    rd.forward(request, response);
}
```

**Aprendizajes:**
- ✅ Integración JDBC
- ✅ Patrón DAO
- ✅ RequestDispatcher
- ✅ Comunicación Servlet-JSP
- ✅ Atributos en request

---

## 📁 Estructura de Proyectos

### Desafío 01
```
Desafio01/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java
│   │       └── entidades/
│   │           └── Usuario.java
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       └── index.jsp
└── pom.xml
```

### Desafío 02
```
Desafio02/
├── src/main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java
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
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.jsp
│       └── Bienvenida.jsp
├── Script.sql
└── pom.xml
```

---

## 🎓 Evaluación

### ¿Qué se espera que domines?

**Desafío 01:**
- [x] Crear un Servlet básico
- [x] Procesar peticiones POST
- [x] Generar HTML con PrintWriter
- [x] Validar credenciales en memoria

**Desafío 02:**
- [x] Integrar Servlet con JDBC
- [x] Implementar patrón DAO
- [x] Usar RequestDispatcher
- [x] Pasar datos entre Servlet y JSP
- [x] Configurar database.properties

---

## 💡 Tips

1. **Desafío 01:**
   - Observa cómo el HTML está embebido en el Java (no es ideal)
   - Aprecia la simplicidad pero también las limitaciones
   - Piensa en qué pasaría si necesitas modificar el HTML

2. **Desafío 02:**
   - Nota la separación de responsabilidades (MVC + DAO)
   - JSP maneja la presentación
   - Servlet maneja la lógica
   - DAO maneja el acceso a datos

3. **General:**
   - Verifica que Tomcat esté corriendo en puerto 8080
   - Revisa logs en caso de error
   - Asegúrate de que web.xml esté bien configurado

---

## 🐛 Solución de Problemas

### Error 404
**Causa**: URL incorrecta o Servlet no mapeado  
**Solución**: Verificar `<url-pattern>` en web.xml

### Error 500
**Causa**: Excepción en el Servlet  
**Solución**: Revisar logs de Tomcat

### Base de datos no conecta (Desafío 02)
**Causa**: Credenciales incorrectas  
**Solución**: Verificar database.properties y MySQL corriendo

---

## 📚 Recursos

- [Java Servlet Tutorial](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Apache Tomcat Documentation](https://tomcat.apache.org/)
- [MVC Pattern](https://www.javatpoint.com/MVC-in-jsp)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**



