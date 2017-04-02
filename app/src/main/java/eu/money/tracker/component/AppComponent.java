package eu.money.tracker.component;

import javax.inject.Singleton;

import dagger.Component;
import eu.money.tracker.login.presenter.DefaultLoginPresenter;
import eu.money.tracker.login.view.LoginActivity;
import eu.money.tracker.module.AppModule;
import eu.money.tracker.module.NetModule;
import eu.money.tracker.module.PresenterModule;

/**
 * Created by alexandr on 01.04.17.
 */
@Component(modules = {AppModule.class, PresenterModule.class, NetModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(DefaultLoginPresenter defaultLoginPresenter);

}
