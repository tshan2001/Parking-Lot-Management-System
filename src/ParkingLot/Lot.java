package ParkingLot;

public class Lot {
	int MAXSIZE_Disa;
	int MAXSIZE_Comp;
	int MAXSIZE_Reg;
	int MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
	Spot[] ParkingSpots;

	public Lot() {
		this.MAXSIZE_Reg = 100;
		this.MAXSIZE_Disa = 10;
		this.MAXSIZE_Comp = 10;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
	}

	public Lot(int Reg, int Disa, int Comp) {
		this.MAXSIZE_Reg = Reg;
		this.MAXSIZE_Disa = Disa;
		this.MAXSIZE_Comp = Comp;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
	}

	public static void main(String args[]) {
		Lot lot1 = new Lot();
		System.out.println(lot1.MAXS);
	}

}


