package eu.cash.wallet.main.presenter;

import android.content.Context;
import android.util.Log;

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.home.model.entity.Me;
import eu.cash.wallet.home.view.event.NavigateEvent;
import eu.cash.wallet.login.model.entity.Currency;
import eu.cash.wallet.main.model.MainRepository;
import eu.cash.wallet.main.view.MainDrawer;
import eu.cash.wallet.main.view.MainView;
import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultMainPresenter implements MainPresenter {
    private MainRepository mainRepository;
    private GlobalDataRepository globalDataRepository;
    private Context context;
    private MainView mainView;
    private MainDrawer mainDrawer;

    @Inject
    public DefaultMainPresenter(Context context, MainRepository mainRepository, GlobalDataRepository globalDataRepository) {
        this.mainRepository = mainRepository;
        this.globalDataRepository = globalDataRepository;
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
    public void attachView(MainView mainView, MainDrawer mainDrawer) {
        this.mainView = mainView;
        this.mainDrawer = mainDrawer;
        EventBus.getDefault().register(this);
        this.mainDrawer.buildDrawer(generateMenuList());
        calcTotal();
        this.mainDrawer.updateDrawerHeader(globalDataRepository.getUserInfo(), globalDataRepository.getConfig());
        this.mainView.goHome();
        this.mainView.setState(NavigationTarget.HOME);
        Log.d("TEST", "MAIN VIEW ATTACHED");
    }

    private List<IDrawerItem> generateMenuList() {
        List<IDrawerItem> drawerItems = new ArrayList<IDrawerItem>();
        List<IDrawerItem> menu = mainRepository.getDrawerItems();
        drawerItems.add(0, new SectionDrawerItem().withName(R.string.accounts).withTextColorRes(R.color.gray_drawer).withDivider(false));
        Me me = globalDataRepository.getUserInfo();
        for (Account account : me.getAccountList())
            drawerItems.add(1, new PrimaryDrawerItem()
                    .withName(account.getTitle())
                    .withDescription(String.valueOf(account.getAmount())+" "+account.getCurrency())
                    .withDescriptionTextColorRes(R.color.gray_drawer)
                    .withIcon(account.getType() == 1 ? R.drawable.account_numerar : R.drawable.account_card)
                    .withTextColorRes(R.color.white)
                    .withIconColorRes(R.color.white));
        return drawerItems;
    }

    private void calcTotal(){
        Me me = globalDataRepository.getUserInfo();
        Map<String, Double> exRates = new HashMap<>();
        for (Currency currency : globalDataRepository.getConfig().getCurrencyList())
            exRates.put(currency.getName(),currency.getExRate());
        double total = 0;
        for (Account account : me.getAccountList()) {
            Double rate = exRates.get(account.getCurrency());
            if (rate == null)
                continue;
            total += account.getAmount() / rate;
        }
        me.setTotal(total);
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
