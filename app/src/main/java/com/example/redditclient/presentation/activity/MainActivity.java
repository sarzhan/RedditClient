package com.example.redditclient.presentation.activity;

import android.os.Bundle;

import com.example.redditclient.R;
import com.example.redditclient.presentation.fragments.list.RedditListFragment;
import com.example.redditclient.presentation.fragments.imageFragment.ImageFragment;

import net.danlew.android.joda.JodaTimeAndroid;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateToList();
        JodaTimeAndroid.init(this);

    }

    private void navigateToList() {
        if (getSupportFragmentManager().findFragmentByTag("REDDIT") != null) return;
        RedditListFragment redditListFragment = new RedditListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, redditListFragment, "REDDIT")
                .commit();
    }

    public void navigateToImage(String url) {
        ImageFragment imageFragment = ImageFragment.createItemsFragment(url);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(imageFragment.toString())
                .add(R.id.fragment, imageFragment)
                .commit();
    }

}

