package android.app.my.myapp.activities;

import android.app.my.myapp.R;
import android.app.my.myapp.api.ApiService.Api;
import android.app.my.myapp.models.ListUsers;
import android.app.my.myapp.models.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.ExclusionStrategy;



public class MainActivity extends AppCompatActivity {

    TextView datosTextView;
    private static final String TAG = "error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datosTextView = (TextView)findViewById(R.id.datosTextView);

         final Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.237.158.103:3000")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        Api api = retrofit.create(Api.class);

        final Call<ListUsers> lista = api.getUsers();

        lista.enqueue(new Callback<ListUsers>() {
            @Override
            public void onResponse(Call<ListUsers> call, Response<ListUsers> response) {
                if (response.isSuccessful()) {
                    datosTextView.setText(response.body().getUsers().get(0).getId());
                    Log.e(TAG, "AAAAAAAAAAAA"+response.body().getUsers().get(0));
                } else {
                    Log.e(TAG, "AAAAAAAAAAAA" );
                }
            }

            @Override
            public void onFailure(Call<ListUsers> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage() );
            }
        });


    }
}
