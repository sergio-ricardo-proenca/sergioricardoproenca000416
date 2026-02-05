# Estágio 1: Build (Compilação)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução) - Usando Eclipse Temurin que é o padrão atual
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia o JAR do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]