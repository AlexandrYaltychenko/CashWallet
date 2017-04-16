package eu.cash.wallet.login.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandr on 16.04.17.
 */

public class Config {
    @SerializedName("isPromoShown")
    @Expose
    private boolean isPromoShown;

    public boolean isPromoShown() {
        return isPromoShown;
    }

    public void setPromoShown(boolean promoShown) {
        isPromoShown = promoShown;
    }
}
