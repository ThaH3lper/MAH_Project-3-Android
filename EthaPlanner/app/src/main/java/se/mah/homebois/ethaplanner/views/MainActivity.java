package se.mah.homebois.ethaplanner.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.controllers.BolagetController;
import se.mah.homebois.ethaplanner.controllers.MainController;
import se.mah.homebois.ethaplanner.controllers.WeatherController;
import se.mah.homebois.ethaplanner.models.SearchModel;

public class MainActivity extends AppCompatActivity {


    TextView tvDate;
    TextView tvLow;
    TextView tvHigh;
    TextView tvText;

    LinearLayout weatherLayout;

    ListView listResult;

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
        listResult = (ListView) findViewById(R.id.listResult);
        weatherLayout = (LinearLayout) findViewById(R.id.layoutWeather);

        mc.setDefaultWeather();

        if ( getIntent().getExtras() != null) {
            SearchModel searchModel = new Gson().fromJson( getIntent().getExtras().getString("model"), SearchModel.class);
            mc.loadResults(searchModel);
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
    public ListView getListResult()
    {
        return listResult;
    }
    public LinearLayout getWeatherLayout(){return weatherLayout;}

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
    protected void onDestroy() {
        bc.dispose();
        super.onDestroy();
    }
}
