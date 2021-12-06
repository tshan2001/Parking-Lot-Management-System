package TicketingSystem;
import ParkingLot.Lot;
import ParkingLot.Spot;
import UserSystem.RegisteredUser;

public class Key {
    RegisteredUser user;
    boolean active;
    Spot spotAssigned;
    int spotType;

    public Key(){
    	/* Default constructor for key */
        this.user = new RegisteredUser();
        this.active = false;
        this.spotAssigned = new Spot();
        this.spotType = -1;
    }

    public Key(RegisteredUser user, int type, Lot lot){
    	/* specified constructor for key */
        this.user = user;
        this.active = true;
        this.spotAssigned = lot.assignSpotReg(type);
        this.spotType = type;
    }

    public void activateAccount(){
    	/* activate the key */
    	if (this.active) {
			System.out.println(" ");
			System.out.println(" <ERROR> : The key is activated right now. ");
			System.out.println(" ");
			return;
    	}
    	
        this.active = true;
    }

    public void deactivateAccount(){
    	/* deactivate the key */
    	if (this.active == false) {
			System.out.println(" ");
			System.out.println(" <ERROR> : The key is deactivated right now. ");
			System.out.println(" ");
			return;
    	}
        this.active = false;
    }

    public String getUserName(){
        return this.user.getUserName();
    }

    public boolean getStatus(){
        return this.active;
    }
}
