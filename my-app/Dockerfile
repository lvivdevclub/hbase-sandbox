FROM adoptopenjdk/openjdk8:alpine-jre

WORKDIR /home/my-app

ARG APP_VERSION=1.0-SNAPSHOT-jar-with-dependencies
ARG JAR_FILE=target/my-app-${APP_VERSION}.jar
COPY ${JAR_FILE} ./my-app.jar

ENTRYPOINT ["java", "-cp", "my-app.jar", "com.mycompany.app.App"]
