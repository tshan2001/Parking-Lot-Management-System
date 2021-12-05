package TicketingSystem;

import Database.Database;
import ParkingLot.Lot;
import UserSystem.RegisteredUser;

public class TicketMachine {
    Database database;
    String machineID;
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

    public TicketMachine(String name, Lot lot){
        this.machineID = name;
        this.currentRate = lot.getPrice();
        this.avaliableReg = lot.getReg();
        this.avaliableComp = lot.getComp();
        this.avaliableDisa = lot.getDisa();
    }

    public void registeredEntering(String UserID, String type){
        if (database.userExists(UserID)){
            if (type.equals("Reg")){
                if (this.avaliableReg > 0) {
                    this.avaliableReg--;
                }
                else{
                    System.out.println("There's no regular parking spot available, please try other types");
                }
            }
            else if (type.equals("Disa")){
                if (this.avaliableDisa > 0) {
                    this.avaliableDisa--;
                }
                else{
                    System.out.println("There's no disability parking spot available, please try other types");
                }
            }
            else if (type.equals("Comp")){
                if (this.avaliableComp > 0) {
                    this.avaliableComp--;
                }
                else{
                    System.out.println("There's no compact parking spot available, please try other types");
                }
            }
            return;
        }
        else{
            System.out.println("User not recogonized, please re-enter username or enter as one time visitor");
        }
    }
}
