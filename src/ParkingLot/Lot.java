package ParkingLot;
import java.util.Scanner;

import Database.Database;
import TicketingSystem.Key;
import TicketingSystem.TicketMachine;
import UserSystem.Admin;
import UserSystem.RegisteredUser;;


public class Lot {
	public static int page = 0;
	public static String[] cmds = {
		"<Main Page>: Please enter the option number: \n	"
			+ "1: Sign in As Admin \n	2: Sign in As User \n	3. Parking \n	4. "
			+ "Register for User \n 	5. Display Parking Lot\n",
		"<Admin>: Entering Comands for next operation: \n 	1. Add User \n 	"
			+ "2. Remove User \n 	3. Add Admin\n 	4. Remove Admin\n 	"
			+ "5. Change rate\n 	6. Update Password\n 	7. Update Admin Password\n 	8. Advanced Setting\n 	9. Back to main\n 	10. Log out\n",
			"",
		"<User>: Entering Comands for next operation: \n	1. Change password \n 	2. Add Credit \n" +  
			"	3. Add Vechicle \n	4. Change email \n	5. Cancel MemberShip \n	6. Membership register \n	7. Remove Vehicle \n  " +
			"	8. Back to main \n", 
		"<Parking>: Entering Commands for next \n 	1. Registered User parking \n 	"
					+ "2. One time parking \n 	3. Registered user leaving \n 	4. One time parking leaving \n 	"
					+ "5. Display current lot availability \n 	6. Return to main page \n",
		
		"<Register>: ",
		"<Advanced Setting>: Entering Commands for next operation:\n 	1. Sudo Shut-down\n 	"
			+ "2. Sudo Restart\n 	3. Start New Lot\n 	4. Back to Admin page", 
			};
	/* page 0 = main page
	 * page 1 = Admin page
	 * page 2 = User Page
	 * page 3 = Parking Page
	 * page 4 = Register Page
	 * page 5 = Advance Setting under Admin page
	 * page 7 = NYI
	 * page 8 = NYI
	 * */
	public static Admin temp_admin = new Admin();
	public static RegisteredUser temp_user = new RegisteredUser();
	int MAXSIZE_Disa; /* Spots for Disability  */
	int MAXSIZE_Comp; /* Spots for compact  */
	int MAXSIZE_Reg;  /* Spots for regular  */
	int num_reg;
	int num_disa;
	int num_comp;
	int MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg; /* Total Spots  */
	int num_registered;
	int rate; /* This parking lot uses flat rate, in dollars  */
	Spot[] ParkingSpots; /* The array for each spot */
	Database db;
	TicketMachine machine;

	public Lot() {
	/* The default constructor for the parking lot*/
		this.MAXSIZE_Reg = 100;
		this.MAXSIZE_Disa = 10;
		this.MAXSIZE_Comp = 10;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.num_reg = MAXSIZE_Reg;
		this.num_disa = MAXSIZE_Disa;
		this.num_comp = MAXSIZE_Comp;
		this.num_registered = 0;
		this.ParkingSpots = new Spot[MAXS]; /* Generate such number for spots class */
		this.rate=10;
		this.db = new Database();
		this.machine = new TicketMachine(this,this.db);
		
		
		/* Update each spot with its type */
		for (int i=0; i<MAXSIZE_Reg;i++) {
			/* For regular */
			this.ParkingSpots[i] = new Spot(i,0);
		}
		
		for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Comp);i++) {
			/* For compact */
			this.ParkingSpots[i] = new Spot(i,1);
		}
		
		for (int i=(MAXSIZE_Reg+MAXSIZE_Comp); i<MAXS;i++) {
			/* For disabiltiy */
			this.ParkingSpots[i] = new Spot(i,2);
		}
		
	}

	public Lot(int Reg, int Disa, int Comp, int price) {
		/* The specified constructor for the parking lot*/
		this.MAXSIZE_Reg = Reg;
		this.MAXSIZE_Disa = Disa;
		this.MAXSIZE_Comp = Comp;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
		this.rate = price;
		
		for (int i=0; i<MAXSIZE_Reg;i++) {
			this.ParkingSpots[i] = new Spot(i,0);
		}
		
		for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Comp);i++) {
			this.ParkingSpots[i] = new Spot(i,1);
		}
		
		for (int i=(MAXSIZE_Reg+MAXSIZE_Comp); i<MAXS;i++) {
			this.ParkingSpots[i] = new Spot(i,2);
		}
		
		
	}
	
	public int getReg() {
		return this.num_reg;
	}
	
	public int getComp() {
		return this.num_comp;
	} 
	
	public int getDisa() {
		return this.num_disa;
	}
	
	public int getPrice() {
		/* Return the current rate of the Parking Lot*/
		return this.rate;
	}
	
	public void changePrice(int price) {
		/* Change the current rate of the Parking Lot*/
		this.rate = price;
	}
	
	public int numAvail() {
		return (this.num_reg + this.num_disa + this.num_comp);
	}
	

	public void freeSpotOneTime(int spot_id) {
		if (this.ParkingSpots[spot_id].availabilty == true){
			System.out.println(" ");
			System.out.println(" <ERROR> : The spot is availability right now");
			System.out.println(" ");
			return;
		}
		
		this.ParkingSpots[spot_id].availabilty = true;
		switch(this.ParkingSpots[spot_id].type){
		case 0:
			this.num_reg += 1;
			break;
		case 1:
			this.num_comp += 1;
			break;
		case 2:
			this.num_disa += 1;
			break;
		}
	}
	
	public void freeSpotReg(int spot_id) {
		if (this.ParkingSpots[spot_id].availabilty == true){
			System.out.println(" ");
			System.out.println(" <ERROR> : The spot has not been registered yet");
			System.out.println(" ");
			return;
		}
		
		this.ParkingSpots[spot_id].availabilty = true;
		this.ParkingSpots[spot_id].register = true;
		this.num_registered -= 1;
		switch(this.ParkingSpots[spot_id].type){
		case 0:
			this.num_reg += 1;
			break;
		case 1:
			this.num_comp += 1;
			break;
		case 2:
			this.num_disa += 1;
			break;
		}
	}


	public Spot assignSpotOneTime(int type) {
		/* Assign Spot to a one time parking
		 * 0 represent regular
		 * 1 represent compact
		 * 2 represent disability*/
		
		switch(type) {
		case 0:
			for (int i=0; i<MAXSIZE_Reg;i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.num_reg -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		case 1:
			for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.num_comp -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		case 2:
			for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.num_disa -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		default:
			return new Spot();
			
		}
		return new Spot();
		
		
	}
	
		
	public Spot assignSpotReg(int type) {
		/* Assign Spot to a Registered User
		 * 0 represent regular
		 * 1 represent compact
		 * 2 represent disability*/
		this.num_registered += 1;
		
		switch(type) {
		case 0:
			for (int i=0; i<MAXSIZE_Reg;i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.ParkingSpots[i].register = true;
					this.num_reg -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		case 1:
			for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.ParkingSpots[i].register = true;
					this.num_comp -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		case 2:
			for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
				if (this.ParkingSpots[i].availabilty == true) {
					this.ParkingSpots[i].availabilty = false;
					this.ParkingSpots[i].register = true;
					this.num_disa -= 1;
					return this.ParkingSpots[i];
				}
			}
			break;
		default:
			this.num_registered -= 1;
			return new Spot();
			
		}
		return new Spot();
	}
	
	
	public void mainCmds(int main_cmd) {
		/* commands for page 0 main page */
		Scanner main_scanner = new Scanner(System.in);
		switch (main_cmd) {
		case 1:  /* sign in as admin */
			if (! temp_admin.getUsername().equals("do not exist")) {
				System.out.println("Signed in as " + temp_admin.getUsername());
				page = 1;
				break;
			}
			
			System.out.print("<Admin>: Enter Admin Username:	");
			String username = main_scanner.nextLine();
			System.out.print("<Admin>: Enter Admin Password:	");
			String pwd = main_scanner.nextLine();
			temp_admin = this.db.verifyAdmin(username, pwd);
			page = 1;
				
			if (temp_admin.getUsername().equals( "do not exist")) {
				System.out.println("<Admin>: Invalid Username or Passward.");
				page = 0;
			}
			break;
			
		case 2: /* sign in as user */
			if (! temp_user.getUserName().equals("do not exist")) {
				System.out.println("Signed in as " + temp_user.getUserName());
				page = 3;
				break;
			}
			
			System.out.print("<User>: Enter Username:	");
			username = main_scanner.nextLine();
			System.out.print("<User>: Enter Password:	");
			pwd = main_scanner.nextLine();
			temp_user = this.db.verifyUser(username, pwd);
			if (temp_user.getUserName().equals("do not exist")) {
				System.out.println("<User>: Invalid Username or Passward.");
				page = 0;
			} else {
				page = 3;
//				System.out.println("Jumped to page 3");
			}
			
			break;
		case 3: /* Go to parking */
			System.out.println("<Parking>: Please select parking options");
			page = 4;
			break;
		case 4: /* Register for user */
			System.out.print("<Register>: Choose a username: ");
			String usn = main_scanner.nextLine();
			System.out.print("<Register>: Choose a password: ");
			String p = main_scanner.nextLine();
			RegisteredUser toAdd = new RegisteredUser(usn, p);
			boolean notExists = this.db.addUser(toAdd);
			if(notExists == false) {
				System.out.println(" - - - - - - - - - - - - - - ");
				System.out.println("User already exists, please sign in instead.");
			} else {
				System.out.println(" - - - - - - - - - - - - - - ");
				System.out.println("Successfully registered.");
			}
			page = 0;
			break;
		case 5: /* Display availability */
			System.out.println(" ");
			System.out.println("<Display>: Current Parking Lot has: ");
			System.out.println(" ");
			System.out.println("	Regular Spots: " + this.getReg());
			System.out.println("	Compact Spots: " + this.getComp());
			System.out.println("	Disability Spots: " + this.getDisa());
			System.out.println(" ");
			break;
	
		
		}
		return;
	}
	
	public void admin_cmd(int cmd, Admin admin) {
		/* commands for page 1 sign in as admin */
		Scanner admin_scanner = new Scanner(System.in);
		switch (cmd) {
		case 1:
			System.out.print("<Admin>: Enter Username:	");
			String username = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Password:	");
			String pwd = admin_scanner.nextLine();
			RegisteredUser user = new RegisteredUser(username, pwd);
			admin.addUser(user, db);
			break;
		case 2:
			System.out.print("<Admin>: Enter Username: ");
			String username1 = admin_scanner.nextLine();
			admin.removeUser(db.getUser(username1), db);
			break;
		case 3:
			System.out.print("<Admin>: Enter Admin Username: ");
			String username2 = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Admin Password: ");
			String pwd2 = admin_scanner.nextLine();
			Admin ad = new Admin(username2, pwd2);
			admin.addAdmin(ad, db);
			break;
		case 4:
			System.out.print("<Admin>: Enter Admin Username: ");
			String username3 = admin_scanner.nextLine();
			admin.removeAdmin(db.getAdmin(username3), db);
			break;
		case 5:
			System.out.println("<Admin>: Current rate is " + rate);
			System.out.print("<Admin>: Enter new rate: ");
			String new_rate = admin_scanner.nextLine();
			admin.changePriceAdmin(Integer.parseInt(new_rate), this);
			System.out.println("Successfully changed rate, the new rate is: " + this.getPrice());
			break;
		case 6:
			System.out.print("<Admin>: Enter Username: ");
			String username4 = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Password: ");
			String pwd4 = admin_scanner.nextLine();
			if(db.verifyUser(username4, pwd4) == null) {
				System.out.println("User does not exist or password does not match.");
				break;
			}else {
				System.out.print("<Admin>: Enter New Password: ");
				String new_pwd = admin_scanner.nextLine();
				db.getUser(username4).setNewPassword(new_pwd);
				System.out.println("<Admin>: The new password for the user is: " + db.getUser(username4).getPassword());
				break;
			}
		case 7:
			System.out.print("<Admin>: Enter Admin Username: ");
			String username5 = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Admin Password: ");
			String pwd5 = admin_scanner.nextLine();
			if(db.verifyAdmin(username5, pwd5) == null) {
				System.out.println("Admin does not exist or password does not match.");
				break;
			}else {
				System.out.print("<Admin>: Enter New Password: ");
				String new_pwd1 = admin_scanner.nextLine();
				db.getAdmin(username5).changePassword(new_pwd1);
				System.out.println("<Admin>: The new password for the admin is: " + db.getAdmin(username5).getPassword());
				break;
			}
		case 8:
			page = 5;
			break;
		case 9:
			page = 0;
			break;
		case 10:
			page = 0;
			temp_admin = new Admin();
			System.out.println("You are logged out now");
			break;

		}
		

	}
	
	public void setting_cmd(int cmd) {
		/* commands for page 2 setting */
		Scanner setting_scanner = new Scanner(System.in);
		switch(cmd) {
		case 1:
			System.out.println("Shutting down will not save everything you have right now...");
			System.out.println("You sure you want to shut down the entire system? ");
			System.out.println("(yes / no) ");
			String setting_input = setting_scanner.nextLine();
			if (setting_input.equals("yes")) {
				System.out.println("The system is down. Thank you for supporting our software.");
				System.exit(0);
			}else if(setting_input.equals("no")) {
				page = 5;
			}else {
				System.out.println("Invalid Input, Shutdown is cancelled");
				page = 5;
			}
			break;

		case 2:
			System.out.println("Restart will not save everything you have right now...");
			System.out.println("You sure you want to restart the entire system? ");
			System.out.println("(yes / no) ");
			setting_input = setting_scanner.nextLine();
			if (setting_input.equals("yes")) {
				System.out.println("The system is restarted. Welcome back");
				this.db = new Database();
				this.num_comp = this.MAXSIZE_Comp;
				this.num_disa = this.MAXSIZE_Disa;
				this.num_registered = 0;
				this.ParkingSpots = new Spot[this.MAXS];
				this.machine = new TicketMachine(this,this.db);
				page = 0;
				System.out.println(" ");
				System.out.println("The default admin Username is " + this.db.getSudoID());
				System.out.println("The default admin Password is " + this.db.getSudoPwd());
				System.out.println("Please use the sudo admin to gain access");
				System.out.println(" ");
			}else if(setting_input.equals("no")) {
				page = 5;
			}else {
				System.out.println("Invalid Input, Restart is cancelled");
				page = 5;
			}
			break;	
		case 4:
			page = 1;
			break;
		
		}
		
	}
	
	public void user_cmd(int cmd, RegisteredUser user) {
		/* commands for page 3 sign in as user */
		Scanner user_scanner = new Scanner(System.in);
		switch(cmd) {
		case 1: 
			System.out.print("<User>: Enter your new password: ");
			String password = user_scanner.nextLine();
			user.setNewPassword(password);
			break;
		case 2: 
			System.out.print("<User>: Enter the amount of credit you want to add:  ");
			String credit = user_scanner.nextLine();
			user.addCredit(Integer.parseInt(credit));
			break;
		case 3:
			System.out.print("<User>: Enter your car plate: ");
			String plate = user_scanner.nextLine();
			System.out.print("<User>: Enter your spot type(yes for comp type, no for non com type: ");
			String spotType = user_scanner.nextLine();
			System.out.print("<User>: Enter you car type: ");
			char cartype = user_scanner.nextLine().charAt(0);
			if(spotType == "yes") {
				user.addVehicle(plate, true, "Reg");
			}
			else {
				user.addVehicle(plate, false, "Reg");
			}
			break;
		case 4:
			System.out.print("<User>: Enter your new email: ");
			String email = user_scanner.nextLine();
			user.setNewEmail(email);
			break;
		case 5:
			user.cancel();
			System.out.print("You have successfully canceled your membership");
			break;
		case 6:
			Key key = new Key(user, 0, this);
			user.memberRegister(key);
			System.out.print("You are a member now");
			break;
			
		case 7:
			System.out.print("Enter the car plate to begin the processing of removing specific Vechile");
			String plateNum = user_scanner.nextLine();
			user.removeVehicle(plateNum);
			System.out.print("Car removed");
			break;
		case 8: 
			page = 0;
		
		}
	}
	
	public void parking_cmds(int cmd, Admin admin){
		/* commands for page 4 parking */
		Scanner parking_scanner = new Scanner(System.in);
		switch (cmd) {
			case 1:
				System.out.println("To enter the lot, Please scan your Key fob or enter your fob ID");
				String user = parking_scanner.nextLine();
				Key userkey = this.db.getUser(user).getMemberKey();
				this.machine.registeredEntering(userkey);
				break;
			case 2:
				System.out.println("To enter the lot, Please select a spot type that you need to retrieve a ticket \n    0 - Regular \n    1 - Disability \n    2 - Compact \n");
				int type = parking_scanner.nextInt();
				this.machine.oneTimeParking(type);
				break;
			case 3:
				System.out.println("To leave the lot, Please scan your Key fob or enter your fob ID");
				String userLeaving = parking_scanner.nextLine();
				Key key = this.db.getUser(userLeaving).getMemberKey();
				this.machine.registeredLeave(key);
				break;
			case 4:
				System.out.println("To leave the lot, please scan your ticket or enter your ticket's ID");
				String ticketID = parking_scanner.nextLine();
				this.machine.oneTimeLeave(ticketID);
				break;
			case 5:
				System.out.println("Available Regular Spots: " + this.num_reg + "\n");
				System.out.println("Available Disability Spots: " + this.num_disa + "\n");
				System.out.println("Available Compact Spots: " + this.num_comp + "\n");
				break;
			case 6:
				page = 0;
				break;

		}
	}
	
	
//	public void register_cmd(int cmd) {
//		/* commands for page 5 register page */
//		
//	}
	
	public static void main(String args[]) {
		Lot mylot = new Lot();
		
		System.out.println("Default parking lot and database has generated");
		System.out.println(" ");
		System.out.println("The default admin Username is " + mylot.db.getSudoID());
		System.out.println("The default admin Password is " + mylot.db.getSudoPwd());
		System.out.println("Please use the sudo admin to gain access");
		System.out.println(" ");
		System.out.println(cmds[page]);
		
		Scanner scanner = new Scanner(System.in);

		while (true) {
			int cmd = scanner.nextInt();
			System.out.println(" - - - - - - - - - - - - - - ");
			switch (page) {
			case 0: /* At main page */
				mylot.mainCmds(cmd);
				break;
			case 1: /* At administration page */
				mylot.admin_cmd(cmd, temp_admin);
				break;
			case 2: /* At Setting page */
				mylot.setting_cmd(cmd);
				break;
			case 3: /* At User page; */
				mylot.user_cmd(cmd, temp_user);
				break;
			case 4: /* At Parking page */
				mylot.parking_cmds(cmd, temp_admin);
				break;
			case 5: /* At Register page */
//				mylot.register_cmd(cmd);
				break;
			}
			System.out.println(" - - - - - - - - - - - - - - ");
			System.out.println("");
			System.out.println(cmds[page]);
			

		}
		
		
		

	}

}


