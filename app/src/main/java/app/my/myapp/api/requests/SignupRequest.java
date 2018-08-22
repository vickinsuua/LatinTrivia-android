package android.app.my.myapp.api.requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignupRequest implements Serializable {
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("contry_code")
    private String contry_code;
    @SerializedName("phone")
    private String phone;

    public SignupRequest(String nickname, String contry_code, String phone) {
        this.nickname = nickname;
        this.contry_code = contry_code;
        this.phone = phone;
    }
}
