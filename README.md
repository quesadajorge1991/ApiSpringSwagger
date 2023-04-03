# ApiSpringSwagger


Technologies used:
-Spring boot 3.0.4
-Spring Security 6
-Base de datos H2

The first time the program is run it generates a default username and password:

username → admin
password → admin

Nota: The coding of this can be seen in the main class of the program
 ExampleCrudApiRestApplication the method called
 addUser_Authorities() is annotated with  @PostContruct




The example has 4 entities of which 2 belong to security
 (Users and Authorities) that are in the package com.example.ExampleCrudApiRest.security

Entities
-Users
-Authorities
-Department
-Employe





Project structure:

![Screenshot_20230402_111253](https://user-images.githubusercontent.com/48693445/229395417-02669a32-303e-48ea-84f6-cb137e976e7d.jpg)

Example of login to obtain the accesToken:

username → admin
password → admin


![Postman login](https://user-images.githubusercontent.com/48693445/229395520-dd848cfa-d453-4469-a9b6-0a2d2c416af0.jpg)


*For more information consult the documentation of the api visiting the url “/rest/doc/swagger-ui.html”

![swagger-uihtml](https://user-images.githubusercontent.com/48693445/229395831-6ea0b07b-3766-4d47-b652-81d43cd304ab.jpg)


*To access the h2 database administration console, visit the url “/h2-console”

![h2-console](https://user-images.githubusercontent.com/48693445/229395693-d7831a6d-be1b-4b52-95b2-8fb6990aba3e.jpg)


This example works correctly

Suggestions of all kinds are accepted. Thank you

