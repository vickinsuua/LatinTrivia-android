package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Game;
import app.my.myapp.models.Setting;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HowToPlayActivity extends AppCompatActivity {

    TextView HowToPlayTitle;
    TextView HowToPlayDescription;

    @BindView(R.id.backHowToPlayButton)
    Button backSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        ButterKnife.bind(this);

        HowToPlayTitle = (TextView)findViewById(R.id.howToPlayTitleTextView);
        HowToPlayDescription = (TextView)findViewById(R.id.howToPlayDescriptionTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Setting> setting = api.getSetting("HowToPlay");

        setting.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                if (response.isSuccessful()) {
                    HowToPlayTitle.setText(response.body().getName());
                    HowToPlayDescription.setText(response.body().getDescription());
                } else {
                }
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.backHowToPlayButton)
    void onBack(){
        Intent intent = new Intent(HowToPlayActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
