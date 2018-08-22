package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Game {
    @SerializedName("_id")
    private String _id;
    @SerializedName("date")
    private String date;
    @SerializedName("prize")
    private int prize;

    public Game(String date, int prize) {
        this.date = date;
        this.prize = prize;
    }

    public String get_id() {
        return _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Number getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Game{" +
                "_id='" + _id + '\'' +
                ", date=" + date +
                ", prize=" + prize +
                '}';
    }

//    private Game game;
//
//    public Game getGame() {
//        return game;
//    }
}
