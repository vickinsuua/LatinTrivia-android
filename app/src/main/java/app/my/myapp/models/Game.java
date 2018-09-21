package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Game {
    @SerializedName("_id")
    private String _id;
    @SerializedName("date")
    private Date date;
    @SerializedName("prize")
    private int prize;

    public Game(Date date, int prize) {
        this.date = date;
        this.prize = prize;
    }

    public String get_id() {
        return _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
