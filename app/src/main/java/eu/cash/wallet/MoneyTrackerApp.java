package eu.cash.wallet;

import android.app.Application;
import android.util.Log;

import eu.cash.wallet.component.AppComponent;
import eu.cash.wallet.component.DaggerAppComponent;
import eu.cash.wallet.login.model.entity.Config;
import eu.cash.wallet.module.AppModule;

/**
 * Created by alexandr on 01.04.17.
 */

public class MoneyTrackerApp extends Application {
    private AppComponent component;
    private Config config;
    public static final String BASE_URL = "http://appapi.eu/cash_wallet/web/";
    public AppComponent getComponent() {
        return component;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = buildComponent();
        Log.d("CHECK",String.valueOf(component == null));
    }
    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
