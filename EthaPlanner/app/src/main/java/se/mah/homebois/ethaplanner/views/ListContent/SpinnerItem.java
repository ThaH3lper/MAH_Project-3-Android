package se.mah.homebois.ethaplanner.views.ListContent;

/**
 * Created by Tim on 2016-10-23.
 */
public class SpinnerItem {
    String label;
    int icon;

    public SpinnerItem(String label)
    {
        this.label = label;
        this.icon = icon;

    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
