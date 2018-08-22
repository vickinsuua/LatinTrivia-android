package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import app.my.myapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.backContactButton)
    Button backContact;

    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backContactButton)
    void onBackContact(){
        Log.i(TAG, "onBackContact: ");
        Intent intent = new Intent(ContactActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
