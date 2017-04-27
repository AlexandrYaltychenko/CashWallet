package eu.cash.wallet.event.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.event.presenter.EventPresenter;
import eu.cash.wallet.home.view.HomeFragment;

/**
 * Created by alexandr on 23.04.17.
 */

public class EventFragment extends Fragment implements EventView {
    @Inject
    EventPresenter eventPresenter;
    public static EventFragment newInstance() {
        Bundle args = new Bundle();
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, rootView);
        ((CashWalletApp) getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

    }

    @Override
    public void onResume() {
        super.onResume();
        eventPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        eventPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventPresenter.onDestroy();
    }

}
