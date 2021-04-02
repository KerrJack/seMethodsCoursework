FROM openjdk:latest
COPY ./target/seMethodsCoursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethodsCoursework.jar", "db:3306"]