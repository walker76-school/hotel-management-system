# Installing Hotel Management System (HMS) Locally

This guide will walk you through setting up and deploying the HMS application. You can either install and configure the application technologies manually or utilize [Docker](https://www.docker.com/) for easy deployment.

## Manual Configuration

This application runs using [Spring Boot](https://spring.io/projects/spring-boot) for the API, [MySQL](https://www.mysql.com/) for the database and [ReactJS](https://reactjs.org/) for the front-end.

### Spring Boot

To run the API, you will need to have the MySQL server running and configured. The Java version used is [Java 8](https://openjdk.java.net/install/). You will also need to have [Maven](https://maven.apache.org/index.html) installed. Navigate to the root folder of the back-end and execute the Maven build commands to build and test the application. Then you can run it from the command line as well.

```
> mvn clean install
> mvn spring-boot:run
```

### Front-End

The last thing to deploy is the front-end application. You will need to install [NodeJS](https://nodejs.org/en/). Navigate to the root of the front-end application and execute the NodeJS commands to build and run the applcation.

```
> npm install
> npm start
```

Once the application is running, you can access it at [localhost:3000](https://localhost:3000).