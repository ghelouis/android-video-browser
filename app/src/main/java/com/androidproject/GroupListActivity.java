package com.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Membres du groupe");
        setSupportActionBar(toolbar);

        ListView list = (ListView) findViewById(R.id.listView);
        String[] groupMembers = {"Jonathan Gabrielli 'gabrie_j'", "Arnaud Gagne 'gagne_a'",
                "Guillaume Hélouis 'heloui_g'"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, groupMembers);
        list.setAdapter(adapter);
    }

}
