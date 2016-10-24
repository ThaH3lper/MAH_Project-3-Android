package se.mah.homebois.ethaplanner.models;

import se.mah.homebois.ethaplanner.views.ListContent.SortItem;

/**
 * Created by Simon on 10/23/2016.
 */

public class SearchModel {
    public long selectedDate;

    public SortItem sortBy;

    public SearchModel(long timeInMillis, SortItem selectedItem) {
        this.selectedDate = timeInMillis;
        this.sortBy = selectedItem;
    }
}
