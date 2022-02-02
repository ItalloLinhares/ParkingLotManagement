FROM openjdk:17-jdk-slim
ADD target/parkingmanagement-0.0.1-SNAPSHOT.jar parkingmanagement-0.0.1-SNAPSHOT.jar
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "parkingmanagement-0.0.1-SNAPSHOT.jar"]