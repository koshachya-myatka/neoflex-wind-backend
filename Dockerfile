FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar ./neoflex-wind-backend.jar
ENTRYPOINT ["java", "-jar", "neoflex-wind-backend.jar"]