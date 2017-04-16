package eu.cash.wallet.component;

import javax.inject.Singleton;

import dagger.Component;
import eu.cash.wallet.login.presenter.DefaultLoginPresenter;
import eu.cash.wallet.login.view.LoginActivity;
import eu.cash.wallet.module.AppModule;
import eu.cash.wallet.module.NetModule;
import eu.cash.wallet.module.PresenterModule;

/**
 * Created by alexandr on 01.04.17.
 */
@Component(modules = {AppModule.class, PresenterModule.class, NetModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginActivity loginActivity);

}
