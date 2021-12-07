package TicketingSystem;
import Database.Database;
import ParkingLot.Lot;
import ParkingLot.Spot;
import UserSystem.tempUser;



import java.time.Instant;
import java.util.*;

public class Ticket {
    String ID;
    Instant start;
    int pricePer15Min;
    Spot spot;

    public Ticket() {
        this.ID = "00000000";
        this.start = Instant.now();
        Random random = new Random();
        this.pricePer15Min = 0;
        this.spot = new Spot();
    }

    public Ticket(Lot lot, int type) {
        Random random = new Random();
        int temp = 0;
        while (temp < 10000000){
            temp = random.nextInt(99999999);
        }
        this.ID = Integer.toString(temp);
        System.out.println(this.ID);
        this.start = Instant.now();
        this.pricePer15Min = lot.getPrice();
        this.spot = lot.assignSpotOneTime(type);
    }

    public int getPrice(){
        return this.pricePer15Min;
    }

    public Instant getStartTime(){
        return this.start;
    }

    public String getID(){ return this.ID; }

    public Spot getSpot(){ return this.spot; }
}
