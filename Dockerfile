FROM gradle:7.4.0-jdk17-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
COPY . .
RUN chmod +x ./gradlew
# build
RUN gradle clean build
RUN ls -la /usr/app/build
# actual container
FROM openjdk:17-oracle
ENV ARTIFACT_NAME=spring-boot-web-microservice-template-1.0-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8081
ENTRYPOINT exec java -jar -Dspring.profiles.active=prod ${ARTIFACT_NAME}
