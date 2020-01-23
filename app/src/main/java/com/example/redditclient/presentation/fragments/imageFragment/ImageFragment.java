package com.example.redditclient.presentation.fragments.imageFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.redditclient.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    ImageView imageView;
    Button button;

    private static final String TYPE_KEY = "type";
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9078;
    SaveToGallery saveGalleryTask;


    public ImageFragment() {
        super(R.layout.fragment_image);
    }

    public static ImageFragment createItemsFragment(String type) {
        ImageFragment fragment = new ImageFragment();

        Bundle bundle = new Bundle();
        bundle.putString(TYPE_KEY, type);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String image = getArguments().getString(TYPE_KEY);

        imageView = view.findViewById(R.id.imageView);
        Glide.with(imageView)
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        button = view.findViewById(R.id.saveToGallery);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToGallery();
            }
        });
    }

    public void saveImageToGallery() {
        Glide.with(this)
                .asBitmap()
                .load(getArguments().getString(TYPE_KEY))
                .listener(new RequestListener<Bitmap>() {
                              @Override
                              public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Bitmap> target, boolean b) {

                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Bitmap bitmap, Object o, Target<Bitmap> target, DataSource dataSource, boolean b) {
                                  saveImage(bitmap);
                                  return false;
                              }
                          }
                ).submit();


    }

    private void saveImage(Bitmap image) {
        //String savedImagePath = null;

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {

            saveGalleryTask = new SaveToGallery(getActivity(), image);
            saveGalleryTask.execute();
        }

    }

}
