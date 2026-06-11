package hust.soict.aims.media;

import hust.soict.aims.exception.PlayerException;

public interface Playable {
    public void play() throws PlayerException;
}