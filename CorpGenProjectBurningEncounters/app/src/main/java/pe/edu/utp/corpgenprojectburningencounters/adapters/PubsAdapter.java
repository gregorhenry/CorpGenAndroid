package pe.edu.utp.corpgenprojectburningencounters.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import pe.edu.utp.corpgenprojectburningencounters.R;
import pe.edu.utp.corpgenprojectburningencounters.models.Pub;

/**
 * Created by Heisenberg on 28/10/2017.
 */

public class PubsAdapter extends RecyclerView.Adapter<PubsAdapter.ViewHolder> {

    private List<Pub> pubs;

    public PubsAdapter(List<Pub> pubs) {
        this.pubs = pubs;
    }

    public PubsAdapter() {
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pub, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pub pub = pubs.get(position);
        holder.logoImageView.setImageResource(R.mipmap.ic_launcher);
        holder.nameTextView.setText(pub.getName());
        holder.addressTextView.setText(pub.getAddress());
        holder.eventTextView.setText(pub.getEvent());
        holder.horaryTextView.setText(pub.getHorary());
       holder.descriptionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: Start About Source Activity
            }
        });
       holder.newsTextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //TODO: Start News
           }
       });
    }

    @Override
    public int getItemCount() {

        return pubs.size();
    }

    public List<Pub> getPubs() {
        return pubs;
    }

    public PubsAdapter setPubs(List<Pub> pubs) {
        this.pubs = pubs;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logoImageView;
        TextView nameTextView;
        RatingBar ratingRatingBar;
        TextView addressTextView;
        TextView eventTextView;
        TextView horaryTextView;
        TextView descriptionTextView;
        TextView newsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            logoImageView = (ImageView) itemView.findViewById(R.id.logoImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            ratingRatingBar = (RatingBar) itemView.findViewById(R.id.ratingRatingBar);
            addressTextView = (TextView) itemView.findViewById(R.id.addressTextView);
            eventTextView = (TextView) itemView.findViewById(R.id.eventTextView);
            horaryTextView = (TextView) itemView.findViewById(R.id.horaryTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
            newsTextView = (TextView) itemView.findViewById(R.id.newsTextView);
        }
    }
}
