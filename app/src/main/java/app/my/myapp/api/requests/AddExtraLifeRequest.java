package app.my.myapp.api.requests;

import android.os.StrictMode;

import com.google.gson.annotations.SerializedName;

public class AddExtraLifeRequest {

    @SerializedName("extra_life")
    private String extra_life;


    public AddExtraLifeRequest(String extra_life ) {
        this.extra_life = extra_life;
    }

}
