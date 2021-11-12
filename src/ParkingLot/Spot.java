package ParkingLot;

public class Spot {
	int SpotId;
	boolean availabilty;
	boolean disability;
	boolean compact;

	public Spot(int i, boolean disa, boolean comp) {
		this.SpotId = i;
		this.availabilty = true;
		this.disability = disa;
		this.compact = comp;
	}


}
