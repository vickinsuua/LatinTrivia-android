package app.my.myapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.my.myapp.R;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.my.myapp.api.ApiService;
import app.my.myapp.api.ApiService.Api;
import android.app.my.myapp.api.requests.SignupRequest;

import app.my.myapp.api.requests.VerificationFacebookRequest;
import app.my.myapp.api.requests.VerificationRequest;
import app.my.myapp.models.User;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Share;

import app.my.myapp.models.Verification;
import butterknife.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.contryCodeEditText)
    EditText contryCodeView;
    @BindView(R.id.phoneEditText)
    EditText phoneView;
    @BindView(R.id.signUpButton)
    Button signButton;


    public String android_id;
    public String token;
    public Boolean Facebook;
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        callbackManager = CallbackManager.Factory.create();



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (checkDataFB()){
                    VerificationFacebookRequest verificationFacebookRequest = new VerificationFacebookRequest(
                            android_id,
                            Facebook = true,
                            token = AccessToken.getCurrentAccessToken().getToken()

                    );
                    verificationUserFacebook(verificationFacebookRequest);
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login_fb, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login_fb, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goRegister() {
        Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * On join click
     */
    @OnClick(R.id.signUpButton)
    void onJoinClick() {
        if (checkData()) {
            VerificationRequest verificationRequest = new VerificationRequest(
                    contryCodeView.getText().toString(),
                    phoneView.getText().toString(),
                    android_id
            );
            verificationUser(verificationRequest);
        }
    }

    /**
     * Check data
     * @return True/false
     */
    private boolean checkData() {
        if (contryCodeView.getText().length() <= 0 || phoneView.getText().length() <= 0) {
            return false;
        }

        return true;
    }

    /**
     * Check data
     * @return True/false
     */
    private boolean checkDataFB() {
        if (android_id.length() <= 0 ) {
            return false;
        }

        return true;
    }


    private void verificationUser(VerificationRequest verification){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.237.158.104:3000").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        Api api = retrofit.create(Api.class);

        Call<User> call = api.signup(verification);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SignUpActivity.this, "SMS send", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, VerificationActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verificationUserFacebook(VerificationFacebookRequest verificationFacebook){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.237.158.104:3000").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        Api api = retrofit.create(Api.class);

        Call<User> call = api.signup(verificationFacebook);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SignUpActivity.this, "Submit", Toast.LENGTH_SHORT).show();
                goRegister();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
