FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN apt-get update && apt-get install -y ffmpeg
ENTRYPOINT ["java", "-jar", "/app.jar"]
