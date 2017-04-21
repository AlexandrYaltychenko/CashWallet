package eu.cash.wallet.account.model.callback;

import eu.cash.wallet.account.model.entity.Account;

/**
 * Created by alex on 21.04.17.
 */

public interface AccountCallback {
    void accountInfoFetched(Account account);
}
