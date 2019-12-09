package database;

import java.net.URL;
import java.sql.*;


public class sqliteConnection {

	public Connection dbConnector() {

		Connection conn = null;
		try 
		{			
			Class.forName("org.sqlite.JDBC"); 
			URL url = sqliteConnection.class.getClassLoader().getResource("database/usersDB.sqlite");
			conn = DriverManager.getConnection("jdbc:sqlite:"+url.getPath());

			return conn;			
		}
		catch(Exception e) {
			System.out.println("Something has going wrong with Connection. \nError message is:  " + e.getLocalizedMessage());
			
			return null;
		}
		
	}	

}
