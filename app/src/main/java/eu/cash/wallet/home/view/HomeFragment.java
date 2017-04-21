package eu.cash.wallet.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;

import org.greenrobot.eventbus.EventBus;


import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.home.presenter.HomePresenter;
import eu.cash.wallet.home.view.event.NavigateEvent;
import eu.cash.wallet.login.model.entity.Currency;
import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class HomeFragment extends Fragment implements HomeView{
    @BindView(R.id.header)
    ViewGroup header;
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.navigateMenu)
    FloatingActionMenu actionMenu;
    private HeaderHolder headerHolder;
    @Inject
    HomePresenter homePresenter;
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,rootView);
        headerHolder = new HeaderHolder(header);
        //header.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
        ((CashWalletApp)getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view,savedInstance);
        homePresenter.attachView(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        homePresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        homePresenter.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        homePresenter.onDestroy();
    }


    @Override
    public void setTotalBalance(double total, Currency currency) {
        headerHolder.balance.setText(String.format(Locale.getDefault(),"%.2f %s",total,currency.getName()));
    }

    @Override
    public void displayList(List<Event> events) {
        Log.d("HOME","SIZE = "+events.size());
        HomeViewAdapter homeViewAdapter = new HomeViewAdapter(getContext(),events);
        listView.setAdapter(homeViewAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem>0) {
                    if (!actionMenu.isMenuButtonHidden())
                        actionMenu.hideMenuButton(true);
                } else
                    if (actionMenu.isMenuButtonHidden())
                        actionMenu.showMenuButton(true);

            }
        });

    }

    @OnClick(R.id.stats)
    public void onClick(){
        actionMenu.hideMenuButton(true);
    }

    static class HeaderHolder {
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.balance)
        TextView balance;
        @BindView(R.id.icon)
        ImageView icon;
        HeaderHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

}
