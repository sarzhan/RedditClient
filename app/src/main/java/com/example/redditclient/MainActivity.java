package com.example.redditclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;

import com.example.redditclient.Model.Child;
import com.example.redditclient.Model.Feed;
import com.example.redditclient.network.FeedApi;
import com.example.redditclient.network.FeedResponse;
import com.example.redditclient.network.RestClient;
import com.example.redditclient.presentation.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Child> feedList = new ArrayList<>();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new FeedAdapter(this,feedList));

        getFeeds();

    }

    public void getFeeds() {

        FeedApi feedApi =  RestClient.getInstance().createService(FeedApi.class);
        disposable = feedApi.getFeeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(feedResponse -> {
                    feedList = feedResponse.data.getChildren();
                    recyclerView.setAdapter(new FeedAdapter(getApplicationContext(),feedList));
                });

    }

    @Override
    protected void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

}
