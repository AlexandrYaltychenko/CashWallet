package eu.cash.wallet.main.view;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainView {
    void goHome();
    void setState(NavigationTarget navigationTarget);
    void goAccount(int accountId);
    void close();

}
