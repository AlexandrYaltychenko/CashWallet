package eu.cash.wallet.home.presenter;

import eu.cash.wallet.base.BasePresenter;
import eu.cash.wallet.home.view.HomeView;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomePresenter extends BasePresenter {
    void attachView(HomeView homeView);
}
