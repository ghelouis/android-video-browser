package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageMedium implements Parcelable {
    public String url;

    protected ImageMedium(Parcel in) {
        url = in.readString();
    }

    public static final Creator<ImageMedium> CREATOR = new Creator<ImageMedium>() {
        @Override
        public ImageMedium createFromParcel(Parcel in) {
            return new ImageMedium(in);
        }

        @Override
        public ImageMedium[] newArray(int size) {
            return new ImageMedium[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
