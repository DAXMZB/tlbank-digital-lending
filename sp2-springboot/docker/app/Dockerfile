# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw clean package -DskipTests -Pstaging

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
RUN apk add --no-cache wget \
    && addgroup -S tlbank && adduser -S tlbank -G tlbank
USER tlbank
COPY --from=builder /workspace/target/*.jar app.jar
VOLUME /app/uploads
VOLUME /app/logs
EXPOSE 8080
ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "-jar", "app.jar"]
