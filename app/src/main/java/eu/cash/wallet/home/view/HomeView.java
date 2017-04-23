package eu.cash.wallet.home.view;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.login.model.entity.Currency;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomeView {
    void displayTotalBalance(double total, Currency currency);
    void displayList(List<Event> events);
    void displayLoading();
    void hideLoading();
    void displayIncomeDialog(List<Account> accounts);
    void displayCostDialog(List<Account> accounts);
}
