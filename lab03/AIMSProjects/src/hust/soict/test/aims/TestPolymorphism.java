package hust.soict.test.aims;
import hust.soict.aims.media.*;
import java.util.ArrayList;
import java.util.List;

public class TestPolymorphism {
    public static void main(String[] args) {
        Media cd=new CompactDisc("Star war");
        Media cd2=new CompactDisc("Star war 2");
        Media dvd=new DigitalVideoDisc("Tay du ky");
        Media book = new Book("Tuoi tho du doi");
        List<Media> mediae=new ArrayList<Media>();
        mediae.add(cd);
        mediae.add(cd2);
        mediae.add(dvd);
        mediae.add(book);
        for(Media m:mediae){
            System.out.println(m.toString());
        }
    }

}
