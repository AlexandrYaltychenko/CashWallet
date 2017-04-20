package eu.cash.wallet.login.model.callback;

import eu.cash.wallet.login.model.entity.Me;

/**
 * Created by alexandr on 20.04.17.
 */

public interface UserInfoCallback {
    void onUserInfoFetched(Me me);
    void onConnectionError();
}
