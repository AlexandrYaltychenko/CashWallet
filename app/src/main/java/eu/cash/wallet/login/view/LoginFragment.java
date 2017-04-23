package eu.cash.wallet.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.login.presenter.LoginPresenter;

/**
 * Created by alexandr on 01.04.17.
 */

public class LoginFragment extends Fragment implements LoginView {
    @Inject
    LoginPresenter loginPresenter;
    private MaterialStyledDialog dialog;



    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);
        ((CashWalletApp)getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
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
    public void displayLoginForm() {
        if (dialog != null) return;
        final LoginDialogHolder loginDialogHolder = new LoginDialogHolder(getActivity().getLayoutInflater().inflate(R.layout.login_dialog, null));
        dialog = new MaterialStyledDialog.Builder(getActivity())
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
                        LoginFragment.this.dialog = null;
                        getActivity().finish();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginFragment.this.dialog = null;
                        loginPresenter.submitLoginForm(loginDialogHolder.email.getText().toString(), loginDialogHolder.password.getText().toString(), loginDialogHolder.remember.isChecked());
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginFragment.this.dialog = null;
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
        final RegisterDialogHolder registerDialogHolder = new RegisterDialogHolder(getActivity().getLayoutInflater().inflate(R.layout.register_dialog, null));
        dialog = new MaterialStyledDialog.Builder(getActivity())
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
                        LoginFragment.this.dialog = null;
                        displayLoginForm();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginFragment.this.dialog = null;
                        loginPresenter.submitRegisterForm(registerDialogHolder.email.getText().toString(),
                                registerDialogHolder.password.getText().toString(),
                                registerDialogHolder.nickname.getText().toString());

                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        LoginFragment.this.dialog = null;
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
