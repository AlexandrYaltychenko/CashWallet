package eu.cash.wallet.account.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandr on 20.04.17.
 */

public class Event {
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("time")
    @Expose
    private long timestamp;
    @SerializedName("event_type")
    @Expose
    private int eventType;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("event_icon")
    @Expose
    private String eventIcon;
    @SerializedName("event_title")
    @Expose
    private String eventTitle;
    @SerializedName("event_desc")
    @Expose
    private String eventDesc;
    @SerializedName("account")
    @Expose
    private Account account;
    private int iconRes;

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEventIcon() {
        return eventIcon;
    }

    public void setEventIcon(String eventIcon) {
        this.eventIcon = eventIcon;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
