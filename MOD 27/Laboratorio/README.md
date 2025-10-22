# 🔬 Laboratorio - Formulario de Empleado Administrativo con Servlets

## 📖 Descripción

Aplicación web que procesa un formulario HTML para registrar datos de un **empleado administrativo**, utilizando Servlets para capturar la información y generar una respuesta HTML dinámica mostrando los datos procesados.

---

## ✨ Funcionalidades

✅ **Formulario HTML completo** (index.html)  
✅ **Captura de datos** de empleado administrativo  
✅ **Servlet procesa** petición POST  
✅ **Generación dinámica** de respuesta HTML  
✅ **Conversión de tipos** (String a Date, Float, Integer)  
✅ **Uso de entidades** POO (Administrativo, Documento, Persona)  
✅ **Enum para tipo de documento**  
✅ **Utilidades de fecha** para formateo  

---

## 🛠️ Tecnologías

- **Java 8+**
- **Servlet API 4.0**
- **Apache Tomcat 9.0**
- **Maven**
- **HTML5/CSS3**
- **POO** (herencia, enums, interfaces)

---

## 📦 Estructura del Proyecto

```
Laboratorio/
├── src/main/
│   ├── java/
│   │   └── com/educacionIT/javase/
│   │       ├── servlets/
│   │       │   └── Principal.java              # Servlet controlador
│   │       ├── entidades/
│   │       │   ├── Persona.java                # Clase base
│   │       │   ├── Empleado.java               # Clase intermedia
│   │       │   ├── Administrativo.java         # Clase específica
│   │       │   └── Documento.java              # Modelo documento
│   │       ├── enumerados/
│   │       │   └── TiposDocumento.java         # Enum de tipos
│   │       ├── interfaces/
│   │       │   ├── UtilidadesFecha.java        # Conversión de fechas
│   │       │   ├── Constantes.java             # Constantes
│   │       │   └── DAO.java                    # Interface genérica
│   │       └── comparadores/
│   │           ├── OrdenDocumento.java
│   │           └── OrdenEdadDesc.java
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                         # Configuración
│       ├── css/
│       │   ├── style.css                       # Estilos generales
│       │   ├── form.css                        # Estilos del formulario
│       │   ├── button.css                      # Estilos de botones
│       │   └── table.css                       # Estilos de tablas
│       ├── images/
│       │   └── editor.png                      # Logo
│       └── index.html                          # Formulario principal
└── pom.xml                                     # Dependencias Maven
```

---

## 🚀 Ejecución

### 1. Importar Proyecto
```bash
File → Import → Maven → Existing Maven Projects
Seleccionar: MOD 27/Laboratorio/Laboratorio
```

### 2. Compilar
```bash
mvn clean package
```

### 3. Desplegar en Tomcat
```
Run As → Run on Server → Tomcat 9.0
```

### 4. Acceder
```
http://localhost:8080/Laboratorio01/
```

### 5. Llenar Formulario
```
Nombre: Juan
Apellido: Pérez
Tipo Documento: DNI
Número Documento: 12345678
Fecha Nacimiento: 1990-05-15
Fecha Cargo: 2020-01-10
Sueldo: 50000
```

---

## 💻 Código Principal

### Administrativo.java (Entidad)
```java
public class Administrativo extends Empleado {
    private Date fechaCargo;
    private Float sueldo;
    
    public Administrativo(String nombre, String apellido, 
                         Documento documento, Date fechaNacimiento,
                         Date fechaCargo, Float sueldo) {
        super(nombre, apellido, documento, fechaNacimiento);
        this.fechaCargo = fechaCargo;
        this.sueldo = sueldo;
    }
    
    // Getters y setters
}
```

### TiposDocumento.java (Enum)
```java
public enum TiposDocumento {
    DNI,
    PASAPORTE,
    LIBRETA_CIVICA,
    LIBRETA_ENROLAMIENTO,
    CEDULA;
}
```

### UtilidadesFecha.java (Interface)
```java
public interface UtilidadesFecha {
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    
    static Date getStringAFechaSQL(String fecha) throws ParseException {
        return formato.parse(fecha);
    }
    
    static String getFechaAString(Date fecha) {
        return formato.format(fecha);
    }
}
```

### Principal.java (Servlet)
```java
public class Principal extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {
        
        Administrativo administrativo = null;
        
        try {
            // Capturar parámetros
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            TiposDocumento tipo = TiposDocumento.valueOf(
                request.getParameter("tipoDocumento")
            );
            Integer numero = Integer.parseInt(
                request.getParameter("numeroDocumento")
            );
            Date fechaNacimiento = UtilidadesFecha.getStringAFechaSQL(
                request.getParameter("fechaNacimiento")
            );
            Date fechaCargo = UtilidadesFecha.getStringAFechaSQL(
                request.getParameter("fechaCargo")
            );
            Float sueldo = Float.parseFloat(
                request.getParameter("sueldo")
            );
            
            // Crear objeto
            administrativo = new Administrativo(
                nombre, apellido, 
                new Documento(tipo, numero),
                fechaNacimiento, fechaCargo, sueldo
            );
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Generar respuesta HTML
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Empleado Registrado</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Empleado Administrativo Registrado</h1>");
        out.println("<h3>Nombre: " + administrativo.getNombre() + "</h3>");
        out.println("<h3>Apellido: " + administrativo.getApellido() + "</h3>");
        out.println("<h3>Documento: " + administrativo.getDocumento() + "</h3>");
        out.println("<h3>Fecha Nacimiento: " + 
            UtilidadesFecha.getFechaAString(
                administrativo.getFechaNacimiento()
            ) + "</h3>");
        out.println("<h3>Fecha Cargo: " + 
            UtilidadesFecha.getFechaAString(
                administrativo.getFechaCargo()
            ) + "</h3>");
        out.println("<h3>Sueldo: $" + administrativo.getSueldo() + "</h3>");
        out.println("<br><a href=\"index.html\">Volver</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
```

### index.html
```html
<form action="Principal" method="post">
    <label>Nombre:</label>
    <input type="text" name="nombre" required />
    
    <label>Apellido:</label>
    <input type="text" name="apellido" required />
    
    <label>Tipo de Documento:</label>
    <select name="tipoDocumento" required>
        <option value="DNI">DNI</option>
        <option value="PASAPORTE">Pasaporte</option>
        <option value="LIBRETA_CIVICA">Libreta Cívica</option>
        <option value="LIBRETA_ENROLAMIENTO">Libreta Enrolamiento</option>
        <option value="CEDULA">Cédula</option>
    </select>
    
    <label>Número de Documento:</label>
    <input type="number" name="numeroDocumento" required />
    
    <label>Fecha de Nacimiento:</label>
    <input type="date" name="fechaNacimiento" required />
    
    <label>Fecha de Cargo:</label>
    <input type="date" name="fechaCargo" required />
    
    <label>Sueldo:</label>
    <input type="number" name="sueldo" step="0.01" required />
    
    <button type="submit">Registrar</button>
</form>
```

---

## 🎯 Flujo de Ejecución

```
1. Usuario accede a index.html
   ↓
2. Llena formulario con 7 campos
   ↓
3. Submit → POST a /Principal
   ↓
4. Servlet captura parámetros (Strings)
   ↓
5. Convierte tipos (String → Date, Integer, Float)
   ↓
6. Crea objetos POO (Administrativo, Documento)
   ↓
7. Genera HTML con PrintWriter
   ↓
8. Usuario ve resultado formateado
```

---

## 📊 Ejemplo de Salida

### Input (Formulario)
```
Nombre: María
Apellido: González
Tipo Documento: DNI
Número: 25678901
Fecha Nacimiento: 1985-08-22
Fecha Cargo: 2021-03-15
Sueldo: 75000
```

### Output (HTML Generado)
```html
<h1>Empleado Administrativo Registrado</h1>
<h3>Nombre: María</h3>
<h3>Apellido: González</h3>
<h3>Documento: DNI 25678901</h3>
<h3>Fecha Nacimiento: 1985-08-22</h3>
<h3>Fecha Cargo: 2021-03-15</h3>
<h3>Sueldo: $75000.0</h3>
<a href="index.html">Volver</a>
```

---

## 🔍 Análisis del Código

### 1. Conversión de Tipos
```java
// String a Integer
Integer numero = Integer.parseInt(request.getParameter("numeroDocumento"));

// String a Float
Float sueldo = Float.parseFloat(request.getParameter("sueldo"));

// String a Enum
TiposDocumento tipo = TiposDocumento.valueOf(
    request.getParameter("tipoDocumento")
);

// String a Date
Date fecha = UtilidadesFecha.getStringAFechaSQL(
    request.getParameter("fechaNacimiento")
);
```

### 2. Uso de Herencia
```java
// Persona → Empleado → Administrativo
public class Administrativo extends Empleado {
    // Hereda: nombre, apellido, documento, fechaNacimiento
    // Agrega: fechaCargo, sueldo
}
```

### 3. Interface para Utilidades
```java
public interface UtilidadesFecha {
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    
    static Date getStringAFechaSQL(String fecha) {
        return formato.parse(fecha);
    }
}
```

### 4. Validación HTML5
```html
<input type="text" name="nombre" required />
<input type="date" name="fechaNacimiento" required />
<input type="number" name="sueldo" step="0.01" required />
```

---

## 🎓 Conceptos Aprendidos

### 1. Procesamiento de Formularios
```java
String nombre = request.getParameter("nombre");
```

### 2. Conversión y Validación
```java
try {
    Integer numero = Integer.parseInt(request.getParameter("numeroDocumento"));
} catch (NumberFormatException e) {
    // Manejar error
}
```

### 3. Generación de HTML Dinámico
```java
PrintWriter out = response.getWriter();
out.println("<h3>Nombre: " + administrativo.getNombre() + "</h3>");
```

### 4. Integración POO
- Herencia (Persona, Empleado, Administrativo)
- Enums (TiposDocumento)
- Interfaces (UtilidadesFecha)
- Encapsulamiento (getters/setters)

---

## 🎯 Puntos Clave

### ✅ Ventajas
- Demuestra integración completa POO + Servlets
- Conversión de tipos bien manejada
- Usa enum para valores fijos
- Interface para utilidades reutilizables

### ⚠️ Áreas de Mejora
- PrintWriter mezcla lógica y presentación
- No hay persistencia (datos se pierden)
- Falta validación del lado del servidor
- Sin manejo de errores robusto

### 💡 Sugerencias
1. Usar JSP en lugar de PrintWriter
2. Implementar DAO para persistir en BD
3. Agregar validación server-side
4. Usar RequestDispatcher
5. Implementar manejo de excepciones personalizado

---

## 🎓 Evaluación

**¿Qué deberías dominar después de este laboratorio?**

- [x] Procesar formularios HTML con POST
- [x] Convertir tipos de datos (String a Date, Integer, Float)
- [x] Usar enums en aplicaciones web
- [x] Crear objetos POO desde datos del formulario
- [x] Generar HTML dinámico con PrintWriter
- [x] Aplicar herencia en entidades
- [x] Usar interfaces para utilidades

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

**Desarrollado con ❤️ como parte del curso Java Fullstack Developer**




