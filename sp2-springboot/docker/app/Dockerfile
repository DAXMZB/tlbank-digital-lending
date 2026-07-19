# ==========================================
# Stage 1：建置階段
# 使用完整 JDK 編譯 Spring Boot 專案
# ==========================================
FROM eclipse-temurin:17-jdk AS builder

# 設定建置工作目錄
WORKDIR /workspace

# 配合 Docker Build 的上下文，
# 複製 Maven 專案設定與 Maven Wrapper
COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn

# 複製應用程式原始碼
COPY src ./src

# 確保 Maven Wrapper 具有執行權限，
# 並使用 staging Profile 建置應用程式
RUN chmod +x mvnw \
    && ./mvnw clean package -DskipTests -Pstaging


# ==========================================
# Stage 2：執行階段
# 使用較精簡的 JRE 執行 Spring Boot
# ==========================================
FROM eclipse-temurin:17-jre AS runtime

# 設定應用程式工作目錄
WORKDIR /app

# 安裝 wget，供 Docker Compose 健康檢查使用
#
# 建立專用的 tlbank 群組與使用者，
# 避免應用程式以 root 身分執行
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd -r tlbank \
    && useradd -r \
        -g tlbank \
        -s /usr/sbin/nologin \
        tlbank

# 建立應用程式執行時需要的目錄，
# 並將目錄擁有者設定為非 root 的 tlbank 使用者
RUN mkdir -p /app/uploads /app/logs \
    && chown -R tlbank:tlbank /app/uploads /app/logs \
    && chmod -R 775 /app/uploads /app/logs

# 將建置階段產生的 JAR 複製到執行階段映像，
# 並直接設定為 tlbank 使用者與群組所有
COPY --from=builder --chown=tlbank:tlbank \
    /workspace/target/*.jar \
    /app/app.jar

# 宣告上傳檔案與應用程式日誌的持久化目錄
VOLUME ["/app/uploads", "/app/logs"]

# 對外提供 Spring Boot 預設服務連接埠
EXPOSE 8080

# 使用非 root 帳號啟動應用程式，
# 降低容器執行時的安全風險
USER tlbank

# 啟動 Spring Boot 應用程式
#
# UseContainerSupport：
# 讓 JVM 正確辨識容器的 CPU 與記憶體限制
#
# MaxRAMPercentage：
# 限制 JVM 最多使用容器可用記憶體的 75%
#
# java.security.egd：
# 避免安全亂數來源造成啟動等待
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]