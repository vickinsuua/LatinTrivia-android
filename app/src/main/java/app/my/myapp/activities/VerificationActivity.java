package app.my.myapp.activities;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Verification;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerificationActivity extends AppCompatActivity {

    @BindView(R.id.verificationCodeEditText)
    EditText codeView;

    public String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    /**
     * On join click
     */
    @OnClick(R.id.codeButtom)
    void onRegisterClick() {
        if (checkData()) {
            Verification verification = new Verification(
                    codeView.getText().toString()
            );

            sendNetworkRequest(verification);


        }
    }

    /**
     * Check data
     * @return True/false
     */
    private boolean checkData() {
        if (codeView.getText().length() <= 0 ) {
            return false;
        }

        return true;
    }

    private void sendNetworkRequest(Verification verification){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.237.158.103:3000").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        Call<Verification> call = api.verified(android_id,verification);


        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                Toast.makeText(VerificationActivity.this, "YESSSSS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerificationActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
