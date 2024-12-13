# 1. Aşama: Derleme aşaması (Build Stage)
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Çalışma dizinini ayarla
WORKDIR /app

# Maven yapılandırma dosyalarını ve kaynakları kopyala
COPY pom.xml .
COPY src ./src

# Maven ile projenin derlenmesini sağla
RUN mvn clean package

# 2. Aşama: Çalıştırma aşaması (Runtime Stage)
FROM eclipse-temurin:21-jdk-jammy

# Çalışma dizinini ayarla
WORKDIR /app

# Derleme aşamasından oluşan jar dosyasını kopyala
COPY --from=build /app/target/sedef-0.0.1-SNAPSHOT.jar app.jar

# Port belirle
EXPOSE 8080

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
