package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.adapters.ListBalancesAdapter;
import app.my.myapp.adapters.ListGamesAdapter;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Balance;
import app.my.myapp.models.Game;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListBalancesActivity extends AppCompatActivity {

    ListView listViewBalances;
    Button balancesWeek;
    Button backToProfile;
    private static final String TAG = "error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_balances);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        listViewBalances =  (ListView) findViewById(R.id.listViewBalances);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.20.153.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<List<Balance>> balance = api.getBalances();

        balance.enqueue(new Callback<List<Balance>>() {
            @Override
            public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {
                ListBalancesAdapter adapter = new ListBalancesAdapter(ListBalancesActivity.this,R.layout.list_balances,response.body());
                listViewBalances.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Balance>> call, Throwable t) {
                Toast.makeText(ListBalancesActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Week
     */
    @OnClick(R.id.balancesWeek)
    void weekBalances() {
        Intent intent = new Intent(ListBalancesActivity.this, ListBalancesWeekActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Friends
     */
    @OnClick(R.id.friends)
    void friends() {
        Intent intent = new Intent(ListBalancesActivity.this, ListBalanceFriendsActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Back Profile
     */
    @OnClick(R.id.backToProfile)
    void backToProfile() {
        Log.i(TAG, "weekBalances: AQUIIIIII");
        Intent intent = new Intent(ListBalancesActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
