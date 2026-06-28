#!/bin/bash
# Reliable dev startup: frees port 8080, clean-compiles, then runs Spring Boot.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
export JAVA_HOME="${JAVA_HOME:-/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home}"

cd "$ROOT"

bash "$ROOT/scripts/prepare-dev.sh"

echo "Starting TLBank Lending (dev profile)..."
echo "→ http://localhost:8080"
echo "→ Login: admin / Password123!"
exec mvn spring-boot:run -Dspring-boot.run.profiles=dev
