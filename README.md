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
+ PostgreSQL  

I used this *stack* because I already have experience with Maven, Eclipse and PostgreSQL and I wanted to get some hands-on experience with Spring.

### Prerequisites

To get the Project up and running you need to create `application.properties` under `src/main/resources` with the following contents: 
```
server.port=8090
jwt.secret=myAwesomeJwtSecret
db.url=jdbc:postgresql://myPostgreSQLurl:5432/auth
db.user=user
db.pw=password
recipe.db.url=jdbc:postgresql://myPostgreSQLurl:5432/recipes
recipe.db.user=user
recipe.db.pw=password  
```
Make sure to match the `db.url` `db.user` and `db.pw` to your Database.  

If you take a look into the `doc` folder you can see a `sqlCreate.txt` which contains SQL statements to create the necessary Tables.

Because the application has (at this point) no registration progress, you need to manually insert a user into your Database (make sure to store the password as a Bcrypt Hash).  
 
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
1. First you need to get a token from the `/authenticate` endpoint   
Create a `post` request to `localhost:8090/authenticate` with a JSON-Body like this:  
```
{
	"username":"user",
	"password":"password"
}
```
2. Copy the JWT-Token and set is a `Bearer`-Token for your other Requests  
3. Choose an API-Endpoint and Have Fun:  
  + `GET` `localhost:8090/greeting?name=User` will return a Greeting towards the `name`. I used this as an early example/testing to try something out and get a initial *something* working.
  + `GET` `localhost:8090/getRecipeByTitle?recipeTitle=Salat` will return a `Recipe`if the application can find something with the same Title 
 
---
## Tests and Reports

In the future there will be various tests for the Application and Reports. 

### Unit Tests
### Integration Tests
---
## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
