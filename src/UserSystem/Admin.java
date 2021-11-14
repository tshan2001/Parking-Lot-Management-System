package UserSystem;
import ParkingLot.Lot;

public class Admin {
	String AdminUsername;
	String AdminPassword;
	
	public Admin(String username, String password) {
		this.AdminUsername = username;
		this.AdminPassword = hashing(password);
	}
	
	public void changePriceAdmin(int price, Lot thelot) {
		thelot.changePrice(price);
	}
	
	public static String hashing(String password) {
		return password;
	}
}

