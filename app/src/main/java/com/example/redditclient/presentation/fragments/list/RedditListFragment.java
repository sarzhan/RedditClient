package com.example.redditclient.presentation.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.redditclient.R;
import com.example.redditclient.model.Child;
import com.example.redditclient.model.Feed;
import com.example.redditclient.presentation.activity.MainActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class RedditListFragment extends Fragment implements RedditListView {
    private RecyclerView posts;
    private PostAdapter adapter;

    private ProgressBar progress;
    private RedditListPresenter presenter = new RedditListPresenter();

    private PostAdapter.OnItemClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reddit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        posts = view.findViewById(R.id.posts);
        progress = view.findViewById(R.id.progress);
        posts.setHasFixedSize(true);
        listener = new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Feed feed) {
                ((MainActivity)getActivity()).navigateToImage(feed.thumbnail);
            }

            @Override
            public void loadMore() {
                progress.setVisibility(View.VISIBLE);
                presenter.loadMore();
            }
        };

        adapter = new PostAdapter(listener);
        posts.setAdapter(adapter);

        presenter.getPosts();
    }

    @Override
    public void onDestroyView() {
        listener = null;
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onError(String text) {
        progress.setVisibility(View.GONE);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaded(List<Child> posts, boolean isThereMoreItems) {
        progress.setVisibility(View.GONE);
        adapter.addAll(posts, isThereMoreItems);
    }
}
