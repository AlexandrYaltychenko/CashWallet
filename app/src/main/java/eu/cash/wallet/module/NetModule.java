package eu.cash.wallet.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.cash.wallet.LocalDataRepository;
import eu.cash.wallet.CashWalletApp;
import eu.cash.wallet.StringConverterFactory;
import eu.cash.wallet.home.model.DefaultHomeRepository;
import eu.cash.wallet.home.model.HomeRepository;
import eu.cash.wallet.home.model.HomeService;
import eu.cash.wallet.login.model.AuthService;
import eu.cash.wallet.login.model.ConfigService;
import eu.cash.wallet.login.model.DefaultLoginRepository;
import eu.cash.wallet.login.model.LoginRepository;
import eu.cash.wallet.main.model.DefaultMainRepository;
import eu.cash.wallet.main.model.MainRepository;
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
    MainRepository provideMainRepository(){
        return new DefaultMainRepository();
    }

    @Provides
    @Singleton
    HomeRepository provideHomeRepository(HomeService homeService){
        return new DefaultHomeRepository(homeService);
    }

    @Provides
    @Singleton
    AuthService provideAuthService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(CashWalletApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(AuthService.class);
    }

    @Provides
    @Singleton
    HomeService provideHomeService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(CashWalletApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(HomeService.class);
    }

    @Provides
    @Singleton
    ConfigService provideConfigService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return (new Retrofit.Builder()
                .baseUrl(CashWalletApp.BASE_URL)
                .client(client)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()).create(ConfigService.class);
    }

}
