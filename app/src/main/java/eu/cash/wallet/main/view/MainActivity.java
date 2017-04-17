package eu.cash.wallet.main.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialize.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.cash.wallet.MoneyTrackerApp;
import eu.cash.wallet.R;
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
        ((MoneyTrackerApp) getApplicationContext()).getComponent().inject(this);
        mainPresenter.attachView(this);
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
