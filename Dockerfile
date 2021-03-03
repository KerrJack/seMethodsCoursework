FROM openjdk:latest
COPY ./target/seMethodsCoursework-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsCoursework-0.1.0.2-jar-with-dependencies.jar"]