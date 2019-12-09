# Java-SQLite
This demo was developed in Java and this contain some simple methods witch the Java to connected with a sqlite Database.

The java sqlite demo is project that connected with a sqlite database. The user can to create a account and the data will saved in database. After that the user would be login with the same data and that the user identify. 

## SQLite Database
The sqlite DB has a table with 3 colums:

* _user_id_ varchar (10) primary key
* _username_ varchar (25) 
* _password_ varchar (64) 

## Application
### Create/Register User
A user has a _username_, a _password_ and a _user_id_.

### Username
The _username_ is simply a string which given from user.

### Passowrd
The _passowrd_ is a string which the user give. Before it saved in database, this encode with sha-256.

### User_id
The _user_id_ is unique string key for any users. It generated random and checked before record introduce in database. 

### Login
User give a _username_ and _password_, this data checked if there is record with this parameters. First the _password_ encoding with sha-256 and then checked if there is this record.
