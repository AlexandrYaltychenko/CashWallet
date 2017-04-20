package eu.cash.wallet.login.model;

import android.os.Build;

import javax.inject.Inject;

import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.home.model.entity.Me;
import eu.cash.wallet.home.model.response.MeResponse;
import eu.cash.wallet.login.model.callback.AuthCallbacks;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.callback.UserInfoCallback;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;
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
    private ConfigService configService;
    private LocalDataRepository localDataRepository;

    @Inject
    public DefaultLoginRepository(AuthService authService, ConfigService configService, LocalDataRepository localDataRepository) {
        this.authService = authService;
        this.configService = configService;
        this.localDataRepository = localDataRepository;
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

    @Override
    public void getConfig(final ConfigCallback callback) {
        Call<ConfigResponse> call = configService
                .getConfig(Build.VERSION.SDK_INT);
        call.enqueue(new Callback<ConfigResponse>() {
            @Override
            public void onResponse(Call<ConfigResponse> call, Response<ConfigResponse> response) {
                callback.onConfigFetched(response.body().getConfig());
            }

            @Override
            public void onFailure(Call<ConfigResponse> call, Throwable t) {
                callback.onConnectionError();
            }
        });
    }

    @Override
    public void getUserInfo(String token, final UserInfoCallback userInfoCallback) {
        Call<MeResponse> call = authService.getMe(token);
        call.enqueue(new Callback<MeResponse>() {
            @Override
            public void onResponse(Call<MeResponse> call, Response<MeResponse> response) {
                userInfoCallback.onUserInfoFetched(response.body().getMe());
            }

            @Override
            public void onFailure(Call<MeResponse> call, Throwable t) {
                userInfoCallback.onConnectionError();
            }
        });
    }

}
