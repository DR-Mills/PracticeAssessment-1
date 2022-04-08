package co.grandcircus;

import java.util.Scanner;

public class Assessment {

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);

		boolean orderInProgress = true;
		int totalBurgersOrdered = 0;
		int totalSodasOrdered = 0;
		double orderSubTotal = 0.00;

		while (orderInProgress) {
			System.out.println("1. Burger\n" + "2. Soda\n" + "3. Exit\n" + "Select an option: ");
			int orderSelection = scnr.nextInt();

			if (orderSelection == 1) {
				totalBurgersOrdered++;
			} else if (orderSelection == 2) {
				totalSodasOrdered++;
			} else if (orderSelection == 3) {
				orderInProgress = false;
			} else {
				System.out.println("Sorry I didn't understand, let's try again.");
			}
		}
		orderSubTotal = calculateSubtotal(totalBurgersOrdered, totalSodasOrdered);
		
		System.out.println("Subtotal: $" + orderSubTotal);
		
		addTaxDisplayTotal(orderSubTotal);
		System.out.println(printStars("Burgers", totalBurgersOrdered));
		System.out.println(printStars("Sodas", totalSodasOrdered));
		
		scnr.close();
	}

	// Methods

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
}
