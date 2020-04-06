package com.asc.home.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Model.Event;
import com.asc.home.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Event> eventArrayList = new ArrayList<>();
    private EventCardClickListener eventCardClickListener;

    public EventListAdapter(Context context, ArrayList<Event> eventArrayList, EventCardClickListener eventCardClickListener) {
        this.context = context;
        this.eventArrayList = eventArrayList;
        this.eventCardClickListener = eventCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events, parent,false);
        final ViewHolder eventViewHolder = new ViewHolder(view, eventCardClickListener);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Event event = eventArrayList.get(position);
        holder.event_title.setText(event.getTitle());
        holder.event_tasks.setText("2");
        holder.event_taskcoins.setText("2");

        holder.event_taskcoins.setText(event.getPoint());
        if(event.getTasks() == null || event.getTasks() == ""){
            holder.event_tasks.setText("");
        }else{
            holder.event_tasks.setText(String.valueOf(event.getTasks().split(";").length));
        }


        try{
            long milliSeconds = event.getTimestamp().getTime();
            String dateString = (String) DateFormat.format("MM/dd/yyyy", new Date(milliSeconds));
            holder.event_date.setText(dateString);
        }catch (Exception e){

        }

        try{
            new DownloadImageTask(holder.event_image)
                    .execute(event.getThumb());
        }catch (Exception e){

        }

        try{
            new DownloadImageTask(holder.event_image)
                    .execute(event.getImage_url());
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView event_image;
        public TextView event_title,event_date,event_tasks,event_taskcoins;
        private LinearLayout eventCard;
        EventCardClickListener eventCardClickListener;

        public ViewHolder(@NonNull View itemView, EventCardClickListener eventCardClickListener) {
            super(itemView);
            event_image=itemView.findViewById(R.id.new_image);
            event_title=itemView.findViewById(R.id.new_title);
            event_date=itemView.findViewById(R.id.new_date);
            event_tasks=itemView.findViewById(R.id.tasks);
            event_taskcoins=itemView.findViewById(R.id.points);
            eventCard = itemView.findViewById(R.id.event_card);
            this.eventCardClickListener = eventCardClickListener;
            eventCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.event_card:
                    try{
                        eventCardClickListener.onParameterSelected(getAdapterPosition());
                    }catch (Exception e){
                        Toast.makeText(context,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }
    public interface EventCardClickListener {
        public void onParameterSelected(int position);
    }
}
