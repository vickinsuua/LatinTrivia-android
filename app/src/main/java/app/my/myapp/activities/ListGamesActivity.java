package app.my.myapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.adapters.ListGamesAdapter;
import app.my.myapp.api.ApiService;
import app.my.myapp.models.Game;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListGamesActivity extends AppCompatActivity {

    ListView listViewGames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);



        listViewGames =  (ListView) findViewById(R.id.listViewGames);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.237.158.103:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService.Api api = retrofit.create(ApiService.Api.class);

        final Call<List<Game>> game = api.getListGames();

        game.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                ListGamesAdapter adapter = new ListGamesAdapter(ListGamesActivity.this,R.layout.list_games,response.body());
                listViewGames.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(ListGamesActivity.this, "Connection refuse", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
