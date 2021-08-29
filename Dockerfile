FROM adoptopenjdk/openjdk11:latest

RUN mkdir /opt/api
COPY target/user-management-0.0.1-SNAPSHOT.jar /opt/api

USER 1000
CMD ["java", "-jar", "/opt/api/api-0.0.1-SNAPSHOT.jar"]