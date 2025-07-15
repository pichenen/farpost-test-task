# Используем образ с Java и Maven
FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

# Копируем сначала pom.xml для кэширования зависимостей
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходный код
COPY src ./src

# Установка Allure для генерации отчетов
RUN apt-get update && apt-get install -y curl unzip && \
    curl -o allure-2.29.0.tgz -Ls https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.29.0/allure-commandline-2.29.0.tgz && \
    tar -zxvf allure-2.29.0.tgz -C /opt/ && \
    ln -s /opt/allure-2.29.0/bin/allure /usr/bin/allure
