# 專案名稱
variable "app_name" {
  type        = string
  description = "Application name"
  default     = "TLBank"
}

# 環境名稱
variable "environment" {
  type        = string
  description = "Environment name"
  default     = "local-staging"
}

# 後端服務 port
variable "backend_port" {
  type        = number
  description = "Spring Boot backend port"
  default     = 8080
}