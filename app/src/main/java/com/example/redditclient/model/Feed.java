package com.example.redditclient.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Feed {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    public String thumbnail;

    @SerializedName("author")
    private String author;

    @SerializedName("created_utc")
    public long createdUtc;

    @SerializedName("num_comments")
    private int numComments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumComments() {
        return numComments;
    }

    @NotNull
    @Override
    public String toString() {
        return "Feed{" +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", author='" + author + '\'' +
                ", createdUtc=" + createdUtc +
                ", numComments=" + numComments +
                '}';
    }
}
