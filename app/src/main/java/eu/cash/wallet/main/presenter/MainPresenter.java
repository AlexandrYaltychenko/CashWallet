package eu.cash.wallet.main.presenter;

import eu.cash.wallet.base.ActivityPresenter;
import eu.cash.wallet.main.view.MainView;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainPresenter extends ActivityPresenter{
    void attachView(MainView mainView);
}
