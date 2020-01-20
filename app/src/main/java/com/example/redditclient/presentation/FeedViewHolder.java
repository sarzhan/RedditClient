package com.example.redditclient.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.redditclient.Model.Child;
import com.example.redditclient.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivFeed;
    private TextView tvAuthor;
    private TextView tvHoursAgo;
    private TextView tvNumComments;
    private TextView tvTitle;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        ivFeed = itemView.findViewById(R.id.ivFeed);
        tvAuthor = itemView.findViewById(R.id.author);
        tvHoursAgo = itemView.findViewById(R.id.hours_ago);
        tvNumComments = itemView.findViewById(R.id.tvNumComments);
        tvTitle = itemView.findViewById(R.id.tvTitle);


    }

    public void bind(Child feed) {

        String urlThumbnail = feed.getData().getThumbnail();
        if (urlThumbnail.contains(".jpg")) {
            Glide.with(itemView.getContext())
                    .load(urlThumbnail)
                    .apply(new RequestOptions().override(300, 250))
                    .centerInside()
                    .into(ivFeed);
        } else {
            ivFeed.setVisibility(View.GONE);
        }

        tvHoursAgo.setText(getDiffHours(feed.getData().createdUtc));
        tvAuthor.setText(feed.getData().getAuthor());
        tvNumComments.setText(String.format("%d", feed.getData().getNumComments()));
        tvTitle.setText(feed.getData().getTitle());


    }

    private String getDiffHours(long unixSeconds) {

        Date date = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
        DateTimeZone dtz = DateTimeZone.getDefault();
        DateTime dateTime = new DateTime(date, dtz);
        DateTime now = new DateTime();

        return Hours.hoursBetween(dateTime, now.toInstant()).getHours() + itemView.getContext().getString(R.string.hours);


    }

}
