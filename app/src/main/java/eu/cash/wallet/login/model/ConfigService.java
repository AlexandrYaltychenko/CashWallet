package eu.cash.wallet.login.model;

import eu.cash.wallet.login.model.response.ConfigResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexandr on 16.04.17.
 */

public interface ConfigService {
    @GET("config")
    Call<ConfigResponse> getConfig(@Query("api") int api);
}
