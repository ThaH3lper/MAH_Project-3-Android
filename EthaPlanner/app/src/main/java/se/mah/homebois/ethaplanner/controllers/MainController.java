package se.mah.homebois.ethaplanner.controllers;

import se.mah.homebois.ethaplanner.views.MainActivity;

/**
 * Created by Simon on 10/23/2016.
 */

public class MainController {

    private final BolagetController bc;
    private final WeatherController wc;

    public MainController(WeatherController wc, BolagetController bc) {
        this.bc = bc;
        this.wc = wc;
    }
}
