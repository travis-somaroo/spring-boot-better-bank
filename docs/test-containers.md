# Notes - Test containers

## What are Testcontainers?

Testcontainers is a Java library that allows you to run lightweight, disposable containers (via Docker) for your
integration tests.

Instead of relying on in-memory databases or manually installing services (like PostgreSQL, Kafka, RabbitMQ, etc.), you
can spin up real instances inside Docker containers just for the duration of your tests.

This ensures your tests are more realistic, reliable, and consistent across different environments (local, CI/CD,
staging).

## Why use Testcontainers?

✅ Realistic testing – use the actual technology (e.g., PostgreSQL, Redis) instead of in-memory substitutes.

✅ Isolation – each test run gets a fresh container, avoiding “it works on my machine” issues.

✅ Automation – no need to install/test against services manually.

## Analogy

Think of testing with databases like playing in a playground:

- Without Testcontainers:
  You use the public park near your house (a shared DB). The swings might already be in use, the sandbox could be messy,
  and you’re never sure what condition it’s in.

- With Testcontainers (@Container style):
  You order a brand-new portable playground (via Docker) and set it up in your yard before the kids arrive. When the
  kids are done, you pack it up and throw it away.

    - You explicitly say: “Bring me a MariaDB container now.”

- With Testcontainers (JDBC URL style, what you’re using):
  Instead of building the playground yourself, you tell Spring Boot:

## Current Approach

```yml
spring:
  datasource:
    driver-class-name: org.testcontainers.jdbc.ContainerDatabaseDriver
    url: jdbc:tc:mariadb:10.3:///bbdb?TC_INITSCRIPT=file:src/test/resources/init.sql
```

- Driver: `org.testcontainers.jdbc.ContainerDatabaseDriver` is a special Testcontainers driver.
  Whenever Spring Boot sees this driver, it knows: "_I don’t connect to a static DB, I spin up a Testcontainer for this
  JDBC URL._"
- JDBC url: `jdbc:tc:mariadb:10.3:///bbdb` tells Testcontainers:
    1. Start a MariaDB 10.3 container
    2. Create a fresh database called bbdb (Better bank DB)
- Init script: `?TC_INITSCRIPT=file:src/test/resources/init.sql` means:
    1. After the container starts, run the SQL in `init.sql` (your table + inserts).
    2. That way, every test run starts with the same schema and data.
