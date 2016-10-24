package se.mah.homebois.ethaplanner.views.ListContent;

import android.content.Context;

import se.mah.homebois.ethaplanner.R;

/**
 * Created by Tim on 2016-10-23.
 */
public class SpinnerCategories {
    private static Context context;

    public static SpinnerItem[] spinnerItems = new SpinnerItem[]{
            new SpinnerItem(0, context.getResources().getString(R.string.no_sort_by_apk)),
            new SpinnerItem(1, context.getResources().getString(R.string.high_alc_per_crown) ),
            new SpinnerItem(2, context.getResources().getString(R.string.low_alc_per_crown)),
            new SpinnerItem(3, context.getResources().getString(R.string.alcohol_free))

    };

    public static void setContext(Context context) {context = context;}

}

