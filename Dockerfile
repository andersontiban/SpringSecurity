#FROM openjdk:17
#
#ARG JAR_FILE=target/*.jar
#
#COPY ./target/security-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]

#Uncomment above if going to do docker compose

#Uncomment below if going to deploy to Render

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/SpringSecurity-0.0.1-SNAPSHOT.jar SpringSecurity.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","SpringSecurity.jar"]
