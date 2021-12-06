package TicketingSystem;
import ParkingLot.Lot;
import ParkingLot.Spot;



import java.time.Instant;
import java.util.*;

public class Ticket {
    Instant start;
    int ID;
    int pricePer15Min;
    Spot spot;

    public Ticket() {
        this.start = Instant.now();
        Random random = new Random();
        this.ID = random.nextInt(999999);
        this.pricePer15Min = 0;
        this.spot = null;
    }

    public Ticket(Lot lot, int type) {
        this.start = Instant.now();
        Random random = new Random();
        this.ID = random.nextInt(999999);
        this.pricePer15Min = lot.getPrice();
        this.spot = lot.assignSpotOneTime(type);
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
