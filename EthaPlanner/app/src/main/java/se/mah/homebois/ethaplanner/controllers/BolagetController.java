package se.mah.homebois.ethaplanner.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import se.mah.homebois.ethaplanner.Globals;

/**
 * Created by Simon on 10/23/2016.
 */

public class BolagetController {

    public BolagetController(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(Globals.APP_SETTINGS_NAME, Context.MODE_PRIVATE);
        prefs.getLong("lastBolagetUpdate")
    }

    private void updateDatabase() {

    }
}
