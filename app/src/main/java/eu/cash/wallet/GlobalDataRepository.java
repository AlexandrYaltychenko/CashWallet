package eu.cash.wallet;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.login.model.callback.ConfigCallback;
import eu.cash.wallet.login.model.callback.UserInfoCallback;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.login.model.entity.Credentials;
import eu.cash.wallet.login.model.entity.Currency;

/**
 * Created by alexandr on 16.04.17.
 */

public interface GlobalDataRepository {
    void saveAuthToken(String token, long exp);
    void saveCredentials(String email, String password);
    void clearToken();
    void clearCredentials();
    void loadConfig(ConfigCallback configCallback);
    void loadUserInfo(String token, UserInfoCallback userInfoCallback);
    List<Account> getAccounts();
    Account getAccountById(int id);
    Currency getDefaultCurrency();
    Config getConfig();
    Me getUserInfo();
    Auth getAuthToken();
    Credentials getCredentials();
}
