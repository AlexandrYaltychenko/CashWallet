package eu.cash.wallet.login.model;

import eu.cash.wallet.login.model.callback.AuthCallbacks;
import eu.cash.wallet.login.model.callback.ConfigCallback;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginRepository {
    void login(String email, String password, AuthCallbacks.LoginCallback loginCallback);
    void register(String email, String password, String nickname, AuthCallbacks.RegisterCallback callback);
    void getConfig(ConfigCallback configCallback);
}
