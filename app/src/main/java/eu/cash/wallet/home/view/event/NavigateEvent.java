package eu.cash.wallet.home.view.event;

import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class NavigateEvent {
    private NavigationTarget target;
    public NavigateEvent(NavigationTarget target){
        this.target = target;
    }

    public NavigationTarget getTarget(){
        return target;
    }
}
