package eu.cash.wallet.main.presenter;

import android.content.Context;
import android.util.Log;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.home.model.entity.Me;
import eu.cash.wallet.home.view.event.NavigateEvent;
import eu.cash.wallet.main.model.MainRepository;
import eu.cash.wallet.main.view.MainView;
import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultMainPresenter implements MainPresenter {
    private MainRepository mainRepository;
    private LocalDataRepository localDataRepository;
    private Context context;
    private MainView mainView;

    @Inject
    public DefaultMainPresenter(Context context, MainRepository mainRepository, LocalDataRepository localDataRepository) {
        this.mainRepository = mainRepository;
        this.localDataRepository = localDataRepository;
        this.context = context;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onDestroy() {
        mainView = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
        EventBus.getDefault().register(this);
        this.mainView.buildDrawer(generateMenuList());
        this.mainView.goHome();
        this.mainView.setState(NavigationTarget.HOME);
        Log.d("TEST", "MAIN VIEW ATTACHED");
    }

    private List<IDrawerItem> generateMenuList() {
        List<IDrawerItem> drawerItems = new ArrayList<IDrawerItem>();
        List<IDrawerItem> menu = mainRepository.getDrawerItems();
        drawerItems.add(0, new SectionDrawerItem().withName(R.string.accounts).withTextColorRes(R.color.aqua).withDivider(false));
        Me me = localDataRepository.getUserInfo();
        for (Account account : me.getAccountList())
            drawerItems.add(1, new PrimaryDrawerItem().withName(account.getTitle())
                    .withIcon(account.getType() == 1 ? R.drawable.account_numerar : R.drawable.account_card)
                    .withTextColorRes(R.color.white)
                    .withIconColorRes(R.color.white));
        //menu.add(0,new SectionDrawerItem().withName(R.string.app_name));
        //drawerItems.addAll(0,menu);
        return drawerItems;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onNavigate(NavigateEvent navigateEvent) {
        Log.d("TEST", "EVENT BUS TRANSMITTED EVENT! ");
        mainView.setState(navigateEvent.getTarget());
        switch (navigateEvent.getTarget()) {
            case CLOSE:
                System.exit(0);
        }
    }

}
