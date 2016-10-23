package se.mah.homebois.ethaplanner.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import se.mah.homebois.ethaplanner.Globals;
import se.mah.homebois.ethaplanner.R;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        initButtons();
    }

    private void initButtons() {
        Button btnAccept = (Button)findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences(Globals.APP_SETTINGS_NAME, MODE_PRIVATE).edit().putBoolean("userAccepted", true).apply();
                finish();
            }
        });

        Button btnDecline = (Button)findViewById(R.id.btnDecline);
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
