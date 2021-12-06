package TicketingSystem;

import java.util.*;
import ParkingLot.Lot;
import ParkingLot.Spot;

public class Key {
    String userName;
    boolean active;
    Spot spotAssigned;
    int spotType;

    public Key(){
        this.userName = "UNKNOWN";
        this.active = false;
        this.spotAssigned = null;
        this.spotType = -1;
    }

    public Key(String name, int type, Lot lot){
        this.userName = name;
        this.active = false;
        this.spotAssigned = lot.assignSpotReg(type, true);
        this.spotType = type;
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
