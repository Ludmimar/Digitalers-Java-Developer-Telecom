# Laboratorio 01 - Sistema de Gestión de Personal Educativo

## 📋 Descripción del Proyecto

Este proyecto es un **sistema de gestión de personal educativo** desarrollado en Java que implementa conceptos avanzados de programación orientada a objetos como herencia, interfaces, comparadores, enumerados y manejo de excepciones. El sistema permite el ingreso y gestión de empleados administrativos con persistencia en base de datos MariaDB.

## 🏗️ Arquitectura del Sistema

### Estructura de Clases

```
Persona (abstracta)
├── Empleado (abstracta)
│   └── Administrativo (concreta)
└── Documento (final)
```

### Jerarquía de Herencia

- **`Persona`**: Clase abstracta base que define los atributos comunes (nombre, apellido, documento, fecha de nacimiento)
- **`Empleado`**: Clase abstracta que extiende `Persona` y agrega atributos específicos de empleados (fecha de cargo, sueldo)
- **`Administrativo`**: Clase concreta que implementa la funcionalidad específica del personal administrativo

## 📁 Estructura del Proyecto

```
src/main/java/com/educacionIT/javase/
├── comparadores/          # Comparadores para ordenamiento
│   ├── OrdenEdadDesc.java
│   └── OrdenDocumento.java
├── entidades/             # Clases del modelo de datos
│   ├── Persona.java
│   ├── Empleado.java
│   ├── Administrativo.java
│   └── Documento.java
├── enumerados/            # Tipos enumerados
│   └── TiposDocumento.java
├── excepciones/           # Manejo de excepciones
│   └── ExcepcionPersona.java
├── interfaces/            # Interfaces del sistema
│   ├── Persistencia.java
│   ├── UtilidadesFecha.java
│   └── Constantes.java
└── principal/             # Clase principal de la aplicación
    └── App.java
```

## 🔧 Componentes Principales

### 1. Entidades

#### `Persona` (Clase Abstracta)
- **Propósito**: Clase base que define los atributos comunes de todas las personas
- **Atributos**:
  - `nombre`: String
  - `apellido`: String
  - `documento`: Documento
  - `fechaNacimiento`: Date
- **Métodos**:
  - `mostrarTipoPersona()`: Método abstracto implementado por las subclases
  - `toString()`: Representación en cadena del objeto
  - Getters y setters para todos los atributos

#### `Empleado` (Clase Abstracta)
- **Propósito**: Extiende `Persona` agregando atributos específicos de empleados
- **Atributos adicionales**:
  - `fechaCargo`: Date
  - `sueldo`: Float

#### `Administrativo` (Clase Concreta)
- **Propósito**: Implementación específica para personal administrativo
- **Funcionalidades**:
  - Implementa `mostrarTipoPersona()`
  - Implementa métodos de `Persistencia` (guardar, eliminar, modificar)

#### `Documento` (Clase Final)
- **Propósito**: Representa un documento de identidad
- **Atributos**:
  - `tipo`: TiposDocumento (enum)
  - `numero`: Integer
- **Características**: Clase `final` que no puede ser extendida

### 2. Comparadores

#### `OrdenEdadDesc`
- **Propósito**: Ordena personas por fecha de nacimiento (descendente)
- **Implementación**: `Comparator<Persona>`
- **Lógica**: Compara fechas de nacimiento usando `compareTo()`

#### `OrdenDocumento`
- **Propósito**: Ordena personas por tipo de documento y número
- **Implementación**: `Comparator<Persona>`
- **Lógica**:
  1. Primero compara el tipo de documento
  2. Si son iguales, compara los números

### 3. Interfaces

#### `Persistencia`
- **Propósito**: Define operaciones CRUD básicas
- **Métodos**:
  - `guardar()`
  - `eliminar()`
  - `modificar()`

#### `UtilidadesFecha`
- **Propósito**: Proporciona utilidades para manejo de fechas
- **Funcionalidades**:
  - Conversión de String a Date
  - Conversión de Date a String
  - Formateo para SQL
- **Patrones**:
  - `dd/MM/yyyy` para interfaz de usuario
  - `yyyy-MM-dd` para base de datos

#### `Constantes`
- **Propósito**: Define constantes del sistema
- **Contenido**:
  - Tipos de documento válidos
  - Mapa de cursos disponibles

### 4. Enumerados

#### `TiposDocumento`
- **Valores**:
  - `DNI`: Documento Nacional de Identidad
  - `PAS`: Pasaporte
  - `LE`: Libreta de Enrolamiento
  - `CI`: Cédula de Identidad
- **Características**: Cada valor tiene una descripción asociada

### 5. Excepciones

#### `ExcepcionPersona`
- **Propósito**: Manejo de errores específicos del sistema de personas
- **Funcionalidades**:
  - Códigos de error personalizados
  - Mensajes descriptivos según el código

## 🚀 Funcionalidad de la Aplicación

### Flujo Principal (`App.java`)

1. **Ingreso de Datos**:
   - Solicita nombre, apellido, tipo de documento, número de documento
   - Valida el tipo de documento usando el enum `TiposDocumento`
   - Solicita fecha de nacimiento y fecha de cargo con validación de formato
   - Solicita el sueldo

2. **Creación del Objeto**:
   - Instancia un objeto `Administrativo` con los datos ingresados
   - Utiliza el patrón de composición con `Documento`

3. **Persistencia**:
   - Conecta a la base de datos MariaDB
   - Inserta el empleado en la tabla `Empleados`
   - Utiliza utilidades de fecha para formateo SQL

### Características Técnicas

- **Validación de Entrada**: Manejo de excepciones para tipos de documento inválidos
- **Formateo de Fechas**: Conversión automática entre formatos de usuario y SQL
- **Conexión a BD**: Uso de JDBC con MariaDB
- **Manejo de Recursos**: Try-with-resources para conexiones de base de datos

## 🛠️ Tecnologías Utilizadas

- **Java 8**: Lenguaje de programación
- **Maven**: Gestión de dependencias y construcción
- **MariaDB**: Base de datos relacional
- **JUnit 4**: Framework de testing
- **JDBC**: API para conectividad con base de datos

## 📦 Dependencias

```xml
<dependencies>
    <!-- JUnit para testing -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Driver MariaDB -->
    <dependency>
        <groupId>org.mariadb.jdbc</groupId>
        <artifactId>mariadb-java-client</artifactId>
        <version>2.7.2</version>
    </dependency>
</dependencies>
```

## 🗄️ Base de Datos

### Configuración
- **Motor**: MariaDB
- **Puerto**: 3306
- **Base de Datos**: `sistemaEducacionIT`
- **Usuario**: `root`
- **Contraseña**: (vacía)

### Tabla Empleados
```sql
CREATE TABLE Empleados (
    TipoDocumento VARCHAR(10),
    NumeroDocumento INT,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    FechaNacimiento DATE,
    FechaCargo DATE,
    sueldo FLOAT,
    Tipo INT
);
```

## 🏃‍♂️ Cómo Ejecutar

### Prerrequisitos
1. Java 8 o superior
2. Maven 3.6 o superior
3. MariaDB instalado y ejecutándose
4. Base de datos `sistemaEducacionIT` creada

### Pasos de Ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone <repository-url>
   cd Laboratorio-01
   ```

2. **Compilar el proyecto**:
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.educacionIT.javase.principal.App"
   ```

4. **Ejecutar tests**:
   ```bash
   mvn test
   ```

## 📝 Ejemplo de Uso

```
Ingreso de Datos de Administrador Laboratorio01

Ingrese el Nombre de la Persona : Juan
Ingrese el Apellido de la Persona : Pérez
Ingrese el Tipo de Documento de la Persona : DNI
Ingrese el Numero de Documento de la Persona : 12345678
Ingrese la Fecha de Nacimiento de la Persona : 
Formato Fecha[dd/mm/aaaa]: 15/03/1985
Ingrese la Fecha de inicio del Cargo de la Persona : 
Formato Fecha[dd/mm/aaaa]: 01/01/2020
Ingrese el Sueldo de la Persona : 50000.0
```

## 🎯 Conceptos de POO Implementados

1. **Herencia**: Jerarquía Persona → Empleado → Administrativo
2. **Polimorfismo**: Método abstracto `mostrarTipoPersona()`
3. **Encapsulación**: Atributos privados con getters/setters
4. **Abstracción**: Clases abstractas e interfaces
5. **Composición**: Clase `Documento` como componente de `Persona`
6. **Comparadores**: Implementación de `Comparator<T>` para ordenamiento
7. **Enumerados**: Tipos de documento con valores predefinidos
8. **Excepciones**: Manejo personalizado de errores
9. **Interfaces**: Contratos para funcionalidades específicas

## 🔍 Características Destacadas

- **Diseño Orientado a Objetos**: Implementación completa de principios SOLID
- **Manejo de Fechas**: Utilidades para diferentes formatos
- **Persistencia**: Integración con base de datos relacional
- **Validación**: Manejo robusto de entrada de datos
- **Extensibilidad**: Fácil agregado de nuevos tipos de empleados
- **Mantenibilidad**: Código bien estructurado y documentado

## 📚 Notas de Desarrollo

Este proyecto forma parte del **Módulo 25 - Laboratorio 01** del curso de Java Developer de EducaciónIT, enfocado en la implementación de conceptos avanzados de programación orientada a objetos y el desarrollo de sistemas de gestión empresarial.

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)


---

*Para más información sobre el proyecto o consultas técnicas, contactar al equipo de desarrollo.*
