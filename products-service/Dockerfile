FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src
ARG ENV=qa
RUN ./mvnw clean package -DskipTests -P${ENV}

FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ARG ENV=qa
ENV TZ=America/Bogota

EXPOSE 8080

ENTRYPOINT [ "java", "-Xms256m", "-Xmx512m", "-jar", "app.jar" ]
