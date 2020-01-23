package com.example.redditclient.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Child {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Feed data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Feed getData() {
        return data;
    }

    public void setData(Feed data) {
        this.data = data;
    }

    @NotNull
    @Override
    public String toString() {
        return "Child{" +
                "kind='" + kind + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
