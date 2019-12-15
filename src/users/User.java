package users;

public class User extends Person{
	private String user_id="";
	private String password;
	
	public User(){
		
	}
	
	public User(String username, String password, String user_id){
		super(username);
		this.password = password;
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = Encoder.getEndcoder(password);
	}
	

}
