package eu.cash.wallet.login.presenter;

import eu.cash.wallet.base.BasePresenter;
import eu.cash.wallet.login.view.LoginView;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginPresenter extends BasePresenter {
    void submitRegisterForm(String email, String password, String nickname);
    void submitLoginForm(String email, String password, boolean save);
    void attachView(LoginView loginView);
}
