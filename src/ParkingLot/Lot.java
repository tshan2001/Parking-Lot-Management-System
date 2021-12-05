package ParkingLot;
import Database.Database;;

public class Lot {
	int MAXSIZE_Disa; /* Spots for Disability  */
	int MAXSIZE_Comp; /* Spots for compact  */
	int MAXSIZE_Reg;  /* Spots for regular  */
	int MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg; /* Total Spots  */
	int numSpots;
	int rate; /* This parking lot uses flat rate, in dollars  */
	Spot[] ParkingSpots; /* The array for each spot */

	public Lot() {
	/* The default constructor for the parking lot*/
		this.MAXSIZE_Reg = 100;
		this.MAXSIZE_Disa = 10;
		this.MAXSIZE_Comp = 10;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.numSpots = MAXS;
		this.ParkingSpots = new Spot[MAXS]; /* Generate such number for spots class */
		this.rate=10;
		
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
	
	public Spot assignSpotReg(int type, boolean in) {
		/* Assign Spot to a Registered User
		 * 0 represent regular
		 * 1 represent disability
		 * 2 represent compact*/
		if (in) {
			this.numSpots -= 1;
			
		}else {
			this.numSpots += 1;
		}
		
		switch(type) {
		case 0:
			for (int i=0; i<MAXSIZE_Reg;i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
					return this.ParkingSpots[i];
				}
			}
		case 1:
			for (int i=MAXSIZE_Reg; i<(MAXSIZE_Reg+MAXSIZE_Disa);i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
					return this.ParkingSpots[i];
				}
			}
			
		case 2:
			for (int i=(MAXSIZE_Reg+MAXSIZE_Disa); i<MAXS;i++) {
				if (this.ParkingSpots[i].register == false) {
					this.ParkingSpots[i].changeReg(in);
					this.ParkingSpots[i].availabilty = (! in);
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
	
	public static void main(String args[]) {
		Lot mylot = new Lot();
		Database mydatabase = new Database();
		
		System.out.println("Default parking lot and database has generated");
		System.out.println("The default admin Username is " + mydatabase.getSudoId());
		System.out.println("The default admin Password is " + mydatabase.getSudoPwd());
		System.out.println("Please use the sudo admin to gain access");
		
		
		System.out.println(" ");
		System.out.println("Now there are " + mylot.numAvail() + " free spots");
		mylot.assignSpotReg(0, true);
		System.out.println("Adding one spot to a register");
		System.out.println("Now there are " + mylot.numAvail() + " free spots");
		

	}

}


