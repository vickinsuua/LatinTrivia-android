package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Setting;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PrivacyActivity extends AppCompatActivity {

    TextView PrivacyTitle;
    TextView PrivacyDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        ButterKnife.bind(this);

        PrivacyTitle = (TextView)findViewById(R.id.privacyTitleTextView);
        PrivacyDescription = (TextView)findViewById(R.id.privacyDescriptionTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Setting> setting = api.getSetting("Privacy");

        setting.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                PrivacyTitle.setText(response.body().getName());
                PrivacyDescription.setText(response.body().getDescription());
            }
            @Override
            public void onFailure(Call<Setting> call, Throwable t) {
                Toast.makeText(PrivacyActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.backPrivacyButton)
    void onRulesGame(){
        Intent intent = new Intent(PrivacyActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
