package eu.money.tracker.login.model.entity;

/**
 * Created by alexandr on 01.04.17.
 */

public class Auth {
    private String token;
    private long exp;

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
