package se.mah.homebois.ethaplanner.views.ListContent;

/**
 * Created by Tim on 2016-10-23.
 */
public class SpinnerItem {
    String label;
    int id;

    public SpinnerItem(int id, String label)
    {
        this.label = label;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return label;
    }
}
