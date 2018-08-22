package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backTermsButton)
    void onRulesGame(){
        Intent intent = new Intent(TermsActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
