package eu.cash.wallet.home.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.cash.wallet.R;
import eu.cash.wallet.account.model.entity.Event;

/**
 * Created by alexandr on 20.04.17.
 */

public class HomeViewAdapter extends BaseAdapter {
    private List<Event> events;
    private Context context;
    private LayoutInflater layoutInflater;

    public HomeViewAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("HOME","CALLING FOR POS "+position);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.homescreen_item, parent, false);
        HomeItemHolder holder = (HomeItemHolder) convertView.getTag();
        if (holder == null) {
            holder = new HomeItemHolder(convertView);
            convertView.setTag(holder);
        }
        Event event = getItem(position);
        holder.account.setText(event.getAccount().getTitle());
        holder.amount.setText(String.format(Locale.getDefault(),"%.2f %s",event.getAmount(),event.getAccount().getCurrency()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm",Locale.getDefault());
        holder.date.setText(dateFormat.format(new Date(event.getTimestamp()*1000)));
        holder.event.setText(event.getEventTitle());
        if (event.getAmount()>=0)
            holder.typeMarker.setBackgroundColor(context.getResources().getColor(R.color.tumblr_green));
        else
            holder.typeMarker.setBackgroundColor(context.getResources().getColor(R.color.red));
        return convertView;
    }

    static class HomeItemHolder {
        @BindView(R.id.account_icon)
        ImageView accountIcon;
        @BindView(R.id.account)
        TextView account;
        @BindView(R.id.event)
        TextView event;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.typeMarker)
        View typeMarker;

        HomeItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
