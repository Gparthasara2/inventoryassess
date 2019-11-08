package com.stockManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuDriver {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		System.out.println("Enter 1 if you are Clerk and 0 if you are Customer: ");
		int user = Integer.parseInt(reader.nextLine());
		stockModule stock = new stockModule();
		do {
		switch(user) {
		case 0:
			
			List<purchase_item> purchaseditems = new ArrayList<purchase_item>();
			int customer_check = 0;
			System.out.println("Welcome to the Store");
			do {
				int item_quantity = 0;
				stock.cusViewStock();
				System.out.println("Enter the item you wanted to buy: \n");
				String item_name = reader.nextLine();

				if (stock.isStockContains(item_name)) {

					itemModule i = stock.getItem(item_name);
					int flag = 0;
					do {
						flag = 1;
						System.out.println("Enter the quantity: \n");
						try {
							item_quantity = Integer.parseInt(reader.nextLine());
						} catch (NumberFormatException n) {
							flag = 0;
							System.err.println("Quantity has alphabets. Enter a valid number!!");
						}
					} while (flag == 0);
					if (stock.checkItemQuantity(i, item_quantity)) {
						double price = stock.priceOfPurchase(i, item_quantity);
						System.out.println("The cost of the item is Rs. " + price);
						purchaseditems.add(new purchase_item(i.getItem_name().toUpperCase(), item_quantity,
								i.getItem_price(), price));
						System.out.println("Type 1 if you wanted to purchase more or -1 to get your invoice: ");
						customer_check = Integer.parseInt(reader.nextLine());
						if (customer_check == -1) {
							break;
						}
					} else {
						System.out.println("Sorry, " + item_name + " is out of goods.");
					}
				} else {
					System.out.println("The Item is not found!!");
				}

			} while (customer_check != -1);

			if (customer_check == -1) {
				double total = 0;
				purchase_item a = new purchase_item();
				a.buildInvoice(purchaseditems);
				for (purchase_item p : purchaseditems) {

					total += p.getItem_cost();
				}

				System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t TOTAL : Rs. " + total);
				System.out.println(
						"----------------------------------------------------------THANK YOU FOR SHOPPING WITH US------------------------------------------------------");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------");
			}
		

		case 1:
			
			System.out.println("Hello, Select a Valid Option: ");
			int wanted = 0;
			do {
				System.out.println("1. View the Stock");
				System.out.println("2. Add a new item to stock");
				System.out.println("3. Remove from the stock");
				System.out.println("4. Modify a stock");
				System.out.println("5. Exit");

				wanted = Integer.parseInt(reader.nextLine());
				switch (wanted) {
				case 1:
					stock.viewStock();
					break;
				case 2:
					stock.addItem();
					break;

				case 3:
					stock.removeTheItem();
					break;
				case 4:

					stock.modifyTheItem();
					break;
				case 5:
					wanted = -1;
					System.out.println("Thank you!!!");
					break;
				default:
					System.out.println("Enter a valid option!!!");
					break;
				}

			} while (wanted != -1);

		case -1:
			System.out.println("Thank You!!!");
			user = -1;
			break;
			
			default:
				System.out.println("Enter a valid option!!! ");
				break;
		}

		}while(user==-1);
	

}
}
