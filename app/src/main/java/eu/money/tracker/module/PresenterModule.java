package eu.money.tracker.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.money.tracker.login.model.LoginRepository;
import eu.money.tracker.login.presenter.DefaultLoginPresenter;
import eu.money.tracker.login.presenter.LoginPresenter;

/**
 * Created by alexandr on 01.04.17.
 */
@Module
public class PresenterModule {
    @Provides
    @NonNull
    @Singleton
    public LoginPresenter provideLoginPresenter(LoginRepository loginRepository){
        return new DefaultLoginPresenter(loginRepository);
    }
}
