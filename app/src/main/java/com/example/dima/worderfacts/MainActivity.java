package com.example.dima.worderfacts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_button_allFacts)
    ImageButton mAllFactsButton;

    @BindView(R.id.main_activity_button_categories)
    ImageButton mCategoriesButton;

    @BindView(R.id.main_activity_button_desk)
    ImageButton mDeskButton;

    @BindView(R.id.main_activity_button_favorites)
    ImageButton mFavoritesButton;

    @BindView(R.id.main_activity_button_topFacts)
    ImageButton mTopFactsButton;

    String tag = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mAllFactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllFacts();
            }
        });

        mCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategories();
            }
        });

        mDeskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDesk();
            }
        });

        mFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFavorites();
            }
        });

        mTopFactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopFacts();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        String titleText = "Wonder Facts";
        actionBar.setTitle(titleText);
    }


    void showAllFacts() {
        Intent intent = new Intent(this, AllFactsActivity.class);
        startActivity(intent);
    }

    void showCategories() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

    }

    void showTopFacts() {
        Intent intent = new Intent(this, TopFactsActivity.class);
        startActivity(intent);

    }

    void showDesk() {
        Intent intent = new Intent(this, DeskActivity.class);
        startActivity(intent);

    }

    void showFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);

    }
}
