package eu.money.tracker.login.presenter;

import eu.money.tracker.ActivityPresenter;
import eu.money.tracker.login.view.LoginView;

/**
 * Created by alexandr on 01.04.17.
 */

public interface LoginPresenter extends ActivityPresenter {
    void submitForm(String email, String password);
    void attachView(LoginView loginView);
}
