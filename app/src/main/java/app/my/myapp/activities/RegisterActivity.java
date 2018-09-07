package app.my.myapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;

import app.my.myapp.R;
import app.my.myapp.api.ApiService;
import app.my.myapp.api.requests.RegisterRequest;
import app.my.myapp.api.requests.VerificationRequest;
import app.my.myapp.models.Setting;
import app.my.myapp.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegisterActivity extends AppCompatActivity {


    @BindView(R.id.nicknameEditText)
    EditText nicknameView;
    @BindView(R.id.referralCodeEditText)
    EditText referralView;
    @BindView(R.id.registerButtom)
    Button registerB;
    @BindView(R.id.avatarImageButton)
    ImageButton avatarImageB;

    ImageButton image;
    public String android_id;
    public String path="";
    Uri myPath;

    private final String ROOT_FOLDER ="uploads/";
    private final String ROOT=ROOT_FOLDER+"photos";

    final int CODE_UPLOAD= 10;
    final int CODE_PHOTO=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        image = (ImageButton)findViewById(R.id.avatarImageButton);


    }


    @OnClick(R.id.avatarImageButton)
    void onAvatarClick() {
        uploadImage();
    }

    private void uploadImage() {
        final CharSequence[] options = {"Take photo", "Upload Image","Cancel"};
        final AlertDialog.Builder alertOptions = new AlertDialog.Builder(RegisterActivity.this);
        alertOptions.setTitle("Select a option");
        alertOptions.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take photo")){
                    takePhoto();
                } else {
                    if (options[which].equals("Upload Image")){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Select"),CODE_UPLOAD);
                    } else {
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOptions.show();
    }

    private void takePhoto() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        String nameImage = "";
        File fileImage= new File(Environment.getExternalStorageDirectory(),ROOT);
        boolean isCreated = fileImage.exists();

        if (isCreated== false){
            isCreated = fileImage.mkdirs();
        } else {
            nameImage = (System.currentTimeMillis()/1000)+".jpg";
        }

        path = Environment.getExternalStorageDirectory()+File.separator+ROOT+File.separator+nameImage;

        File image= new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
        startActivityForResult(intent,CODE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case CODE_UPLOAD:
                    myPath=data.getData();
                    image.setImageURI(myPath);

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), myPath);
                        image.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case CODE_PHOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Root","Path"+path);
                        }
                    });

                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    image.setImageBitmap(bitmap);
            }
//            Log.e( "onActivityResult: ",data.getPackage() );
//            Serializable photo = data.getSerializableExtra('');

//            Log.e( "onActivityResult: ",photo.toString() );

        }
    }

    /**
     * On join click
     */
    @OnClick(R.id.registerButtom)
    void onRegisterClick() {
        if (checkData()) {
            if (checkData()) {

                File file = new File(myPath.getPath());
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);

                MultipartBody.Part avatar = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
                RequestBody nickname = RequestBody.create(MediaType.parse("text/plain"), nicknameView.getText().toString());
                RequestBody referral_code = RequestBody.create(MediaType.parse("text/plain"), referralView.getText().toString());
                RequestBody device_id = RequestBody.create(MediaType.parse("text/plain"), android_id);
                sendNetworkRequest(avatar, nickname, referral_code, device_id);
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

    private void sendNetworkRequest( MultipartBody.Part avatar,RequestBody nickname, RequestBody referral_code, RequestBody device_id){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.237.158.103:3000").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        Call<User> call = api.registerFinal( avatar, nickname,  referral_code,  device_id);

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
