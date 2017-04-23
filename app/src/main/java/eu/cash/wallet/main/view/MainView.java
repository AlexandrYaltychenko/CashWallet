package eu.cash.wallet.main.view;


import android.support.v4.app.Fragment;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainView {
    void goHome(boolean animated);
    void goLogin();
    void goAccount(int accountId);
    void close();
    void setTitle(String text);
    void setButtonMenuState(NavigationTarget navigationTarget);
    void showUI();
    void hideUI();
}
