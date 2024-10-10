package part01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ImageManager {

	public ArrayList<ImageRecord> List;
	public static int nextId = 1;

	public ImageManager() {

		this.List = new ArrayList<>();

		// adding image through ImageManager
		
		List.add(new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy", ImageType.ASTRONOMY,
				LocalDate.parse("2023-01-01"), "Andromeda.png"));
		
		List.add(new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", ImageType.ARCHITECTURE,
				LocalDate.parse("2023-02-01"), "LanyonQUB.png"));
		
		List.add(new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.", ImageType.SPORT,
				LocalDate.parse("2023-03-01"), "KermitGolf.png"));
		
		List.add(new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.", ImageType.LANDSCAPE,
				LocalDate.parse("2023-04-01"), "Mournes.png"));
		
		List.add(new ImageRecord("Homer Simpson", "Homer Simpson - A portrait of the man.", ImageType.POTRAIT,
				LocalDate.parse("2023-03-01"), "Homer.png"));
		
		List.add(new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,
				LocalDate.parse("2023-04-01"), "RedKite.png"));
		
		List.add(new ImageRecord("Central Park", "An overhead view of Central Park New York USA.", ImageType.AERIAL,
				LocalDate.parse("2023-05-01"), "CentralPark.png"));
		
		List.add(new ImageRecord("Apples", "A bunch of apples", ImageType.FOOD, LocalDate.parse("2023-06-01"),
				"Apples.png"));
		
		List.add(new ImageRecord("Programming Meme", "A Chat GPT Programming meme.", ImageType.AERIAL,
				LocalDate.parse("2023-05-01"), "Meme.png"));
	}

	public void addImage(ImageRecord image) {

		List.add(image);
	}

	public ImageRecord searchId(int id) {

		// Iterate through all images in the List
		for (ImageRecord image : List) {
			// Check if the ID of the current image matches the specified ID
			if (image.getId() == id) {
				// If a match is found, return the image
				return image;
			}
		}
		// If no match is found, return null
		return null;
	}

	public ImageAlbum searchTitle(String title) {

		// Create a new ImageAlbum to store the matching images
		ImageAlbum verified = new ImageAlbum();

		// Iterate through all images in the List
		for (ImageRecord image : List) {
			// Check if the title of the current image matches the specified title
			if (image.getTitle().equals(title)) {
				// If a match is found, add the image to the verified ImageAlbum
				verified.addImage(image);
			}
		}
		// Return the ImageAlbum containing the matching images
		return verified;
	}

	public ImageAlbum searchDescription(String desc) {

		// Create a new ImageAlbum to store the matching images
		ImageAlbum verified = new ImageAlbum();

		// Iterate through all images in the List
		for (ImageRecord image : List) {
			// Check if the description of the current image matches the specified
			// description
			if (image.getDesc().equals(desc)) {
				// If a match is found, add the image to the verified ImageAlbum
				verified.addImage(image);
			}
		}
		// Return the ImageAlbum containing the matching images
		return verified;

	}

	public ImageAlbum searchGenre(ImageType type) {

		// Create a new ImageAlbum to store the matching images
		ImageAlbum verified = new ImageAlbum();
		// Iterate through all images in the List
		for (ImageRecord image : List) {
			// Check if the genre of the current image matches the specified genre
			if (image.getType() == type) {
				// If a match is found, add the image to the verified ImageAlbum
				verified.addImage(image);
			}
		}
		// Return the ImageAlbum containing the matching images
		return verified;
	}

	public ImageAlbum searchDates(LocalDate start, LocalDate end) {

		// Create a new ImageAlbum to store the matching images
		ImageAlbum verified = new ImageAlbum();
		// Iterate through all images in the List
		for (ImageRecord image : List) {
			LocalDate date = image.getDate();
			// Check if the date of the current image matches the specified date
			if (date.isAfter(start) && date.isBefore(end)) {
				// If a match is found, add the image to the verified ImageAlbum
				verified.addImage(image);
			}
		}
		// Return the ImageAlbum containing the matching images
		return verified;
	}

	public ImageAlbum getAllImages() {

		ImageAlbum verified = new ImageAlbum();
		verified.List.addAll(List);
		// Return the ImageAlbum containing all images
		return verified;
	}
}
