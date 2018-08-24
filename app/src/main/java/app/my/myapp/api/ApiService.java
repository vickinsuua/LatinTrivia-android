package app.my.myapp.api;



import java.util.Date;

import app.my.myapp.api.requests.RegisterRequest;
import app.my.myapp.api.requests.VerificationFacebookRequest;
import app.my.myapp.api.requests.VerificationRequest;
import app.my.myapp.models.Category;
import app.my.myapp.models.Game;
import app.my.myapp.models.ListCategory;
import app.my.myapp.models.ListUsers;

import app.my.myapp.models.Question;
import app.my.myapp.models.Setting;
import app.my.myapp.models.User;
import app.my.myapp.models.Verification;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiService {

    public interface Api {


        @GET("/api/v1/user/")
        Call<User> getUser(@Header("user-token") String token, @Header("device_id") String device_id);

        @GET("/api/v1/game/show/{date}")
        Call<Game> getGame(@Path("date") String date);

        @GET("/api/v1/category/all")
        Call<ListCategory> getCategory();

        @GET("/api/v1/setting/{type}")
        Call<Setting> getSetting(@Path("type") String type);

        @POST("/api/v1/verification/")
        Call<User> signup(@Body VerificationRequest verification);

        @POST("/api/v1/verification/")
        Call<User> signup(@Body VerificationFacebookRequest verificationFacebook);

        @POST("/api/v1/question")
        Call<Question> uploadQuestion(@Body Question question);

        @Headers({"Content-Type: application/json"})
        @Multipart
        @PATCH("/api/v1/user/registerfinal")
        Call<User> registerFinal(@Part MultipartBody.Part avatar, @Part ("nickname") RequestBody nickname, @Part ("referral_code") RequestBody referral_code, @Part ("device_id") RequestBody device_id);

        @Headers({"Content-Type: application/json"})
        @PATCH("/api/v1/verification/code/{id}")
        Call<Verification> verified(@Path("id") String _id, @Body Verification verification);


    }
}
