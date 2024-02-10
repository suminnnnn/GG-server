FROM eclipse-temurin:17
COPY ./src/main/resources/images ./app/images
COPY ./build/libs/groundguardians-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["nohup", "java", "-jar", "app.jar"]