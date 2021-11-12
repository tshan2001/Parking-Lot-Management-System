package UserSystem;

public class Admin {
	int AdminId;
	String AdminUsername;
	String AdminPassword;
	
	public Admin(String username, String password) {
		this.AdminUsername = username;
		this.AdminPassword = hashing(password);
	}
	
	
	
	public static String hashing(String password) {
		return password;
	}
}

