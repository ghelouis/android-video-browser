package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoSnippet implements Parcelable{
    public String publishedAt;
    public String channelId;
    public String title;
    public String description;
    public String channelTitle;
    public String liveBroadcastContent;
    public VideoThumb thumbnails;

    protected VideoSnippet(Parcel in) {
        publishedAt = in.readString();
        channelId = in.readString();
        title = in.readString();
        description = in.readString();
        channelTitle = in.readString();
        liveBroadcastContent = in.readString();
        thumbnails = in.readParcelable(VideoThumb.class.getClassLoader());
    }

    public static final Creator<VideoSnippet> CREATOR = new Creator<VideoSnippet>() {
        @Override
        public VideoSnippet createFromParcel(Parcel in) {
            return new VideoSnippet(in);
        }

        @Override
        public VideoSnippet[] newArray(int size) {
            return new VideoSnippet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(publishedAt);
        dest.writeString(channelId);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(channelTitle);
        dest.writeString(liveBroadcastContent);
        dest.writeParcelable(thumbnails, flags);
    }
}
