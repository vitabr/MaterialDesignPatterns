package com.example.vito.mdtemplate.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.example.vito.mdtemplate.R;
import com.example.vito.mdtemplate.model.SearchResult;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<SearchResult> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<SearchResult> data = new ArrayList<>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<SearchResult> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (NetworkImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        SearchResult item = data.get(position);
        holder.image.setImageUrl(item.unescapedUrl,VolleySingleton.getInstance().getImageLoader());
        return row;
    }

    static class ViewHolder {
        NetworkImageView image;
    }
}