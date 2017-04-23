package eu.cash.wallet.event.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;
import eu.cash.wallet.account.model.entity.Event;
import eu.cash.wallet.base.Meta;

/**
 * Created by alexandr on 23.04.17.
 */

public class EventResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private List<Event> eventList;

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
