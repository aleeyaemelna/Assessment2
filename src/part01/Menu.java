package part01;

import java.util.Scanner;

import console.Console;

//Menu class to easily generate menu for methods in QUBImages
public class Menu {
	Scanner input = new Scanner(System.in);
	private String items[];
	private String title;

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
	}

	/**
	 * Displays menu in the console. Title and possible choices
	 */
	private void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();

		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	/**
	 * Calls display method and asks user to make a selection. Repeats until a value within the options has been chosen
	 * @return integer value representing index of user's choice (indexed starting with 1)
	 */
	public int getUserChoice() {
		display();
		System.out.print("Enter Selection: ");
		int value = input.nextInt();
		return value;
	}

}
