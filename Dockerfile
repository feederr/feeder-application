FROM openjdk:13-jdk-slim
COPY ./target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
