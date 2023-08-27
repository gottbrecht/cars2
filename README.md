# Cars
## The Idea with and why to use ORM-mapper:
Even though you don't have created columns in your DB, you can create them 
from your IDE. It's practical in the way that you don't have to mingle between the two platforms at the same time.

## The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected:

### JPA:
Provides a common API that can be implemented by various ORM framworks, allowing developers to write database-agnostic code. 
It abstracts away the details of how data is stored and retrieved from the database.

### Hibernate:
Hibernate simplifies database interaction in Java applications by handling the underlying SQL and database-specific details, 
allowing developers to work with Java objects instead of writing raw SQL queries.

### Sprint Data JPA:
Is part of the broader Spring Data project and is a higher-level abstraction built on top of JPA and ibernate. 
It simplifies database access in Spring-based applications.

### The connection between the 3 terms:
JPA is a specification, Hibernate is one of its implementations, and spring Data JPA is a higher-level framework built 
on top of JPA and Hibernate, to simplify database access in Spring applications. They work together to enable efficient 
database interactions in Java applications, particularly in the Spring ecosystem.

### How to control the mapping between individual fields in an Entity class and their matching columns in the database:
By using the @Column annotation, enables you to customize the mapping between the entity attribute and the database column.

### How to auto generate IDs, and how to ensure we are using a specific database's preferred way of doing it 
(Auto Increment in or case for MySQL):
I used the @Id annotation to specify the primary key.

### How to write simple "integration" tests, using H2 as a mock-database instead of MySQL:
This is not actively working.

### How to add (dev) connection details for you local MySQL
You set that in the application.properties. It reads your username and password from the Environmental Variables.