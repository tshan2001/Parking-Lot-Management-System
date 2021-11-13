package TicketingSystem;
import ParkingLot.Lot;

public class Ticket {
	long start;
	int price;
	
	public Ticket() {
		this.start = System.currentTimeMillis();
		this.price = 0;
		
	}
	
	public Ticket(Lot thelot) {
		this.start = System.currentTimeMillis();
		this.price = thelot.getPrice();
		
	}
	
	public int checkout(){
		int total_time = (int)((System.currentTimeMillis() - this.start)/3600000);
		int total = total_time*this.price;
		return total;
		
	}
	
	
	
	public static void main(String args[])  {
		
	}
}
