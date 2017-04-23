package eu.cash.wallet.account.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.account.presenter.AccountPresenter;

/**
 * Created by alexandr on 21.04.17.
 */

public class AccountFragment extends Fragment implements AccountView{
    @BindView(R.id.header)
    ViewGroup header;
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.progress)
    View progress;
    @Inject
    AccountPresenter accountPresenter;
    private HeaderHolder headerHolder;
    public static AccountFragment newInstance(int accountId) {
        Bundle args = new Bundle();
        args.putInt("accountId",accountId);
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this,rootView);
        headerHolder = new HeaderHolder(header);
        ((CashWalletApp)getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void displayLoading() {
        progress.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view,savedInstance);
        accountPresenter.attachView(this,getArguments().getInt("accountId"));
    }

    @Override
    public void displayHeaderAccountInfo(Account account) {
        headerHolder.balance.setText(String.format(Locale.getDefault(),"%.2f %s",account.getAmount(),account.getCurrency()));
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

    @Override
    public void displayHistory(List<Event> events) {
        BaseAdapter accountViewAdapter = new AccountViewAdapter(getContext(),events);
        listView.setAdapter(accountViewAdapter);
    }

    static class HeaderHolder {
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.balance)
        TextView balance;
        @BindView(R.id.icon)
        ImageView icon;

        HeaderHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
