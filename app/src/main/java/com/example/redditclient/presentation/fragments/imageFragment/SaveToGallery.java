package com.example.redditclient.presentation.fragments.imageFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class SaveToGallery extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Bitmap image;

    public SaveToGallery(Context context, Bitmap image) {
        this.context = context;
        this.image = image;
    }

    @Override
    protected Void doInBackground(Void... params) {

        doSaving();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Toast.makeText(context, "IMAGE SAVED", Toast.LENGTH_LONG).show();

    }

    void doSaving() {
        String savedImagePath = null;

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);

        String imageFileName = "Picture" + n + ".jpg";
        File storageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                + "/Reddit");

        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            galleryAddPic(savedImagePath);
        }
    }

    private void galleryAddPic(String imagePath) {
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
