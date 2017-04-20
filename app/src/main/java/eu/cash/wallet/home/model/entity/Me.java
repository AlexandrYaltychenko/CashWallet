package eu.cash.wallet.home.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.cash.wallet.account.model.entity.Account;

/**
 * Created by alexandr on 17.04.17.
 */

public class Me {
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("accounts")
    @Expose
    private List<Account> accountList;
    @SerializedName("total")
    @Expose
    private double total = 1000000;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
