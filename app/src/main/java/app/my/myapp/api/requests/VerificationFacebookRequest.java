package app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;

public class VerificationFacebookRequest {

    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("Facebook")
    private  Boolean Facebook;
    @SerializedName("token")
    private  String token;


    public VerificationFacebookRequest( String deviceId, Boolean Facebook, String token) {
        this.deviceId = deviceId;
        this.Facebook = Facebook;
        this.token = token;
    }
}
