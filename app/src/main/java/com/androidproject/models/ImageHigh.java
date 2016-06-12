package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageHigh implements Parcelable {
    public String url;

    protected ImageHigh(Parcel in) {
        url = in.readString();
    }

    public static final Creator<ImageHigh> CREATOR = new Creator<ImageHigh>() {
        @Override
        public ImageHigh createFromParcel(Parcel in) {
            return new ImageHigh(in);
        }

        @Override
        public ImageHigh[] newArray(int size) {
            return new ImageHigh[size];
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
