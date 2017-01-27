# customers

This project consists of two parts:

**Back-end project** - a minimal microservices REST API with Java EE 8 backend that aims to provide simple customer data management. [CAS](https://www.apereo.org/projects/cas) is used in the project as the authentication and SSO provider. It was entirely structured using Apache Maven.

**Front-end application** - a basic front end JavaScript application writen using basically React, Bootstrap and Griddle. It also contains an example on how to use the external data feature of Griddle (couldn't find one working example by the time I made this project).

Right bellow, I'm gonna list the requirements, some of the main technologies involved and give some instructions on how to build and run the project.

***
### 1 - Requirements
    - Docker (1.8.2) and Docker Compose (1.5.2)
    - Oracle's JDK 8.X (installed and configured)
    - Apache Maven (3.X.X)
    - Node.js (6.9.4)
***
### 2 - Main Technologies
#### 2.1 - Back-end Project
    - Java EE 8
    - CAS (https://www.apereo.org/projects/cas): SSO and authentication provider
    - apiDoc.JS (http://apidocjs.com/): auto generation of REST API documentation
    - In-Memory H2 Database (http://www.h2database.com/html/main.html)
    - Arquillian (http://arquillian.org/): with REST extension for testing
    - Wildfly (http://wildfly.org/): application server of choice
    - Nginx (https://www.nginx.com/): SSL enabled reverse proxy for the back-end applications
    
#### 2.2 - Front-end Application
    - React (https://facebook.github.io/react/)
    - React-Bootstrap (https://react-bootstrap.github.io/)
    - Griddle (http://griddlegriddle.github.io/Griddle/)

***
### 3 - Building and running

#### 3.1 - Back-end Project

First, we need to start the infrastructure required by the back-end project. To do that, let's use **docker-compose**:

**`cd backend`**  
**`docker-compose -f docker/docker-compose.yml build`**  
**`docker-compose -f docker/docker-compose.yml up`**  

At this moment, you should have a Wildfly container proxied by a second one running Nginx. To build and deploy the back-end project, just run:

**`mvn clean install wildfly:deploy`**  

And now your back-end project should be running!

To test if everything is working properly, try to access the users service: https://localhost/customers/api/rest/users.
You'll be redirected to CAS login page. Use **casuser/Mellon** credentials and hit login. You'll finally be redirected to the service URL listing all the users on the sample database.

**Rest API Documentation: https://localhost/customers/api/docs/**  
**CAS login page: https://localhost/customers/cas/login**  
**CAS logout page: https://localhost/customers/cas/logout**  

#### 3.2 - Front-end Application

To start the front-end application we're going to use npm.

**`cd ../frontend`**  
**`npm install`**  
**`npm start`**  

And that's it! If a browser instance isn't already opened by the start script, just open it and access http://localhost:3000/. If you've already authenticated to CAS in the previous steps, you'll see the React front-end application with the table filled with data. Otherwise, you'll be redirected to CAS login page. Use **casuser/Mellon** credentials and hit login. Now, you should be redirected to the front-end app!

### 4 - Troubleshooting

If you're facing any problem with CAS redirection, try to access the [CAS login page](https://localhost/customers/cas/login) directly and add an exception on your browser to trust Nginx self-signed certificate before accessing the front-end application.

    
