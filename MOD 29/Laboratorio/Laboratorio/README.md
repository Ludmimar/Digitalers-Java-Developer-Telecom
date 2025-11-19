# Laboratorio (MOD 29)

## Resumen
Proyecto Maven con modelo de dominio (Persona, Empleado, Profesor, etc.), comparadores, excepciones y DAO/MariaDB.

## Requisitos
- JDK 11+
- Maven 3.6+

## Configuración
Edita `src/main/resources/database.properties` con tus credenciales.

## Build y ejecución
```bash
mvn clean package
mvn -q exec:java -Dexec.mainClass="com.educacionIT.javase.principal.App"
```

Ajusta `-Dexec.mainClass` si cambia el paquete/clase principal.




