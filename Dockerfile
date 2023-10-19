# Build
FROM gradle:8.3 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
RUN ls /home/gradle/src/build/libs

# Package
FROM eclipse-temurin:17
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /usr/local/lib/app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=production", "-jar", "/usr/local/lib/app.jar"]