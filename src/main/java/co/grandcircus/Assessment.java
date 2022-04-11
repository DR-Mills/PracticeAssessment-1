package co.grandcircus;

import java.util.Scanner;

public class Assessment {

	static int totalBurgersOrdered = 0;
	static int totalSodasOrdered = 0;
	static boolean orderInProgress = true;	
	
	public static void main(String[] args) {
		
		double orderSubTotal = 0.00;
		Scanner scnr = new Scanner(System.in);

		while (orderInProgress) {
			int orderSelection = takeCustomerOrder(scnr);

			orderUpdate(orderSelection);
		}
		
		orderSubTotal = calculateSubtotal(totalBurgersOrdered, totalSodasOrdered);
		
		System.out.println("Subtotal: $" + orderSubTotal);
		
		addTaxDisplayTotal(orderSubTotal);
		System.out.println(printStars("Burgers", totalBurgersOrdered));
		System.out.println(printStars("Sodas", totalSodasOrdered));
		
		scnr.close();
	}

	// Methods
	private static int takeCustomerOrder(Scanner scnr) {
		System.out.println("1. Burger\n" + "2. Soda\n" + "3. Exit\n" + "Select an option: ");
		int orderSelection = scnr.nextInt();
		return orderSelection;
	}

	public static double calculateSubtotal(int b, int s) {
		return (b * 2.50) + (s * 1.25);
	}

	public static void addTaxDisplayTotal(double t) {
		double total = t * 1.07;
		System.out.println("Total: $" + total);
	}

	public static String printStars(String word, int a) {
		String starBanner = "";
		for (int i = 0; i < a; i++) {
			starBanner = starBanner.concat("*");
		}
		return word + ": " + starBanner;
	}
	
	public static void orderUpdate(int a) {
		if (a == 1) {
			totalBurgersOrdered++;
		} else if (a == 2) {
			totalSodasOrdered++;
		} else if (a == 3) {
			orderInProgress = false;
		} else {
			System.out.println("Sorry I didn't understand, let's try again.");
		}
	}
}
