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

public class RulesActivity extends AppCompatActivity {

    TextView rulesTitle;
    TextView rulesDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ButterKnife.bind(this);

        rulesTitle = (TextView)findViewById(R.id.rulesTitleTextView);
        rulesDescription = (TextView)findViewById(R.id.rulesDescriptionTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Setting> setting = api.getSetting("Rules");

        setting.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                rulesTitle.setText(response.body().getName());
                rulesDescription.setText(response.body().getDescription());
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {
                Toast.makeText(RulesActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.backRulesButton)
    void onRulesGame(){
        Intent intent = new Intent(RulesActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
