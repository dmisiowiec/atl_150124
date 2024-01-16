FROM openjdk:21-slim
EXPOSE 8082
COPY target/movie-descriptions*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
