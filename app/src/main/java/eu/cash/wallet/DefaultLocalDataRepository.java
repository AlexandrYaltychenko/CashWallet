package eu.cash.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Credentials;

/**
 * Created by alexandr on 16.04.17.
 */

public class DefaultLocalDataRepository implements LocalDataRepository {
    private Context context;

    @Inject public DefaultLocalDataRepository(Context context) {
        this.context = context;
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
}
