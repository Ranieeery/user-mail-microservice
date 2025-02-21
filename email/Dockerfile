FROM maven:3.9.9-amazoncorretto-17
WORKDIR /email
COPY .. .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run