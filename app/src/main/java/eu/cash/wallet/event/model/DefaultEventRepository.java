package eu.cash.wallet.event.model;

import javax.inject.Inject;

/**
 * Created by alexandr on 23.04.17.
 */

public class DefaultEventRepository implements EventRepository {
    private EventService eventService;

    @Inject
    public DefaultEventRepository(EventService eventService) {
        this.eventService = eventService;
    }
}
