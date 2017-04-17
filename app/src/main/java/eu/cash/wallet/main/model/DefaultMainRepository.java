package eu.cash.wallet.main.model;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import eu.cash.wallet.R;


/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultMainRepository implements MainRepository {
    @Override
    public List<IDrawerItem> getDrawerItems() {
        List<IDrawerItem> list = new ArrayList<>();
        list.add(new PrimaryDrawerItem().withName(R.string.home).withIcon(FontAwesome.Icon.faw_home)
                .withTextColorRes(R.color.white)
                .withIconColorRes(R.color.white));
        list.add(new PrimaryDrawerItem().withName(R.string.stats).withIcon(FontAwesome.Icon.faw_money)
                .withTextColorRes(R.color.white)
                .withIconColorRes(R.color.white));
        return list;
    }
}
