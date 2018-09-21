package app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;

public class DeviceRequest {
    @SerializedName("token")
    private String token;

    public DeviceRequest(String token) {
        this.token = token;
    }
}
