package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Setting;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ContactActivity extends AppCompatActivity {

    TextView ContactTitle;
    TextView ContactDescription;

    @BindView(R.id.backContactButton)
    Button backContact;

    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        ContactTitle = (TextView)findViewById(R.id.contactTitleTextView);
        ContactDescription = (TextView)findViewById(R.id.contactDescriptionTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Setting> setting = api.getSetting("Contact");

        setting.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                ContactTitle.setText(response.body().getName());
                ContactDescription.setText(response.body().getDescription());
            }
            @Override
            public void onFailure(Call<Setting> call, Throwable t) {
                Toast.makeText(ContactActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.backContactButton)
    void onBackContact(){
        Intent intent = new Intent(ContactActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
