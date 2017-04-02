package eu.money.tracker;

import android.app.Application;
import android.util.Log;

import eu.money.tracker.component.AppComponent;
import eu.money.tracker.component.DaggerAppComponent;
import eu.money.tracker.module.AppModule;

/**
 * Created by alexandr on 01.04.17.
 */

public class MoneyTrackerApp extends Application {
    private AppComponent component;
    public static final String BASE_URL = "http://novaforen.com/money_tracker/api/v1/";
    public AppComponent getComponent() {
        return component;
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
