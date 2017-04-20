package eu.cash.wallet.home.model;

import javax.inject.Inject;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultHomeRepository implements HomeRepository {
    private HomeService homeService;
    @Inject
    public DefaultHomeRepository(HomeService homeService){
        this.homeService = homeService;
    }

}
