package eu.money.tracker.login.model;

import android.content.Context;

import javax.inject.Inject;

import eu.money.tracker.MoneyTrackerApp;
import eu.money.tracker.StringConverterFactory;
import eu.money.tracker.login.model.entity.Auth;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private Context context;

    @Inject
    public DefaultLoginRepository(AuthService authService, Context context){
        this.authService = authService;
        this.context = context;
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
    public void register(AuthCallbacks.RegisterCallback callback) {

    }

    private AuthService generateService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(MoneyTrackerApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(AuthService.class);
    }
}
