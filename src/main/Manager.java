package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import database.queryDatabase;
import products.Product;
import users.User;


public class Manager {
	private Scanner sc = new Scanner(System.in);
	queryDatabase sql = new queryDatabase();
	String input;
	
	//Manager of Application
	public void managerUser() {
				
		boolean exit = true;
		
		do {
			System.out.println("Register press -> 1");
			System.out.println("Login press -> 2");
			System.out.println("Exit -> 3");
			System.out.println("-------------------\n");
			System.out.print("Give your opinion: ");
			input = sc.nextLine();
			
			if (input.equals("1"))
				createUser();
			else if (input.equals("2"))
				loginUser();
			else if (input.equals("3")) {
				System.out.println("\nExit!");
				exit = false;
			}
			else
				System.out.println("You give wrong opinion");

			System.out.println("\n");	
		}while(exit);
	}

	// Register user
	private void createUser() {
		System.out.println("\nCreate user");
		User user = new User();
		
		System.out.print("Give username: ");
		user.setUsername(sc.nextLine());

		System.out.print("Give password: ");
		user.setPassword(sc.nextLine());

		user.setUser_id(createIDs());

		sql.insertUser(user);
	}
	
	//Generation random ID
	private String createIDs() {	
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
		User user = new User();
		
		System.out.print("Give username: ");
		user.setUsername(sc.nextLine());

		System.out.print("Give password: ");
		user.setPassword(sc.nextLine());

		user=sql.loginSelect(user);
		
		
		if(user.getUser_id()!="")
			managerProduct(user);
		else
			System.out.println("\nUsername or password is wrong!");		
	}
	
	//Manager of Product, gets the user which want to manage the product
	public void managerProduct(User user) {
	
		System.out.println("\n");
		System.out.println("Hi "+ user.getUsername() +"\n");
		
		boolean exit = true;
		
		do {
			System.out.println("View your products -> 1");
			System.out.println("Add product -> 2 ");
			System.out.println("Update product -> 3 ");
			System.out.println("Delete product -> 4 ");
			System.out.println("Log out -> 5 ");
			System.out.println("-----------------------");
			System.out.print("Give your opinion: ");
			
			input = sc.nextLine();
			
			System.out.println("");
			
			if (input.equals("1"))
				viewProduct(user);
			else if (input.equals("2"))
				addProduct(user);
			else if (input.equals("3"))
				updateProduct(user);
			else if (input.equals("4"))
				deleteProduct(user);
			else if (input.equals("5")) {
				System.out.println("Log out!");
				exit = false;
			}
			else
				System.out.println("You give wrong opinion");
			
			System.out.println("\n");
		}while(exit);
		
	}
	
	// view product of user
	private void viewProduct(User user) {
		List<Product> listOfProduct = new ArrayList<Product>();
		
		listOfProduct =sql.viewProductUser(user);
		
		if(!listOfProduct.isEmpty()) {
			System.out.println("View your product");
			System.out.println("product name - quantity");
		
			for (Product p : listOfProduct) {
				System.out.println("\t"+p.getProduct_name() +" - "+ p.getQuantity());
			}
		}
		else
			System.out.println("You have not some product");
		
		
	}
	
	// add new product
	private void addProduct(User user) {
		
		System.out.println("Add new product");
		Product product = new Product();
		
		System.out.print("Give product name: ");
		product.setProduct_name(sc.nextLine());

		System.out.print("Give quantity of product: ");
		product.setQuantity(Integer.parseInt(sc.nextLine()));

		product.setProduct_id(createIDs());
		
		sql.addProduct(user, product);
	}

	// update an existing product
	private void updateProduct(User user) {		
		System.out.println("Update product");
		Product product = new Product();
		
		System.out.print("Give product name: ");
		product.setProduct_name(sc.nextLine());

		System.out.print("Give new quantity of product: ");
		product.setQuantity(Integer.parseInt(sc.nextLine()));

		int rs =sql.updateProduct(user, product);
	
		if(rs == 1)
			System.out.println("Succefully update");
		else
			System.out.println("There are not this product");
		
	}
	
	// delete a product of user
	private void deleteProduct(User user) {		
		System.out.println("Delete product");
		Product product = new Product();
		
		System.out.print("Give product name: ");
		product.setProduct_name(sc.nextLine());

		int rs =sql.deleteProduct(user, product);
	
		if(rs == 1)
			System.out.println("Succefully delete");
		else
			System.out.println("There are not this product");
		
	}
}
