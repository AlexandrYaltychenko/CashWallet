package eu.cash.wallet.login.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.cash.wallet.base.Meta;
import eu.cash.wallet.login.model.entity.Auth;

/**
 * Created by alexandr on 16.04.17.
 */

public class AuthResponse {
    @SerializedName("meta")
    @Expose
    Meta meta;
    @SerializedName("response")
    @Expose
    Auth auth;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
