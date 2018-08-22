package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backRulesButton)
    void onRulesGame(){
        Intent intent = new Intent(RulesActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
