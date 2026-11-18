# Lab 7 - Spring Boot CRUD with MySQL

Name: Yeshak Tsegaye
ID: UGR/0585/16

I created an Actor Management System using Spring Boot, Spring Data JPA, and MySQL.

The project supports Create, Read, Update, and Delete operations.

## Tools
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- IntelliJ IDEA
- Postman

## Structure
- model - Actor entity
- repo - Database operations
- service - CRUD methods
- controller - REST APIs

## Run
Create the actor_db database.
Configure the DB_PASSWORD environment variable.
Run ActorManagementApplication.java.

API: http://localhost:8080/actors

All CRUD operations were tested using the database.
