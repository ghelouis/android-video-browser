package com.androidproject;

public class RestClient {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    private static final String URL = "http://tutos-android.com/MTI/2016/Projet";
    private retrofit.RestAdapter restAdapter;
    private VideoService service;

    public RestClient()
    {

        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();
        service = restAdapter.create(VideoService.class);
    }

    public VideoService getService() {
        return service;
    }
}