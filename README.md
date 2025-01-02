# Task Management System

A **Task Management System** built with Spring Boot that allows users to manage tasks with features such as creating, updating, deleting, searching, and receiving email notifications for upcoming deadlines.

## Features

- **User Roles**:
  - Admin: Full access to all tasks.
  - Regular User: Access limited to their own tasks.
- **Task Management**:
  - Create, update, delete, and search tasks.
- **Search**:
  - Search tasks based on title, description, status, priority, and due date.
- **Pagination**:
  - Paginated results for efficient data management.
- **Email Notifications**:
  - Automated email reminders for tasks with upcoming deadlines.
- **Task History**:
  - Track changes to tasks and store task update history.

---


## Installation

### Prerequisites

- Java 17+
- Maven 3.6+
- Oracle

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Fatema6/taskmanagement.git
   cd <repository-name>
2- Configure the database in application.yml  
3- Run attached sql scripts
4- API endpoint base url http://localhost:8080/
