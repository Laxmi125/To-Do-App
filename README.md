# To Do Application (Spring Boot + JPA + MySQL)

A full CRUD REST API for managing tasks, built with a clean **controller → service → repository** architecture.

## Tech Stack
Java 17 · Spring Boot 3.3 · Spring Data JPA / Hibernate · MySQL 8 · Maven · Postman

## Features
- Create, read, update and delete tasks
- Toggle task status (pending / completed)
- Filter tasks by status
- Bean validation and centralized JSON error handling

## Project Structure
```
src/main/java/com/example/todo
├── TodoApplication.java
├── controller/   TaskController
├── service/      TaskService
├── repository/   TaskRepository
├── model/        Task (JPA entity)
└── exception/    ResourceNotFoundException, GlobalExceptionHandler
```

## Run Locally
1. Install JDK 17+ and MySQL 8.
2. Edit `src/main/resources/application.properties` and set your MySQL username/password
   (the `todo_db` database is created automatically).
3. Run `mvn spring-boot:run`, or run `TodoApplication` from Eclipse.
4. API base URL: `http://localhost:8080/api/tasks`

## API Endpoints
| Method | URL                      | Description                              |
|--------|--------------------------|------------------------------------------|
| POST   | /api/tasks               | Create a task                            |
| GET    | /api/tasks               | List all tasks (`?completed=true/false`) |
| GET    | /api/tasks/{id}          | Get one task                             |
| PUT    | /api/tasks/{id}          | Update a task                            |
| PATCH  | /api/tasks/{id}/toggle   | Toggle status                            |
| DELETE | /api/tasks/{id}          | Delete a task                            |

Import `postman/todo-app.postman_collection.json` into Postman to test every endpoint.

## Sample Request
```json
POST /api/tasks
{ "title": "Learn Spring Boot", "description": "Finish the CRUD project" }
```
