package eu.money.tracker.login.model;

import android.support.annotation.NonNull;

import eu.money.tracker.login.model.entity.Auth;
import io.reactivex.Observer;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginRepository {
    void login(@NonNull String email, @NonNull String password, @NonNull AuthCallbacks.LoginCallback loginCallback);
    void register(@NonNull String email, @NonNull String password, @NonNull String nickname, AuthCallbacks.RegisterCallback callback);
}
