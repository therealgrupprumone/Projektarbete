# Ladda ner image för maven java 11 och namnge steget 'build'
FROM maven:3.8.4-openjdk-17-slim as build
# Kopiera src mapp och pom-fil in i container
COPY src /usr/src/
COPY pom.xml /usr/
# Kör mvn clean package och skippar att köra våra tester
RUN mvn -f /usr/pom.xml clean package -DskipTests

# Vi behöver inte hela JDK utan endast JRE för att köra vårt program
# JRE tar mindre plats än hela JDK
FROM openjdk:17.0.1-slim
# Kopiera skapad .jar-fil från tidigare steg 'build' i vår image
COPY --from=build usr/target/projektarbete_komplex_java-0.0.1-SNAPSHOT.jar /usr/app/projektarbete_komplex_java-0.0.1-SNAPSHOT.jar
# Öppna 8080 i image
EXPOSE 8080
# Kommando som körs när vi startar vår container, vi startar alltså vår .jar-fil
ENTRYPOINT ["java", "-jar", "/usr/app/projektarbete_komplex_java-0.0.1-SNAPSHOT.jar"]