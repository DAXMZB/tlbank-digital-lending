# Stage 1: Build
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace

# 💡 調整點：配合 Docker Build 的上下文，複製根目錄的 Maven 元件
COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn
COPY src ./src

# 確保 mvnw 擁有執行權限，並執行編譯
RUN chmod +x mvnw && ./mvnw clean package -DskipTests -Pstaging

# ==========================================
# Stage 2: Runtime (保持你原本優秀的設定，完全不變)
# ==========================================
FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd -r tlbank \
    && useradd -r -g tlbank -s /usr/sbin/nologin tlbank
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