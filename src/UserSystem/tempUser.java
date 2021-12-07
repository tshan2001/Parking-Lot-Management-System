package UserSystem;

import TicketingSystem.Ticket;
import java.util.*;

public class tempUser {
    String UserID;
    Ticket ticket;
    int type;

    public tempUser(){
        this.UserID = "do not know";
        this.ticket = new Ticket();
    }

    public tempUser(int type, Ticket ticket){
        Random random = new Random();
        int temp = random.nextInt(99999999);
        while (temp < 10000000){
            temp = random.nextInt(99999999);
        }
        this.UserID = Integer.toString(temp);
        this.ticket = ticket;
        this.type = type;
    }
}
