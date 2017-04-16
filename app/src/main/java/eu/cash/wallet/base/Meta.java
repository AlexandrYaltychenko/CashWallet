package eu.cash.wallet.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandr on 16.04.17.
 */

public class Meta {
    @SerializedName("code")
    @Expose
    int code;
    @SerializedName("flush")
    @Expose
    Object[] flush;
}
