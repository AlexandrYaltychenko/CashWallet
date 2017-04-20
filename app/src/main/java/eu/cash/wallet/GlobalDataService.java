package eu.cash.wallet;

import eu.cash.wallet.home.model.response.MeResponse;
import eu.cash.wallet.login.model.response.ConfigResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by alex on 20.04.17.
 */

public interface GlobalDataService {
    @GET("me")
    Call<MeResponse> getMe(@Header("Auth") String auth);
    @GET("config")
    Call<ConfigResponse> getConfig(@Query("api") int api);
}
