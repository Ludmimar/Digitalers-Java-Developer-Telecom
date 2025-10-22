# 🌐 MOD 27 - Java EE: Servlets y Aplicaciones Web

## 📖 Descripción

Este módulo introduce **Java Enterprise Edition (Java EE)** y el desarrollo de aplicaciones web utilizando **Servlets**. Aprenderás los conceptos fundamentales de la arquitectura cliente-servidor, el ciclo de vida de los servlets y cómo procesar peticiones HTTP.

---

## 📂 Contenido del Módulo

### 📚 Desafíos
- **Desafío 01**: Login básico con Servlets y validación en memoria
- **Desafío 02**: Login con Servlets, JSP y validación con base de datos

### 🔬 Laboratorio
- **Laboratorio**: Formulario de empleado administrativo con Servlets

### 📄 Documentación (DOCS)
- Cliente Servidor Conceptos básicos
- Configuración Apache Tomcat
- Java EE Conceptos básicos

### 🎯 Material Adicional
- Ejemplos de Cliente-Servidor (HTML/CSS)
- Proyecto JavaEE base con Servlets

---

## 🎯 Conceptos Clave

### 1️⃣ **Java Enterprise Edition (Java EE)**
- Plataforma para desarrollo de aplicaciones empresariales
- Arquitectura multinivel (cliente, servidor, base de datos)
- Componentes: Servlets, JSP, EJB, JPA, etc.

### 2️⃣ **Arquitectura Cliente-Servidor**
```
Cliente (Navegador) 
    ↓ HTTP Request (POST/GET)
Servidor Web (Apache Tomcat)
    ↓ Procesa con Servlet
Base de Datos (MySQL)
    ↓ HTTP Response (HTML)
Cliente (Navegador muestra resultado)
```

### 3️⃣ **Apache Tomcat**
- Contenedor de Servlets y JSP
- Servidor web ligero
- Puerto por defecto: 8080
- Deployment de archivos .war

### 4️⃣ **Servlets**
```java
// Ciclo de vida
public class MiServlet extends HttpServlet {
    // 1. Constructor
    public MiServlet() { }
    
    // 2. init() - Se ejecuta una vez
    public void init() { }
    
    // 3. service() - Delega a doGet/doPost
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) {
        // Procesar petición
    }
    
    // 4. destroy() - Al finalizar
    public void destroy() { }
}
```

### 5️⃣ **HttpServletRequest**
```java
// Obtener parámetros del formulario
String usuario = request.getParameter("usuario");
String password = request.getParameter("password");

// Establecer atributos
request.setAttribute("mensaje", "Bienvenido");
```

### 6️⃣ **HttpServletResponse**
```java
// Establecer tipo de contenido
response.setContentType("text/html;charset=UTF-8");

// Enviar HTML al cliente
PrintWriter out = response.getWriter();
out.println("<h1>Hola Mundo</h1>");
out.close();
```

### 7️⃣ **RequestDispatcher**
```java
// Forward (reenvío interno)
RequestDispatcher rd = request.getRequestDispatcher("Bienvenida.jsp");
rd.forward(request, response);

// Include (incluir contenido)
rd.include(request, response);
```

### 8️⃣ **web.xml (Descriptor de Despliegue)**
```xml
<servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>com.educacionit.servlets.Principal</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/login</url-pattern>
</servlet-mapping>
```

---

## 🎯 Objetivos de Aprendizaje

Al completar este módulo, serás capaz de:

✅ **Comprender la arquitectura cliente-servidor**  
✅ **Configurar Apache Tomcat en tu IDE**  
✅ **Crear y desplegar Servlets**  
✅ **Procesar peticiones HTTP (GET/POST)**  
✅ **Enviar respuestas HTML dinámicas**  
✅ **Usar RequestDispatcher para navegación**  
✅ **Integrar Servlets con JSP**  
✅ **Conectar Servlets con base de datos (JDBC)**  
✅ **Implementar validación de usuarios**  
✅ **Configurar web.xml correctamente**  

---

## 🚀 Estructura de Proyectos

```
src/
├── main/
│   ├── java/
│   │   └── com/educacionit/
│   │       ├── servlets/
│   │       │   └── Principal.java       # Servlet controlador
│   │       ├── entidades/
│   │       │   └── Usuario.java         # Modelo
│   │       └── implementaciones/
│   │           └── UsuarioImpl.java     # DAO
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                  # Configuración
│       ├── index.jsp                    # Vista de login
│       └── Bienvenida.jsp               # Vista de éxito
└── pom.xml                              # Dependencias Maven
```

---

## 🛠️ Tecnologías Utilizadas

- **Java 8+**: Lenguaje de programación
- **Java EE (Servlet API)**: Framework web
- **Apache Tomcat 9.0**: Servidor de aplicaciones
- **Maven**: Gestión de dependencias
- **JDBC**: Conexión a base de datos
- **MySQL/MariaDB**: Base de datos
- **HTML/CSS**: Frontend básico
- **JSP**: Vistas dinámicas

---

## 📦 Dependencias Maven

```xml
<dependencies>
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- JSP API -->
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>
</dependencies>
```

---

## 🔍 Diferencias entre Desafíos

| Característica | Desafío 01 | Desafío 02 | Laboratorio |
|----------------|------------|------------|-------------|
| **Validación** | En memoria (List) | Base de datos | No aplica |
| **Respuesta** | HTML con PrintWriter | JSP con forward | HTML con PrintWriter |
| **DAO** | ❌ | ✅ | ✅ |
| **JDBC** | ❌ | ✅ | ❌ |
| **RequestDispatcher** | ❌ | ✅ | ❌ |
| **Complejidad** | Básica | Intermedia | Básica |

---

## 💡 Conceptos Importantes

### **¿Por qué usar Servlets?**
- ✅ Control total sobre peticiones HTTP
- ✅ Procesamiento en el servidor (seguro)
- ✅ Integración con Java (POO, JDBC, etc.)
- ✅ Escalabilidad empresarial

### **Flujo de una Petición**
```
1. Usuario llena formulario → Submit
2. Navegador envía POST a Servlet
3. Servlet procesa (valida, consulta BD, etc.)
4. Servlet reenvía a JSP o genera HTML
5. Navegador muestra resultado
```

### **Ventajas de RequestDispatcher**
- ✅ Mantiene request y response
- ✅ URL no cambia (forward)
- ✅ Permite pasar atributos entre componentes
- ✅ Mejor que response.sendRedirect() en muchos casos

---

## 📚 Recursos Adicionales

- [Documentación oficial de Java EE](https://jakarta.ee/)
- [Apache Tomcat Documentation](https://tomcat.apache.org/)
- [Tutorial de Servlets](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**



