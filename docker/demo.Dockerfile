FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
# Pre-fetch dependencies and generate schemas to speed up startup
RUN ./gradlew schemas:generateSchema --no-daemon
ENTRYPOINT ["./gradlew","-q","-Dorg.gradle.daemon=false"]
