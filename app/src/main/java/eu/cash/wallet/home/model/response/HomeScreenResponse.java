package eu.cash.wallet.home.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.cash.wallet.base.Meta;
import eu.cash.wallet.home.model.entity.HomeScreen;

/**
 * Created by alexandr on 20.04.17.
 */

public class HomeScreenResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private HomeScreen homeScreen;

    public HomeScreen getHomeScreen() {
        return homeScreen;
    }

    public void setHomeScreen(HomeScreen homeScreen) {
        this.homeScreen= homeScreen;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
