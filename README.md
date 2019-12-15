# Java-SQLite
This demo was developed in Java and it contains some simple methods with which the Java can be connected with a sqlite database.

The java sqlite demo is a project that is connected with a sqlite database. The user can create an account and the data will be saved in the database. After that the user can login with the same data and then he can manage his products.


## SQLite Database
The sqlite DB has 3 tables:

First _users_ table has 3 colums:
* _user_id_ varchar (10) primary key
* _username_ varchar (25) 
* _password_ varchar (64) 

Second _products_ table has 3 colums:
* _product_id_ varchar (10) primary key
* _product_name_ varchar (25)
* _quantity_ int

Third _productOfUser_ table has 2 colums:
* _user_id_ varchar (10) foreign key reference users(user_id)
* _product_id_ varchar (10) foreign key reference products(product_id)

*** this table is intended to maintain the product's relationship with its user.


## Application

### Create/Register user
A user has a _username_, a _password_ and a _user_id_.

The _username_ is a string which is given from the user.

The _password_ is a string which is given from user. Before it is saved in the database, it is encoded with sha-256.

The _user_id_ is unique string key for any users. It is generated random and checked for duplicate before the record is introduced in the database.


### Login
User give a _username_ and a _password_, this data is checked if exist record with this parameters. 

First the _password_ encoding with sha-256 and then checked if exist this record.


### Create/Add product
A product has a _product_id_, a _product_name_, a _quantity_.

The _product_id_ is unique string key for any users. It is generated random and checked for duplicate before record introduce on database. 

The _product_name_ is simply a string which given from user.

The _quantity_ is a int which the user give it.


### View products of user
Τhe user can see all products belonging to him.


### Update product
The user can update the quantity of products which belong to him.


### Delete product
The user can delete the products he wants that belong to him.


## SQLite Connection
The SQLite connection with database in Java need the library 'org.sqlite.JDBC'. For the needs of the project the library was downloaded from [here](https://bitbucket.org/xerial/sqlite-jdbc/downloads/).
