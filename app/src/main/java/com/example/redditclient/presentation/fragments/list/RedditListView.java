package com.example.redditclient.presentation.fragments.list;

import com.example.redditclient.model.Child;

import java.util.List;

public interface RedditListView {

    void onLoaded(List<Child> posts, boolean isThereMoreItems);

    void onError(String text);

}
