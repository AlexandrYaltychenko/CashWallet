package eu.cash.wallet.account.model;

import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.R;
import eu.cash.wallet.account.model.callback.AccountCallback;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.account.model.response.AccountResponse;
import eu.cash.wallet.home.model.response.HomeScreenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alex on 21.04.17.
 */

public class DefaultAccountRepository implements AccountRepository {
    private AccountService accountService;

    @Inject public DefaultAccountRepository(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public void getAccountFullInfo(String auth, int accountId, final AccountCallback accountCallback){
        Call<AccountResponse> call = accountService.getAccountInfo(auth, accountId);
        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                Account account= response.body().getAccount();
                for (Event event : account.getHistory()) {
                    event.setIconRes(R.drawable.money_2);
                    event.setAccount(account);
                }
                accountCallback.onAccountInfoFetched(account);
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                accountCallback.onConnectionError();
            }
        });
    }
}
