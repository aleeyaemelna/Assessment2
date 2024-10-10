package part01;

import java.time.LocalDate;

public class ImageRecord {

	private int id;
	private String title;
	private String description;
	private ImageType genre;
	private LocalDate dateTaken;
	private String Thumbnail;
	private static int countId = 1;

	/**
	 * ImageRecord constructor
	 * @param title
	 * @param desc
	 * @param genre
	 * @param date
	 * @param thumbnail
	 */
	public ImageRecord(String title, String desc, ImageType genre, LocalDate date, String thumbnail) {

		this.id = countId++;
		setTitle(title);
		setDescription(desc);
		setType(genre);
		setDate(date);
		setThumbnail(thumbnail);

	}

	/**
	 * Id accessor
	 * @return id of Image Record
	 */
	public int getId() {
		return this.id;
	}
	
	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		} else {
			this.title = "Unknown";
		}
	}

	/**
	 * title accessor
	 * @return title of the Image instance
	 */
	public String getTitle() {
		return this.title;
	}

	public void setDescription(String desc) {
		if (desc != null) {
			this.description = desc;
		} else {
			this.description = null;
		}
	}

	/**
	 * descciption accessor
	 * @return description of the Image instance
	 */
	public String getDesc() {
		return this.description;
	}

	public void setType(ImageType genre) {
		if (genre != null) {
			this.genre = genre;
		} else {
			this.genre = ImageType.OTHER;
		}
	}

	/**
	 * genre accessor
	 * @return type of the Image instance
	 */
	public ImageType getType() {

		return this.genre;
	}

	public void setDate(LocalDate date) {

		this.dateTaken = date;
	}

	/**
	 * Date accessor
	 * @return date of the Image instance
	 */
	public LocalDate getDate() {

		return this.dateTaken;
	}

	public void setThumbnail(String tn) {

		if (tn != null) {
			this.Thumbnail = tn;
		} else {
			this.Thumbnail = null;
		}
	}

	/**
	 * thumbnail accessor
	 * @return thumbnail of the Image instance
	 */
	public String getThumbnail() {

		return this.Thumbnail;
	}

	/**
	 * Get Image's Description in string format. Useful for printing in console.
	 * @return string fully describing the Image Record.
	 */
	public String getDetails() {

		String details = "ID: " + id + ", Title: " + getTitle() + ", Description: " + getDesc() + ", Genre: "
				+ getType() + ", Date Taken: " + getDate() + ", Thumbnail: " + getThumbnail();

		return details;
	}

	public String toString() {

		return getDetails();
	}

}
