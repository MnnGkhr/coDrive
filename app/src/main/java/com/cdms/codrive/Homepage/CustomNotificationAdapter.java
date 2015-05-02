package com.cdms.codrive.Homepage;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cdms.codrive.R;
import com.cdms.codrive.classes.Constants;
import com.cdms.codrive.classes.Interaction;
import com.parse.ParseException;

import java.util.List;

public class CustomNotificationAdapter extends RecyclerView.Adapter<CustomNotificationAdapter.ViewHolder>
{
    private List<Interaction> notifications;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        //public TextView mTextView;
        public CardView cardView;
        public ViewHolder(CardView v)
        {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomNotificationAdapter(List<Interaction> notification, Context context)
    {
        this.notifications = notification;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notification_card_view, parent, false);

            ViewHolder vh = new ViewHolder((CardView) v);
            return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
            Log.d("postition", "post" + position);
            StringBuilder content=new StringBuilder();
            if(notifications.get(position).getType().equals(Interaction.Type.STORE) && notifications.get(position).getStatus().equals(Interaction.Status.REQUEST_SENT)
                    && !notifications.get(position).getFromUser().equals(Constants.user))
            {
                content.append(notifications.get(position).getFromUser());
                content.append(" wants to store a file on your device");
                holder.cardView.setOnClickListener(new MyOnClickListener(position));
            }

            if(notifications.get(position).getType().equals(Interaction.Type.STORE) && notifications.get(position).getStatus().equals(Interaction.Status.REQUEST_ACCEPTED)
                && !notifications.get(position).getFromUser().equals(Constants.user))
            {
                content.append("File from ");
                content.append(notifications.get(position).getFromUser());
                content.append(" is ready to be downloaded");
            }

            ((TextView) holder.cardView.findViewById(R.id.content)).setText(content.toString());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return notifications.size();
    }

    class MyOnClickListener implements View.OnClickListener
    {

        int index;

        public MyOnClickListener(int position) {
            this.index = position;
        }

        @Override
        public void onClick(View v)
        {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_dialogbox);
            dialog.setTitle("Request");

            Button okbutton=(Button)dialog.findViewById(R.id.accept);
            okbutton.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context,"accept",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    try {
                        Constants.RespondToStoreRequest(true,notifications.get(index).getServerStatus().getObjectId());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            Button cancelbutton=(Button)dialog.findViewById(R.id.reject);
            cancelbutton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "reject", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    try {
                        Constants.RespondToStoreRequest(false,notifications.get(index).getServerStatus().getObjectId());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            dialog.show();
        }
    }

}
