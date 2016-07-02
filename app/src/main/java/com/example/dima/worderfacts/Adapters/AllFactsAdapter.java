package com.example.dima.worderfacts.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dima.worderfacts.Database.FactItem;
import com.example.dima.worderfacts.R;

import java.util.ArrayList;

/**
 * Created by dima on 04.06.16.
 */
public class AllFactsAdapter extends BaseAdapter {



    Context mContext;
    LayoutInflater lInflater;
    ArrayList<FactItem> objects;
    String tag="MyLogs";

    public AllFactsAdapter(Context context, ArrayList<FactItem> facts) {
        mContext = context;
        objects = facts;

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
            vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fact_item, parent, false));
            vh.root.setTag(vh);

        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }

        FactItem factItem = getFact(position);

        vh.factBodyTextView.setText(factItem.getFactBody());
        vh.userTextView.setText(factItem.getLastName() + " " + factItem.getFirstName());
        vh.factRatingTextView.setText(""+factItem.getFactRating());



        return vh.root;
    }

    FactItem getFact(int position) {
        return ((FactItem) getItem(position));
    }


    private   static  class ViewHolder
    {
        TextView factBodyTextView;
        TextView userTextView;
        TextView   factRatingTextView;
        View root;

        public ViewHolder(View rootView)
        {
            root=rootView;
            factBodyTextView = (TextView) rootView.findViewById(R.id.fact_item_textView_fact);
            userTextView = (TextView) rootView.findViewById(R.id.fact_item_textView_userName);
            factRatingTextView = (TextView) rootView.findViewById(R.id.fact_item_textView_starCount);
        }

    }

}
