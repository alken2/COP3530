import java.util.Scanner;


public class Assignment1 {
	
	public static void main(String[] args) {
		//main
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string to test if it reads the same backward as forward.");
		String string = input.nextLine();
		System.out.println(p(string));
		System.out.println();
		System.out.println("Enter an integer to print it and all other integers between zero in descending order.");
		int integer = input.nextInt();
		printD(integer);
		input.close();
	}
	
	//1
	//Write a recursive Java method that returns true if a given string parameter "s" reads the same backward as forward. For example, your method should return true if the s = "kayak" and should return false, if s = "ab".
	//REMEMBER TO REMOVE STATIC KEYWORD WHEN TURNING IN ASSIGNMENT
	public static boolean p(String s) {
		try {
			s.toUpperCase();
			char reverse = s.charAt(s.length() - 1);
			if (s.charAt(0) == reverse) {
				if (s.length() - 1 != 0) {
					return s.length() - 2 == 0 ? true : p(s.substring(1, s.length() - 1));
				}
				else {
					return true;
				}
			}
			return false;
		}
		catch (StringIndexOutOfBoundsException a) {
			return false;
		}
	}
	
	//2
	//Write a recursive Java method that receives an integer n, and prints all integers starting from n to 0 in descending order. For example, if n = 3, your method should print 3 2 1 0.
	//REMEMBER TO REMOVE STATIC KEYWORD WHEN TURNING IN ASSIGNMENT
	public static void printD(int n) {
		System.out.print(n <= 0 ? "0" : n + " ");
		if (n > 0) {
			printD(n - 1);
		}
	}
}
