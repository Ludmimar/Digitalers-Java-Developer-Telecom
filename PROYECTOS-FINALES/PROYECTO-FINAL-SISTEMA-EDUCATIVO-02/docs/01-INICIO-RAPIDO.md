# 🚀 Guía de Inicio Rápido - Sistema de Gestión Educativa v2.0

## ⚡ **En 5 Minutos**

### **1️⃣ Configurar Base de Datos (2 minutos)**

**En MySQL Workbench:**

1. Abrir **MySQL Workbench**
2. Conectarse a **localhost** (usuario: root)
3. **File → Open SQL Script**
4. Seleccionar `database/01_CREAR_BASE_DATOS_COMPLETA.sql`
5. Click en **⚡ Execute** (rayo) o `Ctrl+Shift+Enter`
6. Esperar mensaje: ✅ "BASE DE DATOS CREADA EXITOSAMENTE"
7. Repetir con `database/02_CARGAR_DATOS_COMPLETOS.sql`
8. Esperar mensaje: ✅ "CARGA DE DATOS COMPLETADA EXITOSAMENTE"

### **2️⃣ Configurar Conexión Java (1 minuto)**

**Editar archivo:**

`backend/src/main/java/com/educacionit/sistemaeducativo/utilidades/ConexionDB.java`

```java
// Líneas 13-15: Verificar tu configuración de MySQL
private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";
private static final String USER = "root";
private static final String PASSWORD = "tu_password_aqui";  // ⬅️ CAMBIAR
```

### **3️⃣ Compilar y Ejecutar (2 minutos)**

**Opción A: Usando IntelliJ** (Recomendado)

1. Abrir proyecto en IntelliJ IDEA
2. **Build** → **Rebuild Project**
3. Esperar compilación (~30 segundos)
4. **Maven** → **Lifecycle** → **tomcat7:run** (doble click)
5. Esperar: `INFO: Starting ProtocolHandler ["http-bio-8081"]`

**Opción B: Usando Terminal**

```bash
cd "PROYECTO-FINAL-SISTEMA-EDUCATIVO 01/backend"
mvn clean compile package
mvn tomcat7:run
```

### **4️⃣ Acceder al Sistema**

```
http://localhost:8081/sistema-educativo/

Usuario: admin
Contraseña: admin123
```

---

## 🎯 **Probar Funcionalidades**

### **Dashboard:**
```
✅ Ver estadísticas en tiempo real
✅ Gráfico de inscripciones por estado
✅ Período activo destacado
```

### **Estudiantes:**
```
✅ Listar estudiantes (15 cargados)
✅ Buscar por matrícula o nombre
✅ Filtrar por estado (ACTIVO, GRADUADO, etc.)
✅ Ver detalle con inscripciones
```

### **Cursos:**
```
✅ Ver correlatividades del curso
✅ Gestionar correlatividades (agregar/eliminar)
✅ Asignar a período
✅ Ver estudiantes inscritos
```

### **Inscripciones:**
```
✅ Crear nueva (validaciones activas)
✅ Filtrar por curso o estado
✅ Editar estado y nota
```

---

## 🔍 **Verificar en MySQL**

```sql
USE sistema_educativo;

-- Ver estudiantes
SELECT * FROM vista_estudiantes_completos LIMIT 5;

-- Ver inscripciones
SELECT * FROM vista_inscripciones_detalle;

-- Ver correlatividades
SELECT * FROM correlatividades;

-- Ver estadísticas
SELECT * FROM vista_estadisticas_cursos;
```

---

## ❓ **Problemas Comunes**

### **❌ Error 404 en servlet:**
**Solución:** Rebuild Project en IntelliJ

### **❌ Error de conexión a BD:**
**Solución:** Verificar password en ConexionDB.java

### **❌ Error 500:**
**Solución:** Ver logs de Tomcat en consola

---

## 📞 **Ayuda**

Ver documentación completa en `docs/`

---

**¡Listo en 5 minutos! 🎉**


