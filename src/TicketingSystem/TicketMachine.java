package TicketingSystem;

import Database.Database;
import ParkingLot.Lot;
import java.time.Duration;
import java.time.Instant;

public class TicketMachine {
    Database database;
    Lot lot;
    int currentRate;

    /* default constructor for TicketMachine */
    public TicketMachine(){
        this.currentRate = 0;
        this.database = new Database();
        this.lot = new Lot();
    }

    /* TicketMachine constructor with inputs */
    public TicketMachine(Lot lot, Database database){
        this.lot = lot;
        this.database = database;
        this.currentRate = lot.getPrice();
    }

    /* Verify registered user entering the lot */
    public boolean registeredEntering(Key userKey){
        if (this.database.userExists(userKey.getUserName())){
            System.out.println(" ");
			System.out.println(" - - - - - - - - - - - - - - ");
            System.out.println("<Parking>: " + userKey.getUserName() + ", Welcome back!");
            return true;
        }
        else{
            System.out.println(" ");
			System.out.println(" - - - - - - - - - - - - - - ");
            System.out.println("<Parking>: " + "User not recogonized, please re-enter username or enter as one time visitor");
            return false;
        }
    }

    /* When first time user enters the lot */
    public Ticket oneTimeParking(int type) {
    	/* create new ticket for one time user */
        Ticket ticket = new Ticket(this.lot, type);
        /* regular vehicle */
        if (type == 0){
            if (this.lot.getReg() > 0) {
                System.out.println(" ");
			    System.out.println(" - - - - - - - - - - - - - - ");
                System.out.println("<Parking>: Your ticket ID is: " + ticket.getID());
                System.out.println("           You will need this ticket or it's ID when you leave");
                this.database.addTicket(ticket);
                return ticket;
            }
            else{
                System.out.println(" ");
			    System.out.println(" - - - - - - - - - - - - - - ");
                System.out.println("<Parking>: There's no regular parking spot available, please try other types");
            }
        }
        /* compact vehicle */
        else if (type == 1){
            if (this.lot.getComp() > 0) {
                System.out.println(" ");
			    System.out.println(" - - - - - - - - - - - - - - ");
                System.out.println("<Parking>: Your ticket ID is: " + ticket.getID());
                System.out.println("           You will need this ticket or it's ID when you leave");
                this.database.addTicket(ticket);
                return ticket;
            }
            else{
                System.out.println("<Parking>: There's no compact parking spot available, please try other types");
            }
        }
        /* disability vehicle */
        else if (type == 2){
            if (this.lot.getDisa() > 0) {
                System.out.println(" ");
			    System.out.println(" - - - - - - - - - - - - - - ");
                System.out.println("<Parking>: Your ticket ID is: " + ticket.getID());
                System.out.println("           You will need this ticket or it's ID when you leave");
                this.database.addTicket(ticket);
                return ticket;                
            }
            else{
                System.out.println("<Parking>: There's no disability parking spot available, please try other types");
            }
        }
        return new Ticket();
    }

    /* Verify registered user leaving the lot */
    public void registeredLeave(Key key){
        if (this.database.userExists(key.getUserName())){
            System.out.println(" ");
			System.out.println(" - - - - - - - - - - - - - - ");
            System.out.println("<Parking>: "+ key.getUserName() + ", Safe Travels!");
        }
        else{
            System.out.println("<Parking>: User not recogonized, please re-enter username or exit the lot by paying your ticket");
        }
    }

    /* When first time user leaves the lot */
    public void oneTimeLeave(String ticketID){
        Instant leaveTime = Instant.now();
        if (!this.database.ticketExists(ticketID)) {
            System.out.println("<Error>: Your ticket is invalid, please try again");
            return;
        }
        Ticket ticket = this.database.getTicket(ticketID);
        this.database.removeTicket(ticket);
        this.lot.freeSpotOneTime(ticket.getSpot().getSpotId());
        Instant entranceTime = ticket.start;
        int price = ticket.pricePer15Min;
        Duration parkingTime = Duration.between(entranceTime, leaveTime);
        int timeIn15 = (int) parkingTime.toMinutes() / 15;
        int finalPrice = price * timeIn15;
	System.out.println("");
	System.out.println(" - - - - - - - - - - - - - - ");
        System.out.println("<Parking>: Your total amount due is: " + Integer.toString(finalPrice));
        System.out.println(" ");
        System.out.println("           Please insert your credit card");
        System.out.println(" ");
        System.out.println("           Thank you for parking with us!");
    }
    
}
