package UserSystem;

import TicketingSystem.Key;

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
	Key Key;
	int credit;
	String creditCard;
	int leasePeriod;


	public RegisteredUser() {
		this.disability = false;
		this.username = "None";
		this.password = "None";
		this.email = "None";
		this.unitNum = -1;
		this.membership = false;
		this.Key = null;
		this.cars = new Vehicle[maxicars];
		this.numCars = 0;
		this.leasePeriod = 0;
		for (int i = 0; i < maxicars; i++) {
			this.cars[i] = null;
		}
		this.creditCard = null;
		this.credit = 0;
	}

	public RegisteredUser(String usern, String pwd) {
		this.username = usern;
		this.password = pwd;
	}

	public RegisteredUser(String usern, String pwd, String email, int unit, boolean disa, int leasePeriod, String creditCard, int credit) {
		this.disability = disa;
		this.username = usern;
		this.password = pwd;
		this.email = email;
		this.unitNum = unit;
		this.membership = false;
		this.Key = null;
		this.cars = new Vehicle[maxicars];
		this.numCars = 0;
		this.leasePeriod = leasePeriod;
		for (int i=0; i<maxicars;i++) {
			this.cars[i] = null;
		}
		this.creditCard = creditCard;
		this.credit = 0;



	}

	public void memberRegister(Key Key) {
		this.membership = true;
		this.Key = Key;
	}

	public Key getMemberKey(){
		return this.Key;
		}

	public void cancel() {
		this.membership = false;
		this.Key = null;
	}
	public String getUserName(){
		return this.username;
	}
	public boolean isDisable(){
		return this.disability;
	}
	public String getPassword (){
		return this.password;
	}
	public void setNewPassword(String pwd){
		this.password = pwd;
	}
	public void setNewEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public int getUnit(){
		return this.unitNum;
	}
	public void  moveUit(int unit){
		this.unitNum = unit;
	}
	public boolean isMember(){
		return this.membership;
	}
	public int getNumCar(){
		return this.numCars;
	}
	public int gerLeasePeriod(){
		return this.leasePeriod;
	}
	public int getCredit(){
		return this.credit;
	}
	public void addCredit(int credit){
		if (this.membership == true){
			System.out.println ("you are already a member");
			return;
		}
		this.credit = this.credit + credit;
		return;
	}

	public void addVehicle(String plate, boolean comp, char type) {
		if (this.numCars == maxicars) {
			System.out.println("You have reached maximum cars registered");
			return;
		}

		for (int j=0; j<maxicars; j++) {
			if (this.cars[j] == null) {
				this.cars[j] = new Vehicle(plate,comp, type);
				this.numCars ++;
				break;
			}
		}
		return;
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
				break;
			}
		}
		return;
	}


	
}
