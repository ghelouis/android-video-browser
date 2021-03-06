package com.androidproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageDefault implements Parcelable {
    public String url;

    protected ImageDefault(Parcel in) {
        url = in.readString();
    }

    public static final Creator<ImageDefault> CREATOR = new Creator<ImageDefault>() {
        @Override
        public ImageDefault createFromParcel(Parcel in) {
            return new ImageDefault(in);
        }

        @Override
        public ImageDefault[] newArray(int size) {
            return new ImageDefault[size];
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
