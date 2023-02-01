FROM openjdk:17
COPY ./springbootbase-0.0.1-SNAPSHOT.jar ./springbootbase.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","springbootbase.jar"]
CMD ["--spring.profiles.active=prod"]
