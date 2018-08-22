package app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;



public class VerificationRequest {

    @SerializedName("contry_code")
    private String contryCode;
    @SerializedName("phone")
    private String phone;
    @SerializedName("device_id")
    private String deviceId;


    public VerificationRequest(String contryCode, String phone, String deviceId) {
        this.contryCode = contryCode;
        this.phone = phone;
        this.deviceId = deviceId;
    }
}
