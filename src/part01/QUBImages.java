package part01;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class QUBImages {

	// Import the Scanner class to read user input
	static Scanner input = new Scanner(System.in);
	// Create an instance of the ImageManager class to manage images
	static ImageManager manager = new ImageManager();
	// Create an instance of the ImageAlbum class to store images
	static ImageAlbum album = new ImageAlbum();
	
	/**
	 * Executes method for selected choice, intended for use with mainMenu
	 * (1) go to add Image
	 * (2) go to search Image
	 * (3) go to display Image by ID
	 * (4) go to view image
	 * (5) quit
	 * @param opt
	 * @return true if quit option is chosen, false for other options or if no option matches value
	 */
	public static void main(String[] args) {
		// Process choice methods - each menu has a method to process it's choices
		// Options for the main menu
		String options[] = { "Add Image", "Seach Image", "Display all image", "View all image", "Exit Application" };
		// Create a menu with the provided options
		Menu mainMenu = new Menu("QUB's Photogaphy Department", options);
		// Create a menu with the provided options
		boolean end = false;
		
		while (!end) {
			int opt = mainMenu.getUserChoice();

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
				System.out.println("Invalid option. please try again.");
			}
		}

	}
	
	/**
	 * Selects ImageType based on user input, to be used with addImage() and searchType()
	 * @param choice
	 * @return ImageType that matches user's chosen Type group, or ImageType.OTHER if none match
	 */
	public static void addImage() {

		try {
			
			//prompt user to add image details
			System.out.println("Adding an Image");
			System.out.print("Enter Title: ");
			String title = input.nextLine();
			
			//prompt user to add image description
			System.out.print("Enter Description: ");
			String desc = input.nextLine();
			System.out.println();
			
			//prompt user to select image type
			ImageType type = SelectType();
			System.out.println();
			
			//prompt user to enter image date
			System.out.println("Enter date in (yyyy-MM-dd) format: ");
			String userInput = input.nextLine();
			LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			//prompt user to enter image thumbnail
			System.out.println("Enter thumbnail: ");
			String thumbnail = input.nextLine();
			
			//create a new ImageRecordd object and add it to the manager
			ImageRecord newImage = new ImageRecord(title, desc, type, date, thumbnail);
			manager.addImage(newImage);

		} catch (DateTimeParseException e) {
			//handle invalid data format exception
			System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
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
		int choice = typeMenu.getUserChoice();

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
	 * Executes method for selected choice, intended for use with searchImage
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

		String options[] = { "ID", "Title", "Description", "Type", "Date", "Cancel Choice" };
		Menu searchMenu = new Menu("Search Image", options);

		boolean end = false;

		while (!end) {
			//get user's choice of search criteria
			int userOpt = searchMenu.getUserChoice();
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
				searchType();
				break;
			case 5:
				searchDate();
				break;
			case 6:
				end = true;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	//method to search by ID
	public static void searchID() {
		System.out.println("Enter ID: ");
		int searchID = input.nextInt();
		// search for image by ID using ImageManager
		ImageRecord image = manager.searchId(searchID); 

		if (image != null) {
			System.out.println("Image Found: ");
			System.out.println();
			System.out.println(image);
		} else {
			System.out.println("No image found");
		}
	}

	public static void searchTitle() {
		System.out.println("Enter Title: ");
		String searchTitle = input.nextLine();
		ImageAlbum title = manager.searchTitle(searchTitle);

		if (title != null) {
			System.out.println("Image Found: ");
			System.out.println();
			for (ImageRecord image : title.List) {
				if (image.getTitle().contains(searchTitle)) {
					System.out.println(image.getDetails());
				}
			}
		}
	}

	public static void searchDescription() {

		System.out.println("Enter Description: ");
		String searchDesc = input.nextLine();
		ImageAlbum desc = manager.searchDescription(searchDesc);

		if (desc != null) {
			System.out.println("Image Found: ");
			System.out.println();
			for (ImageRecord image : desc.List) {
				if (image.getDesc().contains(searchDesc)) {
					System.out.println(image.getDetails());
				}
			}
		} else {
			System.out.println("No image found");
		}
	}

	public static void searchType() {

		System.out.println("Select image type: ");
		ImageType searchGenre = SelectType();
		ImageAlbum genre = manager.searchGenre(searchGenre);

		if (genre != null) {
			System.out.println("Image Found: ");
			System.out.println();
			for (ImageRecord image : genre.List) {
				if (image.getType().equals(searchGenre)) {
					System.out.println(image.getDetails());
				}
			}
		} else {
			System.out.println("No image Found");
		}
	}

	public static void searchDate() {

		try {

			System.out.println("Enter start date in (yyyy-mm-dd) format : ");
			String userInputStart = input.nextLine();
			LocalDate startDate = LocalDate.parse(userInputStart);

			System.out.println("Enter start date in (yyyy-mm-dd) format : ");
			String userInputEnd = input.nextLine();
			LocalDate endDate = LocalDate.parse(userInputEnd);

			ImageAlbum date = manager.searchDates(startDate, endDate);

			if (date != null) {
				System.out.println("Image found : ");
				for (ImageRecord image : date.List) {
					System.out.println(image.getDetails());
				}
			} else {
				System.out.println("No image Found");
			}
		} catch (DateTimeParseException e) {
			System.out.println("Error: Invalid date format. Please use yyyy-mm-dd format.");
		}
	}

	// Method to display an image by ID
	public static void diplayImageID() {

		ImageAlbum all = manager.getAllImages();
		if (!all.List.isEmpty()) {
			for (ImageRecord image : all.List) {
				System.out.println(image.getDetails());
			}
		} else {
			System.out.println("No Image Found");
		}

	}

	public static void displayImageView(ImageRecord image) {

		System.out.println();
		System.out.println(image.getDetails());

	}

	// Method to view all images
	public static void viewImage() {

		ImageAlbum allImages = manager.getAllImages();
		allImages.bubbleSortbyDate(true);

		ImageRecord image = allImages.getFirst();
		displayImageView(image);
		
		while(true) {

		String options[] = { "First Image", "Next Image", "Previous Image", "Quit" };
		Menu viewMenu = new Menu("View Images", options);
		int userOpt = viewMenu.getUserChoice();

		if (userOpt == 1) {
			displayImageView(allImages.getFirst());
			if (image == null) {
				System.out.println("No image in application");
				System.out.println();
				continue;
			}
			displayImageView(image);
		} else if (userOpt == 2) {
			image = allImages.getNext();
			if (image == null) {
				System.out.println("No previous image in application");
				System.out.println();
				continue;
			}
			displayImageView(image);
		} else if (userOpt == 3) {
			image = allImages.getPrevious();
			if (image == null) {
				System.out.println("No previous images in application");
				continue;
			}
			displayImageView(image);
		} else if (userOpt == 4) {
			System.out.println("Main Menu");
			break;
		} else {
			System.out.println("Invalid choise. Please try again.");
		}
		}
	}
}
