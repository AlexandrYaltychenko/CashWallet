package eu.money.tracker.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.money.tracker.MoneyTrackerApp;
import eu.money.tracker.StringConverterFactory;
import eu.money.tracker.login.model.AuthService;
import eu.money.tracker.login.model.DefaultLoginRepository;
import eu.money.tracker.login.model.LoginRepository;
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
    LoginRepository provideLoginRepository(AuthService authService, Context context){
        return new DefaultLoginRepository(authService);
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

}
