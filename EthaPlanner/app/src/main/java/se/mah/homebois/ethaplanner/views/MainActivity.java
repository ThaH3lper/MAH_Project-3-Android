package se.mah.homebois.ethaplanner.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.controllers.BolagetController;
import se.mah.homebois.ethaplanner.controllers.MainController;
import se.mah.homebois.ethaplanner.controllers.WeatherController;
import se.mah.homebois.ethaplanner.models.SearchModel;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;

public class MainActivity extends AppCompatActivity {


    TextView tvDate;
    TextView tvLow;
    TextView tvHigh;
    TextView tvText;

    private BolagetController bc;
    private MainController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initControllers();

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLow = (TextView) findViewById(R.id.tvLow);
        tvHigh = (TextView) findViewById(R.id.tvHigh);
        tvText = (TextView) findViewById(R.id.tvText);

        mc.setDefaultWeather();
        mc.setWeather(0);

        if ( getIntent().getExtras() != null) {
            SearchModel searchModel = new Gson().fromJson( getIntent().getExtras().getString("model"), SearchModel.class);
            SpinnerItem a = searchModel.sortBy;
        }
    }

    public void setTvDate(String date)
    {
        tvDate.setText(date);
    }
    public void setTvLow(String low)
    {
        tvLow.setText(low);
    }
    public void setTvHigh(String high)
    {
        tvHigh.setText(high);
    }
    public void setTvText(String text)
    {
        tvText.setText(text);
    }

    private void initControllers() {
        bc = new BolagetController(this);

        WeatherController wc = new WeatherController(this);
        mc = new MainController(wc, bc, this);
    }

    private void launchSearchActivity() {
        Intent search = new Intent(this, SearchActivity.class);
        startActivity(search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            String toastname = "EthaPlanner©, by Patrik Nilsson, Simon Bothen, Tim Lindstam";

            Toast.makeText(this, toastname, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);

    }

    protected void onDestroy() {
        bc.dispose();
        super.onDestroy();

    }
}
