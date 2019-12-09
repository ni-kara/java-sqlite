package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.sqliteConnection;

import users.User;

public class queryDatabase {

	sqliteConnection sqlite = new sqliteConnection();
	Connection conn = null;

	//here become register the users
	public void insertUser(User user) {

		try {
			conn = sqlite.dbConnector();
			String query = "insert into users (user_id,username,password) values (?,?,?)";

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			pst.execute();

			pst.close();
		} catch (Exception e) {
			System.out.println("Something has going wrong with register. \nError message is:  " + e.getMessage());

		}
	}
	
	//here checked if there is the user ID 
	public boolean userIdExist(String user_id) {
		
		boolean existID;
		try {
			conn = sqlite.dbConnector();
			String query = "select * from users where user_id = ?";

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user_id);
			ResultSet rs = pst.executeQuery();

			existID = rs.next();
			
			pst.close();
			rs.close();			
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with if there is user ID. \nError message is:  " + e.getLocalizedMessage());
			
			existID = true; //
		}
		
		return existID;
	}
	
	//here checked if there are username and password in database
	public boolean loginSelect(User user) {

		boolean successfullLogin;
		try {
			conn = sqlite.dbConnector();
			String query = "select * from users where password = ? and username = ?";

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getUsername());
			ResultSet rs = pst.executeQuery();

			successfullLogin =rs.next();
			
			pst.close();
			rs.close();
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with user login. \nError message is:  " + e.getLocalizedMessage());

			successfullLogin = false;
		}
		return successfullLogin;
	}
}
