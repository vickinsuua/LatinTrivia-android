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
import app.my.myapp.models.Notification;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    private Context context;
    private List<Notification> notifications;

    public NotificationAdapter(Context context, int id_view, List<Notification> notifications){
        super(context, id_view, notifications);
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        NotificationAdapter.NotificationsHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.notifications_mailbox, parent, false);

            holder = new NotificationsHolder();
            holder.textViewTitle = (TextView) row.findViewById(R.id.textViewTitle);
            holder.textViewMessage = (TextView) row.findViewById(R.id.textViewMessage);
            row.setTag(holder);
        } else  {
            holder = (NotificationAdapter.NotificationsHolder)row.getTag();
        }




        Notification notification = notifications.get(position);
        holder.textViewTitle.setText(String.valueOf(notification.getTitle()));
        holder.textViewMessage.setText(String.valueOf(notification.getNotification()));

        return row;
    }

    static class NotificationsHolder {
        TextView textViewTitle;
        TextView textViewMessage;
    }

}
