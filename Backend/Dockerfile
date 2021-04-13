FROM openjdk:8-jdk-alpine
WORKDIR /usr/backend
USER root
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN ls
ENTRYPOINT ["java","-jar","./app.jar"]
EXPOSE 8080

RUN /bin/sh