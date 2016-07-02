package com.example.dima.worderfacts;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.dima.worderfacts.Adapters.AllFactsAdapter;
import com.example.dima.worderfacts.Adapters.DeskAdapter;
import com.example.dima.worderfacts.Database.DataBaseAdapter;
import com.example.dima.worderfacts.Database.UserItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dima on 11.06.16.
 */
public class DeskActivity extends AppCompatActivity {
    ArrayList<UserItem> items;
    String tag = "MyLogs";

    @BindView(R.id.desk_activity_listView_deskList)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desk_activity);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        String titleText = "Доска почета";
        actionBar.setTitle(titleText);

        items = DataBaseAdapter.getAdapter(getApplicationContext()).getDesk();
        loadTable();
    }

    void loadTable() {

        DeskAdapter adapter = new DeskAdapter(this, items);
        mListView.setAdapter(adapter);


    }


}
