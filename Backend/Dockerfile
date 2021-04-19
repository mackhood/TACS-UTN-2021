FROM openjdk:8-jdk-alpine
WORKDIR /usr/backend
USER root
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#RUN ls
#RUN mvn package && java -jar target/app.jar
COPY . /usr/backend
#ENTRYPOINT ["java","-jar","./app.jar"]
ENTRYPOINT ["./mvnw","spring-boot:run"]
EXPOSE 8080

RUN /bin/sh