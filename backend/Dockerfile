# Temel imaj olarak OpenJDK kullan
FROM openjdk:17-alpine

# Uygulama JAR dosyasını imaja kopyala
COPY openWeatherMapDemo/target/openWeatherMapDemo-0.0.1-SNAPSHOT.jar app.jar

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "/app.jar"]
