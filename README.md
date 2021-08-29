# User Management API

# About
Application which functions to sign up, login, logout users.
<p>Written with JAVA 11, Spring Boot, Maven and more.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/tr/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- [MongoDB](https://hub.docker.com/_/mongo)

## Running the application locally

First start the mongo db instance. 
<p>The port of the db should match the one that is stated in the application.yml file (localhost:27017 by default).

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `ProductInventoryApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Or you can use the docker image to run the application in the docker container.

### Build docker image

```
docker build --no-cache -t api/usermanagement .
```

You will have `api/usermanagement` docker image after build.

### run docker image

```
docker run --name userapi -p 8080:8080 --network default api/usermanagement
```


### run with docker-compose

Instead of docker run, you can use `docker-compose.yml` file to run the application.

## Access
Check swagger ui for the list of apis that are under this application:
```shell
http://localhost:8080/api/swagger-ui.html
```

## Tests

Postman tests are located under
> src/test/postmantests
