package hust.soict.aims.media;
import java.util.ArrayList;

import java.util.List;

public class Book extends Media {

    private List<String> authors = new ArrayList<String>();

    public Book() {
    }

    public Book(String title, String category, float cost) {
        super(title,category,cost);
    }

    public Book(int id, String title, String category, float cost){

        super(id,title,category,cost);
    }

    public Book(String title){
        super(title);
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Author already exists!");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        } else {
            System.out.println("Author not found!");
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - Authors: " + authors + ": " + getCost() + " $";
    }
}