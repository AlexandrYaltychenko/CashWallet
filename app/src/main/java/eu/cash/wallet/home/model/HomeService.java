package eu.cash.wallet.home.model;

import eu.cash.wallet.home.model.response.HomeScreenResponse;
import eu.cash.wallet.home.model.response.MeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomeService {
    @GET("homescreen")
    Call<HomeScreenResponse> getHomeScreen(@Header("Auth") String auth);
}
