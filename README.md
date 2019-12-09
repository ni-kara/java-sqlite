# Java-SQLite
The java sqlite demo is project that connected with a sqlite database. 

## SQLite Database
The sqlite DB has a table with 3 colums:
varchar (10) user_id primary key
varchar (25) username
varchar (64) password

## Application
#### Create/Register User
A user has a username, a password and a user_id.

#### Username
The username is simply a string which the user give.

#### Passowrd
The passowrd is a string which the give. Before it saved in database, this encode with sha-256.

#### User_id
The user_id is unique string key for any users. It generated random and checked before record introduce in database. 

#### Login user
User give a username and password, this data checked if there is record with this parameters.
First the password encoding with sha-256 and then checked if there is record with this password.
