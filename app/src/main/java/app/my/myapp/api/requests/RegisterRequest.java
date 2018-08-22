package app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class RegisterRequest {


    @SerializedName("nickname")
    private String nickname;
    @SerializedName("referral_code")
    private String referral_code;
    @SerializedName("device_id")
    private String device_id;


    public RegisterRequest( String nickname,String referral_code, String device_id) {
        this.nickname = nickname;
        this.referral_code = referral_code;
        this.device_id = device_id;
    }


}
