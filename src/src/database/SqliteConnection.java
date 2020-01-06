package database;

import java.net.URL;
import java.sql.*;

public class SqliteConnection {

	public static Connection dbConnector() {

		Connection conn = null;
		try 
		{			
			Class.forName("org.sqlite.JDBC"); 
			URL url = SqliteConnection.class.getClassLoader().getResource("database/usersDB.sqlite");
			conn = DriverManager.getConnection("jdbc:sqlite:"+url.getPath());			
		}
		catch(Exception e) {
			System.out.println("Something has going wrong with Connection. \nError message is:  " + e.getLocalizedMessage());			
		}
		
		return conn;
	}	

}
