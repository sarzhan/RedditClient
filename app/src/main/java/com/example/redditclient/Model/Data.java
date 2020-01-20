package com.example.redditclient.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("modhash")
    public String modhash;
    @SerializedName("children")
    public List<Child> children = null;
    @SerializedName("after")
    public String after;
    @SerializedName("before")
    public Object before;

    @Override
    public String toString() {
        return "Data{" +
                "modhash='" + modhash + '\'' +
                ", children=" + getList() +
                ", after='" + after + '\'' +
                ", before=" + before +"\n"+
                '}';
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

    public String getList() {

        StringBuffer s = new StringBuffer();

        for (Child child : children){

            s.append(child.toString() +"\n");
        }
        return s.toString();

    }
}
