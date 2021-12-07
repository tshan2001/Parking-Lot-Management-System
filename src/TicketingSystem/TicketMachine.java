package TicketingSystem;

import Database.Database;
import ParkingLot.Lot;
import java.time.Duration;
import java.time.Instant;

public class TicketMachine {
    Database database;
    Lot lot;
    int currentRate;

    public TicketMachine(){
        this.currentRate = 0;
        this.database = new Database();
        this.lot = new Lot();
    }

    public TicketMachine(Lot lot, Database database){
        this.lot = lot;
        this.database = database;
        this.currentRate = lot.getPrice();
    }

    public boolean registeredEntering(Key userKey){
        if (this.database.userExists(userKey.getUserName())){
            System.out.println(userKey.getUserName() + "\nWelcome back!");
            return true;
        }
        else{
            System.out.println("User not recogonized, please re-enter username or enter as one time visitor");
            return false;
        }
    }

    public Ticket oneTimeParking(int type) {
        Ticket ticket = new Ticket(this.lot, type);
        if (type == 0){
            if (this.lot.getReg() > 0) {
                System.out.println("Your ticket ID is: " + ticket.getID() + "\n You will need this ticket or it's ID when you leave");
                this.database.addTicket(ticket);
                return ticket;
            }
            else{
                System.out.println("There's no regular parking spot available, please try other types");
            }
        }
        else if (type == 1){
            if (this.lot.getDisa() > 0) {
                System.out.println(ticket.getID());
                this.database.addTicket(ticket);
                return ticket;
            }
            else{
                System.out.println("There's no disability parking spot available, please try other types");
            }
        }
        else if (type == 2){
            if (this.lot.getComp() > 0) {
                System.out.println("Your ticket ID is: " + ticket.getID() + "\n You will need this ticket or it's ID when you leave");
                this.database.addTicket(ticket);
                return ticket;                
            }
            else{
                System.out.println("There's no compact parking spot available, please try other types");
            }
        }
        return new Ticket();
    }

    public void registeredLeave(Key key){
        if (this.database.userExists(key.getUserName())){
            System.out.println(key.getUserName() + "\nSafe Travels!");
        }
        else{
            System.out.println("User not recogonized, please re-enter username or exit the lot by paying your ticket");
        }
    }

    public void oneTimeLeave(String ticketID){
        Instant leaveTime = Instant.now();
        if (!this.database.ticketExists(ticketID)) {
            System.out.println("Your ticket is invalid, please try again");
            return;
        }
        Ticket ticket = this.database.getTicket(ticketID);
        this.lot.freeSpotOneTime(ticket.getSpot().getSpotId());
        Instant entranceTime = ticket.start;
        int price = ticket.pricePer15Min;
        Duration parkingTime = Duration.between(entranceTime, leaveTime);
        int timeIn15 = (int) parkingTime.toMinutes() / 15;
        int finalPrice = price * timeIn15;
        System.out.println("Your total amount due is: " + Integer.toString(finalPrice) + " \nPlease insert your credit card");
        System.out.println("Thank you for parking with us!");
    }
    
}
