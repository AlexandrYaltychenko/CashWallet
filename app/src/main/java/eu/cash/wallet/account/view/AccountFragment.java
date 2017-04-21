package eu.cash.wallet.account.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.account.presenter.AccountPresenter;

/**
 * Created by alexandr on 21.04.17.
 */

public class AccountFragment extends Fragment implements AccountView{
    @Inject
    AccountPresenter accountPresenter;
    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,rootView);
        ((CashWalletApp)getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view,savedInstance);
        accountPresenter.attachView(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        accountPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        accountPresenter.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        accountPresenter.onDestroy();
    }
}
