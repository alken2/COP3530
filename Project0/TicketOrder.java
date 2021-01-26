import java.util.ArrayList;

public class TicketOrder {
	//Data Fields
	int i = 0;
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	//Constructors
	public TicketOrder() {
		//Empty
	}
	
	//Methods
	public boolean add(Ticket ticket) {
		return tickets.add(ticket);
	}
	public int totalPrice() {
		int sum = 0;
		for (int i = 0; i < tickets.size(); i++) {
			sum += tickets.get(i).getPrice();
		}
		return sum;
	}
	@Override
    public String toString() {
		if (i < tickets.size()) {
			i++;
			return tickets.get(i - 1).toString() + System.lineSeparator() + toString();
		}
		else {
			return "";
		}
    }
}
