package eu.cash.wallet.account.presenter;

import android.content.Context;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.account.model.AccountRepository;
import eu.cash.wallet.account.view.AccountView;

/**
 * Created by alexandr on 21.04.17.
 */

public class DefaultAccountPresenter implements AccountPresenter{
    private Context context;
    private AccountRepository accountRepository;
    private GlobalDataRepository globalDataRepository;
    private AccountView accountView;

    public DefaultAccountPresenter(Context context, AccountRepository accountRepository, GlobalDataRepository globalDataRepository){
        this.context = context;
        this.accountRepository = accountRepository;
        this.globalDataRepository = globalDataRepository;
    }

    @Override
    public void attachView(AccountView accountView) {
        this.accountView = accountView;
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
    public void onDestroy() {
        this.accountView = null;
    }
}
