package app.my.myapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Game;
import app.my.myapp.models.User;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    TextView nicknameTextView;
    TextView balanceTextView;
    TextView extraLifeTextView;
    TextView gameDateTextView;
    TextView gamePrizeTextView;
    private static final String TAG = "error";
    public String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        nicknameTextView = (TextView)findViewById(R.id.nicknameTextView);
        balanceTextView = (TextView)findViewById(R.id.balanceTextView);
        extraLifeTextView = (TextView)findViewById(R.id.extraLifeTextView);
        gameDateTextView = (TextView)findViewById(R.id.gameDateTextView);
        gamePrizeTextView = (TextView)findViewById(R.id.gamePrizeTextView);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<Game> game = api.getGame("2018-08-18 12:51:18");

        game.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.isSuccessful()) {
                    gameDateTextView.setText(response.body().getDate());
                    gamePrizeTextView.setText(Integer.toString(response.body().getPrize().intValue()));
                } else {
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });


        final Call<User> user = api.getUser(AccessToken.getCurrentAccessToken().getToken(),android_id);

        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    nicknameTextView.setText(response.body().getNickname());
                    balanceTextView.setText( Integer.toString(response.body().getBalance()));
                    extraLifeTextView.setText( Integer.toString(response.body().getExtraLife()));
                } else {
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "QQQQQQQQQQQQQQQQQ"+t.getMessage() );
            }
        });
    }

    /**
     * Menu
     */
    @OnClick(R.id.menuButton)
    void menuSetting() {
        Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    /**
     * Menu
     */
    @OnClick(R.id.listGameButton)
    void ListGame() {
        Intent intent = new Intent(ProfileActivity.this, SettingActivity.class);
        startActivity(intent);
        finish();
    }
}
