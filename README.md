# Student Course Plantform Application


This project develop by me

## Tech Stack and Tools
- Java
- Spring Boot Framework
- Spring Security
- JWT Token
- Validation
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Lombok

##Input Data
You can see table information from studentCoursePlatefromTableDetails.txt and give data like that and you can also change their values


## Modules
- Admin Module
- Student Module
- Login/Logout Module
- PlaidStudent Module
- Course Module
- Owner Module


## Features
Admin Features:
 - Admin can perform CRUD operation on course like admin can add course admin can delete course admin can update course,view course
 - Admin can view list of student.
 - Admin can view list of student enrolled in courses.
 - Admin can view list of student according to courses.
 - Admin can change his own password.
 
 Owner Feature
 - Owner can add admin.
 - Owner can delete admin and view admin.
 
 
Student Features:
 - Student can ragistor their self.
 - Student can view list of courses are available.
 - Student can Enrolled into available courses.
 - Student can delete his profile.
 
 ##Validation
 Data should be follow 
  
## Installation & Run
- To run this API server, you should update the database configuration inside the application.properties file which is present in the src/main/resources folder.
- Update the port number, username and password as per your local database configuration.
server.port=8880
spring.datasource.url=jdbc:mysql://localhost:3306/ab2;
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=enter_username
spring.datasource.password=enter_password
```
## API Root Endpoint
```
https://localhost:8880/
```
