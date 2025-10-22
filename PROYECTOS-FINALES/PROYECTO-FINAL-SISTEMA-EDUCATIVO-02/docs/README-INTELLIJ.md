# 🚀 Guía Completa: Levantar PROYECTO-FINAL-SISTEMA-EDUCATIVO-02 en IntelliJ IDEA

## 📋 **Requisitos Previos**

Antes de comenzar, asegúrate de tener instalado:

1. **Java JDK 8 o superior** (recomendado JDK 11+)
2. **IntelliJ IDEA** (Community o Ultimate)
3. **MySQL Server** (8.0+) o **MariaDB**
4. **Maven** (viene incluido con IntelliJ)

---

## 🗄️ **Paso 1: Configurar la Base de Datos**

### **1.1 Crear la Base de Datos**

**Opción A: Desde MySQL Workbench**
1. Abre **MySQL Workbench**
2. Conéctate a tu servidor MySQL local
3. **File → Open SQL Script**
4. Selecciona: `PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/database/01_CREAR_BASE_DATOS_COMPLETA.sql`
5. Haz clic en **⚡ Execute** (Ctrl+Shift+Enter)
6. Espera el mensaje: ✅ "BASE DE DATOS CREADA EXITOSAMENTE"
7. Repite el proceso con: `database/02_CARGAR_DATOS_COMPLETOS.sql`
8. Espera el mensaje: ✅ "CARGA DE DATOS COMPLETADA EXITOSAMENTE"

**Opción B: Desde línea de comandos**
```bash
# Navegar a la carpeta del proyecto
cd "PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/database"

# Ejecutar scripts SQL
mysql -u root -p < 01_CREAR_BASE_DATOS_COMPLETA.sql
mysql -u root -p < 02_CARGAR_DATOS_COMPLETOS.sql
```

### **1.2 Verificar la Base de Datos**
```sql
USE sistema_educativo;
SHOW TABLES;
-- Deberías ver 10 tablas: estudiantes, profesores, cursos, etc.
```

---

## ⚙️ **Paso 2: Configurar IntelliJ IDEA**

### **2.1 Abrir el Proyecto**

1. **Abrir IntelliJ IDEA**
2. **File → Open** (o Welcome Screen → Open)
3. Navegar a: `PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/backend`
4. Seleccionar la carpeta `backend` y hacer clic en **OK**
5. IntelliJ detectará automáticamente que es un proyecto Maven

### **2.2 Configurar el SDK de Java**

1. **File → Project Structure** (Ctrl+Alt+Shift+S)
2. En **Project Settings → Project**:
   - **Project SDK**: Seleccionar Java 8 o superior
   - **Project language level**: 8 o superior
3. Hacer clic en **Apply** y **OK**

### **2.3 Configurar Maven**

1. **File → Settings** (Ctrl+Alt+S)
2. **Build, Execution, Deployment → Build Tools → Maven**
3. Verificar que:
   - **Maven home directory**: Apunte a tu instalación de Maven
   - **User settings file**: Esté configurado correctamente
4. Hacer clic en **Apply** y **OK**

### **2.4 Importar Dependencias Maven**

1. IntelliJ debería mostrar una notificación: **"Maven projects need to be imported"**
2. Hacer clic en **Import Maven Projects**
3. O manualmente: **View → Tool Windows → Maven**
4. En el panel Maven, hacer clic en **Reload All Maven Projects** (🔄)

---

## 🔧 **Paso 3: Configurar la Conexión a la Base de Datos**

### **3.1 Editar ConexionDB.java**

1. En IntelliJ, navegar a:
   ```
   src/main/java/com/educacionit/sistemaeducativo/utilidades/ConexionDB.java
   ```

2. **Editar las líneas 18-20** con tu configuración de MySQL:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";
private static final String USUARIO = "root";
private static final String CLAVE = "TU_CONTRASEÑA_AQUI";  // ⚠️ CAMBIAR ESTO
```

**Ejemplo:**
```java
private static final String CLAVE = "mipassword123";  // Tu contraseña de MySQL
```

### **3.2 Verificar la Configuración**

Si usas **MariaDB** en lugar de MySQL, cambia también la línea 16:
```java
// private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  // MySQL
private static final String DRIVER = "org.mariadb.jdbc.Driver";  // MariaDB
```

---

## 🏗️ **Paso 4: Compilar el Proyecto**

### **4.1 Compilar desde IntelliJ**

**Opción A: Usando el menú Build**
1. **Build → Rebuild Project**
2. Esperar la compilación (~30-60 segundos)
3. Verificar que no hay errores en la ventana **Build**

**Opción B: Usando Maven**
1. **View → Tool Windows → Maven**
2. En el panel Maven, expandir **Lifecycle**
3. Hacer doble clic en **clean** (limpiar)
4. Hacer doble clic en **compile** (compilar)
5. Hacer doble clic en **package** (empaquetar)

### **4.2 Verificar la Compilación**

Deberías ver en la consola:
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
[INFO] Final Memory: XXM/XXM
```

Si hay errores, revisar:
- ✅ Conexión a la base de datos
- ✅ Dependencias Maven descargadas
- ✅ SDK de Java configurado correctamente

---

## 🚀 **Paso 5: Ejecutar el Servidor Tomcat**

### **5.1 Ejecutar desde IntelliJ (Recomendado)**

**Usando Maven:**
1. **View → Tool Windows → Maven**
2. Expandir **Plugins → tomcat7**
3. Hacer doble clic en **tomcat7:run**
4. Esperar el mensaje: `INFO: Starting ProtocolHandler ["http-bio-8081"]`

**Usando Terminal integrado:**
1. **View → Tool Windows → Terminal**
2. Ejecutar:
```bash
mvn tomcat7:run
```

### **5.2 Verificar que el Servidor Esté Corriendo**

En la consola deberías ver:
```
[INFO] Starting ProtocolHandler ["http-bio-8081"]
[INFO] Starting Coyote HTTP/1.1 on http-8081
```

### **5.3 Acceder a la Aplicación**

Abre tu navegador y ve a:
```
http://localhost:8081/sistema-educativo/
```

**Credenciales de acceso:**
- **Usuario:** `admin`
- **Contraseña:** `admin123`

---

## 🎯 **Funcionalidades Disponibles**

Una vez que la aplicación esté corriendo, podrás:

### **✅ Dashboard**
- Ver estadísticas en tiempo real
- Gráfico de inscripciones por estado
- Información del período activo

### **✅ Gestión de Estudiantes**
- CRUD completo de estudiantes
- Búsqueda por matrícula o nombre
- Filtros por estado académico
- Ver inscripciones del estudiante

### **✅ Gestión de Profesores**
- CRUD completo de profesores
- Búsqueda por código o nombre
- Ver cursos asignados

### **✅ Gestión de Cursos**
- CRUD completo de cursos
- **Gestionar correlatividades** ⭐
- Ver estudiantes inscritos
- Asignar cursos a períodos

### **✅ Gestión de Inscripciones**
- Crear nuevas inscripciones con validaciones
- **Validación de correlatividades** ⭐
- **Validación de límite de créditos** ⭐
- Filtrar por curso o estado

### **✅ Gestión de Aulas**
- CRUD completo de aulas
- Validación de capacidad
- Filtros por tipo y estado

### **✅ Gestión de Períodos**
- CRUD completo de períodos académicos
- Estados del ciclo de vida
- Fechas de inscripción

---

## ❌ **Solución de Problemas Comunes**

### **Error: "Connection refused" o "Access denied"**
1. Verificar que MySQL esté corriendo:
   ```bash
   # Windows
   Get-Service MySQL*
   
   # Linux/Mac
   sudo systemctl status mysql
   ```

2. Verificar las credenciales en `ConexionDB.java`
3. Verificar que la base de datos `sistema_educativo` exista

### **Error: "Port 8081 already in use"**
1. Cambiar el puerto en `pom.xml`:
   ```xml
   <configuration>
       <port>8082</port>  <!-- Cambiar a otro puerto -->
   </configuration>
   ```

2. O detener el proceso que usa el puerto:
   ```bash
   # Windows
   netstat -ano | findstr :8081
   taskkill /PID [PID] /F
   
   # Linux/Mac
   lsof -ti:8081 | xargs kill
   ```

### **Error: "tomcat-users.xml cannot be read"**
Crear el archivo manualmente:
```bash
# Crear directorio
mkdir -p target/tomcat/conf

# Crear archivo tomcat-users.xml
echo '<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
  <role rolename="manager-gui"/>
  <user username="admin" password="admin" roles="manager-gui"/>
</tomcat-users>' > target/tomcat/conf/tomcat-users.xml
```

### **Error 404 en servlets**
1. **Build → Rebuild Project** en IntelliJ
2. Verificar que el proyecto se compiló correctamente
3. Revisar los logs de Tomcat en la consola

### **Error de compilación Maven**
1. **View → Tool Windows → Maven**
2. Hacer clic en **Reload All Maven Projects** (🔄)
3. Verificar conexión a internet (Maven descarga dependencias)

---

## 🔧 **Configuraciones Adicionales**

### **Cambiar Puerto del Servidor**
Editar `backend/pom.xml`:
```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <configuration>
        <port>8082</port>  <!-- Cambiar aquí -->
        <path>/sistema-educativo</path>
    </configuration>
</plugin>
```

### **Habilitar Hot Reload**
En `pom.xml`, agregar:
```xml
<configuration>
    <contextReloadable>true</contextReloadable>
</configuration>
```

### **Actualizar a Java 11+**
En `pom.xml`:
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

---

## 📚 **Comandos Útiles**

### **Limpiar y Reconstruir**
```bash
mvn clean compile
```

### **Ejecutar Tests**
```bash
mvn test
```

### **Generar WAR para Producción**
```bash
mvn clean package
```

### **Ver Logs Detallados**
```bash
mvn tomcat7:run -X
```

---

## 🎉 **¡Proyecto Listo!**

Una vez completados todos los pasos, tendrás:

- ✅ **Sistema educativo completo** funcionando en IntelliJ
- ✅ **Base de datos** configurada con datos de prueba
- ✅ **Servidor Tomcat** corriendo en puerto 8081
- ✅ **Aplicación web** accesible desde el navegador
- ✅ **Todas las funcionalidades** disponibles para testing

**URL de acceso:** `http://localhost:8081/sistema-educativo/`

**¡Disfruta explorando el sistema educativo! 🎓**

---

## 📞 **Soporte**

Si tienes problemas o dudas:

1. **Revisar logs** en la consola de IntelliJ
2. **Verificar configuración** de base de datos
3. **Consultar documentación** en la carpeta `docs/`
4. **Revisar archivos** `COMO-EJECUTAR.md` y `README.md`

---

**Desarrollado por:** Ludmila Martos  
**Curso:** Digitalers Java Developer Telecom  
**Año:** 2025
