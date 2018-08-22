package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FAQSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backFaqsButton)
    void onRulesGame(){
        Intent intent = new Intent(FAQSActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
