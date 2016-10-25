package se.mah.homebois.ethaplanner.controllers;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.BolagetArticle;
import se.mah.homebois.ethaplanner.models.SearchModel;
import se.mah.homebois.ethaplanner.models.Weather.Forecast;
import se.mah.homebois.ethaplanner.models.Weather.Location;
import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;
import se.mah.homebois.ethaplanner.models.WeatherToType;
import se.mah.homebois.ethaplanner.net.WeatherFetcher;
import se.mah.homebois.ethaplanner.views.ListContent.DrinkResultListAdapter;
import se.mah.homebois.ethaplanner.views.ListContent.ListViewItems;
import se.mah.homebois.ethaplanner.views.MainActivity;

/**
 * Created by Simon on 10/23/2016.
 */

public class MainController {

    private final BolagetController bc;
    private final WeatherController wc;
    private final MainActivity      activity;
    private       SearchModel       searchModel;

    private int day;

    public MainController(WeatherController wc, BolagetController bc, MainActivity activity) {
        this.activity = activity;
        this.bc = bc;
        this.wc = wc;
    }

    public void loadResults(SearchModel searchModel) {
        this.searchModel = searchModel;
        long longDate = searchModel.selectedDate;
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.MINUTE, 0);
        long now = cd.getTimeInMillis();
        long delta = (longDate - now) + 60 * 1000;
        int day = (int) (delta / Globals.DAYS_IN_MS);

        updateWeather(day);
    }

    public void updateWeather(int day) {
        this.day = day;
        this.wc.updateWeather(day, new WeatherListener());
    }

    public class WeatherListener implements WeatherFetcher.ModelListener {
        @Override
        public void getWeather(WeatherModel model) {
            Forecast cast = model.getQuery().getResults().getChannel().getItem().getForecast()[day];
            Location loc = model.getQuery().getResults().getChannel().getLocation();

            activity.setTvLocation(loc.getCountry() + "-" + loc.getRegion().replace(" ", "") + "-" + loc.getCity());
            activity.setTvDate(cast.getDate());
            activity.setTvMaxValue(cast.getHigh());
            activity.setTvMinValue(cast.getLow());
            activity.setTvText(String.format(activity.getResources().getString(R.string.weather_type), cast.getText()));

            String[] types = new String[]{"Alkoholfritt"};
            if (searchModel.sortBy.getId() != 3) {
                types = WeatherToType.getTypes(Integer.parseInt(cast.getCode()));
            }

            int amountEach = Globals.AMOUNT_IN_RESULT / types.length;
            int over = amountEach * types.length;

            List<ListViewItems> listItem = new ArrayList<ListViewItems>();
            String typesStr = "";

            int i = 0;
            for (String type : types) {

                if(i == 0)
                    typesStr += " " + type;
                else
                    typesStr += ", " + type;

                int add = 0;
                if (over > 0) {
                    over--;
                    add = 1;
                }
                i++;

                List<BolagetArticle> list = bc.findByType(type, amountEach + add, searchModel.sortBy);
                for (BolagetArticle ba : list)
                    listItem.add(new ListViewItems(ba.nr, ba.Varugrupp, String.format("%.02f", ba.Apk) + "ml/sek", ba.Namn, ba.Alkoholhalt, ba.Prisinklmoms + ":-"));
            }
            activity.setTvRecommend(activity.getResources().getString(R.string.weather_recommend) + typesStr);

            activity.getListResult().setAdapter(new DrinkResultListAdapter(activity, listItem));

            LinearLayout dialog = activity.getWeatherLayout();
            dialog.setVisibility(LinearLayout.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(activity, R.anim.weatheranimation);
            animation.setDuration(1000);
            dialog.setAnimation(animation);
            dialog.animate();
            animation.start();
        }
    }
}
