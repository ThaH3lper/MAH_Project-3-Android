package se.mah.homebois.ethaplanner.controllers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;
import se.mah.homebois.ethaplanner.net.WeatherFetcher;
import se.mah.homebois.ethaplanner.views.MainActivity;

/**
 * Created by Patrik on 2016-10-23.
 */

public class WeatherController implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    MainActivity activity;

    int day;

    private static int REQUEST_ACCESS_COARSE_LOCATION = 212;

    public WeatherController(MainActivity activity) {
        this.activity = activity;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public void updateWeather(int day, MainController.WeatherListener listener)
    {
        this.day = day;
        Location loc = getLocation();
        if(loc == null)
        {
            Toast.makeText(activity, activity.getResources().getString(R.string.location_error), Toast.LENGTH_LONG).show();
            activity.finish();
        }
        else
        {
            Log.d("Location: ", loc.getLatitude() + " "  + loc.getLongitude());
            getWeather(loc.getLatitude(), loc.getLongitude(), listener);
        }
    }

    private Location getLocation()
    {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
        }
        LocationManager mLocationManager = (LocationManager)activity.getApplicationContext().getSystemService(activity.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private void getWeather(double longitude, double latitude, MainController.WeatherListener listener)
    {
        new WeatherFetcher(listener,
                "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%20WHERE%20text%3D%22("+ longitude + "%2C" + latitude + ")%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys",
                "UTF-8");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

