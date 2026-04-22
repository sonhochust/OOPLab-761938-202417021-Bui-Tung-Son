package hust.soict.aims.media;

public abstract class Disc extends Media {
    private int length;
    private String director;

    // Getter cho các thuộc tính đặc thù của Disc
    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    // Constructor đầy đủ nhất để các lớp con gọi tới
    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost); // Gọi lên lớp cha Media
        this.length = length;
        this.director = director;
    }

    // Các Constructor khác để linh hoạt trong việc khởi tạo
    public Disc(String title, String category, float cost, int length, String director) {
        super(title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(int id, String title, String category, float cost, String director) {
        super(id, title, category, cost); // Gọi lên lớp Media
        this.director = director;
    }

    public Disc(String title) {
        super(title);
    }

    public Disc(){}
}