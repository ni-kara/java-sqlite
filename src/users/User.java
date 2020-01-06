package users;

public class User extends Person{
	
	private String user_id="";
	private String username;	
	private String password;
	private String category;
	
	public User(){		
	}
	
	public User(Person person, String username, String password, String user_id,  String category) {
		super(person);
		this.setPassword(password);
		this.username= username;
		this.user_id = user_id;
		this.category = category;
	}
	
	public User(String firstName, String lastName, String username, String password, String user_id, String category) {
		super(firstName, lastName);
		this.username= username;
		this.setPassword(password); 
		this.user_id = user_id;
		this.category = category;
	}
	
	public  String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = Encoder.getEndcoder(password);
	}
	

}
