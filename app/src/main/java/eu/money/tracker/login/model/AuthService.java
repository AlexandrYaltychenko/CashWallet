package eu.money.tracker.login.model;

import eu.money.tracker.login.model.entity.Auth;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by alexandr on 02.04.17.
 */

public interface AuthService {
    @Multipart
    @POST("/login.json")
    Call<Auth> getAuthFromLogin(@Part("email") String email, @Part("password") String password);
    @Multipart
    @POST("/register.json")
    Call<Auth> getAuthFromRegistration(@Part("email") String email, @Part("password") String password, @Part("nickname") String lang);
}
