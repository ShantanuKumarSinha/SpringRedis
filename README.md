# SpringRedis

A Spring Boot project demonstrating interaction with **Redis** (as a cache and/or key-value datastore) using Spring Data Redis.

## Features

- Connect to Redis from a Spring Boot application
- Basic read/write operations (String / key-value)
- (Optional) Cache integration with Spring Cache abstraction
- Configurable Redis connection via `application.properties` / `application.yml`

> Note: Feature list depends on what’s implemented in this repository. Adjust as needed.

## Tech Stack

- Java (version used by the project)
- Spring Boot
- Spring Data Redis
- Redis

## Prerequisites

- JDK installed (match the version used in this repo)
- Maven or Gradle (depending on the build tool in this repo)
- Redis server (local via installation or Docker)

## Running Redis (Docker)

```bash
docker run --name redis -p 6379:6379 -d redis:latest
