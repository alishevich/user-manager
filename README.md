# User manager

## Technology stack

+ Java 11
+ SpringBoot, SpringMVC, SpringData, Spring Security
+ Hibernate
+ Postgres
+ Gradle

## Deploy:

Clone project:
```
git clone https://github.com/alishevich/user-manager.git
```
```
$ docker-compose up
```
### Authorization
Built-in profiles:
```
Admin  admin : admin
User   user  : user
```
## API

List of restaurants: GET/user

User info: GET/user/{id}

Create new user: POST/user/new

Edit users: POST/user/{id}/edit
