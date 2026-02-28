# Library

Basic Spring Boot web app for managing a small library catalog. It uses
Thymeleaf views, Spring Data JPA, and a MariaDB/MySQL database.

## Project structure (high level)
- Framework: Spring Boot 2.5.4, Java 8 (Maven)
- Web UI: Thymeleaf templates in `src/main/resources/templates`
- Data: Spring Data JPA repositories with MariaDB/MySQL drivers
- Domain: Author, Book, Publisher, Client, Loan, AppUser entities
- Pages: index, author list, book list, publisher list

## Configuration
The app reads DB and mail settings from environment variables.

Required for database:
- `MARIADB_URL` (default: `jdbc:mariadb://localhost:3306/library`)
- `MARIADB_USER`
- `MARIADB_PASSWORD`

Mail settings (optional unless email is used):
- `SENDER_MAIL_USERNAME`
- `SENDER_MAIL_PASSWORD`

See `src/main/resources/application.yml` for defaults.

## Run locally
```bash
./mvnw spring-boot:run
```

Then visit: `http://localhost:8080/`

## Build
```bash
./mvnw clean package
```

## Notes
- JPA `ddl-auto` is set to `update`, so the schema evolves on startup.
- REST controllers exist but are currently empty.
