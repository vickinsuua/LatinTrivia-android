package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

public class Verification {

    @SerializedName("_id")
    private String _id;
    @SerializedName("userId")
    private User user;
    @SerializedName("phone")
    private String phone;
    @SerializedName("contry_code")
    private String contry_code;
    @SerializedName("verify_code")
    private String verify_code;
    @SerializedName("verified")
    private boolean verified;
    @SerializedName("device_id")
    private String device_id;

    public Verification(String _id, User user, String phone, String contry_code, String verify_code, boolean verified, String device_id) {
        this._id = _id;
        this.user = user;
        this.phone = phone;
        this.contry_code = contry_code;
        this.verify_code = verify_code;
        this.verified = verified;
        this.device_id = device_id;
    }

    public Verification(String  verify_code){
        this.verify_code = verify_code;
    }

    public String get_id() {
        return _id;
    }


    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContry_code() {
        return contry_code;
    }

    public void setContry_code(String contry_code) {
        this.contry_code = contry_code;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
