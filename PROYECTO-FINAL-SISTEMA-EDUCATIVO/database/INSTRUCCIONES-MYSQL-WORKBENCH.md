# 🐬 Guía Completa para MySQL Workbench

## 📥 Instalación de MySQL Workbench

Si aún no tienes MySQL Workbench instalado:

1. Descargar de: https://dev.mysql.com/downloads/workbench/
2. Elegir tu sistema operativo (Windows)
3. Instalar MySQL Server si no lo tienes
4. Instalar MySQL Workbench

## 🔌 Conectarse a MySQL

### Primera Vez

1. **Abrir MySQL Workbench**
2. En la pantalla principal verás "MySQL Connections"
3. Click en **"+"** para nueva conexión
4. Configurar:
   - **Connection Name**: Local MySQL
   - **Hostname**: localhost
   - **Port**: 3306
   - **Username**: root
   - **Password**: Click en "Store in Keychain" y poner tu password
5. Click en **"Test Connection"**
6. Si sale ✅ "Successfully connected", click en **OK**
7. Ahora haz doble click en la conexión creada

### Ya Configurado

- Doble click en tu conexión "Local MySQL"
- Ingresa password si lo pide

## 📝 Ejecutar Scripts SQL

### Método 1: Abrir y Ejecutar Archivo (Recomendado)

1. **File → Open SQL Script** (Ctrl+Shift+O)
2. Navegar a: `PROYECTO-FINAL-SISTEMA-EDUCATIVO/database/`
3. Seleccionar `01_schema.sql`
4. Click en **⚡ Execute** (rayo amarillo) o `Ctrl+Shift+Enter`
5. Ver resultado en panel "Output"
6. Deberías ver:
   ```
   11 tablas creadas
   3 vistas creadas
   3 stored procedures creados
   ✅ Base de datos sistema_educativo creada exitosamente!
   ```

7. Repetir con `02_datos_prueba.sql`

### Método 2: Copiar y Pegar

1. Abrir el archivo SQL en un editor de texto
2. Copiar todo el contenido (Ctrl+A, Ctrl+C)
3. En MySQL Workbench, pegar en una nueva pestaña
4. Click en ⚡ Execute All

## 🔍 Explorar la Base de Datos

### Ver Tablas Creadas

1. En el panel izquierdo "SCHEMAS"
2. Expandir **sistema_educativo**
3. Expandir **Tables**
4. Verás las 11 tablas:
   - personas
   - estudiantes
   - profesores
   - administrativos
   - cursos
   - periodos_academicos
   - cursos_ofrecidos
   - inscripciones
   - calificaciones
   - asistencias
   - logs

### Ver Datos de una Tabla

**Opción A**: Click derecho en tabla → **Select Rows - Limit 1000**

**Opción B**: Escribir consulta:
```sql
USE sistema_educativo;
SELECT * FROM estudiantes;
```

### Ver Estructura de una Tabla

Click derecho en tabla → **Table Inspector** → **Columns**

## 📊 Consultas Útiles en Workbench

### Ver Todos los Estudiantes

```sql
SELECT * FROM vista_estudiantes;
```

### Ver Inscripciones Detalladas

```sql
SELECT * FROM vista_inscripciones_detalle;
```

### Buscar un Estudiante

```sql
SELECT * FROM vista_estudiantes
WHERE nombre LIKE '%Juan%' OR apellido LIKE '%Pérez%';
```

### Ver Cursos con Cupos

```sql
SELECT 
    codigo_curso,
    nombre,
    cupos_disponibles
FROM cursos_ofrecidos co
INNER JOIN cursos c ON co.curso_id = c.id
WHERE cupos_disponibles > 0;
```

## 🛠️ Usar Stored Procedures

### Inscribir un Estudiante

```sql
-- Declarar variable para resultado
SET @resultado = '';

-- Llamar al procedure
CALL sp_inscribir_estudiante(1, 1, @resultado);

-- Ver resultado
SELECT @resultado AS Resultado;
```

### Calcular Promedio

```sql
SET @promedio = 0.0;
CALL sp_calcular_promedio_estudiante(1, @promedio);
SELECT @promedio AS 'Promedio del Estudiante';
```

## 📈 Ver Diagramas ER

1. En el menú: **Database → Reverse Engineer**
2. Seleccionar tu conexión
3. Next → Next
4. Seleccionar **sistema_educativo**
5. Next → Execute
6. Verás el diagrama ER completo con todas las relaciones

## 🔧 Herramientas Útiles de Workbench

### Query History
- **View → Panels → History**
- Ver todas las consultas ejecutadas

### Output Panel
- **View → Panels → Output**
- Ver resultados de scripts

### SQL Additions
- **Edit → Format → Beautify Query**
- Formatear SQL automáticamente

### Export Data
- Click derecho en tabla → **Table Data Export Wizard**
- Exportar a CSV, JSON, XML

## 💡 Tips y Trucos

### Autocompletar
- Empezar a escribir nombre de tabla
- Presionar `Ctrl+Space` para autocompletar

### Ejecutar Solo una Línea
- Colocar cursor en la línea
- Presionar `Ctrl+Enter`

### Comentar/Descomentar
- Seleccionar líneas
- `Ctrl+/` para comentar/descomentar

### Formato Automático
- `Ctrl+B` para formatear SQL

## ⚠️ Solución de Problemas

### Error: Access Denied

**Problema**: No puedes conectarte

**Solución**:
1. Verificar usuario y password
2. Asegurarte que MySQL Server esté corriendo
3. En Windows: Services → MySQL → Start

### Error: Unknown Database

**Problema**: Base de datos no existe

**Solución**:
Ejecutar primero el script `01_schema.sql`

### Error: Syntax Error

**Problema**: Error en SQL

**Solución**:
1. Verificar que hayas seleccionado la BD: `USE sistema_educativo;`
2. Ejecutar línea por línea para encontrar error
3. Revisar versión de MySQL (debe ser 8.0+)

## 📚 Recursos Adicionales

### Documentación MySQL
- https://dev.mysql.com/doc/workbench/en/

### Tutoriales en Video
- Buscar "MySQL Workbench tutorial español" en YouTube

### Shortcuts Útiles
- `Ctrl+Enter`: Ejecutar statement actual
- `Ctrl+Shift+Enter`: Ejecutar todo el script
- `Ctrl+T`: Nueva pestaña SQL
- `Ctrl+W`: Cerrar pestaña
- `Ctrl+N`: Nueva conexión

---

## 📞 Contacto

**Desarrollador**: Ludmila Martos  
**Email**: ludmilamartos@gmail.com

---

¡Éxito con tu proyecto! 🎓✨


