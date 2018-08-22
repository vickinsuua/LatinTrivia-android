package app.my.myapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar().hide();
    }
}
