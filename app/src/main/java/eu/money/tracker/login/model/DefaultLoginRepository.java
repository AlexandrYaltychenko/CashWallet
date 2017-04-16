package eu.money.tracker.login.model;

import android.content.Context;

import javax.inject.Inject;

import eu.money.tracker.MoneyTrackerApp;
import eu.money.tracker.StringConverterFactory;
import eu.money.tracker.login.model.entity.Auth;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandr on 01.04.17.
 */

public class DefaultLoginRepository implements LoginRepository {
    private AuthService authService;

    @Inject
    public DefaultLoginRepository(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void login(String email, String password, final AuthCallbacks.LoginCallback loginCallback) {
        Call<Auth> call = authService
                .getAuthFromLogin(email, password);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                loginCallback.onAuthenticationSucceed(response.body());
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                loginCallback.onConnectionError();
            }
        });

    }

    @Override
    public void register(String email, String password, String nickname, final AuthCallbacks.RegisterCallback callback) {
        Call<Auth> call = authService
                .getAuthFromLogin(email, password);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                callback.onRegistrationSucceed(response.body());
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                callback.onConnectionError();
            }
        });
    }

}
