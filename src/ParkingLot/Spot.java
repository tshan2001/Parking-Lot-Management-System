package ParkingLot;

public class Spot {
	int SpotId;
	boolean availabilty;
	boolean disability;
	boolean compact;
	boolean register;
	
	public Spot() {
		this.SpotId = -1;
		this.availabilty = false;
		this.disability = false;
		this.compact = false;
		this.register = false;
	}


	public Spot(int i, boolean disa, boolean comp) {
		this.SpotId = i;
		this.availabilty = true;
		this.disability = disa;
		this.compact = comp;
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
