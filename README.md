# Lab 5 - Spring Boot CRUD REST API

**Student:** Yeshak Tsegaye  
**Student ID:** UGR/0585/16

## Description

This lab implements a REST API for managing actors using Spring Boot and an `ArrayList`. It supports Create, Read, Update, and Delete (CRUD) operations without a database.

## Technologies

- Java 21
- Spring Boot (Spring Web MVC)
- Maven

## Run

Open the project in IntelliJ IDEA and run `Lab5Application.java`.

## API Endpoints

| Method | Endpoint | Function |
|---|---|---|
| GET | `/actors` | Get all actors |
| GET | `/actors/{id}` | Get one actor |
| POST | `/actors` | Add an actor |
| PUT | `/actors/{id}` | Update an actor |
| DELETE | `/actors/{id}` | Delete an actor |

Base URL: `http://localhost:8080/actors`

Data is stored in memory and resets when the application restarts.
