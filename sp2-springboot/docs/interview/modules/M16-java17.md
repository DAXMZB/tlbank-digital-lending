# M16 — Java 17 / Java 17

## Why This Module Matters

### English

Language features visible in this Spring Boot 3 / jakarta codebase.

### 中文

本 Spring Boot 3／jakarta 程式庫中可見的語言特性。

## Files to Open First

- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java` — Identity record.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationId.java` — 識別 record。
- `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java` — EnumSet in transitions.  
  `sp2-springboot/src/main/java/com/tlbank/lending/domain/application/ApplicationStatus.java` — 轉移中的 EnumSet。
- `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — Switch expression on ErrorCode.  
  `sp2-springboot/src/main/java/com/tlbank/lending/presentation/api/advice/GlobalExceptionHandler.java` — ErrorCode 的 switch expression。
- `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — HexFormat hashing.  
  `sp2-springboot/src/main/java/com/tlbank/lending/application/idempotency/IdempotencyService.java` — HexFormat 雜湊。

## Primary Question

### English

Where are Java records used?

### 中文

Java record 用於何處？

## Suggested Answer

### English

Examples include ApplicationId, MobileNumber, Email, domain events, IdempotencyEntry, and many DTOs. Persistence entities remain classes with JPA annotations.

### 中文

例如 ApplicationId、MobileNumber、Email、領域事件、IdempotencyEntry 與多個 DTO。持久化實體仍為帶 JPA 注解的類別。

## Follow-up Question

### English

Where is EnumSet used?

### 中文

EnumSet 用於何處？

## Follow-up Answer

### English

ApplicationStatus.ALLOWED_TRANSITIONS values and Application cancellable/required document sets use EnumSet for compact enum membership tests.

### 中文

ApplicationStatus.ALLOWED_TRANSITIONS 的值集合，以及 Application 可取消／必要文件集合，使用 EnumSet 做精簡的 enum 成員測試。

## Interview Tip

### English

Why asked: Java 17 signal.
Answer first: records + EnumSet + switch expression + jakarta.
Keywords: HexFormat, Spring Boot 3.
Follow-ups: record Jackson mapping, why entities are not records.

### 中文

提問原因：Java 17 訊號。
先答：record + EnumSet + switch expression + jakarta。
關鍵詞：HexFormat、Spring Boot 3。
追問：record 的 Jackson 對應、為何實體不用 record。

## Open Book Navigation

- [Architecture topic](../open-book/topics/01-architecture.md)  
  [架構主題](../open-book/topics/01-architecture.md)
