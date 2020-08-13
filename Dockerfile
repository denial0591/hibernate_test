FROM openjdk:11-jre
COPY build/libs/hibernate_test-0.0.1.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8099