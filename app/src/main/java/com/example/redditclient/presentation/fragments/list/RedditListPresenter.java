package com.example.redditclient.presentation.fragments.list;

import com.example.redditclient.interactor.FeedInteractor;
import com.example.redditclient.model.Child;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RedditListPresenter {
    private RedditListView view;
    private final FeedInteractor interactor;

    String token = "";

    private List<Child> children = new ArrayList<>();

    private Disposable disposable;

    public RedditListPresenter() {
        interactor = new FeedInteractor();
    }

    void loadMore() {
        disposable = interactor.getPosts("20", token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        feedResponse -> {
                            token = feedResponse.data.after;
                            List<Child> feeds = feedResponse.data.getChildren();
                            children.addAll(feeds);
                            view.onLoaded(feeds, !(token == null || token.isEmpty()));
                        },
                        throwable -> view.onError("An Error Occurred"), () -> {
                        }

                );
    }

    void getPosts() {
        if (!children.isEmpty()) {
            view.onLoaded(children, !(token == null || token.isEmpty()));
            return;
        }
        disposable = interactor.getPosts("20", "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        feedResponse -> {
                            token = feedResponse.data.after;
                            List<Child> feeds = feedResponse.data.getChildren();
                            children.addAll(feeds);
                            view.onLoaded(feeds, !(token == null || token.isEmpty()));
                        },
                        throwable -> view.onError("An Error Occurred"), () -> {
                        }

                );

    }

    void attachView(RedditListView view) {
        this.view = view;
    }

    void detachView() {
        if (disposable != null) disposable.dispose();
        view = null;
    }

}
