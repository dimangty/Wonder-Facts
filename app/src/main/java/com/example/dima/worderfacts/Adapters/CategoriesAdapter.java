package com.example.dima.worderfacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dima.worderfacts.Database.CategoryItem;
import com.example.dima.worderfacts.R;

import java.util.ArrayList;

/**
 * Created by dima on 05.06.16.
 */
public class CategoriesAdapter  extends BaseAdapter {

    Context mContext;
    LayoutInflater lInflater;
    ArrayList<CategoryItem> objects;
    String tag="MyLogs";

    public CategoriesAdapter(Context context, ArrayList<CategoryItem> categories) {
        mContext = context;
        objects = categories;

        lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if(convertView == null)
        {
            vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false));
            vh.root.setTag(vh);

        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }

        CategoryItem categoryItem = getCategory(position);

        vh.categoryNameTextView.setText(categoryItem.getCategoryName());
        vh.categoryCountTextView.setText(""+categoryItem.getFactsCount()+" фактов");

        return vh.root;
    }

    CategoryItem getCategory(int position) {
        return ((CategoryItem) getItem(position));
    }

    private   static  class ViewHolder
    {
        TextView categoryNameTextView;
        TextView categoryCountTextView;
        View root;

        public ViewHolder(View rootView)
        {
            root=rootView;
            categoryNameTextView = (TextView) rootView.findViewById(R.id.category_item_textView_categoryName);
            categoryCountTextView = (TextView) rootView.findViewById(R.id.category_item_textView_categoryCount);

        }

    }
}
