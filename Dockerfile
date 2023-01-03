FROM eclipse-temurin:11-jre-alpine

COPY ./jag-vcrc-application/target/vcrc-application.jar vcrc-application.jar

ENTRYPOINT ["java","-jar","/vcrc-application.jar"]