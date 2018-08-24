package app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;

import java.io.File;

import okhttp3.RequestBody;

public class RegisterRequest {


    @SerializedName("nickname")
    private RequestBody nickname;
    @SerializedName("referral_code")
    private RequestBody referral_code;
    @SerializedName("device_id")
    private String device_id;





}
