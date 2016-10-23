package se.mah.homebois.ethaplanner.controllers;

import android.util.Log;
import android.widget.TextView;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;
import se.mah.homebois.ethaplanner.net.WeatherFetcher;
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

    public void setWeather(int day)
    {
        this.day = day;
        wc.updateWeather(day, new WeatherListener());
    }

    public class WeatherListener implements WeatherFetcher.ModelListener {
        @Override
        public void getWeather(WeatherModel model) {
            Log.d("GOT: ", model.getQuery().getResults().getChannel().getItem().getForecast()[day].getText());

            activity.setTvDate(model.getQuery().getResults().getChannel().getItem().getForecast()[day].getDate());

            activity.setTvHigh(String.format(activity.getResources().getString(R.string.weather_high),
                    model.getQuery().getResults().getChannel().getItem().getForecast()[day].getHigh()));

            activity.setTvLow(String.format(activity.getResources().getString(R.string.weather_low),
                    model.getQuery().getResults().getChannel().getItem().getForecast()[day].getLow()));

            activity.setTvText(String.format(activity.getResources().getString(R.string.weather_type),
                    model.getQuery().getResults().getChannel().getItem().getForecast()[day].getText()));

        }
    }
}
