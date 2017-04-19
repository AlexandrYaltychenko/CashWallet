package eu.cash.wallet.base;

/**
 * Created by alexandr on 01.04.17.
 */

public interface BasePresenter {
    void onPause();
    void onStop();
    void onResume();
    void onBackPressed();
    void onDestroy();
}
