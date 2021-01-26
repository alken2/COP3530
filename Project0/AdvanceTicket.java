public class AdvanceTicket extends Ticket {
	//Data Fields
	protected int price;
	protected boolean wrongDays = false;
	
	//Constructors
	AdvanceTicket(int days) {
		makePrice(days);
	}
	
	//Methods
	public void makePrice(int days) {
		if (days >= 10) {
			price = 30;
		}
		else if (days < 1) {
			wrongDays = true;
		}
		else {
			price = 40;
		}
	}
	public int getPrice() {
		return price;
	}
	@Override
    public String toString() { 
		return wrongDays ? "Less then one day is set for this ticket. Invalid ticket!" : super.toString();
    }
}
