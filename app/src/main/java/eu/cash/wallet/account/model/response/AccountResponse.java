package eu.cash.wallet.account.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.base.Meta;
import eu.cash.wallet.home.model.entity.HomeScreen;

/**
 * Created by alexandr on 21.04.17.
 */

public class AccountResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account= account;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}