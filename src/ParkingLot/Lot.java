package ParkingLot;
import java.util.Scanner;

import Database.Database;
import UserSystem.Admin;
import UserSystem.RegisteredUser;;


public class Lot {
	public static int page = 0;
	public static String[] cmds = {"<Main Page>: Please enter the option number: \n	"
			+ "1: Sign in As Admin \n	2: Sign in As User \n	3. Parking \n	4. "
			+ "Register for User \n 	5. Display Parking Lot\n",
			"<Admin>: Entering Comands for next operation: \n 	1. Add User \n 	"
			+ "2. Remove User \n 	3. Add Admin\n 	4. Remove Admin\n 	"
			+ "5. Change rate\n 	6. Update Password\n 	7. Advanced Setting\n 	8. Back to main\n"
			+ " 	9. Log out\n",
			"<Advanced Setting>: Entering Commands for next operation:\n 	1. Sudo Shut-down\n 	"
			+ "2. Sudo Restart\n 	3. Start New Lot\n 	4. Back to Admin page", 
			"<User>: 1. \n 	2. \n" , "<Parking>: Entering Commands for next \n 	1. Registered User parking \n 	"
					+ "2. One time parking \n 	3. Registered user leaving \n 	4. One time parking leaving \n 	"
					+ "5. Display current lot availability \n 	6. Return to main page \n"};
	/* page 0 = main page
	 * page 1 = Admin page
	 * page 2 = Setting page
	 * page 3 = User Page
	 * page 4 = Parking Page
	 * page 5 = Register Page
	 * page 6 = NYI
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
	int numSpots;
	int rate; /* This parking lot uses flat rate, in dollars  */
	Spot[] ParkingSpots; /* The array for each spot */
	Database db;

	public Lot() {
	/* The default constructor for the parking lot*/
		this.MAXSIZE_Reg = 100;
		this.MAXSIZE_Disa = 10;
		this.MAXSIZE_Comp = 10;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.num_reg = MAXSIZE_Reg;
		this.num_disa = MAXSIZE_Disa;
		this.num_comp = MAXSIZE_Comp;
		this.ParkingSpots = new Spot[MAXS]; /* Generate such number for spots class */
		this.rate=10;
		this.db = new Database();
		
		
		/* Update each spot with its type */
		for (int i=0; i<MAXSIZE_Reg;i++) {
			this.ParkingSpots[i] = new Spot(i,0);
		}
		
		for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
			this.ParkingSpots[i] = new Spot(i,1);
		}
		
		for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
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
			this.ParkingSpots[i] = new Spot(i,false,false);
		}
		
		for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
			this.ParkingSpots[i] = new Spot(i,true,false);
		}
		
		for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
			this.ParkingSpots[i] = new Spot(i,false,true);
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
		return this.numSpots;
	}
	
	public void removeSpotReg(int SpotId) {
	/* Given the spot, remove this Spot as a registered Spot */
		this.ParkingSpots[SpotId].register = false;
		this.ParkingSpots[SpotId].availabilty = true;
	}
	
	public Spot assignSpotReg(int type, boolean in) {
		/* Assign Spot to a Registered User
		 * 0 represent regular
		 * 1 represent disability
		 * 2 represent compact*/
	
		
		switch(type) {
		case 0:
			for (int i=0; i<MAXSIZE_Reg;i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
					this.num_reg -= 1;
					return this.ParkingSpots[i];
				}
			}
		case 1:
			for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
					this.num_disa -= 1;
					return this.ParkingSpots[i];
				}
			}
			
		case 2:
			for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
					this.num_comp -= 1;
					return this.ParkingSpots[i];
				}
			}
		default:
			return new Spot();
			
		}

	}
	
	public void freeSpotOneTime(int spot_id) {
		this.ParkingSpots[spot_id].availabilty = true;
	}
	
	public void freeSpotReg(int spot_id) {
		this.ParkingSpots[spot_id].availabilty = true;
		this.ParkingSpots[spot_id].register = true;
		this.num_reg += 1;
	}


	public Spot assignSpotOneTime(int type) {
		/* Assign Spot to a one time parking
		 * 0 represent regular
		 * 1 represent disability
		 * 2 represent compact*/
		this.numSpots -= 1;
		
		switch(type) {
		case 0:
			for (int i=0; i<MAXSIZE_Reg;i++) {
				if (this.ParkingSpots[i].availabilty == false) {
					this.ParkingSpots[i].changeReg(true);
					return this.ParkingSpots[i];
				}
			}
		case 1:
			for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
				if (this.ParkingSpots[i].availabilty == false) {
					this.ParkingSpots[i].changeReg(true);
					return this.ParkingSpots[i];
				}
			}
			
		case 2:
			for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
				if (this.ParkingSpots[i].availabilty == false) {
					this.ParkingSpots[i].changeReg(true);
					return this.ParkingSpots[i];
				}
			}
		default:
			return new Spot();
			
		}
		
		
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
			page = 3;
				
			if (temp_user.getUserName().equals("do not exist")) {
				System.out.println("<User>: Invalid Username or Passward.");
				page = 0;
			}
			break;
		case 3: /* Go to parking */
			page = 4;
			break;
		case 4: /* Register for user */
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
		case 7:
			page = 2;
			break;
		case 8:
			page = 0;
			break;
		case 9:
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
				page = 2;
			}else {
				System.out.println("Invalid Input, Shutdown is cancelled");
				page = 2;
			}
			break;
		case 4:
			page = 1;
			break;
		
		}
		
	}
	
	public void user_cmd(int cmd, RegisteredUser user) {
		/* commands for page 3 sign in as user */
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
				System.out.println("NYI");
				break;
			case 5:
				System.out.println("Available Regular Spots: " + this.num_reg + "\n");
				System.out.println("Available Disability Spots: " + this.num_disa + "\n");
				System.out.println("Available Compact Spots: " + this.num_comp + "\n");
				break;
			case 6:
				break;

		}
	}
	
	
	public void register_cmd(int cmd) {
		/* commands for page 5 register page */
		
	}
	
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
				mylot.register_cmd(cmd);
				break;
			}
			System.out.println(" - - - - - - - - - - - - - - ");
			System.out.println("");
			System.out.println(cmds[page]);
			

		}
		
		
		

	}

}


