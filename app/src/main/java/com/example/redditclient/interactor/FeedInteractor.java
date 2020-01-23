package com.example.redditclient.interactor;

import com.example.redditclient.network.FeedResponse;
import com.example.redditclient.network.FeedsRepository;

import io.reactivex.Observable;

public class FeedInteractor {

    private final FeedsRepository repository = new FeedsRepository();

    public Observable<FeedResponse> getPosts(String limit, String after) {
        return repository.getFeeds(limit, after);
    }
}
