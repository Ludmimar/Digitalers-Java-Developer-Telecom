# 🏗️ Arquitectura del Sistema

## 📐 Diagrama de Capas

```
┌─────────────────────────────────────────────────────────┐
│                    CAPA PRESENTACIÓN                    │
│        (Frontend - HTML/CSS/JavaScript)                 │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐             │
│  │Dashboard │  │Estudiantes│  │ Cursos   │             │
│  └──────────┘  └──────────┘  └──────────┘             │
└─────────────────────────────────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│                   CAPA DE APLICACIÓN                    │
│              (Backend - Java Console)                   │
│  ┌──────────────────────────────────────────────────┐  │
│  │           App.java (Main Class)                  │  │
│  │  - Menú interactivo                              │  │
│  │  - Validación de entrada                         │  │
│  │  - Coordinación de operaciones                   │  │
│  └──────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│                  CAPA DE NEGOCIO                        │
│              (Entidades y Lógica)                       │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐             │
│  │ Persona  │  │Estudiante│  │ Profesor │             │
│  │(abstract)│──│          │  │          │             │
│  └──────────┘  └──────────┘  └──────────┘             │
│  ┌──────────┐  ┌──────────┐                            │
│  │  Curso   │  │Inscripción│                           │
│  └──────────┘  └──────────┘                            │
└─────────────────────────────────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│              CAPA DE ACCESO A DATOS                     │
│                  (Patrón DAO)                           │
│  ┌──────────────────────────────────────────────────┐  │
│  │    DAO<K, V> Interface Genérica                  │  │
│  └──────────────────────────────────────────────────┘  │
│           ▼                    ▼                        │
│  ┌──────────────┐    ┌──────────────┐                  │
│  │EstudianteDAO │    │ ProfesorDAO  │                  │
│  │   Impl       │    │    Impl      │                  │
│  └──────────────┘    └──────────────┘                  │
│  - PreparedStatement                                    │
│  - Try-with-resources                                   │
│  - Transacciones                                        │
└─────────────────────────────────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│                CAPA DE PERSISTENCIA                     │
│             (MySQL/MariaDB + JDBC)                      │
│  ┌──────────────────────────────────────────────────┐  │
│  │         ConexionDB (Singleton)                   │  │
│  │  - DriverManager                                 │  │
│  │  - Connection pooling                            │  │
│  └──────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ▼
┌─────────────────────────────────────────────────────────┐
│                  BASE DE DATOS                          │
│                (sistema_educativo)                      │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐             │
│  │ Personas │──│Estudiantes│  │ Cursos   │             │
│  └──────────┘  └──────────┘  └──────────┘             │
│  - 11 Tablas                                            │
│  - Stored Procedures                                    │
│  - Triggers                                             │
│  - Views                                                │
└─────────────────────────────────────────────────────────┘
```

## 🔄 Flujo de Datos

### Ejemplo: Registrar Estudiante

```
Usuario (Frontend/Console)
    ↓
    Ingresa datos
    ↓
App.java (Validación)
    ↓
    Crea objeto Estudiante
    ↓
EstudianteDAOImpl
    ↓
    PreparedStatement (INSERT)
    ↓
ConexionDB (JDBC)
    ↓
    Transaction BEGIN
    ↓
MySQL - Tabla personas (INSERT)
    ↓
    RETURN generated ID
    ↓
MySQL - Tabla estudiantes (INSERT)
    ↓
    Transaction COMMIT
    ↓
Retorna ID al DAO
    ↓
Actualiza objeto Estudiante
    ↓
Confirma a Usuario
```

## 🎯 Patrones de Diseño Aplicados

### 1. DAO (Data Access Object)
```
Interface DAO<K, V>
    ↓ implements
EstudianteDAOImpl
ProfesorDAOImpl
CursoDAOImpl
```

**Beneficios**:
- Separación de responsabilidades
- Código reutilizable
- Fácil testing
- Cambio de BD sin afectar lógica

### 2. Singleton (ConexionDB)
```
ConexionDB (una sola instancia)
    - getConexion()
    - cerrarConexion()
```

**Beneficios**:
- Una sola conexión
- Reutilización eficiente
- Control centralizado

### 3. Herencia (POO)
```
Persona (abstract)
    ├── Estudiante
    ├── Profesor
    └── Administrativo
```

**Beneficios**:
- Código DRY
- Polimorfismo
- Extensibilidad

### 4. Template Method
```
DAO<K, V>
    - buscarPorID()
    - insertar()
    - actualizar()
    - eliminar()
    - listar()
```

## 📊 Modelo Entidad-Relación Simplificado

```
PERSONAS (1) ──┐
               ├── (1:1) ESTUDIANTES
               ├── (1:1) PROFESORES
               └── (1:1) ADMINISTRATIVOS

CURSOS (1) ────── (1:N) CURSOS_OFRECIDOS

CURSOS_OFRECIDOS (1) ──┬── (N:1) PERIODOS_ACADEMICOS
                       └── (N:1) PROFESORES

ESTUDIANTES (1) ────── (1:N) INSCRIPCIONES

CURSOS_OFRECIDOS (1) ── (1:N) INSCRIPCIONES

INSCRIPCIONES (1) ──┬── (1:N) CALIFICACIONES
                    └── (1:N) ASISTENCIAS
```

## 🔐 Seguridad Implementada

### Nivel Base de Datos
- ✅ Foreign Keys con CASCADE
- ✅ Constraints y validaciones
- ✅ Índices para performance
- ✅ Triggers para integridad

### Nivel Aplicación
- ✅ PreparedStatement (anti SQL Injection)
- ✅ Transacciones (atomicidad)
- ✅ Validación de datos
- ✅ Manejo de excepciones

### Nivel Frontend
- ✅ Validación de formularios
- ✅ Sanitización de input
- ✅ Pattern matching

## 📦 Componentes del Sistema

### Backend Java
- 📂 **entidades**: 5 clases (Persona, Estudiante, Profesor, Curso, Inscripcion)
- 📂 **dao**: 1 interface genérica
- 📂 **implementaciones**: 1+ DAOs
- 📂 **enumerados**: 3 enums
- 📂 **utilidades**: ConexionDB
- 📂 **principal**: App con menú

### Base de Datos
- 🗄️ **11 tablas** relacionadas
- 🔧 **3 stored procedures**
- ⚡ **3 triggers** automáticos
- 👁️ **4 vistas** optimizadas
- 📊 **3 funciones** personalizadas

### Frontend
- 🌐 **1 página principal** (index.html)
- 🎨 **CSS moderno** con Grid/Flexbox
- ⚙️ **JavaScript** para interactividad
- 📱 **Responsive design**

## 🎓 Conceptos del Curso Aplicados

| Módulo | Concepto | Aplicado en |
|--------|----------|-------------|
| MOD 20 | Variables y operadores | Todo el código |
| MOD 21 | POO básica | Clases y objetos |
| MOD 22 | Herencia y polimorfismo | Persona → subclases |
| MOD 23 | Excepciones | Try-catch, SQLException |
| MOD 24 | Colecciones y Genéricos | DAO<K,V>, List, Map |
| MOD 25 | JDBC y DAO | Toda la capa de datos |
| MOD 26 | Java I/O | Logs y exportación |
| MOD 06-09 | Frontend | HTML/CSS/JS |
| MOD 10-14 | SQL | Toda la BD |

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos  
**Proyecto**: Final Java Fullstack Developer  
**Fecha**: Diciembre 2024  

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

*Proyecto desarrollado con ❤️ y ☕ aplicando 26 módulos de conocimiento en Java Fullstack*


