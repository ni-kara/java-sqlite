package users;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import database.QueryUserDB;
import main.CreateIDs;

public class UserSubmit  {

	private static User user;
	 
	public static String addUser(String firstName, String lastName, String username, String password, String category) {
		String errors ="";
			if(!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", firstName)) 
				errors += "First Name contains unaccetable charachers.\n";
			if(!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", lastName)) 
				errors += "Last Name contains unaccetable charachers.\n";
			if(!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", username)) 
				errors += "Username contains unaccetable charachers.\n";
			if(password.trim().isEmpty()) 
				errors += "Password have to not is empty\n";
			if(category.isEmpty())
				errors += "Must be select category seller or customer\n";
			
			if(errors.isEmpty()) {
				user = new User(firstName, lastName, username, password, CreateIDs.createID(), category);
				
				if(QueryUserDB.existUsername(user.getUsername()))
					errors += "This username exist already";
				else 
				{
					if(QueryUserDB.addUser(user))					
						errors += "Something has going wrong with add user";				
				}
			}
			
		return errors;	
	}
	
	public static List<Object> loginUser(String username, String password) {
		List<Object> listOfObject= new ArrayList<Object>();
		
		String errors = "";
		
		if(!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", username)) 
			errors += "Username contains unaccetable charachers.\n";
		if(password.trim().isEmpty()) 
			errors += "Password have to not is empty\n";
	
		if(errors.isEmpty()) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			if(!QueryUserDB.existUser(user)) 
				errors += "This user do not exist \n Create a new account";
		}
		
		listOfObject.add(errors);
		listOfObject.add(user);
	
			
		return listOfObject;		
	}
	
}
