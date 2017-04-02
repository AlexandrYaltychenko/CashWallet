package eu.money.tracker.login.model;

import eu.money.tracker.login.model.entity.Auth;

/**
 * Created by alexandr on 01.04.17.
 */

public interface AuthCallbacks {
    interface LoginCallback {
        void onAuthenticationSucceed(Auth auth);

        void onAuthenticationFailed(String msg);

        void onConnectionError();
    }

    interface RegisterCallback {
        void onRegistrationSucceed(Auth auth);

        void onRegistrationFailed(String msg);

        void onConnectionError();
    }
}