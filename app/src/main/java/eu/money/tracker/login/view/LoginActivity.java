package eu.money.tracker.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import eu.money.tracker.MoneyTrackerApp;
import eu.money.tracker.R;
import eu.money.tracker.login.presenter.DefaultLoginPresenter;
import eu.money.tracker.login.presenter.LoginPresenter;

/**
 * Created by alexandr on 01.04.17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MoneyTrackerApp)getApplicationContext()).getComponent().inject(this);
        loginPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        loginPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        loginPresenter.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        loginPresenter.onResume();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        loginPresenter.onBackPressed();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showInetError() {

    }

    @Override
    public void showInvalidCredentials() {

    }

    @Override
    public void showLoginForm() {

    }

    @Override
    public void showToastMessage(String msg) {

    }
}
