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


public class TermsActivity extends AppCompatActivity {

    TextView termsTitle;
    TextView termsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);

        termsTitle = (TextView)findViewById(R.id.termsTitleTextView);
        termsDescription = (TextView)findViewById(R.id.termsDescriptionTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Setting> setting = api.getSetting("Terms");

        setting.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                termsTitle.setText(response.body().getName());
                termsDescription.setText(response.body().getDescription());
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {
                Toast.makeText(TermsActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.backTermsButton)
    void onRulesGame(){
        Intent intent = new Intent(TermsActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
