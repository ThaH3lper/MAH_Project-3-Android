package se.mah.homebois.ethaplanner.views;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import se.mah.homebois.ethaplanner.R;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerCategories;
import se.mah.homebois.ethaplanner.views.ListContent.SpinnerItem;

public class SearchActivity extends AppCompatActivity {

    String timeAsText;
    int time;
    ArrayAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SpinnerItems();
        RegisterListeners();
    }

    private void RegisterListeners() {
        Button b1 = (Button) findViewById(R.id.setWeatherButton);
        b1.setOnClickListener(new ButtonListener());
    }

    private void SpinnerItems() {
        spinnerAdapter = new ArrayAdapter<SpinnerItem>(this, android.R.layout.simple_spinner_item, SpinnerCategories.spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    public void showDateDialog(View v)
    {
        final View clicked = v;
        Calendar mCurrentDate = Calendar.getInstance();
        int yyyy, mm, dd;
        yyyy = mCurrentDate.get(Calendar.YEAR);
        mm = mCurrentDate.get(Calendar.MONTH);
        dd = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                month++;
                String m = month+ "";
                if(month < 10){ m = "0" + m;}

                String d = day + "";
                if (day < 10){d = "0" + d;}

                timeAsText = year + "-" + m + "-" + d;
                //((Button) clicked).setText(timeAsText);
                time = Integer.parseInt(year + m + d);

            }
        }, yyyy, mm, dd);

        mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v)
        {
            showDateDialog(v);
        }
    }
}
