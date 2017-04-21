package eu.cash.wallet.home.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.GlobalDataService;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.home.model.HomeRepository;
import eu.cash.wallet.home.model.callback.HomeScreenCallback;
import eu.cash.wallet.home.model.entity.HomeScreen;
import eu.cash.wallet.home.view.HomeView;
import eu.cash.wallet.main.presenter.DefaultMainPresenter;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultHomePresenter implements HomePresenter, HomeScreenCallback {
    private HomeView homeView;
    private Context context;
    private HomeRepository homeRepository;
    private GlobalDataRepository globalDataRepository;
    @Inject
    public DefaultHomePresenter(Context context, HomeRepository homeRepository, GlobalDataRepository globalDataRepository){
        this.context = context;
        this.homeRepository = homeRepository;
        this.globalDataRepository = globalDataRepository;
    }
    @Override
    public void attachView(HomeView homeView){
        this.homeView = homeView;
        homeView.setTotalBalance(globalDataRepository.getUserInfo().getTotal(),globalDataRepository.getDefaultCurrency());
        homeRepository.getHomeScreen(globalDataRepository.getAuthToken().getToken(),this);
    }

    @Override
    public void onHomeScreenFetched(HomeScreen homeScreen) {
        List<Event> events = homeScreen.getEvents();
        for (Event event : events)
            event.setAccount(globalDataRepository.getAccountById(event.getAccountId()));
        List<Event> t = new ArrayList<>();
        t.addAll(events);
        t.addAll(events);
        homeView.displayList(t);
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
        homeView = null;
    }


    @Override
    public void onConnectionError() {

    }
}
