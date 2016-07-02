package com.example.dima.worderfacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dima.worderfacts.Adapters.AllFactsAdapter;
import com.example.dima.worderfacts.Database.DataBaseAdapter;
import com.example.dima.worderfacts.Database.FactItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dima on 19.06.16.
 */
public class FavoritesActivity extends AppCompatActivity {
    ArrayList<FactItem> items;
    String tag="MyLogs";

    @BindView(R.id.favorites_activity_listView_favoritesFacts)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        String titleText="Избранное";
        actionBar.setTitle(titleText);

        items= DataBaseAdapter.getAdapter(getApplicationContext()).getFavoritesFacts();
        loadTable();
    }

    void loadTable()
    {

        AllFactsAdapter adapter=new AllFactsAdapter(this, items);
        mListView.setAdapter(adapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.d(tag, "ID=" + items.get(position).getFactBody());
                showFactPage(items.get(position).getFactId());
            }
        });

    }

    void showFactPage(int factId)
    {
        Intent intent=new Intent(this,FactActivity.class);
        intent.putExtra("FactId", "" + factId);
        startActivity(intent);
    }
}
