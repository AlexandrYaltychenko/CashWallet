package eu.cash.wallet.login.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandr on 01.04.17.
 */

public class Auth {
    @SerializedName("auth")
    @Expose
    private String token;
    @SerializedName("exp")
    @Expose
    private long exp;

    public Auth(String token, long exp){
        this.token = token;
        this.exp = exp;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
