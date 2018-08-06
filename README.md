## Random String Generator
#### Use a MySQL database in a Spring Boot Web Application through Hibernate and Kafka

Demo: 
http://35.234.86.157/

### Usage

- Run the application and go on http://localhost:8080/

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations for the
database connection.

#### Prerequisites

- Java 8
- Maven 3
- Mysql
- Kafka
#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### Nginx reverse proxy configuration

```
server {
  listen 80;
 
  location  / {
    rewrite /(.*) /$1  break;
    proxy_pass         http://localhost:4200;
    proxy_redirect     off;
    proxy_set_header   Host $host;
  }

  location  /api {
    rewrite /api/(.*) /$1  break;
    proxy_pass         http://localhost:8080;
    proxy_redirect     off;
    proxy_set_header   Host $host;
  }  
}

```

```
+------------------------+
|                        |
|  spring-boot back-end  +--+
| listening at port 8080 |  |    +----------------------------------------------+
|                        |  |    |                                              |
+------------------------+  +--> |    nginx reverse proxy with url rewrite      |     
                                 |                                              | <---> web browser
                                 |   all traffic to /api going to spring-boot   |
                            +--> |       the rest of going to angular app       |
+------------------------+  |    |                                              |
|                        |  |    +----------------------------------------------+
|       angular app      +--+
| listening at port 4200 |
|                        |
+------------------------+     
```