package com.example.dima.worderfacts.Adapters;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dima.worderfacts.Database.UserItem;
import com.example.dima.worderfacts.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dima on 11.06.16.
 */
public class DeskAdapter extends BaseAdapter {
    Context mContext;

    ArrayList<UserItem> objects;
    String tag = "MyLogs";

    public DeskAdapter(Context context, ArrayList<UserItem> peoples) {
        mContext = context;
        objects = peoples;

        //  lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.desk_item, parent, false));
            vh.root.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        UserItem userItem = getUser(position);


        if (userItem.getStarCount() >= 50) {
            vh.userStar1.setImageResource(R.drawable.star);
            vh.userStar2.setImageResource(R.drawable.star);
            vh.userStar3.setImageResource(R.drawable.star);
            vh.userStar4.setImageResource(R.drawable.star);
            vh.userStar5.setImageResource(R.drawable.star);
        } else if (userItem.getStarCount() >= 40) {
            vh.userStar1.setImageResource(R.drawable.star);
            vh.userStar2.setImageResource(R.drawable.star);
            vh.userStar3.setImageResource(R.drawable.star);
            vh.userStar4.setImageResource(R.drawable.star);
            vh.userStar5.setImageResource(R.drawable.star_off);
        } else if (userItem.getStarCount() >= 30) {
            vh.userStar1.setImageResource(R.drawable.star);
            vh.userStar2.setImageResource(R.drawable.star);
            vh.userStar3.setImageResource(R.drawable.star);
            vh.userStar4.setImageResource(R.drawable.star_off);
            vh.userStar5.setImageResource(R.drawable.star_off);
        } else if (userItem.getStarCount() >= 20) {
            vh.userStar1.setImageResource(R.drawable.star);
            vh.userStar2.setImageResource(R.drawable.star);
            vh.userStar3.setImageResource(R.drawable.star_off);
            vh.userStar4.setImageResource(R.drawable.star_off);
            vh.userStar5.setImageResource(R.drawable.star_off);

        } else if (userItem.getStarCount() >= 10) {
            vh.userStar1.setImageResource(R.drawable.star);
            vh.userStar2.setImageResource(R.drawable.star_off);
            vh.userStar3.setImageResource(R.drawable.star_off);
            vh.userStar4.setImageResource(R.drawable.star_off);
            vh.userStar5.setImageResource(R.drawable.star_off);
        } else {
            vh.userStar1.setImageResource(R.drawable.star_off);
            vh.userStar2.setImageResource(R.drawable.star_off);
            vh.userStar3.setImageResource(R.drawable.star_off);
            vh.userStar4.setImageResource(R.drawable.star_off);
            vh.userStar5.setImageResource(R.drawable.star_off);

        }

        Picasso.with(mContext).load(userItem.getAvatarUrl()).resize(100, 100).onlyScaleDown().error(R.drawable.avatar1).into(vh.userImage);

        vh.userNameTextView.setText(userItem.getLastName() + " " + userItem.getFirstName());

        String titul = userItem.getTitul().replace("\\n", "");

        if (titul.trim().length() != 0) {
            vh.userTitulTextView.setText(titul);
            vh.userTitulTextView.setVisibility(View.VISIBLE);
        } else
            vh.userTitulTextView.setVisibility(View.GONE);

        vh.userFactCountTextView.setText("Добавлено фактов:" + userItem.getStarCount());
        vh.userRatingTextView.setText("Сумма рейтингов:" + userItem.getTotalRating());




        return vh.root;
    }

    UserItem getUser(int position) {
        return ((UserItem) getItem(position));
    }

    private static class ViewHolder {
        TextView userNameTextView;
        TextView userTitulTextView;
        TextView userFactCountTextView;
        TextView userRatingTextView;

        ImageView userImage;
        ImageView userStar1;
        ImageView userStar2;
        ImageView userStar3;
        ImageView userStar4;
        ImageView userStar5;

        View root;

        public ViewHolder(View rootView) {
            root = rootView;
            userNameTextView = (TextView) rootView.findViewById(R.id.deskItem_TextView_Name);
            userTitulTextView = (TextView) rootView.findViewById(R.id.deskItem_TextView_Titul);
            userFactCountTextView = (TextView) rootView.findViewById(R.id.deskItem_TextView_Count);
            userRatingTextView = (TextView) rootView.findViewById(R.id.deskItem_TextView_Rating);

            userImage = (ImageView) rootView.findViewById(R.id.deskItem_image_userImage);
            userStar1 = (ImageView) rootView.findViewById(R.id.deskItem_imageView_Star1);
            userStar2 = (ImageView) rootView.findViewById(R.id.deskItem_imageView_Star2);
            userStar3 = (ImageView) rootView.findViewById(R.id.deskItem_imageView_Star3);
            userStar4 = (ImageView) rootView.findViewById(R.id.deskItem_imageView_Star4);
            userStar5 = (ImageView) rootView.findViewById(R.id.deskItem_imageView_Star5);


        }

    }
}
