package TicketingSystem;

import java.util.*;
import ParkingLot.Lot;
import ParkingLot.Spot;

public class Key {
    String userName;
    boolean active;
    Spot spot;
    int type;

    public Key(){
        this.userName = "UNKNOWN";
        this.active = false;
        this.spot = null;
    }

    public Key(String name, int type, Lot lot){
        this.userName = name;
        Random random = new Random();
        this.active = false;
        this.spot = lot.assignSpotReg(type, true);
        this.type = type;
    }

    public void activateAccount(){
        this.active = true;
    }

    public void deactivateAccount(){
        this.active = false;
    }

    public String getUserName(){
        return this.userName;
    }

    public boolean getStatus(){
        return this.active;
    }
}
