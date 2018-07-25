package android.app.my.myapp.models;

import android.app.my.myapp.models.User;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUsers {

    @SerializedName("user")
    private List <User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
