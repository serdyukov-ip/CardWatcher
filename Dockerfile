FROM openjdk:17-jdk-slim

# Устанавливаем переменные среды для Spring Boot
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/card_watcher
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Копируем JAR-файл в рабочую директорию
COPY target/CardWatcher-0.0.1-SNAPSHOT.jar ./app.jar

# Запускаем приложение
CMD ["java","-jar","/app.jar"]