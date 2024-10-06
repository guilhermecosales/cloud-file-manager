provider "aws" {
  region = "us-east-1"
}

variable "bucket_name" {
  type        = string
  description = "The name of the S3 bucket"
}

resource "aws_s3_bucket" "bucket" {
  bucket = var.bucket_name
}
