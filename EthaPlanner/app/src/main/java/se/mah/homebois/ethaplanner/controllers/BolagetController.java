package se.mah.homebois.ethaplanner.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.List;

import se.mah.homebois.ethaplanner.net.DrinkTypeImageUrlMapper;
import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.db.BolagetDB;
import se.mah.homebois.ethaplanner.models.BolagetArticle;
import se.mah.homebois.ethaplanner.net.BolagetDataDownloader;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetController implements BolagetDataDownloader.IBolagetDownloadListener, DrinkTypeImageUrlMapper.IImageUrlListener{

    private BolagetDB bolagetDB;

    public BolagetController(Activity activity) {
        bolagetDB = new BolagetDB(activity);

        SharedPreferences prefs = activity.getSharedPreferences(Globals.APP_SETTINGS_NAME, Context.MODE_PRIVATE);
        long lastUpdate = prefs.getLong("lastBolagetUpdate", 0);
        long now = Calendar.getInstance().getTimeInMillis();

        if (now - lastUpdate > Globals.DAYS_IN_MS) {
            updateDatabase();
            prefs.edit().putLong("lastBolagetUpdate", now).apply();
        }


        List<BolagetArticle> s;
        s = bolagetDB.findByType(new String[]{"vin", "sprit"});
        s.size();
        if (s.size() > 10)
            new DrinkTypeImageUrlMapper(this).execute(s.get(3));
    }

    private void updateDatabase() {
        new BolagetDataDownloader(bolagetDB, this).execute(Globals.BOLAGET_API_URL);
    }

    @Override
    public void onComplete(List<BolagetArticle> articles) {
        //bolagetDB.clearAndUpdate(articles);
    }

    @Override
    public void onImageUrlReady(String url) {
        url.toString();
    }
}
