# Etapa 1: build do projeto com Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom e as pastas necessárias
COPY pom.xml .
COPY src ./src

# Faz o build (gera o .jar na pasta target)
RUN mvn clean package -DskipTests

# Etapa 2: imagem leve só com o JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/pecafacil-0.0.1-SNAPSHOT.jar app.jar

# Render define a variável de ambiente PORT; usamos ela aqui
EXPOSE 8080
#ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080} --spring.profiles.active=render"]

