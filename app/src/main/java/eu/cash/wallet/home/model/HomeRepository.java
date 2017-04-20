package eu.cash.wallet.home.model;

import eu.cash.wallet.home.model.callback.HomeScreenCallback;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomeRepository {
    void getHomeScreen(String auth, HomeScreenCallback homeScreenCallback);
}
