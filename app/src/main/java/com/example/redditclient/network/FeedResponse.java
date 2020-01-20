package com.example.redditclient.network;

import com.example.redditclient.Model.Data;
import com.google.gson.annotations.SerializedName;

public class FeedResponse {
    @SerializedName("kind")
    public String kind;

    @SerializedName("data")
    public Data data;

    @Override
    public String toString() {
        return "FeedResponse{" +
                "kind='" + kind + '\'' +
                ", data=" + data.toString() +"\n"+
                '}';
    }
}
