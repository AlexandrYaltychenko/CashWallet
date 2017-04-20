package eu.cash.wallet.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.DefaultGlobalDataRepository;
import eu.cash.wallet.GlobalDataRepository;
import eu.cash.wallet.GlobalDataService;

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
    public GlobalDataRepository provideLocalDataRepository(Context context, GlobalDataService globalDataService){
        return new DefaultGlobalDataRepository(context, globalDataService);
    }


}
