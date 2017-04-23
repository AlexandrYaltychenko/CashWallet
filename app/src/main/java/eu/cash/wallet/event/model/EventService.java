package eu.cash.wallet.event.model;

import eu.cash.wallet.event.model.response.AddEventResponse;
import eu.cash.wallet.home.model.response.HomeScreenResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by alexandr on 23.04.17.
 */

public interface EventService {
    @GET("events/types")
    Call<EventRepository> getEventTypes(@Header("Auth") String auth);
    @POST("POST /accounts/{account_id}/add")
    Call<AddEventResponse> addEvent(@Header("Auth") String auth);
}
