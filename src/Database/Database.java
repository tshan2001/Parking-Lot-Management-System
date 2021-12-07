package Database;
import UserSystem.RegisteredUser;
import TicketingSystem.Ticket;
import java.util.HashMap;
import UserSystem.Admin;

public class Database {
	HashMap<String,Admin> AdminDatabase;
	HashMap<String,RegisteredUser> UserDatabase;
	HashMap<String,Ticket> TicketDatabase;
	
	String name;
	
	String sudo_acc;
	String sudo_pwd;
	
	
	
	/* Database default constructor */
	public Database(){
		this.name = "lot";
		//set userName of first Admin default to "admin1"
		this.sudo_acc = "admin1";
		//set password of first Admin default to "12345"
		this.sudo_pwd = "12345";
		
		//Initialize two database
		AdminDatabase = new HashMap<String,Admin>();
		UserDatabase = new HashMap<String,RegisteredUser>();
		TicketDatabase = new HashMap<String,Ticket>();
		
		//Add first admin to the AdminDataBase
		Admin sudo = new Admin(sudo_acc,sudo_pwd);
		AdminDatabase.put(sudo_acc,sudo);
	}


	/* --- User Database Functions --- */
	public HashMap<String,RegisteredUser> getUsers(){
		return UserDatabase;
	}

	public RegisteredUser getUser(String userName) {
		if(UserDatabase.containsKey(userName)) {
			return UserDatabase.get(userName);
		}else {
			return new RegisteredUser();
		}
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
		if(this.userExists(userToAdd.getUserName())) {
			return false;
		}
		this.UserDatabase.put(userToAdd.getUserName(), userToAdd);
		return true;
	}

	public boolean removeUser(RegisteredUser userToDelete) {
		if(userToDelete == null) {
			return false;
		}
		if(this.UserDatabase.containsKey(userToDelete.getUserName())) {
			this.UserDatabase.remove(userToDelete.getUserName());
			return true;
		}else {
			return false;
		}
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
			if(this.getUser(userName).getPassword().equals(pwd)) {
				return this.getUser(userName);
			}
		}
		return new RegisteredUser();
	}
	

	/* --- Admin Database Functions --- */
	public HashMap<String,Admin> getAdmins(){
		return AdminDatabase;
	}

	public Admin getAdmin(String userName) {
		if(AdminDatabase.containsKey(userName)) {
			return AdminDatabase.get(userName);
		}else {
			return new Admin();
		}
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
		if(this.adminExists(adminToAdd.getUsername())) {
			return false;
		}
		this.AdminDatabase.put(adminToAdd.getUsername(), adminToAdd);
		return true;
	}

	public boolean removeAdmin(Admin adminToDelete) {
		if(adminToDelete == null) {
			return false;
		}
		if(this.AdminDatabase.containsKey(adminToDelete.getUsername())) {
			this.AdminDatabase.remove(adminToDelete.getUsername());
			return true;
		}else {
			return false;
		}
	}

	public boolean adminUpdatePwd(String adminToUpdate, String pwd) {
		if(this.adminExists(adminToUpdate)) {
			this.getAdmin(adminToUpdate).changePassword(pwd);
			return true;
		}
		return false;
	}
	
	public Admin verifyAdmin(String userName, String pwd) {
		
		if(this.adminExists(userName)) {
			if(this.getAdmin(userName).getPassword().equals(pwd)) {
				return this.getAdmin(userName);
			}
		}
		return new Admin();
	}


	/* --- Ticket Database Functions --- */
	public HashMap<String,Ticket> getTickets(){
		return TicketDatabase;
	}

	public Ticket getTicket(String TicketID) {
		if(TicketDatabase.containsKey(TicketID)) {
			return TicketDatabase.get(TicketID);
		}else {
			return new Ticket();
		}
	}

	public boolean ticketExists(String ticketToCheck) {
		return TicketDatabase.containsKey(ticketToCheck);
	}

	public boolean addTicket(Ticket ticket) {
		if(this.ticketExists(ticket.getID())) {
			return false;
		}
		this.TicketDatabase.put(ticket.getID(), ticket);
		return true;
	}

	public boolean removeTicket(Ticket ticket) {
		if(ticket == null) {
			return false;
		}
		if(this.TicketDatabase.containsKey(ticket.getID())) {
			this.TicketDatabase.remove(ticket.getID());
			return true;
		}else {
			return false;
		}
	}

	/* --- Database Class Functions --- */
	public boolean adminDatabaseIsEmpty() {
		return this.AdminDatabase.isEmpty();
	}

	public boolean userDatabaseIsEmpty() {
		return this.UserDatabase.isEmpty();
	}
	
	public String getSudoID() {
		return this.sudo_acc;
	}
	
	public String getSudoPwd() {
		return this.sudo_pwd;
	}

}
