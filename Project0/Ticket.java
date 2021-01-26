import java.util.ArrayList;
import java.util.Random;

public abstract class Ticket {
	//Data Fields
	private int serial;
	private static ArrayList<Integer> existingSN = new ArrayList<Integer>();
	
	//Constructors
	public Ticket() {
		makeSN();
	}
	
	//Methods
	protected abstract int getPrice();
	private int getSN() {
		return serial;
	}
	private void makeSN() {
		int i = 0;
		Random rand = new Random();
		int tempSN = rand.nextInt(10000);
		while (i < existingSN.size()) {
			if (tempSN == existingSN.get(i)) {
				tempSN = rand.nextInt(10000);
				i = 0;
			}
			else {
				i++;
			}
		}
		serial = tempSN;
	}
	@Override
    public String toString() { 
        return String.format("SN: " + getSN() + ", $" + getPrice());
    }
}
