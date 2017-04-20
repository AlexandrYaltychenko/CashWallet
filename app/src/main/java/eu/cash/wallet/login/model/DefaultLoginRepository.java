package eu.cash.wallet.login.model;

import android.os.Build;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.home.model.response.MeResponse;
import eu.cash.wallet.login.model.callback.AuthCallbacks;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.callback.UserInfoCallback;
import eu.cash.wallet.login.model.response.AuthResponse;
import eu.cash.wallet.login.model.response.ConfigResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandr on 01.04.17.
 */

public class DefaultLoginRepository implements LoginRepository {
    private AuthService authService;
    private GlobalDataRepository globalDataRepository;

    @Inject
    public DefaultLoginRepository(AuthService authService, GlobalDataRepository globalDataRepository) {
        this.authService = authService;
        this.globalDataRepository = globalDataRepository;
    }

    @Override
    public void login(String email, String password, final AuthCallbacks.LoginCallback loginCallback) {
        Call<AuthResponse> call = authService
                .getAuthFromLogin(email, password);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                loginCallback.onAuthenticationSucceed(response.body().getAuth());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                loginCallback.onConnectionError();
            }
        });

    }

    @Override
    public void register(String email, String password, String nickname, final AuthCallbacks.RegisterCallback callback) {
        Call<AuthResponse> call = authService
                .getAuthFromLogin(email, password);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                callback.onRegistrationSucceed(response.body().getAuth());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onConnectionError();
            }
        });
    }

}
