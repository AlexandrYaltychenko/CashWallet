package eu.cash.wallet.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.account.model.AccountRepository;
import eu.cash.wallet.account.presenter.AccountPresenter;
import eu.cash.wallet.account.presenter.DefaultAccountPresenter;
import eu.cash.wallet.home.model.HomeRepository;
import eu.cash.wallet.home.presenter.DefaultHomePresenter;
import eu.cash.wallet.home.presenter.HomePresenter;
import eu.cash.wallet.login.model.LoginRepository;
import eu.cash.wallet.login.presenter.DefaultLoginPresenter;
import eu.cash.wallet.login.presenter.LoginPresenter;
import eu.cash.wallet.main.model.MainRepository;
import eu.cash.wallet.main.presenter.DefaultMainPresenter;
import eu.cash.wallet.main.presenter.MainPresenter;

/**
 * Created by alexandr on 01.04.17.
 */
@Module
public class PresenterModule {
    @Provides
    @NonNull
    @Singleton
    public LoginPresenter provideLoginPresenter(Context context, LoginRepository loginRepository, GlobalDataRepository globalDataRepository){
        return new DefaultLoginPresenter(context, loginRepository, globalDataRepository);
    }
    @Provides
    @NonNull
    @Singleton
    public MainPresenter provideMainPresenter(Context context, MainRepository mainRepository, GlobalDataRepository globalDataRepository){
        return new DefaultMainPresenter(context, mainRepository, globalDataRepository);
    }
    @Provides
    @NonNull
    @Singleton
    public HomePresenter provideHomePresenter(Context context, HomeRepository homeRepository, GlobalDataRepository globalDataRepository){
        return new DefaultHomePresenter(context, homeRepository, globalDataRepository);
    }
    @Provides
    @NonNull
    @Singleton
    public AccountPresenter provideAccountPresenter(Context context, AccountRepository accountRepository, GlobalDataRepository globalDataRepository){
        return new DefaultAccountPresenter(context, accountRepository, globalDataRepository);
    }
}
