package eu.cash.wallet.account.model;

import eu.cash.wallet.account.model.callback.AccountCallback;

/**
 * Created by alexandr on 20.04.17.
 */

public interface AccountRepository {
    void getAccountFullInfo(String auth, int accountId, AccountCallback accountCallback);
}
