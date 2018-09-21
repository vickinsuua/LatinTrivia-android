package app.my.myapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.activities.ListGamesActivity;
import app.my.myapp.activities.ProfileActivity;
import app.my.myapp.models.Game;
import butterknife.OnClick;

public class ListGamesAdapter  extends ArrayAdapter<Game>{

    private Context context;
    private List<Game> games;

    public ListGamesAdapter(Context context, int id_view, List<Game> games){
        super(context, id_view, games);
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ListGamesHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_games, parent, false);

            holder = new ListGamesHolder();
            holder.textViewGameDate = (TextView) row.findViewById(R.id.textViewGameDate);
            holder.textViewGamePrize = (TextView) row.findViewById(R.id.textViewGamePrize);
            row.setTag(holder);
        } else  {
            holder = (ListGamesHolder)row.getTag();
        }




        Game game = games.get(position);
        holder.textViewGameDate.setText(String.valueOf(game.getDate()));
        holder.textViewGamePrize.setText(String.valueOf(game.getPrize()));
//        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewGameDate);
//        textViewDate.setText( String.valueOf(game.getDate()));
//        TextView textViewPrize = (TextView) convertView.findViewById(R.id.textViewGamePrize);
//        textViewPrize.setText(String.valueOf(game.getPrize()));

        return row;
    }

    static class ListGamesHolder {
        TextView textViewGameDate;
        TextView textViewGamePrize;
    }


}
