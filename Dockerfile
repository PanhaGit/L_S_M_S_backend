FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar /app/L_S_M_S-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","jar","L_S_M_S-0.0.1-SNAPSHOT.jar"]