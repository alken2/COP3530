
public abstract class FixedPriceTicket extends Ticket {
	//Data Fields
	private int price;
	
	//Constructors
	public FixedPriceTicket(int price) {
		setPrice(price);
	}
	
	//Methods
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
