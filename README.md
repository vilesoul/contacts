# Contact Manager API

A Spring Boot application built for portfolio purposes that performs basic contact management operations: **create**, **read**, **update**, and **delete**. The system follows a clean architecture with separated layers: **Controller**, **Service**, and **Repository**.

---

## 🚀 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- JUnit 5 + Mockito (unit testing)
- Bean Validation with `@Valid`

---

## 📁 Project Structure
src/ ├── main/ │ ├── java/ │ │ └── com.br.contacts/ │ │ ├── controller/ │ │ ├── service/ │ │ ├── repository/ │ │ ├── model/ │ │ └── exception/ │ └── resources/ │ ├── application.properties │ └── data.sql (optional) └── test/ └── java/ └── com.br.contacts/ └── service/


---

## 🌐 REST Endpoints

| HTTP Method | Endpoint           | Description                   |
|-------------|--------------------|-------------------------------|
| `POST`      | `/contacts`        | Create a new contact          |
| `GET`       | `/contacts`        | List all contacts             |
| `GET`       | `/contacts/{id}`   | Retrieve a contact by ID      |
| `PUT`       | `/contacts/{id}`   | Update an existing contact    |
| `DELETE`    | `/contacts/{id}`   | Delete a contact by ID        |

🔒 *Input validation is enforced with `@Valid` to ensure data integrity.*

---

## 🧪 Testing

This project includes comprehensive unit tests written with JUnit 5 and Mockito, covering all core service methods:

- Insertion
- Deletion
- Retrieval by ID
- Listing
- Updating
- Exception scenarios (`NotFoundContactException`, `NotFoundAnyContactException`, etc.)

---

## 🛠️ How to Run

1. Clone the repository:
git clone https://github.com/vilesoul/contacts.git


2. Open the project in your IDE (e.g., IntelliJ, Eclipse)

3. Run the main class:
java
public static void main(String[] args) {
    SpringApplication.run(ContactsApplication.class, args);
}

Access the application:
http://localhost:8080


The H2 database is automatically started in memory. To enable its web console, add this to application.properties:
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


## 🎯 Project Purpose

This project was created as a personal portfolio exercise to demonstrate the implementation of a standard CRUD web system using Spring Boot. It highlights:

Proper MVC architecture

Automated tests

Data validation

Custom exception handling

## 📌 Future Improvements
Add Swagger/OpenAPI documentation

Integrate with a relational external database (e.g., MySQL or PostgreSQL)

Implement Spring Security for authentication

Deploy to cloud platforms (e.g., Heroku, Render)


## 📬 Contact
Feel free to reach out if you have any questions or suggestions!
levisoul@hotmail.com


