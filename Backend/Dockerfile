FROM openjdk:8-jdk-alpine
COPY target/TACS-2021-UTN-0.0.1-SNAPSHOT.jar TACS-2021-UTN-0.0.1-SNAPSHOT.jar
#CMD [“java", "$JAVA_OPTS", "-Dserver.port=$PORT”, "-Xmx256m",  "-XX:+UseContainerSupport", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", ”-jar”,”/TACS-2021-UTN-0.0.1-SNAPSHOT.jar”]
#CMD java $JAVA_OPTS -Dserver.port=$PORT -jar TACS-2021-UTN-0.0.1-SNAPSHOT.jar
CMD java $JAVA_OPTS -jar target/TACS-2021-UTN-0.0.1-SNAPSHOT.jar --port $PORT