package se.mah.homebois.ethaplanner.views.ListContent;

import android.content.Context;

import se.mah.homebois.ethaplanner.R;

/**
 * Created by Tim on 2016-10-23.
 */
public class SpinnerCategories {

    public static SpinnerItem[] spinnerItems;

    public static void setContext(Context context) {
        spinnerItems = new SpinnerItem[]{
                new SpinnerItem(3, context.getResources().getString(R.string.no_sort_by_apk)),
                new SpinnerItem(0, context.getResources().getString(R.string.high_alc_per_crown)),
                new SpinnerItem(1, context.getResources().getString(R.string.low_alc_per_crown)),
                new SpinnerItem(2, context.getResources().getString(R.string.alcohol_free))
        };
    }

}

