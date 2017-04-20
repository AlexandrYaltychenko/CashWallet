package eu.cash.wallet;

import android.support.annotation.NonNull;

import eu.cash.wallet.home.model.entity.Me;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Credentials;

/**
 * Created by alexandr on 16.04.17.
 */

public interface LocalDataRepository {
    Auth getAuthToken();
    Credentials getCredentials();
    void saveAuthToken(@NonNull String token, long exp);
    void saveCredentials(@NonNull String email, @NonNull String password);
    void clearToken();
    void clearCredentials();
    void saveUserInfo(Me me);
    Me getUserInfo();

}
