package Database;
import UserSystem.RegisteredUser;
import UserSystem.Admin;
import ParkingLot.Lot;

public class Database {
	RegisteredUser[] RegisteredUserDatabase;
	Admin[] AdminDatabase;
	Lot theParkingLot;
	
	public Database() {
		this.AdminDatabase = new Admin[10];
		this.RegisteredUserDatabase = new RegisteredUser[200];
		this.theParkingLot = new Lot();
	}
}
