package se.mah.homebois.ethaplanner.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerCategories;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;

public class SearchActivity extends AppCompatActivity {

    private String       timeAsText;
    private int          time;
    private ArrayAdapter spinnerAdapter;
    private Button       setWeatherButton;
    private Spinner      sortSpinner;

    private Calendar selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
        Intent result = new Intent(this, MainActivity.class);
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
              /*  month++;
                String m = month+ "";
                if(month < 10){ m = "0" + m;}

                String d = day + "";
                if (day < 10){d = "0" + d;}

                timeAsText = year + "-" + m + "-" + d;
                //((Button) clicked).setText(timeAsText);
                time = Integer.parseInt(year + m + d);*/

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

}
