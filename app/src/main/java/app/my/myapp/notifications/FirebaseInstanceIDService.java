package app.my.myapp.notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();
//        ApiRetriever.registerDevice(null);
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);
    }
}
