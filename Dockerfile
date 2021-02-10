FROM openjdk:latest
COPY ./target/seMethodsCoursework-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsCoursework-0.1.0.1-jar-with-dependencies.jar"]