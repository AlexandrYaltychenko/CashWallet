package eu.cash.wallet.home.presenter;

import android.content.Context;

import javax.inject.Inject;

import eu.cash.wallet.home.model.HomeRepository;
import eu.cash.wallet.home.view.HomeView;
import eu.cash.wallet.main.presenter.DefaultMainPresenter;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultHomePresenter implements HomePresenter {
    private HomeView homeView;
    private Context context;
    private HomeRepository homeRepository;
    @Inject
    public DefaultHomePresenter(Context context, HomeRepository homeRepository){
        this.context = context;
        this.homeRepository = homeRepository;
    }
    @Override
    public void attachView(HomeView homeView){
        this.homeView = homeView;
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
}
