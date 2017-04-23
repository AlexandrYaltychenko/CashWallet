package eu.cash.wallet.main.presenter;

import eu.cash.wallet.base.BasePresenter;
import eu.cash.wallet.home.presenter.event.NavigateEvent;
import eu.cash.wallet.login.presenter.event.LoginEvent;
import eu.cash.wallet.main.view.MainDrawer;
import eu.cash.wallet.main.view.MainView;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainPresenter extends BasePresenter {
    void attachView(MainView mainView, MainDrawer mainDrawer);
    void navigate(NavigateEvent navigateEvent);
    void onDrawerItemClick(int position);
    boolean onBottomMenuSelected(int position);
    public void loginComplete(LoginEvent loginEvent);
}
