package ParkingLot;
import java.util.Scanner;

import Database.Database;
import UserSystem.Admin;
import UserSystem.RegisteredUser;
import TicketingSystem.Key;
import TicketingSystem.TicketMachine;
import TicketingSystem.Ticket;


public class Lot {
	public static int page = 0;
	public static String[] cmds = {"<Main Page>: Please enter the option number: \n	1: Sign in As Admin \n	2: Sign in As User \n	3. Parking \n	4. Register for User \n 	5. Advance Setting \n 	6. Display Parking Lot \n",
			"<Admin>: Entering Comands for next operation: \n 	1. Add User \n 	2. Remove User \n 	3. Add Admin\n 	4. remove Admin\n 	5. change rate\n 	6. Update Password\n 	7. Update Admin Password\n	8. Back to main\n",
			"<User>: Entering Comands for next operation: \n 1. Change password \n  2. Add Credit \n  3. Add Vechicle \n  4. Change email \n  5. Cancel MemberShip \n  6. Membership register \n  7. Remove Vehicle \n  8. Back to main \n",
			"<Parking>: Entering Commands for next \n 	1. Registered User parking \n 	2. One time parking \n 	3. Registered user leaving \n 	4. One time parking leaving \n 	5. Display current lot availability \n 	6. Return to main page \n"};
	public static Admin temp_admin = new Admin();
	
	public static RegisteredUser temp_user = new RegisteredUser("ydai23","dai123","ydai@bu.edu", 455, false, 0);
	String result = temp_user.getUserName();
	String UserPwd = temp_user.getPassword();
	
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
		this.ParkingSpots = new Spot[MAXS]; /* Generate such number for spots class */
		this.rate=10;
		this.db = new Database();


		/* Update each spot with its type */
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

	public Lot(int Reg, int Disa, int Comp, int price) {
		/* The specified constructor for the parking lot*/
		this.MAXSIZE_Reg = Reg;
		this.MAXSIZE_Disa = Disa;
		this.MAXSIZE_Comp = Comp;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
		this.rate = price;
		this.db = new Database();
		this.machine = new TicketMachine("The Machine", this, this.db);

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
		Scanner main_scanner = new Scanner(System.in);
		switch (main_cmd) {
			case 1:

				System.out.print("<Admin>: Enter Admin Username:	");
				String username = main_scanner.nextLine();
				System.out.print("<Admin>: Enter Admin Password:	");
				String pwd = main_scanner.nextLine();
				temp_admin = this.db.verifyAdmin(username, pwd);
				page = 1;

				if (temp_admin.getUsername() == "do not exist") {
					System.out.println("<Admin>: Invalid Username or Passward.");
					page = 0;
				}
				break;
			case 2:
				this.db.addUser(temp_user);
				System.out.println(result);
				System.out.println(UserPwd);
				System.out.print("<User>: Enter Username:     ");
				String user = main_scanner.nextLine();
			
				System.out.print("<User>: Enter Password:      ");
				String password = main_scanner.nextLine();
				temp_user = this.db.verifyUser(user,password);
			
				page = 2;
				if(temp_user == null) {
					System.out.println("<User>: Invalid Username or Password.");
					System.out.println("<User>: Are you a User yet? If not please register!");
					page = 0;
				}
				break;
			case 3:
				System.out.println("<Parking>: Please select parking options");
				page = 3;

			case 6:
				System.out.println("<Display>: Current Parking Lot has: ");
				System.out.println("	Regular Spots: " + this.getReg());
				System.out.println("	Compact Spots: " + this.getComp());
				System.out.println("	Disability Spots: " + this.getDisa());
				break;
		}
	}

	public void admin_cmd(int cmd, Admin admin, Lot lot) {
		Scanner admin_scanner = new Scanner(System.in);
		switch (cmd) {
		case 1:
			System.out.print("<Admin>: Enter Username: ");
			String username = admin_scanner.nextLine();
			System.out.print("<Admin>: Enter Password: ");
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
			admin.changePriceAdmin(Integer.parseInt(new_rate), lot);
			System.out.println("Successfully changed rate, the new rate is: " + lot.getPrice());
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
			page = 0;

		}
	}
	
	//"<User>: Entering Comands for next operation: \n 1. Change password \n  2. Add Credit \n  3. Add Vechicle \n  4. Change email \n  5. Cancel MemberShip \n  6. Membership register \n  7. Remove Vehicle \n  8. Back to main \n"
	public void user_cmd(int cmd, RegisteredUser user, Lot mylot) {
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
			String cartype = user_scanner.nextLine();
			if(spotType == "yes") {
				user.addVehicle(plate, true, cartype);
			}
			else {
				user.addVehicle(plate, false, cartype);
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
			
			Key key = new Key(user.getUserName(), 0, mylot);
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

	// "<Parking>: Entering Commands for next \n 	1. Registered User parking \n 	2. One time parking \n 	3. Registered user leaving \n 	4. One time parking leaving \n 	5. Display current lot available \n 	6. Return to main page \n"
	public void parking_cmds(int cmd, Admin admin){
		Scanner scanner = new Scanner(System.in);
		switch (cmd) {
			case 1:
				System.out.println("To enter the lot, Please scan your Key fob or enter your fob ID");
				String user = scanner.nextLine();
				Key userkey = this.db.getUser(user).getMemberKey();
				this.machine.registeredEntering(userkey);
				break;
			case 2:
				System.out.println("To enter the lot, Please select a spot type that you need to retrieve a ticket \n    0 - Regular \n    1 - Disability \n    2 - Compact \n");
				int type = scanner.nextInt();
				this.machine.oneTimeParking(type);
				break;
			case 3:
				System.out.println("To leave the lot, Please scan your Key fob or enter your fob ID");
				String userLeaving = scanner.nextLine();
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
					mylot.admin_cmd(cmd, temp_admin, mylot);
					break;
				case 2: /* At user page */
					mylot.user_cmd(cmd, temp_user, mylot);
					break;
				case 3: /* The parking option page */
					mylot.parking_cmds(cmd, temp_admin);
					break;
			}
			System.out.println(" - - - - - - - - -  - - - - -");
			System.out.println("");
			System.out.println(cmds[page]);


		}




	}

}
