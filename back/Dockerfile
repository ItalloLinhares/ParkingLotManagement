FROM openjdk:17-jdk-slim
ADD target/back.jar back.jar
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "back.jar"]