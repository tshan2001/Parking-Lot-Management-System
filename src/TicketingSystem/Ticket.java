package TicketingSystem;
import ParkingLot.Lot;



import java.time.Instant;
import java.util.*;

public class Ticket {
	Instant start;
	int ID;
	int pricePer15Min;
	
	public Ticket() {
		this.start = Instant.now();
		Random random = new Random();
		this.ID = random.nextInt(999999);
		this.pricePer15Min = 0;
	}
	
	public Ticket(Lot lot) {
		this.start = Instant.now();
		Random random = new Random();
		this.ID = random.nextInt(999999);
		this.pricePer15Min = lot.getPrice();
	}

	public int getPrice(){
		return this.pricePer15Min;
	}

	public int getID(){
		return this.ID;
	}

	public Instant getStartTime(){
		return this.start;
	}
}
