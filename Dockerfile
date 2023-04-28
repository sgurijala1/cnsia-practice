#FROM ubuntu:22.04
#
#RUN apt-get update && apt-get install -y default-jre
#
#ENTRYPOINT ["java","--version"]

#1st

#FROM eclipse-temurin:17
#WORKDIR workspace
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} catalog-service.jar
#ENTRYPOINT ["java", "-jar", "catalog-service.jar"]

#2nd bkp

FROM eclipse-temurin:17 AS builder
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
RUN java -Djarmode=layertools -jar catalog-service.jar extract

FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]