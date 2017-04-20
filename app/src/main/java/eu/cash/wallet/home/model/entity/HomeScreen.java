package eu.cash.wallet.home.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.cash.wallet.account.model.entity.Event;

/**
 * Created by alexandr on 20.04.17.
 */

public class HomeScreen {
    @SerializedName("last_history")
    @Expose
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
