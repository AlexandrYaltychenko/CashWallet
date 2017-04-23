package eu.cash.wallet.event.presenter;

import android.content.Context;

import javax.inject.Inject;

import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.event.model.EventRepository;

/**
 * Created by alexandr on 23.04.17.
 */

public class DefaultEventPresenter implements EventPresenter {
    private Context context;
    private EventRepository eventRepository;
    private GlobalDataRepository globalDataRepository;
    @Inject public DefaultEventPresenter(Context context, EventRepository eventRepository, GlobalDataRepository globalDataRepository){
        this.context = context;
        this.eventRepository = eventRepository;
        this.globalDataRepository = globalDataRepository;
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

    }
}
