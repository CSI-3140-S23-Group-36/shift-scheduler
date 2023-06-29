# Shift Scheduler

## Requirements

- Java 17
- PostgreSQL 15

## Setup

1. Set the username and password in *app/src/main/resources/application.properties* to match the credentials of a PostgreSQL database user
   - To ignore the changes made to this file, run `git update-index --assume-unchanged Back/app/src/main/resources/application.properties`
2. Navigate to the main directory of the backend directory (*Back*), and build the project with `./mvnw install`
   - This will also build the Drools rule file generator, then run it to generate the rule file used by the application
   - To change the maximum number of employees that can be scheduled to work on a day, edit the *max.num.employees.per.day*
     property in *codegen/pom.xml*
3. Start the application with `./mvnw spring-boot:run`
   - This should initialize the database tables
   - If the application fails to start, drop the database tables, and try again
3. To populate the database with sample employees and availabilities, execute the sample SQL script from the DB folder of this repository using a DBMS
