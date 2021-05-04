#FROM openjdk:8-jdk-alpine
#WORKDIR /usr/backend
#USER root
##RUN addgroup -S spring && adduser -S spring -G spring
##USER spring:spring
#
##ARG JAR_FILE=target/*.jar
##COPY ${JAR_FILE} app.jar
##RUN ls
##RUN mvn package && java -jar target/app.jar
#COPY . /usr/backend
##ENTRYPOINT ["java","-jar","./app.jar"]
#ENTRYPOINT ["./mvnw","spring-boot:run"]
#EXPOSE 8080
#
#RUN /bin/sh

FROM maven:3.5-jdk-8
FROM java:8
WORKDIR /app
COPY ./target/TACS-2021-UTN-0.0.1-SNAPSHOT.jar /app/TACS-2021-UTN-0.0.1-SNAPSHOT.jar.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","TACS-2021-UTN-0.0.1-SNAPSHOT.jar.jar"]