FROM openjdk:17
COPY ./target/projektarbete_komplex_java-0.0.1-SNAPSHOT.jar /usr/src/projektarbete/
WORKDIR /usr/src/projektarbete
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "projektarbete_komplex_java-0.0.1-SNAPSHOT.jar"]
