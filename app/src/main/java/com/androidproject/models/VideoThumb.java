package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoThumb implements Parcelable{
    public ImageDefault defaultImage;
    public ImageMedium medium;
    public ImageHigh high;

    protected VideoThumb(Parcel in) {
        defaultImage = in.readParcelable(ImageDefault.class.getClassLoader());
        medium = in.readParcelable(ImageMedium.class.getClassLoader());
        high = in.readParcelable(ImageHigh.class.getClassLoader());
    }

    public static final Creator<VideoThumb> CREATOR = new Creator<VideoThumb>() {
        @Override
        public VideoThumb createFromParcel(Parcel in) {
            return new VideoThumb(in);
        }

        @Override
        public VideoThumb[] newArray(int size) {
            return new VideoThumb[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(defaultImage, flags);
        dest.writeParcelable(medium, flags);
        dest.writeParcelable(high, flags);
    }
}