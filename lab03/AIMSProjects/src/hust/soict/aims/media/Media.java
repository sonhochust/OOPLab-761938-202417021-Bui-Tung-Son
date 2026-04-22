package hust.soict.aims.media;
import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    public Media(){}

    public int getId(){return this.id;}

    public String getCategory(){return this.category;}

    public float getCost(){return this.cost;}

    public String getTitle(){return this.title;}

    public void setId(int id){this.id = id;}

    public void setCategory(String category){this.category = category;}

    public void setCost(float cost){this.cost = cost;}

    public void setTitle(String title){this.title = title;}

    public Media(int id, String title, String category, float cost){
        this.id=id;
        this.title=title;
        this.category=category;
        this.cost=cost;
    }

    public Media(String title){
        this.title=title;
    }

    public Media(String title, String category, float cost){
        this.title=title;
        this.category=category;
        this.cost=cost;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null ||!(o instanceof Media)){
            return false;
        }
        Media m=(Media)o;
        return this.title.equalsIgnoreCase(m.title);
    }

    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
}
class MediaComparatorByTitleCost implements java.util.Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh tên trước
        int titleDiff = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleDiff != 0) {
            return titleDiff;
        }
        // Nếu tên giống nhau, so sánh giá (giá cao hơn đứng sau)
        return Float.compare(m2.getCost(), m1.getCost());
    }
}
class MediaComparatorByCostTitle implements java.util.Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // 1. So sánh theo giá (Cost)
        // Dùng Float.compare để xử lý chuẩn số thực
        int costDiff = Float.compare(m1.getCost(), m2.getCost());

        if (costDiff != 0) {
            // Nếu muốn giá cao nhất lên đầu (giảm dần) thì trả về -costDiff
            // Còn đây là mặc định tăng dần (giá thấp đến cao)
            return costDiff;
        }

        // 2. Nếu giá bằng nhau (costDiff == 0), so sánh theo Tiêu đề (Title)
        // Sử dụng compareToIgnoreCase để không phân biệt hoa thường
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}

