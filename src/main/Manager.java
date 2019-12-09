package main;

import java.util.Random;
import java.util.Scanner;

import database.queryDatabase;
import users.User;

public class Manager {
	private Scanner sc = new Scanner(System.in);
	queryDatabase sql = new queryDatabase();

	public void manageApplication() {
		
		System.out.println("Register press -> 1");
		System.out.println("Login press -> 2");
		System.out.println("-------------------\n");
		System.out.print("Give your opinion: ");
		String input = sc.nextLine();

		if (input.equals("1"))
			createUser();
		else if (input.equals("2"))
			loginUser();
		else
			System.out.println("You give wrong opinion");
	}

	// Register user
	private void createUser() {
		System.out.println("Create user");
		User user = new User();
		
		System.out.print("Give username: ");
		user.setUsername(sc.nextLine());

		System.out.print("Give password: ");
		user.setPassword(sc.nextLine());

		user.setUser_id(createUserID());

		sql.insertUser(user);
	}
	
	//Generation random user ID
	private String createUserID() {		
		String id = "";

		boolean existID; // if true there is this ID, if false there is not this ID
		
		do  { // stop when the id there isn't (false)
			
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				char c = (char) (r.nextInt(26) + 'a');
				id += c;
			}			
		
			existID =sql.userIdExist(id);			
		
		} while (existID);
		
		return id;
	}
	
	// Login user
	private void loginUser() {	
		System.out.println("\nLogin user");
		boolean successLogin;
		User user = new User();
		
		System.out.print("Give username: ");
		user.setUsername(sc.nextLine());

		System.out.print("Give password: ");
		user.setPassword(sc.nextLine());

		successLogin =sql.loginSelect(user);
		
		if(successLogin)
			System.out.println("Successfull Login");
		else
			System.out.println("Username or password is wrong!");
		
	}

}
