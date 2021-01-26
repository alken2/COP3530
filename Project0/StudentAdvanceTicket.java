
public class StudentAdvanceTicket extends AdvanceTicket {
	//Data Fields (None)
	
	//Constructors
	StudentAdvanceTicket(int days) {
		super(days);
		makePrice();
	}
	
	//Methods
	public void makePrice() {
		price = price / 2;
	}
	public int getPrice() {
		return price;
	}
	@Override
    public String toString() { 
		return wrongDays ? super.toString() : super.toString() + "(student)";
    }
}
