package eu.cash.wallet.login.model;

import eu.cash.wallet.home.model.response.MeResponse;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.response.AuthResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by alexandr on 02.04.17.
 */

public interface AuthService {
    @Multipart
    @POST("login")
    Call<AuthResponse> getAuthFromLogin(@Part("email") String email, @Part("password") String password);
    @Multipart
    @POST("register")
    Call<AuthResponse> getAuthFromRegistration(@Part("email") String email, @Part("password") String password, @Part("nickname") String lang);
}
