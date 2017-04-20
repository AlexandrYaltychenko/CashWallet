package eu.cash.wallet.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.ogaclejapan.arclayout.ArcLayout;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.home.presenter.HomePresenter;
import eu.cash.wallet.home.view.event.NavigateEvent;
import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class HomeFragment extends Fragment implements HomeView{
    @BindView(R.id.arcLayout)
    ArcLayout arcLayout;
    @BindView(R.id.header)
    View header;
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
        header.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
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
        arcLayout.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.arc_in));
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

    @OnClick(R.id.logout)
    public void onClick(){
        Log.d("TEST","POSTED NAVIGATE EVENT!");
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.CLOSE));
    }

}
