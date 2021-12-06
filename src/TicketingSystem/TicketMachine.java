package TicketingSystem;

import Database.Database;
import ParkingLot.Lot;
import java.time.Duration;
import java.time.Instant;

public class TicketMachine {
    Database database;
    Lot lot;
    int currentRate;
    int avaliableReg;
    int avaliableComp;
    int avaliableDisa;

    public TicketMachine(){
        this.currentRate = 0;
        this.avaliableReg = 0;
        this.avaliableComp = 0;
        this.avaliableDisa = 0;
    }

    public TicketMachine(Lot lot, Database database){
        this.lot = lot;
        this.database = database;
        this.currentRate = lot.getPrice();
        this.avaliableReg = lot.getReg();
        this.avaliableComp = lot.getComp();
        this.avaliableDisa = lot.getDisa();
    }

    public boolean registeredEntering(Key userKey){
        if (this.database.userExists(userKey.getUserName())){
            if (userKey.spotType == 0){
                if (this.avaliableReg > 0) {
                    System.out.println("Welcome Back!");
                    this.avaliableReg--;
                }
                else{
                    System.out.println("There's no regular parking spot available, please try other types");
                    return false;
                }
            }
            else if (userKey.spotType == 1){
                if (this.avaliableDisa > 0) {
                    this.avaliableDisa--;
                }
                else{
                    System.out.println("There's no disability parking spot available, please try other types");
                    return false;
                }
            }
            else if (userKey.spotType == 2){
                if (this.avaliableComp > 0) {
                    this.avaliableComp--;
                }
                else{
                    System.out.println("There's no compact parking spot available, please try other types");
                    return false;
                }
            }
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
            if (this.avaliableReg > 0) {
                this.avaliableReg--;
                return ticket;
            }
            else{
                System.out.println("There's no regular parking spot available, please try other types");
            }
        }
        else if (type == 1){
            if (this.avaliableDisa > 0) {
                this.avaliableDisa--;
                return ticket;
            }
            else{
                System.out.println("There's no disability parking spot available, please try other types");
            }
        }
        else if (type == 2){
            if (this.avaliableComp > 0) {
                this.avaliableComp--;
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
            if (key.spotType == 0){
                this.avaliableReg++;
            }
            else if (key.spotType == 1){
                this.avaliableDisa++;
            }
            else if (key.spotType == 2) {
                this.avaliableComp++;
            }
        }
        else{
            System.out.println("User not recogonized, please re-enter username or exit the lot by paying your ticket");
        }
    }

    public void oneTimeLeave(Ticket ticket){
        Instant leaveTime = Instant.now();
        Instant entranceTime = ticket.start;
        int price = ticket.pricePer15Min;
        Duration parkingTime = Duration.between(entranceTime, leaveTime);
        int timeIn15 = (int) parkingTime.toMinutes() / 15;
        int finalPrice = price * timeIn15;
        System.out.println("Your total amount due is: " + Integer.toString(finalPrice) + " \nPlease insert your credit card");
    }
    
}
