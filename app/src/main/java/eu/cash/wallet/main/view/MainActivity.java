package eu.cash.wallet.main.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.home.view.HomeFragment;
import eu.cash.wallet.main.presenter.MainPresenter;

/**
 * Created by alexandr on 16.04.17.
 */

public class MainActivity extends AppCompatActivity implements MainView, Drawer.OnDrawerItemClickListener, View.OnClickListener {
    private Drawer drawer;
    private HeaderHolder headerHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    MainPresenter mainPresenter;

    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ((CashWalletApp) getApplicationContext()).getComponent().inject(this);
        mainPresenter.attachView(this);
        toolbar.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
    }

    @Override
    public void onResume(){
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mainPresenter.onPause();
    }

    @Override
    public void onDestroy(){
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
        View header = getLayoutInflater().inflate(R.layout.header_view, null);
        ButterKnife.bind(headerHolder, header);
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withShowDrawerOnFirstLaunch(true)
                .withDisplayBelowStatusBar(false)
                .withHeader(header)
                .withSliderBackgroundColorRes(R.color.primary)
                .withDrawerItems(list)
                .build();
        drawer.setOnDrawerItemClickListener(this);
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
    public void goAccount(int accountId) {

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
    }
}
