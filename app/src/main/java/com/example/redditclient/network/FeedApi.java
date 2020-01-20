package com.example.redditclient.network;

import com.example.redditclient.Model.Child;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface FeedApi {
    @GET("top/.json")
    Observable<FeedResponse> getFeeds();
}
