package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

public class Balance {
    @SerializedName("_id")
    private String _id;
    @SerializedName("totalBalance")
    private Number totalBalance;
    @SerializedName("users")
    private User users;

    public Balance(String _id, Number totalBalance, User users) {
        this._id = _id;
        this.totalBalance = totalBalance;
        this.users = users;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Number getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Number totalBalance) {
        this.totalBalance = totalBalance;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
