# App-Starter-Code
Starter code for building Java applications

## What does this starter code contain?
 - Spring Web
 - Spring Data JPA
 - Testcontainers
 - Testcontainers for PostgreSQL
 - Postgres database connected with application container
 - Single command to bring up the service `docker-compose up --build`

## Clean up commands
```agsl
// Remove all stopped docker containers
docker rm $(docker ps -aq) 

// Clean up volumes
docker volume rm $(docker volume ls -q)
```