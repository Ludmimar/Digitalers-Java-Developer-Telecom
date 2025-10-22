# 🚀 Guía de Inicio Rápido

## ⚡ En 5 Minutos

### 1️⃣ Configurar Base de Datos (2 minutos)

**Abrir MySQL Workbench:**

1. Abrir **MySQL Workbench**
2. Conectarse a **localhost** (usuario: root)
3. **File → Open SQL Script**
4. Seleccionar `database/01_schema.sql`
5. Click en **⚡ Execute** (rayo) o `Ctrl+Shift+Enter`
6. Esperar mensaje: ✅ "Base de datos sistema_educativo creada exitosamente!"
7. Repetir con `database/02_datos_prueba.sql`

### 2️⃣ Configurar Java (1 minuto)

**Editar archivo de conexión:**

Abrir: `backend/src/main/java/com/educacionit/sistemaeducativo/utilidades/ConexionDB.java`

```java
// Línea 16-18: Cambiar tu password de MySQL
private static final String USUARIO = "root";
private static final String CLAVE = "";  // ⬅️ Poner tu password aquí
```

### 3️⃣ Ejecutar Backend (1 minuto)

**En terminal:**

```bash
cd PROYECTO-FINAL-SISTEMA-EDUCATIVO/backend
mvn clean compile
mvn exec:java
```

**Resultado esperado:**
```
╔════════════════════════════════════════════════════════╗
║       SISTEMA DE GESTIÓN EDUCATIVA                     ║
╚════════════════════════════════════════════════════════╝

✅ Conexión exitosa a la base de datos

[Menú interactivo]
```

### 4️⃣ Ver Frontend (1 minuto)

**Doble click en:**
```
frontend/index.html
```

Se abrirá en tu navegador con el dashboard del sistema.

---

## 🎯 Probar Funcionalidades

### Opción 1: Listar Estudiantes
```
Menú → Opción 2
```
Verás los 5 estudiantes de prueba.

### Opción 2: Buscar por Matrícula
```
Menú → Opción 6
Ingresar: EST-2024-001
```
Verás los detalles de Juan Pérez.

### Opción 3: Registrar Nuevo Estudiante
```
Menú → Opción 1
Seguir las instrucciones en pantalla
```

---

## 🔍 Verificar en MySQL Workbench

```sql
-- Ver todos los estudiantes
SELECT * FROM vista_estudiantes;

-- Ver inscripciones
SELECT * FROM vista_inscripciones_detalle;

-- Ver estadísticas
SELECT * FROM vista_estadisticas_cursos;
```

---

## ❓ Problemas Comunes

### ❌ Error de Conexión

**Problema**: No se puede conectar a la base de datos

**Solución**:
1. Verificar que MySQL esté corriendo
2. Revisar usuario y contraseña en `ConexionDB.java`
3. Verificar que la BD `sistema_educativo` exista

### ❌ Driver no encontrado

**Problema**: ClassNotFoundException

**Solución**:
```bash
mvn clean install
```

### ❌ Tabla no existe

**Problema**: Table doesn't exist

**Solución**:
Ejecutar nuevamente `01_schema.sql` en MySQL Workbench

---

## 📞 Ayuda

**Desarrollador**: Ludmila Martos
**Email**: ludmilamartos@gmail.com

---

¡Listo! Ya puedes usar el sistema. 🎉


