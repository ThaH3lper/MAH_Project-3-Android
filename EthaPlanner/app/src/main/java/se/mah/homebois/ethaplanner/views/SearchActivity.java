package se.mah.homebois.ethaplanner.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.models.SearchModel;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerCategories;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;

public class SearchActivity extends AppCompatActivity {

    private String       timeAsText;
    private int          time;
    private ArrayAdapter<SpinnerItem> spinnerAdapter;
    private Button       setWeatherButton;
    private Spinner      sortSpinner;

    private Calendar selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initSpinner();
        initSearchDate();
        initSearch();
    }

    private void initSearch() {
        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchResultActivity();
            }
        });
    }

    private void launchResultActivity() {
        long now = Calendar.getInstance().getTimeInMillis();

        if (selectedDay.getTimeInMillis() - now > Globals.TEN_DAYS_MS || selectedDay.getTimeInMillis() - now < -Globals.DAYS_IN_MS) {
            Snackbar.make(sortSpinner, "Choose a date within 10 days from now", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent result = new Intent(this, MainActivity.class);
        result.putExtra("model", new Gson().toJson(new SearchModel(selectedDay.getTimeInMillis(), spinnerAdapter.getItem( sortSpinner.getSelectedItemPosition()))));
        startActivity(result);
    }

    private void initSearchDate() {
        setWeatherButton = (Button) findViewById(R.id.setWeatherButton);
        setWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(v);
            }
        });

        selectedDay = Calendar.getInstance();
        updateSearchDate();
    }

    private void initSpinner() {
        sortSpinner = (Spinner)findViewById(R.id.spinnerAPK);
        spinnerAdapter = new ArrayAdapter<SpinnerItem>(this, android.R.layout.simple_spinner_item, SpinnerCategories.spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Globals.APP_SETTINGS_NAME, MODE_PRIVATE);
        if (!preferences.getBoolean("userAccepted", false)) {
            Intent disclaimer = new Intent(this, DisclaimerActivity.class);
            startActivity(disclaimer);
        }
    }

    public void showDateDialog(View v) {
        final View clicked = v;
        Calendar mCurrentDate = Calendar.getInstance();
        int yyyy, mm, dd;
        yyyy = mCurrentDate.get(Calendar.YEAR);
        mm = mCurrentDate.get(Calendar.MONTH);
        dd = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                selectedDay.set(year, month, day, 0, 0);
                updateSearchDate();

            }
        }, yyyy, mm, dd);

        mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }

    private void updateSearchDate() {
        setWeatherButton.setText(new SimpleDateFormat("dd/MM/yyyy").format(selectedDay.getTime()));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
