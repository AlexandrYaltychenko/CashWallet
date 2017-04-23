package eu.cash.wallet.account.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexandr on 17.04.17.
 */

public class Account {
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("account_title")
    @Expose
    private String title;
    @SerializedName("account_type")
    @Expose
    private int type;
    @SerializedName("account_cur")
    @Expose
    private String currency;
    @SerializedName("account_amount")
    @Expose
    private double amount;
    @SerializedName("account_icon")
    @Expose
    private String iconUrl;
    @SerializedName("history")
    @Expose
    private List<Event> history;

    public List<Event> getHistory() {
        return history;
    }

    public void setHistory(List<Event> history) {
        this.history = history;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
