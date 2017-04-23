package eu.cash.wallet.home.presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.home.model.HomeRepository;
import eu.cash.wallet.home.model.callback.HomeScreenCallback;
import eu.cash.wallet.home.model.entity.HomeScreen;
import eu.cash.wallet.home.view.HomeView;

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
        Log.d("HOME","VIEW IS ATTACHED!");
        homeView.displayTotalBalance(globalDataRepository.getUserInfo().getTotal(),globalDataRepository.getDefaultCurrency());
        homeView.displayLoading();
        homeRepository.getHomeScreen(globalDataRepository.getAuthToken().getToken(),this);
    }

    @Override
    public void onHomeScreenFetched(HomeScreen homeScreen) {
        if (homeView == null) return;
        homeView.hideLoading();
        List<Event> events = homeScreen.getEvents();
        for (Event event : events)
            event.setAccount(globalDataRepository.getAccountById(event.getAccountId()));
        List<Event> t = new ArrayList<>();
        t.addAll(events);
        t.addAll(events);
        homeView.displayList(t);
    }

    @Override
    public void addCostClick() {
        homeView.displayCostDialog(globalDataRepository.getAccounts());
    }

    @Override
    public void addIncomeClick() {
        homeView.displayIncomeDialog(globalDataRepository.getAccounts());
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
        Log.d("HOME","VIEW IS DETACHED");
    }


    @Override
    public void onConnectionError() {

    }
}
