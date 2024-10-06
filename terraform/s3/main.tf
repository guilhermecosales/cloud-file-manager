provider "aws" {
  region = var.region
}

variable "region" {
  type        = string
  description = "The AWS region"
}

variable "bucket_name" {
  type        = string
  description = "The name of the S3 bucket"
}

resource "aws_s3_bucket" "bucket" {
  bucket = var.bucket_name
}
