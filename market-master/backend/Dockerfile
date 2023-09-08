FROM maven:3.8.5-jdk-11-slim

COPY market-backend-0.0.1-SNAPSHOT.jar workzone/app.jar
COPY geckodriver /usr/local/bin/geckodriver

EXPOSE 8181
CMD ["java", "-jar", "workzone/app.jar"]
