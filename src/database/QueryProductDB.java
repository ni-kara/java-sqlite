package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import products.Product;
import users.User;

public class QueryProductDB {
	
	static Connection conn = null;
	
	public static boolean addProduct(User user, Product product) {
		boolean error = false;		
		try {
			conn = SqliteConnection.dbConnector();
			String query = "INSERT INTO products (product_id, product_name, quantity, description, owner) VALUES (?,?,?,?,?)";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, product.getProduct_id());
			pst.setString(2, product.getProduct_name());
			pst.setInt(3, product.getQuantity());
			pst.setString(4, product.getDescription());
			pst.setString(5, user.getUsername());
			pst.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with add product. \nError message is:  " + e.getMessage());

			error = true;
		}		
		return error;
	}

	public static boolean existProductName(String productName, String owner) {
		boolean existProductName  =	false;
	
		try {
			conn = SqliteConnection.dbConnector();
			String query ="SELECT * FROM products WHERE product_name = ? and owner = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, productName);
			pst.setString(2, owner);
			
			ResultSet rs =pst.executeQuery();
			existProductName = rs.next();
			
			pst.close();
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with exist product name. \nError message is:  " + e.getMessage());

		} 		
		return existProductName;
	}
	
	public static boolean updateProduct(User user, Product product) {
		boolean error = false;
		try {
			conn = SqliteConnection.dbConnector();
			String query = "UPDATE products "
						 + "SET quantity = ?, "
						 + "description = ? "
						 + "WHERE product_name = ? AND "
						 + "owner = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, product.getQuantity());
			pst.setString(2, product.getDescription());
			pst.setString(3, product.getProduct_name());
			pst.setString(4, user.getUsername());
			
			pst.executeUpdate();
			
			pst.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with update product. \nError message is:  " + e.getLocalizedMessage());	
			error = true;
		}
		return error;
		
	}

	public static List<Object> viewProducts(User user) {
		List<Product> listOfProducts = new ArrayList<Product>();
		List<Object> listOfObject = new ArrayList<Object>();		
		boolean error = false; 
		
		try {
			conn = SqliteConnection.dbConnector();
			String query = "SELECT * "
					 	 + "FROM products "
						 + "WHERE owner = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				listOfProducts.add(new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getInt("quantity"), rs.getString("description")));
			}
			
			pst.close();
			rs.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Something has going wrong with view product. \nError message is:  " + e.getLocalizedMessage());			
			error = true;
		}
		
		listOfObject.add(error);
		listOfObject.add(listOfProducts);
		
		return listOfObject;		
	}

	public static boolean deleteProduct(User user, Product product) {
		boolean error =false;		
		try {
			conn = SqliteConnection.dbConnector();
			String query = "DELETE FROM products "
					+ "WHERE product_name = ? AND "
					+ "owner = ?";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, product.getProduct_name());
			pst.setString(2, user.getUsername());
			
			pst.executeUpdate();
			
			pst.close();
			conn.close();
		}
		catch(Exception e) {
			System.out.println("Something has going wrong with delete product. \nError message is:  " + e.getLocalizedMessage());			
			
			error = true;
		}
		
		return error;
	}
}
