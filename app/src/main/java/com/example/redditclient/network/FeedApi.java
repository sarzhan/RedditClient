package com.example.redditclient.network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FeedApi {
    @GET("top/.json")
    Observable<FeedResponse> getFeeds(@Query("limit") String limit , @Query("after") String after);

}

