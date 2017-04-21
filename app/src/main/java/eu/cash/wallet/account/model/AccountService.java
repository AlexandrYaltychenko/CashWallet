package eu.cash.wallet.account.model;

import eu.cash.wallet.home.model.response.HomeScreenResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alex on 21.04.17.
 */

public interface AccountService {
    @GET("accounts/{account_id}/history")
    Call<HomeScreenResponse> getAccountInfo(@Header("Auth") String auth, @Path("account_id") int accountId);
}
