package eu.cash.wallet.login.model.entity;

import android.support.annotation.Nullable;

/**
 * Created by alexandr on 05.06.17.
 */

public class DialogData {
    private String email;
    private String nickname;
    private String password;
    private boolean remember;
    private String errorMsg;

    public DialogData(String email, String password, @Nullable String nickname, boolean remember, @Nullable String errorMsg){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.remember = remember;
        this.errorMsg = errorMsg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRemember() {
        return remember;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
