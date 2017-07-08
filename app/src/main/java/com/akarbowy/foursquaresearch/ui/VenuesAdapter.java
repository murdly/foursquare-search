package com.akarbowy.foursquaresearch.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akarbowy.foursquaresearch.R;
import com.akarbowy.foursquaresearch.network.model.Venue;
import com.akarbowy.foursquaresearch.network.model.VenueItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.VenuesViewHolder> {

    private List<VenueItem> items;

    public VenuesAdapter() {
        items = new ArrayList<>();
    }

    public void addItems(List<VenueItem> venues) {
        items.addAll(venues);
        notifyDataSetChanged();
    }

    @Override public VenuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new VenuesViewHolder(inflater.inflate(R.layout.list_item_venue, null));
    }

    @Override public void onBindViewHolder(VenuesViewHolder holder, int position) {
        Venue venue = items.get(position).venue;

        holder.textView.setText(venue.name);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class VenuesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view) TextView textView;

        public VenuesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}