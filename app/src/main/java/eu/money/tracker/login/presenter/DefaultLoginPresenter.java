package eu.money.tracker.login.presenter;


import android.content.Context;

import javax.inject.Inject;

import eu.money.tracker.BaseView;
import eu.money.tracker.MoneyTrackerApp;
import eu.money.tracker.login.model.AuthCallbacks;
import eu.money.tracker.login.model.DefaultLoginRepository;
import eu.money.tracker.login.model.LoginRepository;
import eu.money.tracker.login.model.entity.Auth;
import eu.money.tracker.login.view.LoginView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by alexandr on 01.04.17.
 */

public class DefaultLoginPresenter implements LoginPresenter, AuthCallbacks.LoginCallback, AuthCallbacks.RegisterCallback {
    private LoginRepository loginRepository;
    private LoginView loginView;

    @Inject public DefaultLoginPresenter(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @Override
    public void attachView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void submitForm(String email, String password) {
        loginRepository.login(email, password, this);
    }

    @Override
    public void onAuthenticationSucceed(Auth auth) {

    }

    @Override
    public void onAuthenticationFailed(String msg) {

    }

    @Override
    public void onRegistrationSucceed(Auth auth) {

    }

    @Override
    public void onRegistrationFailed(String msg) {

    }

    @Override
    public void onConnectionError() {

    }
}
