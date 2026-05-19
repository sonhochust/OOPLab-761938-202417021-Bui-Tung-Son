package hust.soict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

	private static int nbDigitalVideoDiscs = 0;

	public DigitalVideoDisc(String title) {
		super(title);
		nbDigitalVideoDiscs++;
		this.setId(nbDigitalVideoDiscs);
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		super(0, title, category, cost, 0, null);
		nbDigitalVideoDiscs++;
		this.setId(nbDigitalVideoDiscs);
	}

	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(0, title, category, cost, length, director);
		nbDigitalVideoDiscs++;
		this.setId(nbDigitalVideoDiscs);
	}

	public DigitalVideoDisc(int id,String title, String category, String director, int length, float cost) {
		super(0, title, category, cost, length, director);
		nbDigitalVideoDiscs++;
		this.setId(nbDigitalVideoDiscs);
	}

	@Override
	public String toString() {
		return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + "$";
	}

	public boolean isMatch(String title) {
		if (this.getTitle() == null) return false;
		return this.getTitle().toLowerCase().contains(title.toLowerCase());
	}
	@Override
	public void play() {
		System.out.println("Playing " + this.getTitle());
		System.out.println("DVD Length:  " +this.getLength())	;
	}

}