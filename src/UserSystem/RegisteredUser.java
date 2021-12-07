package UserSystem;

import TicketingSystem.Key;

public class RegisteredUser {
	
	public int maxicars = 3;

	String username;
	String password;
	String email;
	boolean membership;
	boolean disability;
	Key Key;


	/* default constructor for user */
	public RegisteredUser() {
		this.disability = false;
		this.username = "do not exist";
		this.password = "do not exist";
		this.email = "do not exist";
		this.membership = false;
		this.Key = null;
	}

	/* user constructor with input values */
	public RegisteredUser(String usern, String pwd) {
		this.username = usern;
		this.password = pwd;
	}

	/* user constructor with input values */
	public RegisteredUser(String usern, String pwd, String email, boolean disa) {
		this.disability = disa;
		this.username = usern;
		this.password = pwd;
		this.email = email;
		this.membership = false;
		this.Key = null;
	}

	public void memberRegister(Key Key) {
		this.membership = true;
		this.Key = Key;
	}

	public Key getMemberKey(){
		return this.Key;
		}

	public void cancel() {
		this.membership = false;
		this.Key = null;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	public boolean isDisable(){
		return this.disability;
	}
	
	public String getPassword (){
		return this.password;
	}
	
	public void setNewPassword(String pwd){
		this.password = pwd;
	}
	
	public void setNewEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void changeEmail(String email) {
		this.email = email;
		return;
	}
	
	public boolean isMember(){
		return this.membership;
	}
	
}
