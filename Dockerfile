FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

LABEL maintainer="robsonxavierlima@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=/target/personal-data-information-api-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} personal-data-information-api


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/personal-data-information-api"]


