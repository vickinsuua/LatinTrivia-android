package app.my.myapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.my.myapp.R;
import app.my.myapp.models.Balance;
import app.my.myapp.models.User;

public class ListBalancesAdapter extends ArrayAdapter<Balance> {
    private Context context;
    private List<Balance> balances;

    public ListBalancesAdapter(Context context, int id_view, List<Balance> balances){
        super(context, id_view, balances);
        this.context = context;
        this.balances = balances;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ListBalancesAdapter.ListBalancesHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_balances, parent, false);

            holder = new ListBalancesAdapter.ListBalancesHolder();
            holder.textViewNicknameBalance = (TextView) row.findViewById(R.id.textViewNicknameBalance);
            holder.textViewBalancePrize = (TextView) row.findViewById(R.id.textViewBalancePrize);
            row.setTag(holder);
        } else  {
            holder = (ListBalancesAdapter.ListBalancesHolder)row.getTag();
        }

        Balance balance = balances.get(position);
        holder.textViewNicknameBalance.setText(String.valueOf(balance.getUsers().getNickname()));
        holder.textViewBalancePrize.setText(String.valueOf(balance.getTotalBalance()));

        return row;
    }

    static class ListBalancesHolder {
        TextView textViewNicknameBalance;
        TextView textViewBalancePrize;
    }
}
