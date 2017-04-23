package eu.cash.wallet.account.presenter;

import android.content.Context;
import android.util.Log;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.account.model.AccountRepository;
import eu.cash.wallet.account.model.callback.AccountCallback;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.view.AccountView;

/**
 * Created by alexandr on 21.04.17.
 */

public class DefaultAccountPresenter implements AccountPresenter, AccountCallback{
    private Context context;
    private AccountRepository accountRepository;
    private GlobalDataRepository globalDataRepository;
    private AccountView accountView;
    private int accountId;

    public DefaultAccountPresenter(Context context, AccountRepository accountRepository, GlobalDataRepository globalDataRepository){
        this.context = context;
        this.accountRepository = accountRepository;
        this.globalDataRepository = globalDataRepository;
    }

    @Override
    public void attachView(AccountView accountView, int accountId) {
        this.accountView = accountView;
        Log.d("ACCOUNT","VIEW ATTACHED");
        accountView.displayLoading();
        accountView.displayHeaderAccountInfo(globalDataRepository.getAccountById(accountId));
        accountRepository.getAccountFullInfo(globalDataRepository.getAuthToken().getToken(),accountId,this);
    }

    @Override
    public void onAccountInfoFetched(Account account) {
        if (accountView == null) return;
        accountView.hideLoading();
        accountView.displayHistory(account.getHistory());
    }

    @Override
    public void onConnectionError() {

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
        Log.d("ACCOUNT","VIEW DETTACHED");
    }
}
