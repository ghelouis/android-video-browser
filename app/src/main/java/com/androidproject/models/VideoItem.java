package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoItem implements Parcelable {
    public String kind;
    public String etag;
    public VideoId id;
    public VideoSnippet snippet;

    protected VideoItem(Parcel in) {
        kind = in.readString();
        etag = in.readString();
        id = in.readParcelable(VideoId.class.getClassLoader());
        snippet = in.readParcelable(VideoSnippet.class.getClassLoader());
    }

    public static final Creator<VideoItem> CREATOR = new Creator<VideoItem>() {
        @Override
        public VideoItem createFromParcel(Parcel in) {
            return new VideoItem(in);
        }

        @Override
        public VideoItem[] newArray(int size) {
            return new VideoItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(etag);
        dest.writeParcelable(id, flags);
        dest.writeParcelable(snippet, flags);
    }
}
