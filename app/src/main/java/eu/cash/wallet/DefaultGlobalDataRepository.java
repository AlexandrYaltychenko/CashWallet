package eu.cash.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.home.model.response.MeResponse;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.callback.UserInfoCallback;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.login.model.entity.Credentials;
import eu.cash.wallet.login.model.entity.Currency;
import eu.cash.wallet.login.model.response.ConfigResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandr on 16.04.17.
 */

public class DefaultGlobalDataRepository implements GlobalDataRepository {
    private Context context;
    private Config config;
    private Me me;
    private GlobalDataService globalDataService;

    @Inject
    public DefaultGlobalDataRepository(Context context, GlobalDataService globalDataService) {
        this.context = context;
        this.globalDataService = globalDataService;
    }

    @Override
    public Auth getAuthToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String authToken = sharedPreferences.getString("auth",null);
        if (authToken == null)
            return null;
        long exp = sharedPreferences.getLong("exp", 0);
        if (exp == 0 || exp < (System.currentTimeMillis()-3600000)/1000) {
            return null;
        }
        Auth auth = new Auth(authToken, exp);
        return auth;
    }

    @Override
    public Credentials getCredentials() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String email = sharedPreferences.getString("email",null);
        String password = sharedPreferences.getString("password",null);
        if (email == null || password == null)
            return null;
        else
            return new Credentials(email, password);
    }

    @Override
    public void saveAuthToken(@NonNull String token, long exp) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString("auth",token)
                .putLong("exp",exp)
                .apply();
    }

    @Override
    public void saveCredentials(String email, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString("email", email)
                .putString("password",password)
                .apply();
    }

    @Override
    public void clearToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString("auth",null)
                .putLong("exp",0)
                .apply();
    }

    @Override
    public void clearCredentials() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString("email",null)
                .putString("password",null)
                .apply();
    }

    @Override
    public void loadConfig(final ConfigCallback callback) {
        Call<ConfigResponse> call = globalDataService
                .getConfig(Build.VERSION.SDK_INT);
        call.enqueue(new Callback<ConfigResponse>() {
            @Override
            public void onResponse(Call<ConfigResponse> call, Response<ConfigResponse> response) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                config = response.body().getConfig();
                config.setDefaultTotalCurrency(sharedPreferences.getString("def_cur","USD"));
                callback.onConfigFetched(config);
            }

            @Override
            public void onFailure(Call<ConfigResponse> call, Throwable t) {
                callback.onConnectionError();
            }
        });
    }

    @Override
    public void loadUserInfo(String token, final UserInfoCallback userInfoCallback) {
        Call<MeResponse> call = globalDataService.getMe(token);
        call.enqueue(new Callback<MeResponse>() {
            @Override
            public void onResponse(Call<MeResponse> call, Response<MeResponse> response) {
                userInfoCallback.onUserInfoFetched(me = response.body().getMe());
            }

            @Override
            public void onFailure(Call<MeResponse> call, Throwable t) {
                userInfoCallback.onConnectionError();
            }
        });
    }

    @Override
    public List<Account> getAccounts() {
        return me.getAccountList();
    }

    @Override
    public Account getAccountById(int id) {
        for (Account account : me.getAccountList())
            if (id == account.getAccountId())
                return account;
        return null;
    }

    @Override
    public Currency getDefaultCurrency() {
        if (config == null)
            throw new RuntimeException("Called get Default Currency before Config was fetched!");
        for (Currency currency : config.getCurrencyList())
            if (currency.getName().equals(config.getDefaultTotalCurrency()))
                return currency;
        return null;
    }


    @Override
    public Me getUserInfo() {
        return me;
    }


    @Override
    public Config getConfig() {
        return config;
    }
}
