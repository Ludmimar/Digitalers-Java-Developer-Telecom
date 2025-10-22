# 🚀 GUÍA DE DESPLIEGUE EN RENDER

## 📋 **Requisitos Previos**

- Cuenta en [Render](https://render.com) (gratis)
- Cuenta en [GitHub](https://github.com)
- Proyecto subido a GitHub

---

## 🎯 **OPCIÓN 1: DESPLIEGUE AUTOMÁTICO CON RENDER.YAML**

### **Paso 1: Preparar el Repositorio**

1. **Subir proyecto a GitHub:**
```bash
cd "PROYECTO-FINAL-SISTEMA-EDUCATIVO 01"
git init
git add .
git commit -m "Sistema de Gestión Educativa v2.0"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/sistema-educativo.git
git push -u origin main
```

### **Paso 2: Conectar con Render**

1. Ir a [render.com](https://render.com)
2. Click en **Sign Up** o **Sign In**
3. Conectar con tu cuenta de GitHub
4. Autorizar a Render para acceder a tus repositorios

### **Paso 3: Crear Nuevo Blueprint**

1. En el Dashboard de Render, click en **New** → **Blueprint**
2. Seleccionar el repositorio: `sistema-educativo`
3. Render detectará automáticamente el archivo `render.yaml`
4. Click en **Apply**

### **Paso 4: Configurar Variables de Entorno**

Render configurará automáticamente:
- `DB_HOST` → URL de la base de datos MySQL
- `DB_PORT` → 3306
- `DB_NAME` → sistema_educativo
- `DB_USER` → edu_user
- `DB_PASSWORD` → (generado automáticamente)

### **Paso 5: Esperar el Despliegue**

1. Render construirá:
   - Base de datos MySQL
   - Aplicación Java con Maven
   - Contenedor Docker con Tomcat
2. Tiempo estimado: 10-15 minutos
3. URL generada: `https://sistema-educativo-app.onrender.com`

---

## 🎯 **OPCIÓN 2: DESPLIEGUE MANUAL (MÁS CONTROL)**

### **Paso 1: Crear Base de Datos MySQL**

1. En Render Dashboard → **New** → **MySQL**
2. Configurar:
   - **Name:** `sistema-educativo-db`
   - **Database:** `sistema_educativo`
   - **User:** `edu_user`
   - **Region:** Oregon (US West)
   - **Plan:** Free
3. Click en **Create Database**
4. **Guardar las credenciales:**
   - Hostname
   - Port
   - Database
   - Username
   - Password
   - Internal Database URL

### **Paso 2: Cargar el Schema SQL**

1. Usar la conexión externa para conectarse:
```bash
mysql -h <hostname> -P <port> -u <username> -p<password> sistema_educativo
```

2. Ejecutar los scripts:
```sql
SOURCE database/01_CREAR_BASE_DATOS_COMPLETA.sql
SOURCE database/02_CARGAR_DATOS_COMPLETOS.sql
```

**O usar MySQL Workbench:**
- Crear nueva conexión con los datos de Render
- Ejecutar los scripts manualmente

### **Paso 3: Crear Web Service**

1. En Render Dashboard → **New** → **Web Service**
2. Conectar repositorio de GitHub
3. Configurar:
   - **Name:** `sistema-educativo-app`
   - **Region:** Oregon (US West)
   - **Branch:** main
   - **Root Directory:** `backend`
   - **Environment:** Docker
   - **Dockerfile Path:** `./Dockerfile`
   - **Plan:** Free

### **Paso 4: Configurar Variables de Entorno**

En la sección **Environment Variables**, agregar:

```
DB_HOST=<hostname_de_render_mysql>
DB_PORT=3306
DB_NAME=sistema_educativo
DB_USER=edu_user
DB_PASSWORD=<password_generado>
```

### **Paso 5: Configurar Build Command**

```bash
mvn clean package -DskipTests
```

### **Paso 6: Deploy**

1. Click en **Create Web Service**
2. Render comenzará el build automáticamente
3. Esperar ~10-15 minutos

---

## 📊 **POST-DESPLIEGUE**

### **Verificar la Aplicación**

1. **URL:** `https://sistema-educativo-app.onrender.com`
2. **Credenciales de acceso:**
   - Usuario: `admin`
   - Contraseña: `admin123`

### **Probar Funcionalidades:**

```
✅ Login
✅ Dashboard con estadísticas
✅ CRUD Estudiantes
✅ CRUD Profesores
✅ CRUD Cursos
✅ Gestión de Inscripciones
✅ Validaciones de correlatividades
✅ Filtros y búsquedas
```

---

## ⚙️ **CONFIGURACIONES IMPORTANTES**

### **1. Timeout de Sleep en Plan Free**

El plan Free de Render hiberna después de 15 minutos de inactividad:
- Primera carga: 30-60 segundos
- Cargas subsiguientes: instantáneas

**Solución para mantenerlo activo:**
- Usar [UptimeRobot](https://uptimerobot.com) (gratis)
- Ping cada 5 minutos a tu URL

### **2. Límites del Plan Free**

```
Database: 1 GB storage
Web Service: 750 horas/mes
Build time: 500 minutos/mes
Bandwidth: 100 GB/mes
```

### **3. Logs y Debugging**

Ver logs en tiempo real:
1. Dashboard → Tu servicio → **Logs**
2. Buscar errores de conexión o startup

---

## 🔧 **TROUBLESHOOTING**

### **Error: "Build Failed"**

**Causa:** Maven no puede descargar dependencias

**Solución:**
1. Verificar `pom.xml`
2. Asegurar que las dependencias sean válidas
3. Rebuild localmente primero

### **Error: "Can't connect to database"**

**Causa:** Variables de entorno incorrectas

**Solución:**
1. Verificar `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`
2. Usar la **Internal Database URL** de Render
3. No usar `localhost` en producción

### **Error: "Application doesn't start"**

**Causa:** Puerto incorrecto

**Solución:**
- Render espera que la app escuche en `0.0.0.0:8080`
- Tomcat ya está configurado correctamente

---

## 📈 **MONITOREO**

### **Métricas Disponibles (Dashboard):**

```
- Request count
- Response time
- Memory usage
- CPU usage
- Deployment history
```

### **Health Check:**

Render hace ping automático a:
```
https://tu-app.onrender.com/
```

---

## 🔄 **ACTUALIZACIONES AUTOMÁTICAS**

Render se actualiza automáticamente con cada push a GitHub:

```bash
# Hacer cambios en el código
git add .
git commit -m "Mejoras en el sistema"
git push origin main

# Render detecta el push y redeploy automáticamente
```

---

## 💡 **MEJORES PRÁCTICAS**

### **1. Seguridad:**
```
✅ Nunca hacer commit de contraseñas
✅ Usar variables de entorno
✅ Cambiar contraseña de admin en producción
✅ Habilitar HTTPS (Render lo hace automático)
```

### **2. Performance:**
```
✅ Usar pool de conexiones (ya implementado)
✅ Cachear consultas frecuentes
✅ Minimizar CSS/JS
✅ Comprimir imágenes
```

### **3. Backup:**
```
✅ Exportar base de datos regularmente
✅ Guardar snapshots del código
✅ Documentar cambios importantes
```

---

## 📞 **SOPORTE**

### **Render Support:**
- Documentación: [render.com/docs](https://render.com/docs)
- Community: [community.render.com](https://community.render.com)
- Status: [status.render.com](https://status.render.com)

### **Proyecto:**
- GitHub Issues
- Email: ludmilamartos@gmail.com

---

## 🎉 **CHECKLIST FINAL**

```
☐ Repositorio en GitHub
☐ Archivos Dockerfile creados
☐ ConexionDB.java actualizado con variables de entorno
☐ Base de datos MySQL creada en Render
☐ Scripts SQL ejecutados
☐ Web Service creado
☐ Variables de entorno configuradas
☐ Aplicación desplegada
☐ Login funciona
☐ Todas las funcionalidades probadas
☐ URL compartida con profesor/equipo
```

---

## 🚀 **¡LISTO PARA PRODUCCIÓN!**

Tu Sistema de Gestión Educativa está ahora disponible en:
```
https://sistema-educativo-app.onrender.com
```

---

**Fecha:** Octubre 2025  
**Versión:** 2.0  
**Estado:** ✅ Producción Ready

---

## 👨‍💻 Autor

**Desarrolladora**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

