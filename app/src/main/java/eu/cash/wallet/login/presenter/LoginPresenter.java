package eu.cash.wallet.login.presenter;

import eu.cash.wallet.base.ActivityPresenter;
import eu.cash.wallet.login.view.LoginView;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginPresenter extends ActivityPresenter {
    void submitRegisterForm(String email, String password, String nickname);
    void submitLoginForm(String email, String password);
    void attachView(LoginView loginView);
}
