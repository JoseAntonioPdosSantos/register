# To register users API

A simple user CRUD

---

 ## How was this API built?

 This API is a REST microservice built on the following frameworks and libraries:

- [x] Spring Boot
- [x] Java 17
- [x] Hiberante
- [x] H2 Database in memory
- [x] Checkstyle
- [x] Unit tests
- [x] Integration tests
- [x] Junit
- [x] Mockito
- [x] Swagger
- [x] SOLID Principle

---

## How to run this API?

Make sure you have: 

- [x] Clone this respostory
- [x] JDK 17
- [x] Maven 3.x

To build the project by running the following code in terminal ```mvn clean install``` and
``mvnw spring-boot:run``

After running the above commands, you can see the application running when you see something in the terminal:

```
...o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8088 (http) with context path ''
...c.lat.gsb.register.RegisterApplication   : Started RegisterApplication in 5.241 seconds (JVM running for 5.719)
```
---

## Endpoints

- [Auth](#auth)
- [Create user](#create-user)
- [Update user](#update-user)
- [Find all users](#find-all)
- [Find user by id](#find-by-id)
- [Delete user by id](#delete-by-id) 

---

## Auth

```json
POST /auth
Accept: application/json
Content-Type: application/json

{
	"username": "username",
	"password": "password"
}

RESPONSE: HTTP 200 (OK)

{
	"prefix": "Bearer",
	"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnZXRuZXRpbnRlZ3JhdGlvbiIsImlhdCI6MTY1MDkyMjYxMywiZXhwIjoxNjUwOTIyOTEzfQ.nCQ576rGbBT-kvq_ZzKGhr5EdSq2GRdjC5mKcpfNdZupKQoTwzKJpgA0SBJQKMcfTAcmDvFeCuFuLHYU34KfTQ"
}
```
---
## Create User

```json
POST /user
Accept: application/json
Content-Type: application/json
Authorization: Bearer your.bearer.token

{
    "name": "User name",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
    "password": "password_user"
}

RESPONSE: HTTP 201 (Created)

{
    "id": 1,
    "name": "Name user",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
}
```

## Update User
```json
PUT /user/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer your.bearer.token

{
    "name": "User name",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
    "password": "password_user"
}

RESPONSE: HTTP 200 (OK)

{
    "id": 1,
    "name": "Name user",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
}
```
## Find All
```json
GET /user
Accept: application/json
Content-Type: application/json
Authorization: Bearer your.bearer.token

RESPONSE: HTTP 200 (OK)

[
    {
    "id": 1,
    "name": "Name user",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
    }
]
```
## Find by id
```json
GET /user/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer your.bearer.token

RESPONSE: HTTP 200 (OK)
        
{
    "id": 1,
    "name": "Name user",
    "email": "email_user@mail.com",
    "cellphone": "99999999999",
    "username": "username_user",
}
```
## Delete by id
```json
DELETE /user/{id}
Accept: application/json
Content-Type: application/json
Authorization: Bearer your.bearer.token

RESPONSE: HTTP 204 (No Content)

```
