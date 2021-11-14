package UserSystem;

public class RegisteredUser {
	
	public int maxicars = 3;
	
	String username;
	String password;
	String email;
	int unitNum;
	boolean membership;
	boolean disability;
	Vehicle[] cars;
	int numCars;
	
	public RegisteredUser(String usern, String pwd, String email, int unit, boolean disa) {
		this.disability = disa;
		this.username = usern;
		this.password = pwd;
		this.email = email;
		this.unitNum = unit;
		this.membership = false;
		this.cars = new Vehicle[maxicars];
		this.numCars = 0;
		for (int i=0; i<maxicars;i++) {
			this.cars[i] = null;
		}
	}
	
	public void register() {
		this.membership = true;
	}
	
	public void cancel() {
		this.membership = false;
	}
	
	public void addVehicle(String plate, boolean comp) {
		if (this.numCars == maxicars) {
			System.out.println("You have reached maximum cars registered");
			return;
		}
		
		for (int j=0; j<maxicars; j++) {
			if (this.cars[j] == null) {
				this.cars[j] = new Vehicle(plate,comp);
				this.numCars ++;
			}
		}
	}
	
	public void removeVehicle(String plate) {
		if (this.numCars == 0) {
			System.out.println("You have no car registered");
			return;
		}
		
		for (int j=0; j<maxicars; j++) {
			if (this.cars[j].plate == plate) {
				this.cars[j] = null;
				this.numCars --;
			}
		}
	}
	
}
