package TicketingSystem;

public class Payment {
	public String[] AllMethod = {"Apple_Pay", "Credit_Card","Cash"};
	
	String PaymentMethod;
	int AmountDue;
	boolean isSuccess;
	boolean dueReceived;
	
	public Payment() {
		this.PaymentMethod = "Payment created unsuccessfully";
		this.AmountDue = -1;
		this.isSuccess = false;
		this.dueReceived = false;
	}
	
	public Payment(int type, int amount) {
		this.PaymentMethod = AllMethod[type];
		this.AmountDue = amount;
		this.isSuccess = false;
		this.dueReceived = false;
	}
	
	public boolean process() {
		if (this.dueReceived == true) {
			this.isSuccess = true;
			return true;
		}else {
			return false;
		}
	}
	
}
