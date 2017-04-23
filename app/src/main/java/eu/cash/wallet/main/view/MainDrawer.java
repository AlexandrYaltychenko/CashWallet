package eu.cash.wallet.main.view;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.login.model.entity.Config;

/**
 * Created by alex on 20.04.17.
 */

public interface MainDrawer {
    void buildDrawer(List<IDrawerItem> list);
    void setDrawerItems(List<IDrawerItem> list);
    void updateDrawerHeader(Me me, Config config);
    void changeItem(IDrawerItem item, int pos);
    void hideDrawer();
    void select(int position);
    void deselect();
}
