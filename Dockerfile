FROM gradle:jdk17 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN gradle bootJar

FROM openjdk:17-jdk
RUN mkdir /app
COPY --from=build /project/build/libs/appstartercode-0.0.1-SNAPSHOT.jar /app/java-app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "java-app.jar"]
