package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tis on 20/01/2016.
 */
public class VideoId implements Parcelable{
    public String kind;
    public String videoId;

    protected VideoId(Parcel in) {
        kind = in.readString();
        videoId = in.readString();
    }

    public static final Creator<VideoId> CREATOR = new Creator<VideoId>() {
        @Override
        public VideoId createFromParcel(Parcel in) {
            return new VideoId(in);
        }

        @Override
        public VideoId[] newArray(int size) {
            return new VideoId[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(videoId);
    }
}
