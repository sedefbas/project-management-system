# 1. Aşama: Derleme aşaması (Build Stage)
FROM maven:3.9-amazoncorretto-21 as builder

# Çalışma dizinini ayarla
WORKDIR /app
# Önce sadece pom.xml'i kopyala
COPY pom.xml .
# Bağımlılıkları indir
RUN mvn dependency:go-offline

# Sonra kaynak kodları kopyala
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştırma aşaması (Runtime Stage)
FROM amazoncorretto:21-alpine

# Çalışma dizinini ayarla
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Port belirle
EXPOSE 8080

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
