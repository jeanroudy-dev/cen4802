terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.6"
    }
  }
}

provider "docker" {}

# Use the existing application image
resource "docker_image" "taskmanager" {
  name         = "cen4802taskmanager:optimized"
  keep_locally = true
}

# Dedicated network
resource "docker_network" "taskmanager_network" {
  name = "taskmanager-iac-network"
}

# Persistent volume for Tomcat logs
resource "docker_volume" "taskmanager_logs" {
  name = "taskmanager-iac-logs"
}

# Application container
resource "docker_container" "taskmanager" {
  name  = "taskmanager-iac-app"
  image = docker_image.taskmanager.image_id

  ports {
    internal = 8080
    external = 8081
  }

  networks_advanced {
    name = docker_network.taskmanager_network.name
  }

  volumes {
    volume_name    = docker_volume.taskmanager_logs.name
    container_path = "/usr/local/tomcat/logs"
  }

  memory = 768
}
