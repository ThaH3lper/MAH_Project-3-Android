package se.mah.homebois.ethaplanner.controllers;

import android.os.Debug;
import android.util.Log;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.db.BolagetDB;
import se.mah.homebois.ethaplanner.models.BolagetArticle;
import se.mah.homebois.ethaplanner.models.SearchModel;
import se.mah.homebois.ethaplanner.models.Weather.Forecast;
import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;
import se.mah.homebois.ethaplanner.models.WeatherToType;
import se.mah.homebois.ethaplanner.net.WeatherFetcher;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;
import se.mah.homebois.ethaplanner.views.MainActivity;

/**
 * Created by Simon on 10/23/2016.
 */

public class MainController {

    private final BolagetController bc;
    private final WeatherController wc;
    private MainActivity activity;
    private SearchModel searchModel;

    private int day;

    public MainController(WeatherController wc, BolagetController bc, MainActivity activity) {
        this.activity = activity;
        this.bc = bc;
        this.wc = wc;
    }

    public void loadResults(SearchModel searchModel)
    {
        this.searchModel = searchModel;
        long longDate = searchModel.selectedDate;

        long now = Calendar.getInstance().getTimeInMillis();
        long delta = longDate - now;
        int day = (int)(delta / Globals.DAYS_IN_MS);

        setWeather(day);
    }

    public void setDefaultWeather()
    {
        activity.setTvDate("01 jan 1999");
        activity.setTvHigh(String.format(activity.getResources().getString(R.string.weather_high), "0"));
        activity.setTvLow(String.format(activity.getResources().getString(R.string.weather_low), "0"));
        activity.setTvText(String.format(activity.getResources().getString(R.string.weather_type), ""));
    }

    public void setWeather(int day)
    {
        this.day = day;
        wc.updateWeather(day, new WeatherListener());
    }

    public class WeatherListener implements WeatherFetcher.ModelListener {
        @Override
        public void getWeather(WeatherModel model) {
            Forecast cast = model.getQuery().getResults().getChannel().getItem().getForecast()[day];
            activity.setTvDate(cast.getDate());
            activity.setTvHigh(String.format(activity.getResources().getString(R.string.weather_high), cast.getHigh()));
            activity.setTvLow(String.format(activity.getResources().getString(R.string.weather_low), cast.getLow()));
            activity.setTvText(String.format(activity.getResources().getString(R.string.weather_type), cast.getText()));

            //Do seach;
            String[] types = WeatherToType.getTypes(Integer.parseInt(cast.getCode()));
            int amountEach = Globals.AMOUNT_IN_RESULT / types.length;
            int over = amountEach * types.length;

            for (String type: types) {
                int add = 0;
                if(over > 0)
                {
                    over--;
                    add = 1;
                }
                List<BolagetArticle> list = bc.findByType(type, amountEach + add, searchModel.sortBy);
                showItemsInList(list);
            }
        }
    }

    private void showItemsInList(List<BolagetArticle> list)
    {
        for (BolagetArticle ba: list) {
            Log.d("Item: ", ba.Namn + " | " + ba.Apk);
        }
    }
}
