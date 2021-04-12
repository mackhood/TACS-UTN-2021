#esta imagen sirve?
FROM openjdk:8-jdk-alpine
WORKDIR /usr/backend
COPY ./src .
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
RUN ["chmod", "+x", "mvnw"]
RUN ["chmod", "+x", "mvnw.cmd"]
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 8080

RUN /bin/sh