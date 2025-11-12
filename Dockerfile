# Usamos Java 21 LTS
FROM eclipse-temurin:21-jdk-jammy

# Carpeta de trabajo
WORKDIR /app

# Copiamos cualquier jar que esté en target como taskmanager.jar
COPY target/*.jar taskmanager.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Ejecutamos la app
ENTRYPOINT ["java", "-jar", "taskmanager.jar"]