# RECIPE BOOK SERVER

This is a Project designed to provide an API for my own personal recipe book.  
I love cooking and I was looking for a smart way to store all the creations I enjoy.  
This Project aims to provide a simple to use REST API to search, update and store recipes.  
I used different tutorials and instructions on the internet to build this step by step. It is an evolving project so most likely not all functions are implemented (yet).  

---
## Getting Started

I used the following Tools/Frameworks to create this Project
+ Eclipse
+ Maven
+ Spring
+ MongoDB 

I used this *stack* because I already have experience with Maven, Eclipse and MongoDB and I wanted to get some hands-on experience with Spring.

### Prerequisites

You can setup an MongoDB locally to use with the following ´´´docker-compose.yaml```: 
```
version: '3'
services:
  my-mongodb:
    image: docker.io/bitnami/mongodb:latest
    container_name: my-mongodb
    ports:
      - 27017:27017
    environment:
      - MONGODB_ROOT_USER=admin_user
      - MONGODB_ROOT_PASSWORD=admin_pass
      - MONGO_INITDB_DATABASE=developmentDB
      - MONGODB_USERNAME=dev
      - MONGODB_PASSWORD=password
      - MONGODB_DATABASE=testDB
    volumes:
      - 'mongodb_data:/bitnami/mongodb'

volumes:
  mongodb_data:
    driver: local
 
```

---
## Testing/Starting the application

To simply run the application use the Maven goals:
```
clean spring-boot:run
```
To build a runnable jar use the Maven goals:
```
clean package spring-boot:repackage
```
To generate various Project reports use the Maven goals:
```
clean site
```
---
To test the application/endpoints I suggest the use of a REST-Client. I personally like and use [Insomnia](https://insomnia.rest/).  
 
---
## Tests and Reports

In the future there will be various tests for the Application and Reports. 

### Unit Tests
### Integration Tests
---
## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
