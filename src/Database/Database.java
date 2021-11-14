package Database;
import UserSystem.RegisteredUser;
import java.util.HashMap;
import UserSystem.Admin;

public class Database {
	HashMap<String,Admin> AdminDatabase = new HashMap<String,Admin>();
	HashMap<String,RegisteredUser> UserDatabase = new HashMap<String,RegisteredUser>();
	
	public Database(){
		String sudo_id = "thelot";
		String sudo_pwd = "12345";
		Admin sudo = new Admin(sudo_id,sudo_pwd);
		AdminDatabase.put((sudo_id+sudo_pwd),sudo);
	}
	
	protected HashMap<String,RegisteredUser> getUsers(){
		return UserDatabase;
	}
	
	protected HashMap<String,Admin> getAdmins(){
		return AdminDatabase;
	}
	
}
