package eu.cash.wallet.login.presenter;


import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.login.model.entity.DialogData;
import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.login.model.callback.AuthCallbacks;
import eu.cash.wallet.login.model.LoginRepository;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.callback.UserInfoCallback;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.login.presenter.event.LoginEvent;
import eu.cash.wallet.login.view.LoginView;

/**
 * Created by alexandr on 01.04.17.
 */

public class DefaultLoginPresenter implements LoginPresenter, AuthCallbacks.LoginCallback,
        AuthCallbacks.RegisterCallback, ConfigCallback,
        UserInfoCallback{
    private LoginRepository loginRepository;
    private LoginView loginView;
    private GlobalDataRepository globalDataRepository;
    private Context context;
    private Config config;
    private DialogData dialogData;

    @Inject
    public DefaultLoginPresenter(Context context, LoginRepository loginRepository, GlobalDataRepository globalDataRepository) {
        this.context = context;
        this.loginRepository = loginRepository;
        this.globalDataRepository = globalDataRepository;
    }

    @Override
    public void submitRegisterForm(String email, String password, String nickname, boolean save) {
        dialogData = new DialogData(email, password, nickname, save, null);
    if (save)
        globalDataRepository.saveCredentials(email, password);
        loginRepository.register(email, password, nickname, this);

    }

    @Override
    public void submitLoginForm(String email, String password, boolean save) {
        dialogData = new DialogData(email, password, null, save, null);
        if (save)
            globalDataRepository.saveCredentials(email, password);
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
        globalDataRepository.loadConfig(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onAuthenticationSucceed(Auth auth) {
        Log.d("TEST", "TOKEN GOT = " + auth.getToken());
        globalDataRepository.saveAuthToken(auth.getToken(),auth.getExp());
        globalDataRepository.loadUserInfo(auth.getToken(),this);
    }

    @Override
    public void onAuthenticationFailed(String msg) {

    }

    @Override
    public void onRegistrationSucceed(Auth auth) {
        globalDataRepository.saveAuthToken(auth.getToken(),auth.getExp());
        globalDataRepository.loadUserInfo(auth.getToken(),this);
    }

    @Override
    public void onRegistrationFailed(String msg) {
        Log.d("REGDEV", "REG ERROR = "+msg);
        if (dialogData != null){
            dialogData.setErrorMsg(msg);
        }
        loginView.displayRegisterForm(dialogData);
    }

    @Override
    public void onConfigFetched(Config config) {
        Log.d("TEST", "CONFIG = " + config.isPromoShown());
        this.config = config;
        if (globalDataRepository.getAuthToken() != null) {
            globalDataRepository.loadUserInfo(globalDataRepository.getAuthToken().getToken(),this);
            Log.d("TEST","CONTINUING WITH STORED TOKEN");
        }
        else
        if (globalDataRepository.getCredentials() != null) {
            loginRepository.login(globalDataRepository.getCredentials().getEmail(), globalDataRepository.getCredentials().getPassword(), this);
            Log.d("TEST","SIGNING IN WITH STORED CREDENTIALS");
        }
        else
            loginView.displayLoginForm(null);
    }

    @Override
    public void onUserInfoFetched(Me me) {
        EventBus.getDefault().post(new LoginEvent());
    }

    @Override
    public void onConnectionError() {
        Log.d("TEST", "CONNECTION ERROR!");
    }
}
