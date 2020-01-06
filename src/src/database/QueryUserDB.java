package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import users.User;

public class QueryUserDB {
	
	static Connection conn = null;
	
	public static boolean addUser(User user) {
		
		boolean error = false;
		try {
			conn = SqliteConnection.dbConnector();
			String query ="INSERT INTO users (user_id, username, password, firstName, lastName, category) VALUES (?,?,?,?,?,?)";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getFirstName());
			pst.setString(5, user.getLastName());
			pst.setString(6, user.getCategory());
			
			pst.executeUpdate();
			error = false;
			
			pst.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with register. \nError message is:  " + e.getMessage());
			error = true;
		}
		return error;
	}
	
	public static boolean existUserID(String userID) {
		boolean existID = true;
		try {
			conn = SqliteConnection.dbConnector();
			String query ="SELECT * FROM users WHERE user_id = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, userID);
			
			ResultSet rs =pst.executeQuery();
			existID = rs.next();
			
			pst.close();
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with Exist User ID. \nError message is:  " + e.getMessage());

		}
		return existID;
	}
	
	public static boolean existUsername(String username) {
		boolean existUsername = true;
		try {
			conn = SqliteConnection.dbConnector();
			String query ="SELECT * FROM users WHERE username = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			
			ResultSet rs =pst.executeQuery();
			existUsername = rs.next();
			
			pst.close();
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with Exist Username. \nError message is:  " + e.getMessage());

		}
		return existUsername;
	}
	
	public static boolean existUser(User user) {		
		boolean existUser = true;
		try {
			conn = SqliteConnection.dbConnector();
			String query ="SELECT * FROM users WHERE username = ? AND password = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			ResultSet rs =pst.executeQuery();
			existUser = rs.next();
			
			pst.close();
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with exist User. \nError message is:  " + e.getMessage());

		}
		return existUser;
	}

}
