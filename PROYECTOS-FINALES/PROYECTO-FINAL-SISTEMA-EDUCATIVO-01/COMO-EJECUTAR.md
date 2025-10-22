# 🚀 Cómo Ejecutar el Proyecto Sistema Educativo

## 📋 Requisitos Previos

1. **Java JDK 8 o superior** instalado
2. **Maven** instalado (viene con NetBeans)
3. **MySQL o MariaDB** instalado y corriendo
4. **Base de datos** `sistema_educativo` creada

---

## 🗄️ Paso 1: Configurar la Base de Datos

### Opción A: Desde MySQL Workbench
```sql
CREATE DATABASE IF NOT EXISTS sistema_educativo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Luego ejecuta los scripts en orden:
1. `database/01_schema.sql`
2. `database/02_datos_prueba.sql`

### Opción B: Desde línea de comandos
```bash
cd database
mysql -u root -p < 01_schema.sql
mysql -u root -p < 02_datos_prueba.sql
```

---

## ⚙️ Paso 2: Configurar la Conexión a BD

Edita el archivo `backend/src/main/java/com/educacionit/sistemaeducativo/utilidades/ConexionDB.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";
private static final String USUARIO = "root";
private static final String CLAVE = "TU_CONTRASEÑA";  // ⚠️ Cambia esto
```

---

## 🏗️ Paso 3: Compilar el Proyecto

### Desde la terminal (PowerShell):
```powershell
cd "PROYECTO-FINAL-SISTEMA-EDUCATIVO 01\backend"

# Con Maven instalado:
mvn clean compile

# O con la ruta completa de NetBeans:
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" clean compile
```

### Desde NetBeans:
1. Abrir el proyecto en NetBeans
2. Click derecho en el proyecto → **Clean and Build**

---

## 🚀 Paso 4: Ejecutar el Servidor Tomcat

### Opción A: Con Maven (Recomendado para desarrollo)

```powershell
# Crear directorio de configuración (si no existe)
New-Item -ItemType Directory -Force -Path "target\tomcat\conf"

# Ejecutar Tomcat
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" tomcat7:run
```

El servidor iniciará en: **http://localhost:8081/sistema-educativo**

### Opción B: Desde NetBeans
1. Asegurarte de tener Tomcat configurado en NetBeans
2. Click derecho en el proyecto → **Run**

### Opción C: Crear WAR y desplegar en Tomcat standalone

```powershell
# Generar el archivo WAR
mvn clean package

# El archivo estará en: target/sistema-educativo-1.0.0.war
# Copiar este archivo a: [TOMCAT_HOME]/webapps/
```

---

## 🌐 Paso 5: Acceder a la Aplicación

Abre tu navegador y ve a:
- **URL principal**: http://localhost:8081/sistema-educativo
- **Dashboard**: http://localhost:8081/sistema-educativo/dashboard

---

## ❌ Solución de Problemas

### Error: "tomcat-users.xml cannot be read"

**Solución**: Crear el archivo manualmente antes de ejecutar:

```powershell
# Crear directorio
New-Item -ItemType Directory -Force -Path "target\tomcat\conf"

# Crear archivo tomcat-users.xml
@"
<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
  <role rolename="manager-gui"/>
  <user username="admin" password="admin" roles="manager-gui"/>
</tomcat-users>
"@ | Out-File -Encoding UTF8 "target\tomcat\conf\tomcat-users.xml"
```

### Error: "Connection refused" o "Access denied"

1. Verifica que MySQL/MariaDB esté corriendo:
   ```powershell
   Get-Service MySQL*
   ```

2. Verifica las credenciales en `ConexionDB.java`

3. Verifica que la base de datos exista:
   ```sql
   SHOW DATABASES;
   ```

### Error: "Port 8081 already in use"

**Opción 1**: Cambiar el puerto en `pom.xml`:
```xml
<configuration>
    <port>8082</port>  <!-- Cambiar a otro puerto -->
    ...
</configuration>
```

**Opción 2**: Detener el proceso que usa el puerto 8081:
```powershell
# Ver qué proceso usa el puerto
netstat -ano | findstr :8081

# Detener el proceso (reemplaza PID con el número que aparece)
taskkill /PID [PID] /F
```

### Compilación: Warnings sobre Java 8 obsoleto

Estos warnings no afectan la ejecución. Para eliminarlos, actualiza a Java 11+ en `pom.xml`:
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

---

## 🔧 Configuración Adicional

### Cambiar el Puerto

Edita `backend/pom.xml`:
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

### Habilitar Hot Reload (recarga automática)

En `pom.xml`, agrega:
```xml
<configuration>
    ...
    <contextReloadable>true</contextReloadable>
</configuration>
```

---

## 📝 Scripts Útiles

### Limpiar y Reconstruir
```powershell
mvn clean compile
```

### Ejecutar Tests
```powershell
mvn test
```

### Generar WAR para producción
```powershell
mvn clean package
```

### Ver logs de Tomcat
Los logs aparecen en la consola donde ejecutaste `mvn tomcat7:run`

---

## 🎯 Funcionalidades Disponibles

Una vez corriendo, puedes:
- ✅ Gestionar estudiantes
- ✅ Gestionar profesores
- ✅ Crear y administrar cursos
- ✅ Gestionar aulas
- ✅ Gestionar períodos académicos
- ✅ Inscribir estudiantes en cursos
- ✅ Ver dashboard con estadísticas

---

## 📚 Referencias

- **Documentación completa**: Ver `docs/` para más detalles
- **Inicio rápido**: `docs/01-INICIO-RAPIDO.md`
- **Comandos BD**: `COMANDOS-VER-BD.md`
- **Guía de entrega**: `PROYECTO-LISTO-PARA-ENTREGAR.md`

---

## ⚠️ Notas Importantes

1. **Primera ejecución**: La primera vez puede tardar más (Maven descarga dependencias)
2. **Base de datos**: Debe estar corriendo ANTES de iniciar Tomcat
3. **Puerto 8081**: Asegúrate de que no esté ocupado
4. **Navegador**: Se recomienda Chrome o Firefox actualizado
5. **Encoding**: El proyecto usa UTF-8, asegúrate de que tu IDE lo soporte

---

**¿Dudas?** Revisa el archivo `README.md` o la documentación en `docs/`

