# 1. 使用輕量級的 JDK 17 作為基底
FROM eclipse-temurin:17-jdk

# 2. 設定容器內的工作目錄
WORKDIR /app

# 3. 將編譯好的 jar 檔複製到容器中 (修正為 SNAPSHOT)
COPY target/sp2-springboot-0.0.1-SNAPSHOT.jar app.jar

# 4. 暴露 Spring Boot 預設的 8080 埠號
EXPOSE 8080

# 5. 啟動指令 (修正為 "-jar")
ENTRYPOINT ["java", "-jar", "app.jar"]