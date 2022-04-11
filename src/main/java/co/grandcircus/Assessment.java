package co.grandcircus;

import java.util.Scanner;

public class Assessment {

	static int totalBurgers = 0;
	static int totalSodas = 0;
	static boolean orderInProgress = true;

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);

		while (orderInProgress) {
			updateItemsOrdered(printMenuTakeOrder(scnr));
		}
		printSubTotal("Subtotal", calculateSubTotal(totalBurgers, totalSodas));
		addTaxDisplayTotal(calculateSubTotal(totalBurgers, totalSodas));
		printItemQuantity("Burgers", totalBurgers);
		printItemQuantity("Sodas", totalSodas);
		
		scnr.close();
	}

	// Methods
	public static int printMenuTakeOrder(Scanner scnr) {
		System.out.print("1. Burger\n" + "2. Soda\n" + "3. Exit\n" + "Select an Option: ");
		return scnr.nextInt();
	}

	public static void updateItemsOrdered(int a) {
		if (a == 1) {
			totalBurgers++;
		} else if (a == 2) {
			totalSodas++;
		} else if (a == 3) {
			orderInProgress = false;
		} else {
			System.out.println("Sorry I didn't understand, let's try again.");
		}
	}

	public static double calculateSubTotal(int a, int b) {
		return (a * 2.5) + (b * 1.25);
	}

	public static void printSubTotal(String str, double d) {
		System.out.println(str + ": $" + d);
	}

	public static void addTaxDisplayTotal(double d) {
		double totalWithTax = d + (d * .07);
		System.out.println("Total: $" + totalWithTax);
	}

	public static void printItemQuantity(String str, int a) {
		System.out.print(str + ": ");
		for (int i = 0; i < a; i++) {
			System.out.print("*");
		}
		System.out.println();
	}
}
