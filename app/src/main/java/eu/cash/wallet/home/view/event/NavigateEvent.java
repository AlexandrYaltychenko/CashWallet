package eu.cash.wallet.home.view.event;

import eu.cash.wallet.main.view.NavigationTarget;

/**
 * Created by alexandr on 17.04.17.
 */

public class NavigateEvent {
    private NavigationTarget target;
    private int param;
    public NavigateEvent(NavigationTarget target){
        this.target = target;
    }
    public NavigateEvent(NavigationTarget target, int param){
        this.target = target;
        this.param = param;
    }
    public int getParam(){
        return this.param;
    }
    public NavigationTarget getTarget(){
        return target;
    }
}
