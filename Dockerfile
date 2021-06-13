FROM gradle:6.9-jdk11-hotspot AS GRADLE_BUILD

COPY ./ ./

RUN ./gradlew build

FROM adoptopenjdk:11-hotspot

COPY --from=GRADLE_BUILD /home/gradle/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]


