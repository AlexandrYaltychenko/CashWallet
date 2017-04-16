package eu.cash.wallet.login.model.entity;

/**
 * Created by alexandr on 16.04.17.
 */

public class Credentials {
    private String email;
    private String password;

    public Credentials(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
