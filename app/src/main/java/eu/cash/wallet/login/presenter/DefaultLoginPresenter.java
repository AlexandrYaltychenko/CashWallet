package eu.cash.wallet.login.presenter;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import javax.inject.Inject;

import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.MoneyTrackerApp;
import eu.cash.wallet.login.model.callback.AuthCallbacks;
import eu.cash.wallet.login.model.LoginRepository;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.login.view.LoginView;

/**
 * Created by alexandr on 01.04.17.
 */

public class DefaultLoginPresenter implements LoginPresenter, AuthCallbacks.LoginCallback, AuthCallbacks.RegisterCallback, ConfigCallback {
    private LoginRepository loginRepository;
    private LoginView loginView;
    private LocalDataRepository localDataRepository;
    private Context context;
    private Config config;

    @Inject
    public DefaultLoginPresenter(Context context, LoginRepository loginRepository, LocalDataRepository localDataRepository) {
        this.context = context;
        this.loginRepository = loginRepository;
        this.localDataRepository = localDataRepository;
    }

    @Override
    public void submitRegisterForm(String email, String password, String nickname) {

    }

    @Override
    public void submitLoginForm(String email, String password) {
        loginRepository.login(email, password, this);
        Log.d("TEST", "LOGIN email = " + email + " password = " + password);
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
        loginRepository.getConfig(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onAuthenticationSucceed(Auth auth) {
        Log.d("TEST", "TOKEN GOT = " + auth.getToken());
        loginView.goNext();
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
    public void onConfigFetched(Config config) {
        Log.d("TEST", "CONFIG = " + config.isPromoShown());
        this.config = config;
        ((MoneyTrackerApp)context.getApplicationContext()).setConfig(config);
        loginView.displayLoginForm();
    }

    @Override
    public void onConnectionError() {
        Log.d("TEST", "CONNECTION ERROR!");
    }
}
