package part02;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageAlbum {

	// List to store ImageRecords
	public ArrayList<ImageRecord> List;
	// position pointer for navigation
	private int position;

	// constructor
	public ImageAlbum() {
		// initialise the List in constructor
		this.List = new ArrayList<>();
		// initialise position pointer
		this.position = -1;
	}

	// method to get the first image in the list
	public ImageRecord getFirst() {
		// set position to the beginning of the list
		position = 0;
		try {
			if (!List.isEmpty()) {
				// returning the first image in the list
				return List.get(0);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: Index out of bounds while getting the first image.");
		}
		// return null if the list is empty
		return null;
	}

	// method to get the next image in the list initialising ImageRecord
	public ImageRecord getNext() {

		try {
			if (position < List.size() - 1) {
				// Move the position pointer to the next index
				position++;
				// Return the image at the updated position
				return List.get(position);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: Index out of bounds while getting the next image.");
		}
		// Return null if there are no more images
		return null;
	}

	// Method to get the previous image in the list
	public ImageRecord getPrevious() {
		try {
			if (position > 0) {
				// Move the position pointer to the previous index
				position--;
				// Return the image at the updated position
				return List.get(position);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: Index out of bounds while getting the previous image.");
		}
		// Return null if already at the beginning of the list
		return null;
	}

	// Method to add an image to the list
	public void addImage(ImageRecord image) {

		// Add the image to the list
		List.add(image);
		// Sort the list by date after adding the image
		bubbleSortbyDate(true);

	}

	/**
	 * Receives an ArrayList of date and sorts it in ascending order
	 * @param up
	 * @return ArrayList containing the same elements but in ascending order
	 */
	public void bubbleSortbyDate(boolean up) {

		// Get the length of the list
		int length = List.size();
		// Variable to track changes made during sorting
		boolean swapped;
		do {
			// Reset change counter
			swapped = false;
			try {
				for (int i = 0; i < length - 1; i++) {
					// Get the current image
					ImageRecord now = List.get(i);
					// Get the next image
					ImageRecord next = List.get(i + 1);

					// Compare dates and swap if necessary based on the sorting order
					if ((now.getDate().isAfter(next.getDate()) && up)
							|| (now.getDate().isBefore(next.getDate()) && !up)) {
						// Set the current image to the next image
						List.set(i, next);
						// Set the next image to the current image
						List.set(i + 1, now);
						swapped = true;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error: Index out of bounds while sorting images by date.");
			}

			// Continue sorting until no changes are made
		} while (swapped);
	}
}
