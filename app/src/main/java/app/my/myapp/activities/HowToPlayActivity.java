package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import app.my.myapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HowToPlayActivity extends AppCompatActivity {

    @BindView(R.id.backHowToPlayButton)
    Button backSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backHowToPlayButton)
    void onBack(){
        Intent intent = new Intent(HowToPlayActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
