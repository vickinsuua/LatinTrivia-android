package app.my.myapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBalance {
    @SerializedName("balances")
    private List<Balance> balances;

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }
}
