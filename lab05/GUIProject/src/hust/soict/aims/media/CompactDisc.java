package hust.soict.aims.media;

import hust.soict.aims.exception.PlayerException;

import java.util.ArrayList;


public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public String getArtist() {
        return artist;
    }

    public CompactDisc() {
        super();
    }

    public CompactDisc(String title) {
        super(title);
    }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        // Sửa lại thứ tự ở đây để khớp với lớp Disc/Media
        super(id, title, category, cost, director);
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, String director, String artist, float cost, ArrayList<Track> tracks) {
        super(id, title, category, cost, director);
        this.artist = artist;
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("The track [" + track.getTitle() + "] is already in the tracklist.");
        } else {
            tracks.add(track);
            System.out.println("Track [" + track.getTitle() + "] has been added.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track [" + track.getTitle() + "] has been removed.");
        } else {
            System.out.println("Track [" + track.getTitle() + "] does not exist in the tracklist.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            java.util.Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = iter.next();
                try {
                    nextTrack.play();
                } catch (PlayerException e) {
                    throw e; // hoặc throw new PlayerException("Track lỗi: " + nextTrack.getTitle());
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }
    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() +
                " - Artist: " + this.artist + " - Director: " + this.getDirector() +
                " - Length: " + this.getLength() + " - Cost: " + this.getCost() + " $";
    }
}