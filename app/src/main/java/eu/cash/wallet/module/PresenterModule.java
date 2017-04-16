package eu.cash.wallet.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.login.model.LoginRepository;
import eu.cash.wallet.login.presenter.DefaultLoginPresenter;
import eu.cash.wallet.login.presenter.LoginPresenter;

/**
 * Created by alexandr on 01.04.17.
 */
@Module
public class PresenterModule {
    @Provides
    @NonNull
    @Singleton
    public LoginPresenter provideLoginPresenter(Context context, LoginRepository loginRepository, LocalDataRepository localDataRepository){
        return new DefaultLoginPresenter(context, loginRepository, localDataRepository);
    }
}
