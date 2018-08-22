package app.my.myapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.api.requests.RegisterRequest;
import app.my.myapp.api.requests.VerificationRequest;
import app.my.myapp.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.nicknameEditText)
    EditText nicknameView;
    @BindView(R.id.referralCodeEditText)
    EditText referralView;
    @BindView(R.id.registerButtom)
    Button registerB;
    @BindView(R.id.avatarImageButton)
    ImageButton avatarImageB;

    public String android_id;
    public Uri path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);


    }

    @OnClick(R.id.avatarImageButton)
    void onAvatarClick() {
        uploadImage();
    }

    private void uploadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "seleccione"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            path=data.getData();
            avatarImageB.setImageURI(path);
        }
    }

    /**
     * On join click
     */
    @OnClick(R.id.registerButtom)
    void onRegisterClick() {
        if (checkData()) {
            if (checkData()) {
                RegisterRequest registerRequest = new RegisterRequest(
                        nicknameView.getText().toString(),
                        referralView.getText().toString(),
                        android_id
                );
                sendNetworkRequest(registerRequest);
            }
        }
    }

    /**
     * Check data
     * @return True/false
     */
    private boolean checkData() {
        if (nicknameView.getText().length() <= 0 || referralView.getText().length() <= 0) {
            return false;
        }

        return true;
    }

    private void sendNetworkRequest(RegisterRequest registerRequest){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.237.158.103:3000").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        Call<User> call = api.registerFinal( registerRequest);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterActivity.this, "YESSSSS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ",t.getMessage() );

            }
        });
    }

}
