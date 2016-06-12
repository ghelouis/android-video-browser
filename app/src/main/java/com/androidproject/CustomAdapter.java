package com.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.models.VideoItem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

// Custom adapter for list of videos
public class CustomAdapter extends ArrayAdapter<String> {
    private List<VideoItem> videos;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<VideoItem> videos) {
        super(context, resource);
        this.videos = videos;
        this.inflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (videos != null)
            return videos.size();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.video_list_row, null);

        String title = videos.get(position).snippet.title;
        ((TextView) convertView.findViewById(R.id.video_title)).setText(title);

        String url = videos.get(position).snippet.thumbnails.medium.url;
        ImageView imageView = (ImageView) convertView.findViewById(R.id.video_thumbnail);
        new ImageDownloaderTask(imageView).execute(url);

        return convertView;
    }

    private class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }
}
