package hust.soict.aims.media;

import hust.soict.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public Track(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (this.length <= 0) {
            System.err.println("Error: Track length is non-positive");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || !(obj instanceof Track)) return false;

        Track other = (Track) obj;
        
        boolean titleMatch = (this.title != null && this.title.equalsIgnoreCase(other.title));
        boolean lengthMatch = (this.length == other.length);

        return titleMatch && lengthMatch;
    }
}