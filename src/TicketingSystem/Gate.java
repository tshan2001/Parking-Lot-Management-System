package TicketingSystem;

public class Gate {
	int ID;
	boolean is_open;
	
	public Gate() {
		this.is_open=false;
	}

	public Gate(int ID){
		this.ID = ID;
		this.is_open = false;
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
