package eu.cash.wallet.home.model;

import java.util.List;

import javax.inject.Inject;

import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.home.model.callback.HomeScreenCallback;
import eu.cash.wallet.home.model.entity.HomeScreen;
import eu.cash.wallet.home.model.response.HomeScreenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandr on 17.04.17.
 */

public class DefaultHomeRepository implements HomeRepository {
    private HomeService homeService;
    private HomeScreen homeScreen;

    @Inject
    public DefaultHomeRepository(HomeService homeService) {
        this.homeService = homeService;
    }

    @Override
    public void getHomeScreen(String auth, final HomeScreenCallback homeScreenCallback) {
        if (homeScreen == null) {
            Call<HomeScreenResponse> call = homeService.getHomeScreen(auth);
            call.enqueue(new Callback<HomeScreenResponse>() {
                @Override
                public void onResponse(Call<HomeScreenResponse> call, Response<HomeScreenResponse> response) {
                    List<Event> list = response.body().getHomeScreen().getEvents();
                    for (Event event : list)
                        event.setIconRes(getIconRes(event.getEventType()));
                    homeScreenCallback.onHomeScreenFetched(response.body().getHomeScreen());
                }

                @Override
                public void onFailure(Call<HomeScreenResponse> call, Throwable t) {
                    homeScreenCallback.onConnectionError();
                }
            });
        } else
            homeScreenCallback.onHomeScreenFetched(homeScreen);

    }

    private int getIconRes(int type) {
        return 0;
    }
}
