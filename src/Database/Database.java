package Database;
import UserSystem.RegisteredUser;
import java.util.HashMap;
import UserSystem.Admin;

public class Database {
	HashMap<String,Admin> AdminDatabase;
	HashMap<String,RegisteredUser> UserDatabase;

	String sudo_acc;
	String sudo_pwd;


	/* Database constructor */
	public Database(String sudo_acc){
		this.sudo_acc = sudo_acc;
		//set password of first Admin default to "12345"
		this.sudo_pwd = "12345";

		//Initialize two database
		AdminDatabase = new HashMap<String,Admin>();
		UserDatabase = new HashMap<String,RegisteredUser>();

		//Add first admin to the AdminDataBase
		Admin sudo = new Admin(sudo_acc,sudo_pwd);
		AdminDatabase.put(sudo_acc,sudo);
	}


	/* --- User Database Functions --- */
	public HashMap<String,RegisteredUser> getUsers(){
		return UserDatabase;
	}

	public RegisteredUser getUser(String userName) {
		return UserDatabase.get(userName);
	}

	/* Check if the user exists in the user database */
	public boolean userExists(String userToCheck) {
		return UserDatabase.containsKey(userToCheck);
	}

	/* Add user to the UserDatabase
	 * set the key of the hash map to be the username
	 * if user already exists, return false
	 */
	public boolean addUser(RegisteredUser userToAdd) {
		if(this.userExists(userToAdd.getUserName)) {
			return false;
		}
		this.UserDatabase.put(userToAdd.getUserName(), userToAdd);
		return true;
	}

	public boolean removeUser(RegisteredUser userToDelete) {
		RegisteredUser removed = this.UserDatabase.remove(userToDelete.getUserName());
		if (removed==null) {
			return false;
		}
		return true;
	}

	public boolean userUpdatePwd(String userToUpdate, String pwd) {
		if(this.userExists(userToUpdate)) {
			this.getUser(userToUpdate).setNewPassword(pwd);
			return true;
		}
		return false;
	}
	
	public RegisteredUser verifyUser(String userName, String pwd) {
		if(this.userExists(userName)) {
			if(this.getUser(userName).getPassword()==pwd) {
				return this.getUser(userName);
			}
		}
		return null;
	}
	

	/* --- Admin Database Functions --- */
	public HashMap<String,Admin> getAdmins(){
		return AdminDatabase;
	}

	public Admin getAdmin(String userName) {
		return AdminDatabase.get(userName);
	}

	/* Check if the admin exists in the user database */
	public boolean adminExists(String userName) {
		return AdminDatabase.containsKey(userName);
	}

	/* Add admin to the AdminDatabase
	 * set the key of the hash map to be the username
	 * if user already exists, return false
	 */
	public boolean addAdmin(Admin adminToAdd) {
		if(this.adminExists(adminToAdd.getUserName())) {
			return false;
		}
		this.AdminDatabase.put(adminToAdd.getUserName(), adminToAdd);
		return true;
	}

	public boolean removeAdmin(Admin adminToDelete) {
		Admin removed = this.AdminDatabase.remove(adminToDelete.getUserName());
		if (removed==null) {
			return false;
		}
		return true;
	}

	public boolean adminUpdatePwd(String adminToUpdate, String pwd) {
		if(this.adminExists(adminToUpdate)) {
			this.getAdmin(adminToUpdate).updatePwd(pwd);
			return true;
		}
		return false;
	}
	
	public Admin verifyAdmin(String userName, String pwd) {
		if(this.adminExists(userName)) {
			if(this.getAdmin(userName).getPassword()==pwd) {
				return this.getAdmin(userName);
			}
		}
		return null;
	}


	/* --- Database Class Functions --- */
	public boolean adminDatabaseIsEmpty() {
		return this.AdminDatabase.isEmpty();
	}

	public boolean userDatabaseIsEmpty() {
		return this.UserDatabase.isEmpty();
	}
}
