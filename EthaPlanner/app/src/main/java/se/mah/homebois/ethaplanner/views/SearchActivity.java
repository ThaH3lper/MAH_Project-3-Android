package se.mah.homebois.ethaplanner.views;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.controllers.BolagetController;
import se.mah.homebois.ethaplanner.models.SearchModel;
import se.mah.homebois.ethaplanner.views.ListContent.SortCategories;
import se.mah.homebois.ethaplanner.views.ListContent.SortItem;

public class SearchActivity extends AppCompatActivity {

    private String                 timeAsText;
    private int                    time;
    private ArrayAdapter<SortItem> spinnerAdapter;
    private Button                 setWeatherButton;
    private Spinner                sortSpinner;

    private Calendar          selectedDay;
    private BolagetController bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.bc = new BolagetController(this);

        selectedDay = Calendar.getInstance();
        selectedDay.set(Calendar.HOUR_OF_DAY, 0);
        if (savedInstanceState != null && savedInstanceState.getLong("selectedDay", 0) != 0 ) {
            selectedDay.setTimeInMillis(savedInstanceState.getLong("selectedDay", 0));
        }

        initSpinner();
        initSearchDate();
        initSearch();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("selectedDay", selectedDay.getTimeInMillis());
    }

    private void initSearch() {
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchResultActivity();
            }
        });
    }

    private void launchResultActivity() {
        if (bc.isDownloading()) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Loading Data");
            dialog.setMessage("Downloading data please wait...");
            dialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (!bc.isDownloading()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    launchResultActivity();
                                }
                            });
                            break;
                        }
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return;
        }

        long now = Calendar.getInstance().getTimeInMillis();
        if (selectedDay.getTimeInMillis() - now > Globals.TEN_DAYS_MS || selectedDay.getTimeInMillis() - now < -Globals.DAYS_IN_MS) {
            Snackbar.make(sortSpinner, "Choose a date within 10 days from now", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent result = new Intent(this, MainActivity.class);
        result.putExtra("model", new Gson().toJson(new SearchModel(selectedDay.getTimeInMillis(), spinnerAdapter.getItem(sortSpinner.getSelectedItemPosition()))));
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

        updateSearchDate();
    }

    private void initSpinner() {
        sortSpinner = (Spinner) findViewById(R.id.spinnerAPK);
        SortCategories.setContext(this);
        spinnerAdapter = new ArrayAdapter<SortItem>(this, android.R.layout.simple_spinner_item, SortCategories.sortItems);
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
        Calendar mCurrentDate = selectedDay;
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

        mDatePicker.setTitle(getString(R.string.select_date));
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
            String aboutText = "EthaPlanner©, by Simon Bothén, Tim Lindstam, Patrik Nilsson";
            Toast.makeText(this, aboutText, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        bc.dispose();
        super.onDestroy();
    }
}
