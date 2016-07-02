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
import com.example.dima.worderfacts.Adapters.CategoriesAdapter;
import com.example.dima.worderfacts.Database.DataBaseAdapter;
import com.example.dima.worderfacts.Database.CategoryItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dima on 05.06.16.
 */
public class CategoryActivity extends AppCompatActivity {

    ArrayList<CategoryItem> items;
    String tag = "MyLogs";

    @BindView(R.id.category_activity_listView_categories)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        ButterKnife.bind(this);


        ActionBar actionBar = getSupportActionBar();
        String titleText = "Категории";
        actionBar.setTitle(titleText);

        items = DataBaseAdapter.getAdapter(getApplicationContext()).getAllCategories();
        loadTable();
    }

    void loadTable() {

        CategoriesAdapter adapter = new CategoriesAdapter(this, items);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.d(tag, "ID=" + items.get(position).getCategoryName());
                showFactPage(items.get(position).getCategoryId(), items.get(position).getCategoryName());
            }
        });

    }

    void showFactPage(int categoryId, String categoryName) {
        Intent intent = new Intent(this, FactsActivity.class);
        intent.putExtra("CategoryId", "" + categoryId);
        intent.putExtra("CategoryName", categoryName);
        startActivity(intent);
    }
}
