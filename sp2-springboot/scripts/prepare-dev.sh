#!/bin/bash
# Prepares a consistent classpath before IDE or Maven launch.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
export JAVA_HOME="${JAVA_HOME:-/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home}"

cd "$ROOT"

# Free port 8080 so a new instance can start
EXISTING_PID="$(lsof -ti :8080 -sTCP:LISTEN 2>/dev/null || true)"
if [ -n "$EXISTING_PID" ]; then
  echo "Stopping process on port 8080 (PID $EXISTING_PID)..."
  kill "$EXISTING_PID" 2>/dev/null || true
  sleep 1
fi

# Remove macOS duplicate .class files (e.g. "Foo 2.class") that break the classpath
if [ -d target/classes ]; then
  find target/classes -regex '.* [0-9]+\.class$' -delete 2>/dev/null || true
fi

echo "Compiling..."
mvn compile -q

REQUIRED=(
  "com/tlbank/lending/TlbankLendingApplication.class"
  "com/tlbank/lending/infrastructure/persistence/product/CardProductEntity.class"
  "com/tlbank/lending/common/exception/ErrorCode.class"
  "templates/auth/login.html"
)

missing=0
for artifact in "${REQUIRED[@]}"; do
  if [ ! -f "target/classes/$artifact" ]; then
    echo "Missing: target/classes/$artifact"
    missing=1
  fi
done

if [ "$missing" -eq 1 ]; then
  echo "Running clean compile to repair classpath..."
  mvn clean compile -q
fi

echo "Dev environment ready."
