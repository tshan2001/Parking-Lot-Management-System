package TicketingSystem;

public class Gate {
	boolean is_open;
	
	public Gate() {
		this.is_open=false;
	}
	
	public void openForCars() {
		this.is_open = true;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Error: The Gate is broken");
			System.exit(0);
		}
		this.is_open = false;
	}
	
	public void hold() {
		this.is_open = true;
	}
	
	public void cancelHold() {
		this.is_open = false;
	}
}
