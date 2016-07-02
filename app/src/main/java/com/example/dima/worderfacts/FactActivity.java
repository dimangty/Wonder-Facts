package com.example.dima.worderfacts;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.worderfacts.Database.DataBaseAdapter;
import com.example.dima.worderfacts.Database.FactItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FactActivity extends AppCompatActivity {


    String tag = "MyLogs";

    private FactItem mFactItem;
    private Boolean mFavoriteState;

    @BindView(R.id.fact_activity_button_favorite)
    ImageButton mFavoriteButton;

    @BindView(R.id.fact_activity_button_share)
    ImageButton mShareButton;

    @BindView(R.id.fact_activity_textView_fact)
    TextView mFactBodyText;

    @BindView(R.id.fact_activity_textView_factDate)
    TextView mFactDateText;

    @BindView(R.id.fact_activity_textView_factCategory)
    TextView mFactCategoryText;

    @BindView(R.id.fact_activity_textView_wonderCount)
    TextView mFactWonderText;

    @BindView(R.id.fact_activity_textView_userRating)
    TextView mUserRatingText;

    @BindView(R.id.fact_activity_textView_userFactsCount)
    TextView mUserFactCountText;

    @BindView(R.id.fact_activity_textView_userTitul)
    TextView mUserTitulText;

    @BindView(R.id.fact_activity_textView_userName)
    TextView mUserNameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fact_activity);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        String titleText = "Удивительный факт";
        actionBar.setTitle(titleText);


        Intent intent = getIntent();
        int factId = Integer.parseInt(intent.getStringExtra("FactId"));
        mFactItem = DataBaseAdapter.getAdapter(getApplicationContext()).getFact(factId);
        String category = DataBaseAdapter.getAdapter(getApplicationContext()).getCategoryForFact(factId);

        mFactBodyText.setText(mFactItem.getFactBody());
        mFactDateText.setText(mFactItem.getFactDateStr() + "                 ");
        mFactCategoryText.setText("Категория:" + category);
        mFactWonderText.setText("" + mFactItem.getFactRating());


        mUserNameText.setText(mFactItem.getLastName() + " " + mFactItem.getFirstName());


        String titul = mFactItem.getCurrentTitul().replace("\\n", "");

        if (titul.trim().length() != 0) {
            mUserTitulText.setText("Титул:" + titul);
            mUserTitulText.setVisibility(View.VISIBLE);
        } else
            mUserTitulText.setVisibility(View.GONE);

        mUserFactCountText.setText("Добавлено фактов:" + mFactItem.getFactCount());
        mUserRatingText.setText("Сумма рейтингов:" + mFactItem.getCurrentTotalRating());



        mFavoriteState = DataBaseAdapter.getAdapter(getApplicationContext()).testFavorite(factId);
        if (mFavoriteState)
            mFavoriteButton.setImageResource(R.drawable.favorites_on);
        else
            mFavoriteButton.setImageResource(R.drawable.favorites_off);

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavoriteState = !mFavoriteState;
                if (mFavoriteState) {
                    DataBaseAdapter.getAdapter(getApplicationContext()).addToFavorite(mFactItem.getFactId());
                    mFavoriteButton.setImageResource(R.drawable.favorites_on);
                } else {
                    DataBaseAdapter.getAdapter(getApplicationContext()).removeFromFavorite(mFactItem.getFactId());
                    mFavoriteButton.setImageResource(R.drawable.favorites_off);
                }
            }
        });




        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFact();
            }
        });


        ImageView avatarImage = (ImageView) findViewById(R.id.fact_activity_imageView_avatar);

        Picasso.with(getApplicationContext()).load(mFactItem.getCurrentAvatar()).resize(100, 100).onlyScaleDown().error(R.drawable.avatar1).into(avatarImage);

    }

    void shareFact() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                mFactItem.getFactBody());
        startActivity(Intent.createChooser(shareIntent, "Share fact"));


    }


}
