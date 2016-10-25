package se.mah.homebois.ethaplanner.views.ListContent;

/**
 * Created by Tim on 2016-10-23.
 */
public class ListViewItems {

    private String Title;
    private String Alcohol;
    private String Price;
    private String APK;
    private String Id;
    private String Volym;
    private String Type;
    private String ImageUrl;

    public ListViewItems(String id, String type, String apk, String Title, String Alcohol, String Price, String Volym) {
        this.Title = Title;
        this.Alcohol = Alcohol;
        this.Price = Price;
        this.APK = apk;
        this.Id = id;
        this.Type = type;
        this.Volym = Volym;
    }

    public String getVolym() {
        return Volym;
    }

    public String getTitle() {
        return Title;
    }

    public String getAlcohol() {
        return Alcohol;
    }

    public String getPrice() {
        return Price;
    }

    public String getAPK() {
        return APK;
    }

    public String getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
