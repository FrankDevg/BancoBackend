# Usar OpenJDK 17 como imagen base
FROM eclipse-temurin:17-jdk

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY target/ms-cuentas-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto 8082
EXPOSE 8082

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
