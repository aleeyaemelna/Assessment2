package part02;

import java.awt.Font;
import console.Console;

import java.util.Scanner;

public class Menu extends QUBMediaImages {
	Scanner input = new Scanner(System.in);
	private String items[];
	private String title;
	private static final Font f1 = new Font("Times New Roman", Font.BOLD, 20);
	
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
	}

	/**
	 * Displays menu in the console. Title and possible choices
	 */
	private void display(Console con) {
		
		con.setVisible(true);
		con.setFont(f1);
		con.println(title);
		for (int count = 0; count < title.length(); count++) {
			con.print("+");
		}
		con.println();

		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}
		con.println();
	}

	/**
	 * Calls display method and asks user to make a selection. Repeats until a value within the options has been chosen
	 * @return integer value representing index of user's choice (indexed starting with 1)
	 */
	public int getUserChoice(Console con) {
		display(con);
		con.println("Enter Selection: ");
		String inputString = con.readLn();
		int value = Integer.parseInt(inputString);
		
		return value;
	}

}
