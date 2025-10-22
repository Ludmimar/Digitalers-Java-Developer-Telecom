# Etapa 1: Build con Maven
FROM maven:3.8.6-openjdk-11 AS builder

# Autor
LABEL maintainer="Ludmila Martos <ludmilamartos@gmail.com>"
LABEL description="Sistema de Gestión Educativa v2.0"

# Establecer directorio de trabajo
WORKDIR /build

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto y generar WAR
RUN mvn clean package -DskipTests

# Etapa 2: Runtime con Tomcat
FROM tomcat:9-jdk11-openjdk

# Eliminar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el archivo WAR desde la etapa de build
COPY --from=builder /build/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto 8080
EXPOSE 8080

# Variables de entorno por defecto (se sobrescriben con Render)
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=sistema_educativo
ENV DB_USER=root
ENV DB_PASSWORD=password

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]

