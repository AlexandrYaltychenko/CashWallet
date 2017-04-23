package eu.cash.wallet.account.view;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.base.BaseView;

/**
 * Created by alexandr on 21.04.17.
 */

public interface AccountView{
    void displayHistory(List<Event> events);
    void displayHeaderAccountInfo(Account account);
    void displayLoading();
    void hideLoading();
}
