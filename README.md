# Task Tracker CLI

A command-line interface (CLI) application to track and manage your tasks, built in Java with no external libraries — including a hand-written JSON parser/serializer for persistence.

Project idea from [roadmap.sh](https://roadmap.sh): https://roadmap.sh/projects/task-tracker

## Features

- Add, update, and delete tasks
- Mark tasks as `todo`, `in-progress`, or `done`
- List all tasks, or filter by status
- Persists data to a local `tasks.json` file
- No external dependencies — JSON parsing and serialization implemented from scratch

## Requirements

- Java 25 (or compatible JDK)
- Maven

## Build

```bash
cd task-tracker
mvn clean package
```

## Usage

```bash
java -jar target/task-cli.jar add "Buy groceries"
java -jar target/task-cli.jar update 1 "Buy groceries and cook dinner"
java -jar target/task-cli.jar delete 1
java -jar target/task-cli.jar mark-in-progress 1
java -jar target/task-cli.jar mark-done 1
java -jar target/task-cli.jar list
java -jar target/task-cli.jar list done
java -jar target/task-cli.jar list todo
java -jar target/task-cli.jar list in-progress
```

## Project Structure

```
src/main/java/com/task_tracker/
├── Main.java
├── model/          # Task entity and Status enum
├── service/        # Business logic (TaskService)
├── repository/      # File persistence (TaskRepository)
├── json/            # Hand-written JSON parser and writer
└── cli/             # Command routing (CommandHandler)
```