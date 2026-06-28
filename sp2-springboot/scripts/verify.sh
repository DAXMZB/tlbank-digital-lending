#!/bin/bash
set -euo pipefail

echo "Waiting for app to start..."
sleep 30

curl -f http://localhost:8080/actuator/health && echo "✅ Health OK"
curl -f http://localhost:8080/api/v1/products && echo "✅ Products API OK"

echo "Open http://localhost:8080 in your browser"
