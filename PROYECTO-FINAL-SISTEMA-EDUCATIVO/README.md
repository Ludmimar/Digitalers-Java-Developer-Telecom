# 🎓 Sistema de Gestión Educativa - Proyecto Final

[![Java](https://img.shields.io/badge/Java-8+-orange?style=flat-square&logo=openjdk)](https://openjdk.java.net/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)](https://www.mysql.com/)
[![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow?style=flat-square&logo=javascript)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)

## 📋 Descripción del Proyecto

**Sistema integral de gestión educativa** desarrollado como proyecto final del curso Java Fullstack Developer. El sistema permite administrar estudiantes, profesores, cursos, inscripciones y calificaciones de una institución educativa.

Este proyecto integra **TODOS los conocimientos adquiridos** en los 26 módulos del curso:
- ✅ Java SE avanzado (POO, Excepciones, Colecciones, Genéricos)
- ✅ JDBC y acceso a bases de datos con patrón DAO
- ✅ MySQL/MariaDB con diseño relacional completo
- ✅ Frontend moderno (HTML5, CSS3, JavaScript)
- ✅ Java I/O para exportación de reportes
- ✅ Patrones de diseño (DAO, Singleton)

## 🎯 Objetivos del Proyecto

1. Demostrar dominio completo del stack Java Fullstack
2. Implementar un sistema real con arquitectura profesional
3. Aplicar mejores prácticas de desarrollo
4. Crear un portfolio destacado para oportunidades laborales

## 🚀 Características Principales

### 👨‍🎓 **Gestión de Estudiantes**
- CRUD completo (Create, Read, Update, Delete)
- Búsqueda por ID, matrícula o documento
- Cálculo automático de edad
- Control de estado académico
- Seguimiento de promedio y créditos

### 👨‍🏫 **Gestión de Profesores**
- Registro de datos personales y laborales
- Asignación de cursos
- Control de especialidades
- Gestión de sueldos

### 📚 **Gestión de Cursos**
- Catálogo de cursos con créditos
- Control de cupos
- Asignación de profesores
- Horarios y aulas

### 📝 **Sistema de Inscripciones**
- Inscripción de estudiantes a cursos
- Validación de cupos disponibles
- Prevención de inscripciones duplicadas
- Seguimiento de estado (Inscrito, Cursando, Aprobado, Reprobado)

### 📊 **Calificaciones y Evaluaciones**
- Registro de evaluaciones parciales y finales
- Cálculo automático de promedios ponderados
- Diferentes tipos de evaluación (Parcial, Final, Trabajo, Proyecto)
- Triggers automáticos para actualizar notas finales

### 📈 **Reportes y Estadísticas**
- Vistas SQL para consultas optimizadas
- Estadísticas por curso (aprobación, promedios)
- Reportes de estudiantes
- Exportación de datos

## 🛠️ Tecnologías Utilizadas

### **Backend**
- **Java SE 8+**: Lenguaje principal
- **JDBC**: Conectividad con base de datos
- **Maven**: Gestión de dependencias
- **Patrón DAO**: Arquitectura de acceso a datos
- **Genéricos**: Interface DAO<K, V>
- **Colecciones**: List, Map, Set

### **Base de Datos**
- **MySQL 8.0** (compatible con MariaDB)
- **MySQL Workbench**: Herramienta de administración
- **Stored Procedures**: Lógica de negocio en BD
- **Triggers**: Automatización de cálculos
- **Views**: Consultas optimizadas
- **Foreign Keys**: Integridad referencial

### **Frontend**
- **HTML5**: Estructura semántica
- **CSS3**: Estilos modernos con Flexbox y Grid
- **JavaScript ES6**: Interactividad
- **Responsive Design**: Compatible con móviles

## 📁 Estructura del Proyecto

```
PROYECTO-FINAL-SISTEMA-EDUCATIVO/
├── backend/
│   ├── src/main/java/com/educacionit/sistemaeducativo/
│   │   ├── entidades/
│   │   │   ├── Persona.java                (abstracta)
│   │   │   ├── Estudiante.java
│   │   │   ├── Profesor.java
│   │   │   ├── Curso.java
│   │   │   └── Inscripcion.java
│   │   ├── dao/
│   │   │   └── DAO.java                    (interface genérica)
│   │   ├── implementaciones/
│   │   │   └── EstudianteDAOImpl.java
│   │   ├── enumerados/
│   │   │   ├── TipoDocumento.java
│   │   │   ├── EstadoAcademico.java
│   │   │   └── EstadoInscripcion.java
│   │   ├── utilidades/
│   │   │   └── ConexionDB.java
│   │   └── principal/
│   │       └── App.java
│   └── pom.xml
├── database/
│   ├── 01_schema.sql                       (esquema completo)
│   └── 02_datos_prueba.sql                 (datos de ejemplo)
├── frontend/
│   ├── index.html
│   ├── css/
│   │   └── styles.css
│   ├── js/
│   │   └── main.js
│   └── assets/
├── docs/
│   └── diagramas/
└── README.md
```

## 🔧 Instalación y Configuración

### Requisitos Previos

- ✅ Java JDK 8 o superior
- ✅ Maven 3.6+
- ✅ MySQL 8.0 o MariaDB 10.5+
- ✅ MySQL Workbench (recomendado)
- ✅ IDE (Eclipse, IntelliJ IDEA o VS Code)

### Paso 1: Clonar el Repositorio

```bash
cd "d:\JAVA DEVELOPERS\Digitalers-Java-Developer-Telecom"
# El proyecto ya está en PROYECTO-FINAL-SISTEMA-EDUCATIVO/
```

### Paso 2: Configurar la Base de Datos

#### Opción A: Usando MySQL Workbench (Recomendado)

1. **Abrir MySQL Workbench**
2. **Conectarse a tu servidor local**
3. **Abrir el archivo** `database/01_schema.sql`
4. **Ejecutar el script completo** (⚡ Execute All)
5. **Abrir el archivo** `database/02_datos_prueba.sql`
6. **Ejecutar el script de datos** (⚡ Execute All)
7. **Verificar**: Deberías ver la base de datos `sistema_educativo` con todas las tablas

#### Opción B: Usando línea de comandos

```bash
# Crear esquema
mysql -u root -p < database/01_schema.sql

# Cargar datos de prueba
mysql -u root -p < database/02_datos_prueba.sql
```

### Paso 3: Configurar Conexión en Java

Editar el archivo `backend/src/main/java/com/educacionit/sistemaeducativo/utilidades/ConexionDB.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";
private static final String USUARIO = "root";
private static final String CLAVE = "tu_password";  // ⚠️ Cambiar por tu password
```

### Paso 4: Compilar y Ejecutar Backend

```bash
cd PROYECTO-FINAL-SISTEMA-EDUCATIVO/backend

# Compilar con Maven
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="com.educacionit.sistemaeducativo.principal.App"
```

### Paso 5: Abrir Frontend

Simplemente abre el archivo `frontend/index.html` en tu navegador favorito.

## 💻 Uso del Sistema

### Backend - Menú Interactivo

Al ejecutar la aplicación Java, verás un menú con las siguientes opciones:

```
╔═══════════════════ MENÚ PRINCIPAL ═══════════════════╗
║                                                      ║
║  1. 📝 Registrar nuevo estudiante                    ║
║  2. 📋 Listar todos los estudiantes                  ║
║  3. 🔍 Buscar estudiante por ID                      ║
║  4. ✏️  Actualizar datos de estudiante                ║
║  5. 🗑️  Eliminar estudiante                           ║
║  6. 🎓 Buscar por matrícula                          ║
║  0. 🚪 Salir                                         ║
║                                                      ║
╚══════════════════════════════════════════════════════╝
```

### Frontend - Interfaz Web

El frontend ofrece:
- 🏠 **Dashboard**: Vista general con estadísticas
- 👨‍🎓 **Estudiantes**: Listado, búsqueda, registro
- 👨‍🏫 **Profesores**: Gestión de profesores
- 📚 **Cursos**: Catálogo de cursos
- 📝 **Inscripciones**: Gestión de inscripciones
- 📊 **Reportes**: Estadísticas y gráficos

## 📊 Modelo de Base de Datos

### Tablas Principales

1. **personas**: Tabla base (herencia)
2. **estudiantes**: Datos académicos
3. **profesores**: Datos laborales
4. **administrativos**: Personal administrativo
5. **cursos**: Catálogo de cursos
6. **periodos_academicos**: Semestres/cuatrimestres
7. **cursos_ofrecidos**: Cursos en periodos específicos
8. **inscripciones**: Relación estudiante-curso
9. **calificaciones**: Evaluaciones parciales
10. **asistencias**: Control de asistencia
11. **logs**: Auditoría del sistema

### Relaciones Clave

- **Persona → Estudiante/Profesor/Administrativo** (Herencia)
- **Curso → Cursos Ofrecidos** (Uno a Muchos)
- **Estudiante → Inscripciones** (Uno a Muchos)
- **Inscripción → Calificaciones** (Uno a Muchos)

## 🎨 Capturas de Pantalla

### Backend - Menú Principal
```
╔════════════════════════════════════════════════════════╗
║       SISTEMA DE GESTIÓN EDUCATIVA                     ║
║       Proyecto Final - Java Fullstack                  ║
║       Desarrollado por: Ludmila Martos                 ║
╚════════════════════════════════════════════════════════╝

✅ Conexión exitosa a la base de datos
```

### Frontend - Dashboard
- Estadísticas en tiempo real
- Tarjetas con información clave
- Diseño moderno y responsive

## 🧪 Testing y Validación

### Datos de Prueba Incluidos

El script `02_datos_prueba.sql` incluye:
- ✅ 5 estudiantes de ejemplo
- ✅ 4 profesores
- ✅ 2 administrativos
- ✅ 8 cursos
- ✅ 7 cursos ofrecidos
- ✅ 19 inscripciones
- ✅ Calificaciones y asistencias de muestra

### Consultas de Verificación

```sql
-- Ver todos los estudiantes
SELECT * FROM vista_estudiantes;

-- Ver estadísticas de cursos
SELECT * FROM vista_estadisticas_cursos;

-- Ver inscripciones con detalles
SELECT * FROM vista_inscripciones_detalle;
```

## 📚 Conceptos Aplicados del Curso

### Módulos 20-23: Java Avanzado
- ✅ Herencia (Persona → Estudiante/Profesor)
- ✅ Polimorfismo
- ✅ Clases abstractas
- ✅ Interfaces
- ✅ Excepciones personalizadas
- ✅ Try-with-resources

### Módulo 24: Colecciones y Genéricos
- ✅ List<Estudiante> para listados
- ✅ Map<String, Object> para búsquedas
- ✅ Set para IDs únicos
- ✅ Genéricos en DAO<K, V>
- ✅ Enumerados (TipoDocumento, Estados)

### Módulo 25: JDBC y Bases de Datos
- ✅ Connection, PreparedStatement, ResultSet
- ✅ Patrón DAO implementado
- ✅ Transacciones (commit/rollback)
- ✅ Prevención de SQL Injection
- ✅ getGeneratedKeys() para IDs

### Módulo 26: Java I/O
- ✅ BufferedReader/Writer para archivos
- ✅ Sistema de logging
- ✅ Exportación de reportes
- ✅ Try-with-resources

### Módulos 06-09: Frontend
- ✅ HTML5 semántico
- ✅ CSS3 con Flexbox y Grid
- ✅ JavaScript para interactividad
- ✅ Diseño responsive

### Módulos 10-14: SQL Avanzado
- ✅ Diseño relacional completo
- ✅ Foreign Keys e índices
- ✅ Stored Procedures
- ✅ Triggers automáticos
- ✅ Vistas para reportes
- ✅ Funciones personalizadas

## 🔐 Seguridad y Mejores Prácticas

### Implementadas

✅ **PreparedStatement** para prevenir SQL Injection
✅ **Transacciones** para operaciones atómicas
✅ **Try-with-resources** para gestión de recursos
✅ **Validación de datos** en frontend y backend
✅ **Cascade DELETE** para integridad referencial
✅ **Índices** para optimizar consultas
✅ **Logging** de operaciones importantes

### Recomendaciones para Producción

- 🔒 Implementar autenticación (login/password)
- 🔒 Encriptar contraseñas (BCrypt)
- 🔒 Usar variables de entorno para credenciales
- 🔒 Implementar roles y permisos
- 🔒 HTTPS para comunicación segura
- 🔒 Validación server-side robusta

## 📈 Funcionalidades Avanzadas

### Stored Procedures

```sql
-- Inscribir estudiante (con validación de cupos)
CALL sp_inscribir_estudiante(1, 1, @resultado);

-- Calcular promedio automáticamente
CALL sp_calcular_promedio_estudiante(1, @promedio);
```

### Triggers Automáticos

- 📊 Actualización automática de promedio al registrar calificación
- 📊 Cálculo de porcentaje de asistencia
- 📝 Logging automático de inscripciones

### Vistas Optimizadas

- 👁️ `vista_estudiantes`: Join de estudiantes con personas
- 👁️ `vista_profesores`: Profesores con cantidad de cursos
- 👁️ `vista_inscripciones_detalle`: Información completa
- 👁️ `vista_estadisticas_cursos`: Métricas por curso

## 🎓 Casos de Uso

### 1. Registrar Nuevo Estudiante
```
Entrada: Datos personales y académicos
Proceso: 
  1. Validar datos
  2. Insertar en tabla personas (con transacción)
  3. Insertar en tabla estudiantes
  4. Obtener IDs generados
Salida: Confirmación y matrícula asignada
```

### 2. Inscribir a Curso
```
Entrada: ID estudiante + ID curso ofrecido
Proceso:
  1. Verificar si ya está inscrito
  2. Verificar cupos disponibles
  3. Crear inscripción
  4. Actualizar cupos
Salida: Confirmación o mensaje de error
```

### 3. Registrar Calificación
```
Entrada: Nota y tipo de evaluación
Proceso:
  1. Insertar calificación
  2. Trigger calcula promedio ponderado
  3. Actualizar nota final en inscripción
  4. Actualizar estado (APROBADO/REPROBADO)
Salida: Nota registrada y promedio actualizado
```

## 📖 Guía de Uso

### Para MySQL Workbench

1. **Abrir MySQL Workbench**
2. **Conectarse** a tu servidor local (localhost:3306)
3. **File → Open SQL Script** → Seleccionar `01_schema.sql`
4. **Ejecutar** el script completo (Lightning icon o Ctrl+Shift+Enter)
5. **Verificar** que se creó la base de datos `sistema_educativo`
6. **Abrir y ejecutar** `02_datos_prueba.sql`
7. **Explorar** las tablas creadas en el panel izquierdo

### Para Desarrolladores

```bash
# 1. Configurar base de datos (ver arriba)

# 2. Compilar backend
cd backend
mvn clean install

# 3. Ejecutar aplicación
mvn exec:java

# 4. Abrir frontend
# Doble click en frontend/index.html
```

## 🏆 Logros Técnicos

### Arquitectura
- ✅ Separación en capas (Entidades, DAO, Principal)
- ✅ Patrón DAO implementado correctamente
- ✅ Genéricos para código reutilizable
- ✅ Herencia multinivel (Persona → subclases)

### Base de Datos
- ✅ Diseño normalizado (3FN)
- ✅ 11 tablas relacionadas
- ✅ 3 Stored Procedures
- ✅ 3 Triggers automáticos
- ✅ 4 Vistas optimizadas
- ✅ 3 Funciones personalizadas

### Código Java
- ✅ 10+ clases bien estructuradas
- ✅ Manejo robusto de excepciones
- ✅ Uso de LocalDate (Java 8)
- ✅ PreparedStatement en todos los DAOs
- ✅ Transacciones con commit/rollback
- ✅ Documentación JavaDoc completa

### Frontend
- ✅ HTML5 semántico
- ✅ CSS Grid y Flexbox
- ✅ Diseño responsive
- ✅ JavaScript moderno
- ✅ Validación de formularios

## 🚀 Próximas Mejoras

### Corto Plazo
- [ ] Implementar CursoDAO y ProfesorDAO
- [ ] Agregar módulo de calificaciones en frontend
- [ ] Sistema de login con autenticación
- [ ] Exportación de reportes a PDF

### Mediano Plazo
- [ ] API REST para integración
- [ ] Dashboard con gráficos (Chart.js)
- [ ] Notificaciones en tiempo real
- [ ] Migrar a Spring Boot

### Largo Plazo
- [ ] Aplicación móvil (Android)
- [ ] Microservicios
- [ ] Cloud deployment
- [ ] Integración con IA para recomendaciones

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

**Formación**: Java Fullstack Developer - Digitalers Telecom
**Módulos Completados**: 26/26 (100%)
**Proyecto Final**: Sistema de Gestión Educativa

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

---

## 🎉 Agradecimientos

Gracias al programa **Digitalers** por la formación completa en Java Fullstack Development.

Este proyecto representa **6 meses de aprendizaje** y **150+ horas** de estudio intensivo, aplicando conceptos de:
- Programación Orientada a Objetos
- Bases de Datos Relacionales
- Desarrollo Web Full Stack
- Patrones de Diseño
- Arquitectura de Software

---

**⭐ Si te gusta este proyecto, dale una estrella en GitHub!**

**Desarrollado con ❤️ y ☕ por Ludmila Martos**


