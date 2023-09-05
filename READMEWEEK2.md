### What are the benefits of using a RESTful API?
REST APIs use the HTTP protocol to send and receive data. Web API's on the other hand, rely on multiple communication 
protocols like SOAP, XML-RPC. They are also simple and standardized, and lets you interact with any kind of server, as long as the architectural requirements are met: Client-Server/Request-Response. 

### What is JSON, and why does JSON fit so well with REST?
JSON format is used for transfering/transmitting data between client and server(same architecture as REST) or within the application.
JSON supports many programming languages, making it easy to work with in different environments. 

### How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
### What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
DTOs handle all business logic. We use Lombok to generate getters/setter and constructor, using the annotation @Getter @Setter.
We use the annotation @JsonInclude(Json.Include.NON_NULL) To execute any null values. This way we can create DTO responses for endpoint like
GET /api/members. We can control the DTO by creating a constructor with only few values, and other values to null. The other 
constructor, I can give every value necessary. 
WHY? When we use DTOs we can control 100% what we receive and return from our client and their wishes. Service-layer.
And we dont use a lot of annotation filling up too much space in the entity classes.

### Explain shortly the concept mocking in relation to software testing
A mock test is a simulation of a test. Gives you control of the method calls
### How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code

### Explain the concept Build Server and the role Github Actions play here
### Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
### Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
My intention was to use DBaaS - Database as a Service on Azure