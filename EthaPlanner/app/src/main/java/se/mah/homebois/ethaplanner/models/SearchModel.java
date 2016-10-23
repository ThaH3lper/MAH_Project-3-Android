package se.mah.homebois.ethaplanner.models;

import se.mah.homebois.ethaplanner.views.ListContent.SpinnerCategories;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;

/**
 * Created by Simon on 10/23/2016.
 */

public class SearchModel {
    public long selectedDate;

    public SpinnerItem sortBy;

    public SearchModel(long timeInMillis, SpinnerItem selectedItem) {
        this.selectedDate = timeInMillis;
        this.sortBy = selectedItem;
    }
}
