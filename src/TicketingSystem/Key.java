package TicketingSystem;

import java.util.*;

public class Key {
    String userName;
    boolean active;

    public Key(){
        this.userName = "UNKNOWN";
        this.active = false;
    }

    public Key(String name){
        this.userName = name;
        Random random = new Random();
        this.active = false;
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
