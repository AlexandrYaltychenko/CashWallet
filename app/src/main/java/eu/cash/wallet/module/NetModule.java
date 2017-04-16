package eu.cash.wallet.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.MoneyTrackerApp;
import eu.cash.wallet.StringConverterFactory;
import eu.cash.wallet.login.model.AuthService;
import eu.cash.wallet.login.model.ConfigService;
import eu.cash.wallet.login.model.DefaultLoginRepository;
import eu.cash.wallet.login.model.LoginRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandr on 02.04.17.
 */
@Module
public class NetModule {
    @Provides
    @Singleton
    LoginRepository provideLoginRepository(AuthService authService, ConfigService configService, LocalDataRepository localDataRepository){
        return new DefaultLoginRepository(authService, configService, localDataRepository);
    }
    @Provides
    @Singleton
    AuthService provideAuthService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(MoneyTrackerApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(AuthService.class);
    }

    @Provides
    @Singleton
    ConfigService provideConfigService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(MoneyTrackerApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(ConfigService.class);
    }

}
