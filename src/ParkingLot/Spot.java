package ParkingLot;

public class Spot {
	int SpotId;
	boolean availabilty;
	int type;
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
		this.type = ty[e;
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
