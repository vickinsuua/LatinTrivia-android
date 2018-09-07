package app.my.myapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.api.requests.AddExtraLifeRequest;
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
    Button shareCode;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private static final String TAG = "error";
    public String android_id;
    public String token;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        token = AccessToken.getCurrentAccessToken().getToken();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        //init view

        nicknameTextView = (TextView)findViewById(R.id.nicknameTextView);
        balanceTextView = (TextView)findViewById(R.id.balanceTextView);
        extraLifeTextView = (TextView)findViewById(R.id.extraLifeTextView);
        gameDateTextView = (TextView)findViewById(R.id.gameDateTextView);
        gamePrizeTextView = (TextView)findViewById(R.id.gamePrizeTextView);
        shareCode = (Button)findViewById(R.id.getExtraLifeButton);


        //init FB
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ProfileActivity.this,"Share success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ProfileActivity.this,"Share Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (ShareDialog.canShow(ShareLinkContent.class)){
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setQuote("This is a useful link")
                            .setContentUrl(Uri.parse("https://youtube.com"))
                            .build();
                    shareDialog.show(linkContent);
                }
            }
        });


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


        final Call<User> user = api.getUser(token,android_id);

        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    nicknameTextView.setText(response.body().getNickname());
                    balanceTextView.setText( Integer.toString(response.body().getBalance()));
                    extraLifeTextView.setText( Integer.toString(response.body().getExtraLife()));
                    saveCredentials(response);
                } else {
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "QQQQQQQQQQQQQQQQQ"+t.getMessage() );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
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

    private void saveCredentials(Response<User> response) {
        SharedPreferences preferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);

        String userCredential = response.body().getId().toString();
        String tokenCredential = token;
        String deviceCredential = android_id;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userCredential", userCredential);
        editor.putString("tokenCredential",tokenCredential);
        editor.putString("deviceCredential", deviceCredential);
        editor.commit();

    }

    private void getCredentials() {
        SharedPreferences preferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String userId = preferences.getString("userCredential","NO EXISTE VALOR");

    }

}
