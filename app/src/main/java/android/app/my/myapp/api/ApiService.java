package android.app.my.myapp.api;


import java.util.List;

import android.app.my.myapp.models.ListUsers;
import android.app.my.myapp.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public class ApiService {

    public interface Api {

        @GET("/api/v1/user/all/users")
        Call<ListUsers> getUsers();

        @GET("/api/v1/user/")
        Call<ListUsers> getUser();
    }
}
