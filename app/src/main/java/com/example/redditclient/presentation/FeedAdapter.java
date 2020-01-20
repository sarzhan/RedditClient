package com.example.redditclient.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditclient.Model.Child;
import com.example.redditclient.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private Context context;
    public List<Child> feedList;

    public FeedAdapter(Context context, List list) {
        this.context = context;
        feedList = list;

    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.bind(feedList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

}
