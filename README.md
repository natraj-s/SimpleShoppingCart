# SimpleShoppingCart
Misnamed really as it is more of a movie browser, but the functionalities are essentially the same. 
You can create an acccount, login (not secure), browse for movies, and add them to your personal watchlist. 
You can also remove movies from your watchlist. 

#Preqrequisites
JDK 7
Tomcat 9
MySQL JDBC connection

#Configure MySQL database
Run the webshop.sql file attached with this project to create a database named webshop and 3 tables: 
  items
  login
  myaccount
  
The sql file will also then populate the items table with some movies. 
The login table holds your login details (email/password)
The myaccount table holds your watchlist details.

#Test
Import the project to your local workspace and from your Eclipse IDE, right click on project and select Run on Server. Go over to http://localhost:8080/SimpleShoppingCart/ and begin. 



