package eu.cash.wallet.login.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 20.04.17.
 */

public class Currency {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ex_to_usd")
    @Expose
    private double exRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExRate() {
        return exRate;
    }

    public void setExRate(double exRate) {
        this.exRate = exRate;
    }
}
