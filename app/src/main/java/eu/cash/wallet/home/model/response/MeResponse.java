package eu.cash.wallet.home.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.cash.wallet.base.Meta;
import eu.cash.wallet.home.model.entity.Me;

/**
 * Created by alexandr on 17.04.17.
 */

public class MeResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Me me;

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
