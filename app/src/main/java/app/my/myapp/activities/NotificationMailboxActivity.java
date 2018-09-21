package app.my.myapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.adapters.ListGamesAdapter;
import app.my.myapp.adapters.NotificationAdapter;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Game;
import app.my.myapp.models.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationMailboxActivity extends AppCompatActivity {

    ListView listViewNotificationMailbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_mailbox);


        listViewNotificationMailbox =  (ListView) findViewById(R.id.listViewNotificationsMailbox);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<List<Notification>> notification = api.getNotifications();

        notification.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                NotificationAdapter adapter = new NotificationAdapter(NotificationMailboxActivity.this,R.layout.notifications_mailbox,response.body());
                listViewNotificationMailbox.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Toast.makeText(NotificationMailboxActivity.this, "Connection refuse"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ",t.getMessage() );
            }
        });
    }
}
