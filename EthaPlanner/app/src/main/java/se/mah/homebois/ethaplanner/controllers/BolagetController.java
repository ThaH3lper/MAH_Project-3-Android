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
import se.mah.homebois.ethaplanner.views.ListContent.SortItem;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetController implements BolagetDataDownloader.IBolagetDownloadListener {

    private final BolagetDB bolagetDB;
    private boolean isDownloading = false;

    public BolagetController(Activity activity) {
        bolagetDB = new BolagetDB(activity);

        SharedPreferences prefs = activity.getSharedPreferences(Globals.APP_SETTINGS_NAME, Context.MODE_PRIVATE);
        long lastUpdate = prefs.getLong("lastBolagetUpdate", 0);
        long now = Calendar.getInstance().getTimeInMillis();

        if (lastUpdate == 0) {
            isDownloading = true;
        }

        // Update each week
        if (now - lastUpdate > Globals.DAYS_IN_MS * 7) {
            updateDatabase();
            prefs.edit().putLong("lastBolagetUpdate", now).apply();
        }
    }

    private void updateDatabase() {
        new BolagetDataDownloader(bolagetDB, this).execute(Globals.BOLAGET_API_URL);
    }

    public List<BolagetArticle> findByType(String type, int count, SortItem sort) {
        return bolagetDB.findByType(type, count, sort);
    }

    @Override
    public void onComplete(List<BolagetArticle> articles) {
        isDownloading = false;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void dispose() {
        bolagetDB.close();
    }
}
