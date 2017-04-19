package eu.cash.wallet.home.model;

import eu.cash.wallet.home.model.response.MeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by alexandr on 17.04.17.
 */

public interface HomeService {
    @GET("me")
    Call<MeResponse> getMe(@Header("Auth") String auth);
}
