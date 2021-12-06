package ParkingLot;

public class Spot {
	int SpotId;
	boolean availabilty;
	int type; /* 0 represent regular, 1 represent compact, 2 represent disability */
	boolean register;
	
	public Spot() {
		this.SpotId = -1;
		this.availabilty = false;
		this.type = -1;
		this.register = false;
	}


	public Spot(int i, int type) {
		this.SpotId = i;
		this.availabilty = true;
		this.type = type;
		this.register = false;
	}
	
	
	public void changeReg(boolean reg) {
		/* change the register state of the spot */
		this.register = reg;
		
	}
	
	public void changeState(boolean avail) {
		this.availabilty = avail;
	}


}
