package eu.cash.wallet.home.model.callback;

import eu.cash.wallet.home.model.entity.HomeScreen;
import eu.cash.wallet.login.model.entity.Config;

/**
 * Created by alexandr on 20.04.17.
 */

public interface HomeScreenCallback {
    void onHomeScreenFetched(HomeScreen homeScreen);
    void onConnectionError();
}
