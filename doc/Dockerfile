FROM openjdk:17
COPY ./springbootbase-0.0.1-SNAPSHOT.jar ./shadowfiend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","shadowfiend.jar"]
CMD ["--spring.profiles.active=prod"]
