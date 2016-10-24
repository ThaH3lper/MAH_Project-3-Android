package se.mah.homebois.ethaplanner.views.ListContent;

/**
 * Created by Tim on 2016-10-23.
 */
public class    ListViewItems {

    public String Title;
    public String Alcohol;
    public String Price;
    public String APK;

    //Väder vaiabler



    public ListViewItems(String Title, String Alcohol, String Price, String pricePerLiter)
    {
        this.Title = Title;
        this.Alcohol = Alcohol;
        this.Price = Price;

        //matemagi
        //double apk = Double.parseDouble(Alcohol) / Double.parseDouble(pricePerLiter);

        this.APK = "---";
    }

    public void setTitle(String Title)
    {
        this.Title = Title;
    }
    public void setAlcohol(String Alcohol)
    {
        this.Alcohol = Alcohol;
    }
    public void setPrice(String Price)
    {
        this.Price = Price;
    }
    public void setAPK(String APK)
    {
        this.APK = APK;
    }

    public String getTitle()
    {
        return Title;
    }
    public String getAlcohol()
    {
        return Alcohol;
    }
    public String getPrice()
    {
        return Price;
    }
    public String getAPK()
    {
        return APK;
    }
}
