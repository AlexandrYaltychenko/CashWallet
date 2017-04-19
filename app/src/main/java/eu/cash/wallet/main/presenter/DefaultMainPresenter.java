package eu.cash.wallet.main.presenter;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import eu.cash.wallet.home.view.event.NavigateEvent;
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
    public void onDestroy() {
        mainView = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
        this.mainView.buildDrawer(mainRepository.getDrawerItems());
        this.mainView.goHome();
        EventBus.getDefault().register(this);
        Log.d("TEST","MAIN VIEW ATTACHED");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onNavigate(NavigateEvent navigateEvent) {
        Log.d("TEST","EVENT BUS TRANSMITTED EVENT! ");
        switch (navigateEvent.getTarget()){
            case CLOSE: System.exit(0);
        }
    }

}
