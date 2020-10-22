import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MarketApp {

	static Map<String, Double> menu = new HashMap<>();
	static List<Double> orderedPrices = new ArrayList<>();
	static List<String> orderedItems = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Welcome to Tim's Market!\n");

		menu.put("Apple", 0.99);
		menu.put("Banana", 0.59);
		menu.put("Asparagus", 2.99);
		menu.put("Pumpkin", 4.99);
		menu.put("Sliced Turkey", 5.99);
		menu.put("Dish Soap", 1.99);
		menu.put("Milk", 1.99);
		menu.put("Bread", 1.39);

		do {		
			printMenu();
			addItem(orderItemName());		
		} while (Validator.getYesNo(scnr, "Do you want to add another? "));
		
		printOrder(orderedItems, orderedPrices);
		System.out.println("==============================");
		
		double total = getTotal(orderedPrices);		
		
		System.out.printf("Your total is: $%.2f",  total);
		System.out.println();
		System.out.printf("The average price of your items: $%.2f", getAverage(orderedItems.size(), total));
		System.out.println();
		printMin();		
		printMax();
		
	}

	public static void printMenu() {
		System.out.printf("%-14s %1s\n", "Item", "Price");
		System.out.println("==============================");
		for (Map.Entry<String, Double> item : menu.entrySet()) {
			System.out.printf("%-14s $%1s\n", item.getKey(), item.getValue());
		}
	}

	public static String orderItemName() {
		Scanner scnr = new Scanner(System.in);
		
		do {
			String userOrder = Validator.getString(scnr, "\nWhat item would you like to order? ");
			if (menu.containsKey(userOrder)) {
				return userOrder;
			} else {
				System.out.println("Sorry, we dont have those.  Please try again");
			}
		} while (true);

	}
	
	public static void addItem(String userItem) {
		orderedItems.add(userItem);		
		orderedPrices.add(menu.get(userItem));
		System.out.println("Adding " + userItem + " to cart at $" + menu.get(userItem));
	}
	
	public static void printOrder(List<String> items, List<Double> prices) {
		System.out.println("Thanks for the order!");
		System.out.println("Here's what you got:");
		for (int i = 0; i < items.size(); i++) {
			System.out.printf("%-14s $%1s\n", items.get(i), prices.get(i));
		}
	}
	public static double getTotal(List<Double> itemPrices) {
		double total = 0;
		for (Double price: itemPrices) {
			total += price;
		}
		return total;
	}
	public static double getAverage (double items, double total) {		
		return total / items;
	}
	public static void printMin() {
		System.out.println("Mininum item price was $ " + Collections.min(orderedPrices));
	}
	public static void printMax() {
		System.out.println("Maximum item price was $ " + Collections.max(orderedPrices));
	}
}
