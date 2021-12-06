package UserSystem;
import ParkingLot.Lot;
import Database.Database;


import java.util.HashMap;
import java.util.Scanner;

public class Admin { 
	
	HashMap<String, String> Admins;
	/* a unique attribute of the admin class */
	String AdminUsername;
	/* used for logins */
	String AdminPassword;
	
	public Admin() {
		this.AdminUsername = "do not exist";
		this.AdminPassword = "do not exist";
	}
	public Admin(String username, String password) {		
		this.AdminUsername = username;
		this.AdminPassword = password;

	}
	
	/*returns the username of Admin*/
	public String getUsername() {
		return this.AdminUsername;
	}
	
	/*returns the password of Admin*/
	public String getPassword() {
		return this.AdminPassword;
	}
	
	/*resets the password of Admin*/
	public void changePassword(String p) {
		this.AdminPassword = p;
	}
	
	/*returns true if the password given matches the password*/
	/*for the given Admin, and false otherwise*/
	public boolean verifyPassword(Admin a, String p){
		if(p == a.getPassword()) {
			return true;
		}else {
			System.out.println("Password does not match, access denied.");
			return false;
		}
	}
	
	/* manually changes the price rate for the parking lot */
	public void changePriceAdmin(int price, Lot thelot) {
		thelot.changePrice(price);
	}
	
	/* calling methods in Database class to add user, output lines correspondingly*/
	public void addUser(RegisteredUser s, Database db) {
		if(db.addUser(s)) {
			System.out.println("User successfully added!");
		}else {
			System.out.println("User already Exist.");
		}
	}
	
	/* calling methods in Database class to remove user, output lines correspondingly*/
	public void removeUser(RegisteredUser s, Database db) {
		if(db.removeUser(s)) {
			System.out.println("User successfully removed!");
		}else {
			System.out.println("User does not exist.");
		}
	}
	
	/* calling methods in Database class to remove Admin, output lines correspondingly*/
	public void removeAdmin(Admin a, Database db) {
		if(db.removeAdmin(a)) {
			System.out.println("Admin successfully removed!");
		}else {
			System.out.println("Admin does not exist.");
		}
	}
	
	/* calling methods in Database class to add Admin, output lines correspondingly*/
	public void addAdmin(Admin a, Database db) {
		if(db.addAdmin(a)) {
			System.out.println("Admin successfully added!");
		}else {
			System.out.println("Admin already exist.");
		}
	}
	
	public void admin_cmd(int cmd, Database db) {
		Scanner admin_scanner = new Scanner(System.in);
		switch (cmd) {
		case 1:
			System.out.print("<Admin>: Enter Username:	");
			String username = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Password:	");
			String pwd = admin_scanner.nextLine();
			RegisteredUser user = new RegisteredUser(username, pwd);
			this.addUser(user, db);
		}
		admin_scanner.close();
	}
	
	
}

