package com.androidproject;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidproject.models.VideoItem;

import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class VideoPageAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<VideoItem> videos;
    ImageView imageView;

    public VideoPageAdapter(Context context, List<VideoItem> videos) {
        this.videos = videos;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.fragment_detail, container, false);

        imageView = (ImageView) itemView.findViewById(R.id.video_image);
        TextView title = (TextView) itemView.findViewById(R.id.video_title);
        TextView desc = (TextView) itemView.findViewById(R.id.video_desc);
        TextView channelTitle = (TextView) itemView.findViewById(R.id.video_channel_title);
        TextView dateText = (TextView) itemView.findViewById(R.id.video_date);

        final VideoItem parcelableVideo = videos.get(position);

        if (parcelableVideo.snippet.thumbnails.high.url != null)
            new ImageLoader().execute(parcelableVideo.snippet.thumbnails.high.url);
        title.setText(parcelableVideo.snippet.title);
        if (parcelableVideo.snippet.description != null)
            desc.setText(parcelableVideo.snippet.description);
        if (parcelableVideo.snippet.channelTitle != null && !Objects.equals(parcelableVideo.snippet.channelTitle, ""))
            channelTitle.setText(parcelableVideo.snippet.channelTitle);
        if (parcelableVideo.snippet.publishedAt != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
            try {
                Date date = format.parse(parcelableVideo.snippet.publishedAt);
                DateFormat resultDate = DateFormat.getDateInstance();
                dateText.setText(resultDate.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + parcelableVideo.id.videoId)));
                }
                catch (ActivityNotFoundException e)
                {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + parcelableVideo.id.videoId)));
                }
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private class ImageLoader extends AsyncTask<String, String, Bitmap> {

        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null){
                imageView.setImageBitmap(image);
                notifyDataSetChanged();
            }
            else{
                //Toast.makeText(ImageLoader.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
