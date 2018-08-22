package app.my.myapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;
import android.content.Intent;
import android.os.Handler;

public class InitActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(InitActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);

    }
}
