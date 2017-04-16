package eu.cash.wallet.login.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.cash.wallet.base.Meta;
import eu.cash.wallet.login.model.entity.Auth;
import eu.cash.wallet.login.model.entity.Config;

/**
 * Created by alexandr on 16.04.17.
 */

public class ConfigResponse {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Config config;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config auth) {
        this.config = auth;
    }
}
