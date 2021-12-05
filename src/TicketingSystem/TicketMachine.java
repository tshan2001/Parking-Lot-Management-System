package TicketingSystem;

import Database.Database;
import ParkingLot.Lot;
import UserSystem.RegisteredUser;

public class TicketMachine {
    Database database;
    String machineID;
    Lot lot;
    int currentRate;
    int avaliableReg;
    int avaliableComp;
    int avaliableDisa;

    public TicketMachine(){
        this.machineID = "UNKNOWN";
        this.currentRate = 0;
        this.avaliableReg = 0;
        this.avaliableComp = 0;
        this.avaliableDisa = 0;
    }

    public TicketMachine(String name, Lot lot, Database database){
        this.machineID = name;
        this.lot = lot;
        this.database = database;
        this.currentRate = lot.getPrice();
        this.avaliableReg = lot.getReg();
        this.avaliableComp = lot.getComp();
        this.avaliableDisa = lot.getDisa();
    }

    public boolean registeredEntering(Key userKey, String type){
        if (this.database.userExists(userKey.getUserName())){
            if (type.equals("Reg")){
                if (this.avaliableReg > 0) {
                    this.avaliableReg--;
                }
                else{
                    System.out.println("There's no regular parking spot available, please try other types");
                    return false;
                }
            }
            else if (type.equals("Disa")){
                if (this.avaliableDisa > 0) {
                    this.avaliableDisa--;
                }
                else{
                    System.out.println("There's no disability parking spot available, please try other types");
                    return false;
                }
            }
            else if (type.equals("Comp")){
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
        return null;
    }

    public void registeredLeave(Key key, String type){
        if (this.database.userExists(key.getUserName())){
            if (type.equals("Reg")){
                this.avaliableReg++;
            }
            else if (type.equals("Disa")){
                this.avaliableDisa++;
            }
            else if (type.equals("Comp")) {
                this.avaliableComp++;
            }
        }
        else{
            System.out.println("User not recogonized, please re-enter username or exit the lot by paying your ticket");
        }
    }
    
}
