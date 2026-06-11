package hust.soict.aims.store;

import hust.soict.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if(itemsInStore.contains(media)){
            System.out.println("Media already exists");
        }else{
            itemsInStore.add(media);
            System.out.println("Media added");
        }
    }

    public void removeMedia(Media media) {
        if(itemsInStore.contains(media)){
            itemsInStore.remove(media);
            System.out.println("Media removed");
        }else{
            System.out.println("Media Not found");
        }
    }

    public void view(){
        System.out.println("**************************************");
        int i=1;
        for(Media media : itemsInStore){
            System.out.println(i+". "+media.toString());
            i++;
        }
        System.out.println("**************************************");
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}