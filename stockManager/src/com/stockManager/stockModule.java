package com.stockManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class stockModule {
	public List<itemModule> items = new ArrayList<itemModule>();
	public List<String> itemCodes = new ArrayList<String>();

	public stockModule() {

		this.items.add(new itemModule("Potato", 50, "111", 100));
		this.items.add(new itemModule("Tomato", 100, "222", 120));
		this.items.add(new itemModule("Wheat", 150, "333", 130));
		this.items.add(new itemModule("Oil", 100, "444", 140));
		this.items.add(new itemModule("Soap", 250, "555", 150));
		this.items.add(new itemModule("Detergent", 350, "666", 160));
		this.items.add(new itemModule("Table", 150, "777", 170));
		this.items.add(new itemModule("Rice", 150, "888", 180));
		this.items.add(new itemModule("Barley", 100, "999", 190));
		this.items.add(new itemModule("Corn", 150, "765", 200));

		for (itemModule i : this.items) {
			this.itemCodes.add(i.getItem_code());
		}
	}

	public boolean checkcodeDigits(String code) {
		int c = Integer.parseInt(code);
		int count = 0;
		while (c != 0) {

			c /= 10;
			++count;
		}
		if (count != 3) {
			return false;
		}

		return true;

	}

	public boolean checkItemAvailability(String item_name) {
		for (itemModule item : this.items) {
			if (item.getItem_name().toUpperCase().equals(item_name.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	public boolean checkItemQuantity(itemModule i,int item_quantity) {
		
			if (i.getItem_quantity() >= item_quantity) {
				return true;
			}
		

		return false;
	}

	public double priceOfPurchase(itemModule i, int item_quantity) {
		double price = 0.0;
		
		price = i.getItem_price() * item_quantity;
		i.setItem_quantity(i.getItem_quantity() - item_quantity);

		return price;
	}

	public List<itemModule> getItems() {
		return items;
	}

	public void setItems(List<itemModule> items) {
		this.items = items;
	}

	public void printStockHeader() {
		System.out.println(
				"--------------------------------------------------------------------AVAILABLE STOCKS-----------------------------------------------------------");
		System.out.println(String.format("%30s %25s %10s %25s %10s %25s %10s", "ITEM", "|", "PRICE", "|", "CODE", "|",
				"QUANTITY"));
		System.out.println(String.format("%s",
				"---------------------------------------------------------------------------------------------------------------------------------------------------"));
	}

	public void customerStockHeader() {
		System.out.println(
				"--------------------------------------------------------------------AVAILABLE ITEMS-----------------------------------------------------------");
		System.out.println(String.format("%30s %25s %10s %25s  %10s", "ITEM", "|", "PRICE", "|", "QUANTITY"));
		System.out.println(String.format("%s",
				"---------------------------------------------------------------------------------------------------------------------------------------------------"));
	}

	public void cusViewStock() {
		this.customerStockHeader();
		for (itemModule i : this.items) {
			i.CusprintItem();
		}
//		System.out.println("The Available Stock:");
//		System.out.println("\t Name \t Code \t Quantity \t Cost");
//		for (itemModule i : this.getItems()) {
//			System.out.println("\t " + i.getItem_name() + "\t " + i.getItem_code() + "\t\t " + i.getItem_quantity()
//					+ "\t\t " + i.getItem_price());
//		}
	}

	public void viewStock() {
		this.printStockHeader();
		for (itemModule i : this.items) {
			i.printItem();
		}
//		System.out.println("The Available Stock:");
//		System.out.println("\t Name \t Code \t Quantity \t Cost");
//		for (itemModule i : this.getItems()) {
//			System.out.println("\t " + i.getItem_name() + "\t " + i.getItem_code() + "\t\t " + i.getItem_quantity()
//					+ "\t\t " + i.getItem_price());
//		}
	}

	public void removeTheItem() {
		
		String code = "";
		boolean ispresent = true;
		do {
			ispresent = true;
			int flag = 0;
			do {
				
				Scanner reader = new Scanner(System.in);
				System.out.println("Enter the Code of the item to remove: ");
				try {
					flag = 1;
					code = reader.nextLine();
				} catch (NumberFormatException n) {
					System.err.println("The Code has alphabets.. Please try again!!");
				}
			} while (flag == 0);
			
			
			if(this.itemCodes.contains(code) == false)
			{
				ispresent = false;
				System.out.println("The Code is not found. Please try again.");
				continue;
			}
			
			itemModule temp = new itemModule();
			for(itemModule i : this.items) {
				if(i.getItem_code().equals(code))
				{
					temp = i;
				}
			}
			this.itemCodes.remove(temp.getItem_code());
			this.items.remove(temp);
			
//			int found = 0;
//			for (itemModule i : this.items) {
//				if (i.getItem_code().equals(code)) {
//					temp = i;
//					found = 1;
//				}
//			}
//			if (found == 1) {
//				this.items.remove(temp);
//				System.out.println(temp.getItem_name() + " has been removed from the stock.");
//			} else {
//				System.out.println("Item is not found!!!");
//			}
			
		} while (ispresent == false);
	}

//	private boolean checkcodeChange(int codeCheck) {
//		if (codeCheck == 1) {
//			System.out.println("The Code should have three digits. Please try again.");
//			return true;
//		}
//		return false;
//	}

	public void modifyTheItem() {
		Scanner reader = new Scanner(System.in);
		String code = "";
		boolean ispresent = true;
		do {
			ispresent = true;
			int flag = 0;
			do {
				
				
				System.out.println("Enter the Code of the item to Modify: ");
				try {
					flag = 1;
					code = reader.nextLine();
				} catch (NumberFormatException n) {
					System.err.println("The Code has alphabets.. Please try again!!");
				}
			} while (flag == 0);
			
			
			if(this.itemCodes.contains(code) == false)
			{
				ispresent = false;
				System.out.println("The Code is not found. Please try again.");
				continue;
			}
			
			itemModule temp = new itemModule();
			for(itemModule i : this.items) {
				if(i.getItem_code().equals(code))
				{
					temp = i;
				}
			}
			
			String name = "";
			float price = 0;
			int quantity = 0;
			System.out.println("Enter the New name, New Price and Quantity: \n");
			temp.setItem_name(this.getName(name, flag));
			temp.setItem_price(this.getPrice(price, flag));
			temp.setItem_quantity(this.getQuantity(quantity, flag)); 
			
			System.out.println("The Item has been successfully modified.");
			
			
//			int found = 0;
//			for (itemModule i : this.items) {
//				if (i.getItem_code().equals(code)) {
//					temp = i;
//					found = 1;
//				}
//			}
//			if (found == 1) {
//				this.items.remove(temp);
//				System.out.println(temp.getItem_name() + " has been removed from the stock.");
//			} else {
//				System.out.println("Item is not found!!!");
//			}
			
		} while (ispresent == false);

	}

	public boolean checkName(String name) {
		char[] nameChar = name.toCharArray();
		for (char c : nameChar) {
			if (Character.isDigit(c) == true) {
				return false;
			}
		}

		return true;

	}

	public boolean checkprice(float price) {
		// TODO Auto-generated method stub
		if (price == (float) price) {
			return true;
		}
		return false;
	}

	public String getName(String name, int flag) {

		Scanner reader = new Scanner(System.in);
		do {

			flag = 1;
			System.out.println("Enter the name of the item: ");
			name = reader.nextLine();
			try {
				if (this.checkName(name) == false) {
					flag = 0;
					throw new NameException("The Name has a digit. Please try again!!");
				}
			} catch (NameException n) {
				System.err.println(n.getMsg());
			}
		} while (flag == 0);
		return name;
	}

	public float getPrice(float price, int flag) {
		Scanner reader = new Scanner(System.in);
		do {

			flag = 0;
			do {
				System.out.println("Enter the Price of the item: ");

				try {
					flag = 1;
					price = Float.parseFloat(reader.nextLine());
				} catch (NumberFormatException a) {
					flag = 0;
					System.err.println("This price has alphabet. Please try again!!");
				}
			} while (flag == 0);
			try {
				if (this.checkprice(price) == false) {
					flag = 0;
					throw new priceException("The Price must be a float value. Please try again!!");
				}

			} catch (priceException n) {
				System.err.println(n.getMsg());
			}
		} while (flag == 0);
		return price;
	}

	public String getCode(String code, int flag) {
		Scanner reader = new Scanner(System.in);
		do {
			do {
				flag = 0;
				do {
					System.out.println("Enter the code of the item: ");

					try {
						flag = 1;
						code = reader.nextLine();
					} catch (NumberFormatException a) {
						flag = 0;
						System.err.println("This code has alphabet. Please try again!!");
					}
				} while (flag == 0);
				try {
					if (this.checkcodeDigits(code) == false) {
						flag = 0;
						throw new codeException("The Code must have three digits. Please try again!!");
					}
				} catch (codeException n) {
					System.err.println(n.getMsg());
				} catch (NumberFormatException n) {
					flag = 0;
					System.err.println("\"This code has alphabet. Please try again!!\"");
				}
			} while (flag == 0);
		} while (this.isCodePresent(code) == true);
		return code;
	}
	private boolean isCodePresent(String code) {
		if (this.itemCodes.contains(code)) {
			System.out.println(
					"The Code is already present. Please check the code or check if the item is already present.");
			return true;

		}
		return false;
	}

	public int getQuantity(int quantity, int flag) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		do {
			try {
				flag = 1;
				System.out.println("Enter the Quantity of items: ");
				quantity = Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException n) {
				flag = 0;
				System.err.println("This Quantity has alphabet. Please try again!!");
			}
		} while (flag == 0);
		return quantity;

	}

	public void addItem() {
		int flag = 0;
		String name = "", code = "";
		float price = 0;
		int quantity = 0;
		code = this.getCode(code, flag);
		name = this.getName(name, flag);
		price = this.getPrice(price, flag);
		
		quantity = this.getQuantity(quantity, flag);
		itemModule i = new itemModule(name, price, code, quantity);
		this.itemCodes.add(i.getItem_code());
		this.items.add(i);
	}

	public boolean isStockContains(String item_name) {
		for(itemModule i: this.items) {
			if(i.getItem_name().toUpperCase().equals(item_name.toUpperCase()))
			{
				return true;
			}
		}
		return false;
	}

	public itemModule getItem(String item_name) {
		itemModule temp = new itemModule();
		for(itemModule i: this.items) {
			if(i.getItem_name().toUpperCase().equals(item_name.toUpperCase()))
			{
				temp = i;
			}
		}
		return temp;
	}
}