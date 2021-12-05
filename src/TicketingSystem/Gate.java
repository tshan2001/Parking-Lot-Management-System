package TicketingSystem;

import java.time.Duration;
import java.time.Instant;

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

	public static void main(String args[]){
		Instant start = Instant.now();
		Instant end = Instant.parse("2021-12-05T21:10:00Z");
		System.out.println(start.toString());
		System.out.println(end.toString());
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: "+ timeElapsed.toMillis() / 1000 +" milliseconds");
	}
}
