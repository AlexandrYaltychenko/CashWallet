package eu.cash.wallet.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.DefaultLocalDataRepository;
import eu.cash.wallet.LocalDataRepository;

/**
 * Created by alexandr on 01.04.17.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(@NonNull Context context){
        this.context = context;
    }
    @Provides
    @NonNull
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public LocalDataRepository provideLocalDataRepository(Context context){
        return new DefaultLocalDataRepository(context);
    }


}
