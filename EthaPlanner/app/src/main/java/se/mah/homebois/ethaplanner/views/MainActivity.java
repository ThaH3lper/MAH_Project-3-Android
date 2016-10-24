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

    TextView tvLocation;
    TextView tvDate;
    TextView tvLowValue;
    TextView tvHighValue;
    TextView tvText;
    TextView tvRecommend;

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

        tvRecommend = (TextView) findViewById(R.id.tvRec);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLowValue = (TextView) findViewById(R.id.tvMinValue);
        tvHighValue = (TextView) findViewById(R.id.tvMaxValue);
        tvText = (TextView) findViewById(R.id.tvText);
        listResult = (ListView) findViewById(R.id.listResult);
        weatherLayout = (LinearLayout) findViewById(R.id.layoutWeather);

        if ( getIntent().getExtras() != null) {
            SearchModel searchModel = new Gson().fromJson( getIntent().getExtras().getString("model"), SearchModel.class);
            mc.loadResults(searchModel);
        }
    }

    public void setTvRecommend(String recommend)
    {
        tvRecommend.setText(recommend);
    }
    public void setTvLocation(String loc)
    {
        tvLocation.setText(loc);
    }
    public void setTvDate(String date)
    {
        tvDate.setText(date);
    }
    public void setTvMinValue(String low)
    {
        tvLowValue.setText(low);
    }
    public void setTvMaxValue(String high)
    {
        tvHighValue.setText(high);
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

    @Override
    protected void onDestroy() {
        bc.dispose();
        super.onDestroy();
    }
}
