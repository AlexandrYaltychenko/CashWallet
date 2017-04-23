package eu.cash.wallet.login.view;


import eu.cash.wallet.base.BaseView;

/**
 * Created by alexandr on 01.04.17.
 * Here are described actions applied to LoginView by Presenter
 */

public interface LoginView extends BaseView {
    void showLoading();
    void showInetError();
    void showInvalidCredentials();
    void showLoginForm();
    void displayLoginForm();
    void displayRegisterForm();
}
