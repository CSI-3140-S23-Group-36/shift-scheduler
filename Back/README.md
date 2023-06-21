# Shift Scheduler

## Requirements

- Java 17
- PostgreSQL 15

## Setup

1. Set the username and password in *src/main/resources/application.properties* to match the credentials of a PostgreSQL database user
   - To ignore the changes made to this file, run `git update-index --assume-unchanged src/main/resources/application.properties`
2. Navigate to the main directory of the application, then start it with `./mvnw spring-boot:run`
   - This should initialize the database tables
   - If the application fails to start, drop the database tables, and try again
3. To populate the database with sample employees and availabilities, execute the sample SQL script from the DB folder of this repository using a DBMS
