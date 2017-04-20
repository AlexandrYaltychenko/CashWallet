package eu.cash.wallet.main.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.login.model.entity.Me;
import eu.cash.wallet.home.view.HomeFragment;
import eu.cash.wallet.home.view.event.NavigateEvent;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.main.presenter.MainPresenter;

/**
 * Created by alexandr on 16.04.17.
 */

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements MainView, MainDrawer, Drawer.OnDrawerItemClickListener, View.OnClickListener {
    private Drawer drawer;
    private HeaderHolder headerHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.menu)
    ViewGroup menu;
    @BindView(R.id.home_icon)
    ImageView homeIcon;
    @BindView(R.id.accounts_icon)
    ImageView accountsIcon;
    @BindView(R.id.settings_icon)
    ImageView settingsIcon;
    @BindView(R.id.home_caption)
    TextView homeCaption;
    @BindView(R.id.accounts_caption)
    TextView accountsCaption;
    @BindView(R.id.settings_caption)
    TextView settingsCaption;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @Inject
    MainPresenter mainPresenter;


    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ((CashWalletApp) getApplicationContext()).getComponent().inject(this);
        mainPresenter.attachView(this, this);
        /*toolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        menu.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));*/
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        buildMenu();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }


    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void buildDrawer(List<IDrawerItem> list) {
        headerHolder = new HeaderHolder();
        @SuppressLint("InflateParams") View header = getLayoutInflater().inflate(R.layout.header_view, null);
        ButterKnife.bind(headerHolder, header);
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withHeader(header)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowStatusBar(false)
                .withSliderBackgroundColorRes(R.color.primary)
                .withDrawerItems(list)
                .build();
        drawer.setOnDrawerItemClickListener(this);
    }

    @Override
    public void setDrawerItems(List<IDrawerItem> list) {
        drawer.setItems(list);
    }

    @Override
    public void updateDrawerHeader(Me me, Config config) {
        headerHolder.email.setText(me.getEmail());
        headerHolder.balanceHeader.setText(String.format(Locale.getDefault(),"%.2f",me.getTotal()));
        headerHolder.currency.setText(config.getDefaultTotalCurrency());
    }

    @Override
    public void changeItem(IDrawerItem item, int pos) {
        drawer.setItemAtPosition(item, pos);
    }

    @Override
    public void goHome() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, HomeFragment.newInstance());
        transaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_left, R.anim.back_slide_in_left, R.anim.back_slide_out_right);
        transaction.commit();
    }

    @Override
    public void setState(NavigationTarget navigationTarget) {
        homeCaption.setTextColor(getResources().getColor(R.color.gray));
        settingsCaption.setTextColor(getResources().getColor(R.color.gray));
        accountsCaption.setTextColor(getResources().getColor(R.color.gray));
        homeIcon.setImageResource(R.drawable.home_1);
        accountsIcon.setImageResource(R.drawable.money_1);
        settingsIcon.setImageResource(R.drawable.settings_1);
        switch (navigationTarget) {
            case HOME:
                homeIcon.setImageResource(R.drawable.home_2);
                homeCaption.setTextColor(getResources().getColor(R.color.primary));
                break;
            case ACCOUNTS:
                accountsIcon.setImageResource(R.drawable.money_2);
                accountsCaption.setTextColor(getResources().getColor(R.color.primary));
                break;
            case SETTINGS:
                settingsIcon.setImageResource(R.drawable.settings_2);
                settingsCaption.setTextColor(getResources().getColor(R.color.primary));
                break;
        }
    }

    @OnClick(R.id.home_button)
    public void onHomeButtonClick(){
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.HOME));
    }

    @OnClick(R.id.accounts_button)
    public void onAccountsButtonClick(){
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.ACCOUNTS));
    }

    @OnClick(R.id.settings_button)
    public void onSettingsButtonClick(){
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.SETTINGS));
    }

    @Override
    public void goAccount(int accountId) {

    }

    public void buildMenu(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.menu_home, R.drawable.home_1, R.color.menu_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.menu_accounts, R.drawable.wallet, R.color.menu_accounts);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.menu_stats, R.drawable.stats, R.color.menu_stats);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.menu_settings, R.drawable.settings_1, R.color.menu_settings);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setColored(true);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                NavigateEvent navigateEvent = null;
                switch (position){
                    case 0: navigateEvent = new NavigateEvent(NavigationTarget.HOME); break;
                    case 1: navigateEvent = new NavigateEvent(NavigationTarget.ACCOUNTS); break;
                    case 2: navigateEvent = new NavigateEvent(NavigationTarget.STATS); break;
                    case 3: navigateEvent = new NavigateEvent(NavigationTarget.SETTINGS); break;
                }
                EventBus.getDefault().post(navigateEvent);
                return true;
            }
        });
    }

    @Override
    public void close() {

    }

    static class HeaderHolder {
        @BindView(R.id.balance_header)
        TextView balanceHeader;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.currency)
        TextView currency;
    }
}
