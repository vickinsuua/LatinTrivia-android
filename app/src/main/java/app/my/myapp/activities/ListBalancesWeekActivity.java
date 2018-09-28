package app.my.myapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.adapters.ListBalancesAdapter;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Balance;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListBalancesWeekActivity extends AppCompatActivity {

    ListView listViewBalances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_balances_week);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        listViewBalances =  (ListView) findViewById(R.id.listViewBalances);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.20.153.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<List<Balance>> balance = api.getBalancesWeek();


        balance.enqueue(new Callback<List<Balance>>() {
            @Override
            public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {
                ListBalancesAdapter adapter = new ListBalancesAdapter(ListBalancesWeekActivity.this,R.layout.list_balances,response.body());
                listViewBalances.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Balance>> call, Throwable t) {
                Toast.makeText(ListBalancesWeekActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Week
     */
    @OnClick(R.id.balancesWeek)
    void weekBalances() {
        Intent intent = new Intent(ListBalancesWeekActivity.this, ListBalancesActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Back Profile
     */
    @OnClick(R.id.backToProfile)
    void backToProfile() {
        Intent intent = new Intent(ListBalancesWeekActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
