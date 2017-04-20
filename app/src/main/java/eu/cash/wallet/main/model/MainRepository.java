package eu.cash.wallet.main.model;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * Created by alexandr on 17.04.17.
 */

public interface MainRepository {
    List<IDrawerItem> getDrawerItems();

}
