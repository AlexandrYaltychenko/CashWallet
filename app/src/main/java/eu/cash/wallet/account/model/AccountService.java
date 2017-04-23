package eu.cash.wallet.account.model;

import eu.cash.wallet.account.model.response.AccountResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by alex on 21.04.17.
 */

public interface AccountService {
    @GET("accounts/{account_id}/history")
    Call<AccountResponse> getAccountInfo(@Header("Auth") String auth, @Path("account_id") int accountId);
}
