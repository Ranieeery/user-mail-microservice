FROM maven:3.9.9-amazoncorretto-17
WORKDIR /user
COPY .. .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run