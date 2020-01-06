package products;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import database.QueryProductDB;
import users.User;

public class ProductSubmit {

	private static Product product;
	
	public static String addProduct(User user, String productName, String product_id, int quantity,	String description) {
		String errors = "";

		if (!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", productName))
			errors += "Product name contains unaccetable charachers.\n";
		if (quantity < 1)
			errors += "The quantity is not accetable.\n";

		if (errors.isEmpty()) {
			product = new Product();
			product.setProduct_name(productName);
			product.setProduct_id(product_id);
			product.setQuantity(quantity);
			product.setDescription(description);

			if (!QueryProductDB.existProductName(product.getProduct_name(), user.getUsername())) {
				if (QueryProductDB.addProduct(user, product))
					errors += "Something has going wrong with add product";
			}
			else
				errors += "This product belongs your already";
		}
		return errors;
	}
	
	public static String updateProduct(User user, String productName, int quantity, String description) {
		String errors = "";
		
		if (!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", productName))
			errors += "Product name contains unaccetable charachers.\n";
		if (quantity < 1)
			errors += "The quantity is not accetable.\n";

		if (errors.isEmpty()) {
			product = new Product();
			product.setProduct_name(productName);
			product.setQuantity(quantity);
			product.setDescription(description);

			if (QueryProductDB.existProductName(product.getProduct_name(), user.getUsername())) {
				if (QueryProductDB.updateProduct(user, product))
					errors += "Something has going wrong with add product";
			}
			else
				errors += "There is not this product";
		}
		
		return errors;
	}

	public static List<Product> viewProduct(User user) {
		List<Product> listOfProducts = new ArrayList<Product>();
		List<Object> listOfObject = null; // First (1) value is if there is errors, Second (2) value is List Product
		
		listOfObject = QueryProductDB.viewProducts(user);
		
		if(!(boolean)listOfObject.get(0)) {
			listOfProducts = (List<Product>)listOfObject.get(1);
		}		
		return listOfProducts;
	}

	public static String deleteProduct(User user, Product product) {
		String errors ="";
		if(QueryProductDB.deleteProduct(user, product))
			errors ="Something going wrong with Delete Product";
		
		return errors;
	}
}
