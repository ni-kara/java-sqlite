package products;

public class Product {
	
	private String product_id;
	private String product_name;
	private int quantity;
	
	public Product() {
		
	}
	
	public Product(String product_id, String product_name, int quantity) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
