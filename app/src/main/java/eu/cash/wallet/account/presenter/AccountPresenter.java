package eu.cash.wallet.account.presenter;

import android.provider.BaseColumns;

import eu.cash.wallet.account.view.AccountView;
import eu.cash.wallet.base.BasePresenter;

/**
 * Created by alexandr on 21.04.17.
 */

public interface AccountPresenter extends BasePresenter{
    void attachView(AccountView accountView);
}
