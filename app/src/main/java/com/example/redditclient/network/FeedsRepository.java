package com.example.redditclient.network;

import io.reactivex.Observable;

public class FeedsRepository {

    public Observable<FeedResponse> getFeeds(String limit, String after) {

        FeedApi api = RestClient.getInstance().createService(FeedApi.class);
        return api.getFeeds(limit, after);
    }
}
