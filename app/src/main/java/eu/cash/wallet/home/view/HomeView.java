package eu.cash.wallet.home.view;

import eu.cash.wallet.login.model.entity.Currency;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomeView {
    void setTotalBalance(double total, Currency currency);
}
