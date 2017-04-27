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
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.home.presenter.event.NavigateEvent;
import eu.cash.wallet.login.model.entity.Currency;
import eu.cash.wallet.login.presenter.event.LoginEvent;
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
    private static final int DRAWER_OFFSET = 3;

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
    public void onDrawerItemClick(int position) {
        Log.d("MAIN","DRAWER CLICKED = "+position);
        if (position == DrawerItems.ALL){
            mainDrawer.hideDrawer();
            goHome(true);
            return;
        }
        if (position<DRAWER_OFFSET) return;
        mainDrawer.hideDrawer();
        Log.d("DRAWER","DRAWER CLICKED AT "+position);
        Account account = globalDataRepository.getAccounts().get(position-DRAWER_OFFSET);
        mainView.goAccount(account.getAccountId());
        mainView.setTitle(account.getTitle());
        mainView.setButtonMenuState(NavigationTarget.ACCOUNTS);
    }

    @Override
    public boolean onBottomMenuSelected(int position) {
        mainDrawer.hideDrawer();
        switch (position){
            case MenuTabs.HOME:
                goHome(true); break;
        }
        return true;
    }

    @Override
    public void attachView(MainView mainView, MainDrawer mainDrawer) {
        this.mainView = mainView;
        this.mainView.hideUI();
        this.mainDrawer = mainDrawer;
        EventBus.getDefault().register(this);
        mainView.goLogin();
    }

    private List<IDrawerItem> generateMenuList() {
        List<IDrawerItem> drawerItems = new ArrayList<IDrawerItem>();
        drawerItems.add(new PrimaryDrawerItem()
                .withName(R.string.all_accounts)
                .withDescription(String.format(Locale.getDefault(),
                        "%.2f %s ",
                        globalDataRepository.getUserInfo().getTotal(),
                        globalDataRepository.getDefaultCurrency().getName()))
                .withDescriptionTextColorRes(R.color.gray_drawer)
                .withIcon(R.drawable.account_all)
                .withSelectedBackgroundAnimated(true)
                .withSelectedColorRes(R.color.primary_dark)
                .withTextColorRes(R.color.white)
                .withSelectedTextColorRes(R.color.tumblr_orange)
                .withIconColorRes(R.color.white));
        drawerItems.add(new SectionDrawerItem().withName(R.string.accounts).withTextColorRes(R.color.gray_drawer).withDivider(false));
        Me me = globalDataRepository.getUserInfo();
        for (Account account : me.getAccountList())
            drawerItems.add(new PrimaryDrawerItem()
                    .withName(account.getTitle())
                    .withSelectedBackgroundAnimated(true)
                    .withSelectedTextColorRes(R.color.tumblr_orange)
                    .withSelectedColorRes(R.color.primary_dark)
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
    public void loginComplete(LoginEvent loginEvent){
        calcTotal();
        this.mainDrawer.buildDrawer(generateMenuList());
        this.mainDrawer.updateDrawerHeader(globalDataRepository.getUserInfo(), globalDataRepository.getConfig());
        goHome(false);
        mainView.showUI();
        Log.d("TEST", "MAIN VIEW ATTACHED");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void navigate(NavigateEvent navigateEvent) {
        Log.d("TEST", "EVENT BUS TRANSMITTED EVENT! ");
        switch (navigateEvent.getTarget()) {
            case ACCOUNTS: mainView.goAccount(navigateEvent.getParam()); mainView.setButtonMenuState(navigateEvent.getTarget());break;
            case HOME: goHome(true); break;
            case ADD: goAddEvent(navigateEvent.getParam()); break;
            case CLOSE:
                System.exit(0);
        }
    }

    private void goAddEvent(int accountId){
        mainView.goAdd(accountId);
        mainView.setTitle(context.getString(R.string.add_event));
    }

    private void goHome(boolean animated){
        Log.d("MAIN","GO HOME!");
        mainView.goHome(animated);
        mainView.setTitle(context.getString(R.string.all_accounts));
        mainView.setButtonMenuState(NavigationTarget.HOME);
    }

    private static class MenuTabs {
        private static final int HOME = 0;
        private static final int ACCOUNTS = 0;
        private static final int STATS = 0;
        private static final int SETTINGS = 0;
    }
    private static class DrawerItems {
        private static final int ALL = 1;
    }

}
