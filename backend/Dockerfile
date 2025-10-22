# Usar imagen de Tomcat 9 con JDK 11
FROM tomcat:9-jdk11-openjdk

# Autor
LABEL maintainer="Ludmila Martos <ludmilamartos@gmail.com>"
LABEL description="Sistema de Gestión Educativa v2.0"

# Eliminar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el archivo WAR al directorio de webapps de Tomcat
COPY target/sistema-educativo.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto 8080 (Render lo mapeará automáticamente)
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]

