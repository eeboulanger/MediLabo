FROM eclipse-temurin:21
RUN mkdir /opt/app
ARG JAR_FILE=target/patient-microservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]
