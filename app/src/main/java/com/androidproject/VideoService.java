package com.androidproject;

import com.androidproject.models.VideoJson;

import retrofit.Callback;
import retrofit.http.GET;

public interface VideoService {

    @GET("/search.json")
    public void getJson(Callback<VideoJson> callback);
}
