package eu.cash.wallet.main.view;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainView {
    void buildDrawer(List<IDrawerItem> list);
    void goHome();
    void goAccount(int accountId);
    void close();

}
