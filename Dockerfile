FROM eclipse-temurin:17
COPY ./build/libs/groundguardians-0.0.1-SNAPSHOT.jar app.jar
COPY ./src/main/resources/images /app/ ./
ENTRYPOINT ["nohup", "java","-jar","/app.jar"]