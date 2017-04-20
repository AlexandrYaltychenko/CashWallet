package eu.cash.wallet.login.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexandr on 16.04.17.
 */

public class Config {
    @SerializedName("isPromoShown")
    @Expose
    private boolean isPromoShown;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencyList;
    private String defaultTotalCurrency;

    public boolean isPromoShown() {
        return isPromoShown;
    }

    public void setPromoShown(boolean promoShown) {
        isPromoShown = promoShown;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public String getDefaultTotalCurrency() {
        return defaultTotalCurrency;
    }

    public void setDefaultTotalCurrency(String defaultTotalCurrency) {
        this.defaultTotalCurrency = defaultTotalCurrency;
    }
}
