package eu.money.tracker;

/**
 * Created by alexandr on 01.04.17.
 */

public interface ActivityPresenter {
    void onPause();
    void onStop();
    void onResume();
    void onBackPressed();
}
