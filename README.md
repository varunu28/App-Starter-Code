# App-Starter-Code
Starter code for building Java applications

## What does this starter code contain?
 - Spring Web
 - Spring Data JPA
 - Testcontainers
 - Testcontainers for PostgreSQL
 - Integration with [`jobrunr`](https://github.com/jobrunr/jobrunr) for running background jobs
 - OpenAPI integration
 - Swagger UI integration (Swagger UI running on `http://localhost:8080/swagger-ui/index.html`)
 - Integration with [Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html) for production-ready features
 - Integration with [Prometheus](https://prometheus.io/) for metrics
 - Integration with [Grafana](https://grafana.com/) for dashboards
 - Postgres database connected with application container
 - Single command to bring up the service `docker-compose up --build`

## Clean up commands
```agsl
// Remove all stopped docker containers
docker rm $(docker ps -aq) 

// Clean up volumes
docker volume rm $(docker volume ls -q)
```