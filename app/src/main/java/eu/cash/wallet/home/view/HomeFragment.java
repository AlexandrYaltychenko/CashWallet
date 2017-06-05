package eu.cash.wallet.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionMenu;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.jaredrummler.materialspinner.MaterialSpinner;


import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.home.presenter.HomePresenter;
import eu.cash.wallet.home.presenter.event.NavigateEvent;
import eu.cash.wallet.login.model.entity.Currency;
import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class HomeFragment extends Fragment implements HomeView {
    @BindView(R.id.header)
    ViewGroup header;
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.navigateMenu)
    FloatingActionMenu actionMenu;
    @BindView(R.id.progress)
    View progress;
    private HeaderHolder headerHolder;
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
        ButterKnife.bind(this, rootView);
        headerHolder = new HeaderHolder(header);
        Log.d("CRASHDEV","HOME FRAGMENT");
        //header.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
        ((CashWalletApp) getContext().getApplicationContext()).getComponent().inject(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        homePresenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        homePresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.onDestroy();
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
    public void displayTotalBalance(double total, Currency currency) {
        headerHolder.balance.setText(String.format(Locale.getDefault(), "%.2f %s", total, currency.getName()));
    }

    @Override
    public void displayList(List<Event> events) {
        Log.d("HOME", "SIZE = " + events.size());
        HomeViewAdapter homeViewAdapter = new HomeViewAdapter(getContext(), events);
        listView.setAdapter(homeViewAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int previous = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!actionMenu.isMenuButtonHidden() && firstVisibleItem > previous) {
                    actionMenu.hideMenuButton(true);
                    Log.d("HOME", "HIDE MENU!");
                } else if (actionMenu.isMenuButtonHidden() && firstVisibleItem < previous) {
                    actionMenu.showMenuButton(true);
                    Log.d("HOME", "SHOW MENU!");
                }
                if (previous != firstVisibleItem)
                    Log.d("HOME", "PREV = " + previous + " FIRST = " + firstVisibleItem);
                previous = firstVisibleItem;

            }
        });

    }

    @Override
    public void displayIncomeDialog(List<Account> accounts) {
        final AddDialogHolder loginDialogHolder = new AddDialogHolder(getActivity().getLayoutInflater().inflate(R.layout.add_dialog, null));
        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(getContext())
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle(R.string.sign_in_big)
                .setHeaderDrawable(R.drawable.dialog_header)
                .setPositiveText(R.string.add_big)
                .setNegativeText(R.string.cancel_big)
                .setCustomView(loginDialogHolder.view, 20, 20, 20, 0)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .withDialogAnimation(true)
                .build();
        dialog.show();
    }

    @Override
    public void displayCostDialog(List<Account> accounts) {

    }

    static class AddDialogHolder {
        @BindView(R.id.email)
        EditText email;
        @BindView(R.id.password)
        EditText password;
        @BindView(R.id.spinner)
        MaterialSpinner spinner;
        View view;

        AddDialogHolder(View view) {
            ButterKnife.bind(this, view);
            this.view = view;
            spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
            spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                }
            });
        }

    }

    @OnClick(R.id.stats)
    public void onClick() {
        actionMenu.hideMenuButton(true);
    }

    @OnClick(R.id.add_cost)
    public void onAddCostClick() {
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.ADD));
    }

    @OnClick(R.id.add_income)
    public void onAddIncomeClick() {
        EventBus.getDefault().post(new NavigateEvent(NavigationTarget.ADD));
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
