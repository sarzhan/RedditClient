package com.example.redditclient.presentation.fragments.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.redditclient.R;
import com.example.redditclient.model.Child;
import com.example.redditclient.model.Feed;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    public PostAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Child> list = new ArrayList<>();

    private static int VIEW_TYPE_POST = 0;
    private static int VIEW_TYPE_LOADER = 1;

    private OnItemClickListener listener;


    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.bind(list.get(position));
        if (canLoadMore && position > (list.size() - 5)) {
            canLoadMore = false;
            listener.loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private boolean canLoadMore = false;

    public void addAll(List<Child> posts, boolean isThereMoreItems) {
        canLoadMore = isThereMoreItems;
        list.addAll(posts);
//     todo if have time add notify inserted
        notifyDataSetChanged();
    }

    class PostHolder extends RecyclerView.ViewHolder {
        private ImageView ivFeed;
        private TextView tvAuthor;
        private TextView tvHoursAgo;
        private TextView tvNumComments;
        private TextView tvTitle;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            ivFeed = itemView.findViewById(R.id.ivFeed);
            tvAuthor = itemView.findViewById(R.id.author);
            tvHoursAgo = itemView.findViewById(R.id.hours_ago);
            tvNumComments = itemView.findViewById(R.id.tvNumComments);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        void bind(Child post) {
            Feed feed = post.getData();
            String urlThumbnail = feed.getThumbnail();
            if (urlThumbnail.contains(".jpg")) {
                ivFeed.setVisibility(View.VISIBLE);
                Glide.with(ivFeed)
                        .load(urlThumbnail)
                        .apply(new RequestOptions().override(300, 250))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(ivFeed);
            } else {
                ivFeed.setVisibility(View.GONE);
            }

            tvHoursAgo.setText(getDiffHours(feed.createdUtc, tvHoursAgo.getContext()));
            tvAuthor.setText(feed.getAuthor());
            tvNumComments.setText(feed.getNumComments() + "");
            tvTitle.setText(feed.getTitle());
            ivFeed.setOnClickListener(v -> listener.onItemClick(feed));
        }

        private String getDiffHours(long unixSeconds, Context context) {

            Date date = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
            DateTimeZone dtz = DateTimeZone.getDefault();
            DateTime dateTime = new DateTime(date, dtz);
            DateTime now = new DateTime();

            return Hours.hoursBetween(dateTime, now.toInstant()).getHours() + context.getString(R.string.hours);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Feed feed);

        void loadMore();
    }
}
