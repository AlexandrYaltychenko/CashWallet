package eu.cash.wallet.login.model.callback;

import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;

/**
 * Created by alexandr on 16.04.17.
 */

public interface ConfigCallback {
    void onConfigFetched(Config config);

    void onConnectionError();
}
