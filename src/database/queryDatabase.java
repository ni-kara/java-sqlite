package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.sqliteConnection;
import products.Product;
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
			conn.close();
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
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with if there is user ID. \nError message is:  " + e.getLocalizedMessage());
			
			existID = true; //
		}
		
		return existID;
	}

	
	/*
	 * here checked if there are username and password in database
	 * If there is record then take the user_id.
	 */
	public User loginSelect(User user) {
		
		try {
			conn = sqlite.dbConnector();
			String query = "select * from users where password = ? and username = ?";

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getUsername());
		
			
			//if there is record then take the user_id from database
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {		
					if(!rs.getBoolean(1))						
						user.setUser_id(rs.getString("user_id"));
				}
				rs.close();
			}
			
			pst.close();
			conn.close();
		} 
		catch (SQLException e) {
			System.out.println("Something has going wrong with user login. \nError message is:  " + e.getLocalizedMessage());
		}
		
		return user;
	}
	
	/*
	 * Add new product
	 * Take user who had login
	 * Get the new product created by the user
	 */
	public void addProduct(User user, Product product) {
		try {
			conn = sqlite.dbConnector();
			// add product in table products
			String query = "insert into products (product_id,product_name,quantity) values (?,?,?)"; //Insert product

			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, product.getProduct_id());
			pst.setString(2, product.getProduct_name());
			pst.setInt(3, product.getQuantity());
			pst.executeUpdate();
			
			// add the relations product and user
			query = "insert into productOfUser (user_id,product_id) values (?,?)"; // Relate product with user

			pst = conn.prepareStatement(query);
			pst.setString(1, user.getUser_id());
			pst.setString(2, product.getProduct_id());
			pst.execute();
			
			pst.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Something has going wrong with add product. \nError message is:  " + e.getMessage());

		}
	}
	
	// view all products of user
	public List<Product> viewProductUser(User user) {
		List<Product> listOfProducts = new ArrayList<Product>();
		try {
			conn = sqlite.dbConnector();
			String query = "select products.product_id, products.product_name, products.quantity "
						 + "from users, products, productOfUser "
						 + "where users.user_id = productOfUser.user_id and "
						 + "products.product_id = productOfUser.product_id and "
						 + "users.username = ?";					
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				listOfProducts.add(new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getInt("quantity")));
			}
			
			pst.close();
			rs.close();			
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with view product. \nError message is:  " + e.getLocalizedMessage());			
		}		
		return listOfProducts;
	}
	
	//Update an existing user owned product
	public int updateProduct(User user, Product product) {
		int rs; // Result query return 1 only is product exist
		try {
			conn = sqlite.dbConnector();
			String query = "update products "
						 + "set quantity = ? "
						 + "where product_name = ? and "
						 + "exists (select * "
						 		+ "from productOfUser, users "
						 		+ "where productOfUser.product_id = products.product_id and "
						 			  + "productOfUser.user_id = users.user_id and "
						 			  + "users.user_id = ?)";					
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, product.getQuantity());
			pst.setString(2, product.getProduct_name());
			pst.setString(3, user.getUser_id());
			
			rs = pst.executeUpdate();			
			
			pst.close();		
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with update product. \nError message is:  " + e.getLocalizedMessage());	
			rs =0;
		}
		
		return rs;
	}
	
	//Delete an existing user owned product
	public int deleteProduct(User user, Product product) {
		int rs; // Result query return 1 only is product exist
	
		try {
			conn = sqlite.dbConnector();

			conn.createStatement().execute("PRAGMA foreign_keys = ON");			
			
			String query = "delete from products "
						 + "where product_name = ? and "
						 + "exists (select * "
						 		+ "from productOfUser, users "
						 		+ "where productOfUser.product_id = products.product_id and "
						 			  + "productOfUser.user_id = users.user_id and "
						 			  + "users.user_id = ?)";					
		
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, product.getProduct_name());
			pst.setString(2, user.getUser_id());
			
			rs =pst.executeUpdate();			
			
			pst.close();	
			conn.close();
		} 
		catch (Exception e) {
			System.out.println("Something has going wrong with delete product. \nError message is:  " + e.getLocalizedMessage());
			
			rs =0;
		}
		
		return rs;
	}
	
	
	
}
