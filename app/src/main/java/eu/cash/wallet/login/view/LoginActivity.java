package eu.cash.wallet.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import eu.cash.wallet.main.view.MainActivity;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.login.presenter.LoginPresenter;

/**
 * Created by alexandr on 01.04.17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {
    @Inject
    LoginPresenter loginPresenter;
    private MaterialStyledDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((CashWalletApp) getApplicationContext()).getComponent().inject(this);
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
    public void onDestroy(){
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
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
    public void goNext() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayLoginForm() {
        if (dialog != null) return;
        final LoginDialogHolder loginDialogHolder = new LoginDialogHolder(getLayoutInflater().inflate(R.layout.login_dialog, null));
        dialog = new MaterialStyledDialog.Builder(this)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle(R.string.sign_in_big)
                .setHeaderDrawable(R.drawable.dialog_header)
                .setPositiveText(R.string.sign_in_big)
                .setNeutralText(R.string.register_big)
                .setNegativeText(R.string.cancel_big)
                .setCustomView(loginDialogHolder.view, 20, 20, 20, 0)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        finish();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        loginPresenter.submitLoginForm(loginDialogHolder.email.getText().toString(), loginDialogHolder.password.getText().toString(), loginDialogHolder.remember.isChecked());
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        displayRegisterForm();
                    }
                })
                .withDialogAnimation(true)
                .build();
        dialog.show();
    }

    @Override
    public void displayRegisterForm() {
        if (dialog != null) return;
        final RegisterDialogHolder registerDialogHolder = new RegisterDialogHolder(getLayoutInflater().inflate(R.layout.register_dialog, null));
        dialog = new MaterialStyledDialog.Builder(this)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle(R.string.register_big)
                .setHeaderDrawable(R.drawable.dialog_header)
                .setPositiveText(R.string.register_big)
                .setNeutralText(R.string.sign_in_big)
                .setNegativeText(R.string.cancel_big)
                .setCustomView(registerDialogHolder.view, 20, 20, 20, 0)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        displayLoginForm();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        loginPresenter.submitRegisterForm(registerDialogHolder.email.getText().toString(),
                                registerDialogHolder.password.getText().toString(),
                                registerDialogHolder.nickname.getText().toString());

                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginActivity.this.dialog = null;
                        displayLoginForm();
                    }
                })
                .withDialogAnimation(true)
                .build();
        dialog.show();
    }

    @Override
    public void showToastMessage(String msg) {

    }

    static class LoginDialogHolder {
        @BindView(R.id.email)
        EditText email;
        @BindView(R.id.password)
        EditText password;
        @BindView(R.id.remember)
        CheckBox remember;
        View view;

        LoginDialogHolder(View view) {
            ButterKnife.bind(this, view);
            this.view = view;
        }

    }

    static class RegisterDialogHolder {
        @BindView(R.id.email)
        EditText email;
        @BindView(R.id.password)
        EditText password;
        @BindView(R.id.nickname)
        EditText nickname;
        View view;


        RegisterDialogHolder(View view) {
            ButterKnife.bind(this, view);
            this.view = view;
        }

    }
}
