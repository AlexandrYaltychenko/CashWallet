package eu.cash.wallet.main.presenter;

import android.content.Context;

import javax.inject.Inject;

import eu.cash.wallet.main.model.MainRepository;
import eu.cash.wallet.main.view.MainView;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultMainPresenter implements MainPresenter {
    private MainRepository mainRepository;
    private Context context;
    private MainView mainView;
    @Inject public DefaultMainPresenter(Context context, MainRepository mainRepository){
        this.mainRepository = mainRepository;
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
    public void attachView(MainView mainView) {
        this.mainView = mainView;
        this.mainView.buildDrawer(mainRepository.getDrawerItems());
    }
}
