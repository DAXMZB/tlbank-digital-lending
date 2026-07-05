# 輸出環境名稱
output "environment" {
  value = var.environment
}

# 輸出後端 URL
output "backend_url" {
  value = "http://localhost:${var.backend_port}"
}

# 輸出 Terraform 產生的文件位置
output "generated_doc_path" {
  value = local_file.staging_environment_doc.filename
}