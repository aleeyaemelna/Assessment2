package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import console.Console;

public class QUBMediaImages {

	// Import the Scanner class to read user input
	static Scanner input = new Scanner(System.in);
	// Create an instance of the ImageManager class to manage images
	static ImageManager manager = new ImageManager();
	// Create an instance of the ImageAlbum class to store images
	static ImageAlbum album = new ImageAlbum();
	private static Console con = setup1(600, 600, 100, 100, true);
	private static final Font f1 = new Font("Helvetica", Font.BOLD, 15);
	
	// Main method where the program starts execution
	public static void main(String[] args) {
		
		con.clear();

		// Options for the main menu
		String options[] = { "Add Image", "Seach Image", "Display all image", "View all image", "Exit Application" };
		// Create a menu with the provided options
		Menu mainMenu = new Menu("QUB's Photogaphy Department", options);

		int opt = 0;
		// Create a menu with the provided options
		boolean end = false;

		// Main loop to display the menu and handle user choices
		while (!end) {
			// Get the user's choice from the main menu
			opt = mainMenu.getUserChoice(con);

			// Perform actions based on the user's choice
			switch (opt) {
			case 1:
				addImage();
				break;
			case 2:
				searchImage();
				break;
			case 3:
				diplayImageID();
				break;
			case 4:
				viewImage();
				break;
			case 5:
				end = true;
				break;
			default:
				con.println("Invalid option. please try again.");
			}
		}

	}

	private static Console setup1(int width, int height, int xPos, int yPos, boolean isInput) {
		
		Console con = new Console(isInput); // true means user input is possible
		con.setSize(width, height); // set console size
		con.setVisible(true); // make console visible
		con.setLocation(xPos, yPos); // fix location of console window
		con.setFont(f1); // set console font
		con.setColour(Color.white); // set text colour
		con.setBgColour(Color.pink); // set background colour
		return con;
	}

	// Method to add an image
	public static void addImage() {

		try {
			
			con.clear();
			con.println("Adding an Image");
			con.println("Enter Title: ");
			String title = con.readLn();

			con.println("Enter Description: ");
			String desc = con.readLn();
			con.println();

			ImageType genre = SelectType();
			con.println();

			con.println("Enter date in (yyyy-MM-dd) format: ");
			String userInput = con.readLn();
			LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			con.println("Enter thumbnail: ");
			String thumbnail = con.readLn();

			ImageRecord newImage = new ImageRecord(title, desc, genre, date, thumbnail);
			manager.addImage(newImage);

		} catch (DateTimeParseException e) {
			con.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
		}
	}

	/**
	 * Selects ImageType based on user input, to be used with addImage() and searchType()
	 * @param choice
	 * @return ImageType that matches user's chosen Type group, or ImageType.OTHER if none match
	 */
	public static ImageType SelectType() {

		String options[] = { "Astronomy", "Architecture", "Sport", "Landscape", "Portrait", "Nature", "Aerial", "Food",
				"Other" };
		Menu typeMenu = new Menu("Choose image type", options);
		int choice = typeMenu.getUserChoice(con);

		switch (choice) {
		case 1:
			return ImageType.ASTRONOMY;
		case 2:
			return ImageType.ARCHITECTURE;
		case 3:
			return ImageType.SPORT;
		case 4:
			return ImageType.LANDSCAPE;
		case 5:
			return ImageType.POTRAIT;
		case 6:
			return ImageType.NATURE;
		case 7:
			return ImageType.AERIAL;
		case 8:
			return ImageType.FOOD;
		default:
			return ImageType.OTHER;
		}

	}

	/**
	 * Executes method for selected choice, intended for use with searchImage()
	 * (1) search by ID
	 * (2) search by Title
	 * (3) search by Description
	 * (4) Search by Type
	 * (5) Search by Date
	 * (6) quit
	 * @param userOpt
	 * @return true if quit option is chosen, false for other options or if no option matches value
	 */
	public static void searchImage() {
		
		con.clear();

		String options[] = { "ID", "Title", "Description", "Type", "Date", "Cancel Choice" };
		Menu searchMenu = new Menu("Search Image", options);

		boolean end = false;

		while (!end) {
			int userOpt = searchMenu.getUserChoice(con);
			switch (userOpt) {
			case 1:
				searchID();
				break;
			case 2:
				searchTitle();
				break;
			case 3:
				searchDescription();
				break;
			case 4:
				searchGenre();
				break;
			case 5:
				searchDate();
				break;
			case 6:
				end = true;
				break;
			default:
				con.println("Invalid option. Please try again.");
			}
		}
	}

	public static void searchID() {con.clear();
	
	
		con.clear();
		con.println("Enter ID: ");
		String userString = con.readLn();
		int searchId = Integer.parseInt(userString);
		ImageRecord image = manager.searchId(searchId); // using search id in ImageManager class to find the image

		if (image != null) {
			con.println("Image Found: ");
			con.println();
			con.println(image);
		} else {
			con.println("No image found");
		}
	}

	public static void searchTitle() {
		con.println("Enter Title: ");
		String searchTitle = con.readLn();
		ImageAlbum title = manager.searchTitle(searchTitle);

		if (title != null) {
			con.println("Image Found: ");
			con.println();
			for (ImageRecord image : title.List) {
				if (image.getTitle().contains(searchTitle)) {
					con.println(image.getDetails());
				}
			}
		}
	}

	public static void searchDescription() {

		con.println("Enter Description: ");
		String searchDesc = con.readLn();
		ImageAlbum desc = manager.searchDescription(searchDesc);

		if (desc != null) {
			con.println("Image Found: ");
			con.println();
			for (ImageRecord image : desc.List) {
				if (image.getDesc().contains(searchDesc)) {
					con.println(image.getDetails());
				}
			}
		} else {
			con.println("No image found");
		}
	}

	public static void searchGenre() {

		con.println("Select genre: ");
		ImageType searchGenre = SelectType();
		ImageAlbum genre = manager.searchGenre(searchGenre);

		if (genre != null) {
			con.println("Image Found: ");
			con.println();
			for (ImageRecord image : genre.List) {
				if (image.getType().equals(searchGenre)) {
					con.println(image.getDetails());
				}
			}
		} else {
			con.println("No image Found");
		}
	}

	public static void searchDate() {

		try {

			con.println("Enter start date in (yyyy-mm-dd) format : ");
			String userInputStart = con.readLn();
			LocalDate startDate = LocalDate.parse(userInputStart);

			con.println("Enter start date in (yyyy-mm-dd) format : ");
			String userInputEnd = con.readLn();
			LocalDate endDate = LocalDate.parse(userInputEnd);

			ImageAlbum date = manager.searchDates(startDate, endDate);

			if (date != null) {
				con.println("Image found : ");
				for (ImageRecord image : date.List) {
					con.println(image.getDetails());
				}
			} else {
				con.println("No image Found");
			}
		} catch (DateTimeParseException e) {
			con.println("Error: Invalid date format. Please use yyyy-mm-dd format.");
		}
	}

	// Method to display an image by ID
	public static void diplayImageID() {

		ImageAlbum all = manager.getAllImages();
		
		if (!all.List.isEmpty()) {
			for (ImageRecord image : all.List) {
				con.println(image.getDetails());
				ImageIcon tn = image.getThumbnail();
				if (tn != null) {
					con.print(tn);
					con.println();
					con.println();
				}
			}
		} else {
			con.println("No Image Found");
		}

	}

	public static void displayImageView(ImageRecord image) {

		con.println();
		con.println(image.getDetails());

	}

	// Method to view all images
	public static void viewImage() {

		ImageAlbum allImages = manager.getAllImages();
		allImages.bubbleSortbyDate(true);

		ImageRecord image = allImages.getFirst();
		displayImageView(image);

		String options[] = { "First Image", "Next Image", "Previous Image", "Quit" };
		Menu viewMenu = new Menu("View Images", options);
		int userOpt = viewMenu.getUserChoice(con);
		
		
			if (userOpt == 1) {
				displayImageView(allImages.getFirst());
				if (image == null) {
					con.println("No image in application");
					con.println();
				}
				displayImageView(image);
			} else if (userOpt == 2) {
				image = allImages.getNext();
				if (image == null) {
					con.println("No previous image in application");
					con.println();
				}
				displayImageView(image);
			} else if (userOpt == 3) {
				image = allImages.getPrevious();
				if (image == null) {
					con.println("No previous images in application");
					con.println();
				}
				displayImageView(image);
			} else if (userOpt == 4) {
				con.println("Main Menu");
			} else {
				con.println("Invalid choise. Please try again.");
			}
	}
}
