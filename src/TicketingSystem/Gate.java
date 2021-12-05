package TicketingSystem;

public class Gate {
	boolean is_open;
	
	public Gate() {
		this.is_open=false;
	}
	
	public void open(){
		this.is_open = true;
	}

	public void close(){
		this.is_open = false;
	}

	public boolean getStatus(){
		return this.is_open;
	}
}
