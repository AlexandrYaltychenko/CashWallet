package eu.money.tracker.login.model;

import eu.money.tracker.login.model.entity.Auth;
import io.reactivex.Observer;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginRepository {
    void login(String email, String password, AuthCallbacks.LoginCallback loginCallback);
    void register(AuthCallbacks.RegisterCallback callback);
}
