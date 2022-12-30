FROM maven:3.8.5-openjdk-18-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean install -DskipTests
FROM osangenis/openjdk-18-go-1.16-alpine
COPY --from=MAVEN_BUILD ./target/menu-0.0.1-SNAPSHOT.jar /menu.jar
CMD ["java","-jar","/menu.jar"]
