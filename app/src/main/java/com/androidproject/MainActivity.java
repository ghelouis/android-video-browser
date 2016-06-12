package com.androidproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidproject.models.VideoItem;
import com.androidproject.models.VideoJson;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private ListView videoListView;
    private RestClient restClient;
    private ArrayList<VideoItem> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restClient = new RestClient();
        videos = new ArrayList<VideoItem>();

        restClient.getService().getJson(new Callback<VideoJson>() {
            @Override
            public void success(VideoJson videoJson, Response response) {
                for (int i = 0; i < videoJson.items.size(); i++) {
                    videos.add(videoJson.items.get(i));
                }

                initListView(videos);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        handleIntent(getIntent());
    }

    private void initListView(final ArrayList<VideoItem> list) {
        videoListView = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this,
                R.layout.video_list_row, list);
        videoListView.setAdapter(adapter);
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                int pos = videos.indexOf(list.get(position));
                intent.putExtra("pos", pos);
                intent.putParcelableArrayListExtra("videos", videos);
                startActivity(intent);
            }
        });
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        restoreActionBar();

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                searchVideo(query);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (item.getItemId() == R.id.group) {
            Intent intent = new Intent(MainActivity.this, GroupListActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            searchVideo(query);
        }
    }

    private void searchVideo(String query) {
        String q = query.toLowerCase();

        ArrayList<VideoItem> filteredVideos = new ArrayList<>();
        for (int i = 0; i < videos.size(); i++)
            if (videos.get(i).snippet.title.toLowerCase().contains(q))
                filteredVideos.add(videos.get(i));

        initListView(filteredVideos);
    }

}
