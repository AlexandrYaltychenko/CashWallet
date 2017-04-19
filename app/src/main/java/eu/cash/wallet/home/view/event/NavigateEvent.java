package eu.cash.wallet.home.view.event;

/**
 * Created by alexandr on 17.04.17.
 */

public class NavigateEvent {
    public enum Target {HOME, FAQ, ADD, STATS, LOGOUT, CLOSE};
    private Target target;
    public NavigateEvent(Target target){
        this.target = target;
    }

    public Target getTarget(){
        return target;
    }
}
