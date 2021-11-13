package ParkingLot;

public class Lot {
	int MAXSIZE_Disa;
	int MAXSIZE_Comp;
	int MAXSIZE_Reg;
	int MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
	int price_per_hour;
	Spot[] ParkingSpots;

	public Lot() {
		this.MAXSIZE_Reg = 100;
		this.MAXSIZE_Disa = 10;
		this.MAXSIZE_Comp = 10;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
		this.price_per_hour=2;
		
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
		this.MAXSIZE_Reg = Reg;
		this.MAXSIZE_Disa = Disa;
		this.MAXSIZE_Comp = Comp;
		this.MAXS = MAXSIZE_Disa + MAXSIZE_Comp + MAXSIZE_Reg;
		this.ParkingSpots = new Spot[MAXS];
		this.price_per_hour = price;
		
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
		return this.price_per_hour;
	}
	
	public void changePrice(int price) {
		this.price_per_hour = price;
	}

	
	public static void main(String args[]) {
		Lot lot1 = new Lot();
		System.out.println(lot1.ParkingSpots[101].disability);
		System.out.println(lot1.ParkingSpots[101].compact);

	}

}


