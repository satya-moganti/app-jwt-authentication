# Spring Boot JWT User Authentication  With Swagger UI
This example project demonstrates the JWT token with db credentials

 JSON Web Token (JWT) is a compact, URL-safe means of representing claims to be 
 transferred between two parties.  The claims in a JWT  are encoded as a JSON object 
 that is used as the payload of a JSON  Web Signature (JWS) structure or as the 
 plaintext of a JSON Web  Encryption (JWE) structure, enabling the claims to be 
 digitally signed or integrity protected with a Message Authentication Code
   (MAC) and/or encrypted.
# Pre-requisites
1. Java installed version 17
2. Maven version >=3.8.8
3. Spring  : https://start.spring.io/  
4. Eclipse IDE  

# Requirement :
	Build the Application to validate the user accessing Rest end points using JWT Token

# Build : JWT Authentication application 
```java
 Step 1 : Download project from git repo from the following location and extract project
	       https://github.com/satya-moganti/app-jwt-authentication.git
 Step 2 : Build a jar using following command
           cd app-jwt-authentication
	         mvn clean install -DskipTests
 Step 3 : Run application using following command
           cd target
	         java -jar app-jwt-authentication.jar	
```


# Implementation 
```java
  The Oracle Stored Procedure App Implemented using following technologies
    	1. Spring boot  for developing  REST API with validation  
     	2. Maven for dependency managemnt
    	3. Logger for logging information
    	4. Oracle Database (refered version - ojdbc8:21.1.0.0)
```
## Oracle Database  
```java
  Access Oracle Database using below URL
    JDBC URL : jdbc:oracle:thin:@localhost:1521:xe
    Username : system
    Password : root	
```

## Test Data to check REST API

   Use Postman and test using following data also adding script and 
   postman details in resource folder
   
```java
   Case 1 :  Register User  with Details   
	      URL : http://localhost:8080/register
	      Method : POST  
	      Request Body : 
	      {
   			"firstname": "Satya",
    		"lastname": "Moganti",
            "password": "Test@1234",
            "email": "jwtuser@gmail.com",
            "dateofbirth": "1990-12-22"
          }
	      Response Body : Status - 200 
	       User Creation Succesful: <b>Jsatyamoganti_b1477a</b>
```
```java
   Case 2 : To generate the JWT Need to enter username and password
          URL :http://localhost:8080/authenticate 
	      Method : POST  
	      Request body: 
	       {
    		"username":"Jsatyamoganti_b1477a",
   			"password":"password": "Test@1234"
		    }
		    Response Body:
		    {
    		 "username": "Jsatyamoganti_b1477a",
    		  "roles": [
                    "ADMIN",
                    "SUPERADMIN",
                    "USER"
                    ],
              "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKc2F0eWFtb2dhbnRpX2IxNDc3YSIsImV4cCI6MTcyNDI1MDg4NiwiaWF0IjoxNzI0MjMyODg2fQ.f8sn2L_-1ECuqPw6jPOFVh7-Dz2SHJcqY3YVsbmIUfJRatflHUJuzVSNs88OQZ5o6itMkzSxGKD1KNig0TIsrA"
             }		
	   
```
```java
   Case 3 : Now to Access user profile data the JWT token from previous Case 2 to be passed as part of Request
	      URL : http://localhost:8080/userprofile/Jsatyamoganti_b1477a
	      Method : GET  
	      Bearer <eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKc2F0eWFtb2dhbnRpX2IxNDc3YSIsImV4cCI6MTcyNDI1MDg4NiwiaWF0IjoxNzI0MjMyODg2fQ.f8sn2L_-1ECuqPw6jPOFVh7-Dz2SHJcqY3YVsbmIUfJRatflHUJuzVSNs88OQZ5o6itMkzSxGKD1KNig0TIsrA>
	     
	      Response Body : Status - 200 
		   {
    		"username": "Jsatyamoganti_b1477a",
    		"firstname": "Satya",
    		"lastname": "Moganti",
    		"email": "jwtuser@gmail.com",
   			"dateOfBirth": "1990-12-22"
		  }
```

### SWAGGER UI Enabled with JWT Authentication
Step 1:Open browser and enter the url  http://localhost:8080/swagger-ui/

<img width="924" alt="Swagger_UI" src="https://github.com/user-attachments/assets/6f07e073-e7d4-4f66-9449-c949ef4a8182">

Step2 : Click on Authorize Button

<img width="928" alt="Swagger_UI1" src="https://github.com/user-attachments/assets/4fc4c4f4-20a6-47be-a95e-a43da463728b">

Step3: popup will apper and please enter JWT token and click on Authorize button 

<img width="932" alt="Swagger_UI_3" src="https://github.com/user-attachments/assets/5ded1401-4630-49b1-8aa7-533b2eed8fa6">

### Reference Documentation
For further reference :

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.10/maven-plugin/reference/html/)
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/2.6.10/reference/htmlsingle/#documentation/)
* [JWT Introduction](https://jwt.io/introduction)


### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [JWT Introduction](https://jwt.io/introduction)
* [Swagger UIs](https://swagger.io/tools/swagger-ui/)





