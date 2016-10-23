package se.mah.homebois.ethaplanner.controllers;

import android.icu.util.Calendar;
import android.util.Log;
import android.widget.TextView;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.Weather.Forecast;
import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;
import se.mah.homebois.ethaplanner.net.WeatherFetcher;
import se.mah.homebois.ethaplanner.views.ListContent.ListAdapter;
import se.mah.homebois.ethaplanner.views.MainActivity;

/**
 * Created by Simon on 10/23/2016.
 */

public class MainController {

    private final BolagetController bc;
    private final WeatherController wc;
    private MainActivity activity;

    private int day;

    public MainController(WeatherController wc, BolagetController bc, MainActivity activity) {
        this.activity = activity;
        this.bc = bc;
        this.wc = wc;
    }

    public void loadResults()
    {
        //activity.getListResult().setAdapter(new ListAdapter(activity, ));
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
        }
    }
}
