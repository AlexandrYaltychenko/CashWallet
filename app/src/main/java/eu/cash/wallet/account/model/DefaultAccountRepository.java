package eu.cash.wallet.account.model;

import javax.inject.Inject;

import eu.cash.wallet.account.model.callback.AccountCallback;

/**
 * Created by alex on 21.04.17.
 */

public class DefaultAccountRepository implements AccountRepository {
    private AccountService accountService;

    @Inject public DefaultAccountRepository(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public void getAccountFullInfo(int accountId, AccountCallback accountCallback){

    }
}
