package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListGames {

    @SerializedName("games")
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }


}
